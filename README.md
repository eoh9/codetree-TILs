# GECE GESTT and test app

## GECE: GE Command Engine (pronounced "Geese")
### Design Principles

This application makes use of Stanford's NLP software and AI models to achieve Natural Language Understanding (NLU) for use in appliance voice commands. 
The design has undergone a few iterations to be as simple as possible, while attempting to keep in mind memory constraints of our android products.
The basic flow of data is as follows:

- User speaks a command after listening is triggered (wakeword not yet supported) --->> GESTT
- Voice data is sent to OpenAI's Whsiper API for transcription (local STT currently in development) --->> GESTT
- Transcription is fed to the MatchEngine, which attempts to find a match based on words and their aliases that fit the correct requirements (verb/subject/object) for a particular Pattern --->> GECE
- If a match is found, the Pattern's Task is run, which is fed the AI generated metadata and the raw transcription to do any further parsing it needs to complete its objective. --->> GECE
- Once the task has completed its parsing, it can now complete the objective of the task --->>> Test app, in the future feeding intents to whoever wants them

An example, with real code:

```java
IWord oven = new Word("oven", "stove", "range"); // a word is created with any "alias" to the word, i.e. any word that, in this context, means the same thing
IWord ovenSet = new Word("set", "heat", "preheat", "warm");

oven.addAlias("cooktop"); // aliases can be added on the fly, allowing a user to set their own names for words

Pattern bakePattern = new Pattern.Builder()
        .require(oven).as(SUBJECT, OBJECT) // we require the oven word as either a subject or object in our sentence
        .require(ovenSet).as(VERB) // we further require the ovenSet word as a verb in our sentence
        .task(new OvenSetTask()) // this will be the task that runs to do any further parsing, and completes setting the temperature on the oven
        .build();

// make any other patterns or words that are needed
        
MatchEngine matchEngine = new MatchEngine(bakePattern)

        
matchEngine.addPattern(someOtherPattern); // patterns can be either added at construction, or on the fly like this, which gives support for custom patterns to be made at runtime

// an example of our "OvenSetTask"

public class OvenSetTask implements Pattern.Task {
    // The following function is provided with a SemanticGraph (dependency tree) a map with all the subjects, verbs, and objects that are relevant to the match, and the raw
    // command input to do any further parsing needed
    @Override
    public Pattern.Result run(SemanticGraph depTree, Map<Pattern.WordType, List<IndexedWord>> wordMap, String rawInput) {
        double temp;
        try {
            temp = Pattern.findNumber( //Common tasks like finding numbers have been abstracted to make task development more simple
                    Pattern.verbChildrenDeep(depTree, wordMap, 0)// We can get the children from the dependency tree of particular types of words,
                                                                // here we want the children of the "Set" verb, which will contain the temperature number
            );
        } catch (NumberFormatException e) {
            return new Pattern.Result("Please input a valid temperature", false, true); // returning with "false, true" signifies a partial match, but not a full match, allowing the matching engine to
                                                                                        // query the user for more information, this feature is not complete yet

        }
        return new Pattern.Result("Setting oven to " + (int) temp + " degrees", true, false); // returning with true, false signifies a full match, and our task is complete
    }
}
```

The above is all the code needed to properly catch an "Oven Set" command. Although some commands are more complex and require deeper parsing, this exhibits the simplicity of developing new commands
for this system. Although everything has been built with simplicity and quick development in mind, this system is very robust and much smarter than a "dumb parser". For example, if we had a rule based parser simply
searching for the words "oven" and "set" in a sentence, then searching for a number to set the oven to, the following phrase would fail:

    I saw an oven on my TV set last night on channel 350.

This system correctly ignores this statement, as the words it is searching for do not appear in the correct context. The following statements would succeed:
    
    Please heat my oven to 350.
    Set my stove to 400 degrees.
    Hey can you preheat my oven to 400 please?
    Warm up the oven up to 325, would you?

And many others like them. The matching engine is still under development, and further improvements may be made as more phrases for more commands are tested. The models used from
Stanford's NLP library work well off the shelf for annotating speech with information about each word, so no training or model upkeep is required for continued development.


### Further development goals

- ~~Local STT with whisper, or equivalent model~~ Rigorous testing needed, but local STT has been added
- Adding all requested commands from the various programs looking to use this technology
- Contextual partial matching, making assumptions based on previous commands or context to do partial matches where we can requrest further information
- Backup dumb parser partial matching, for very poorly formed speech, the AI matcher *may* have occasional problems. Having a backup dummy parser may be useful in some cases to do partial matches.
- Wakeword detection.

### Building locally
For some reason Stanford does not include their "models" jar in their maven repo.

This model should be available through git LFS now, but if you don't get it, you can follow the following instructions.

For this reason the build expects to find the `stanford-corenlp-4.5.5-models.jar` file in a directory named `libs` under the GECE
directory. This models jar can be found in the archive downloadable from here: https://stanfordnlp.github.io/CoreNLP/ 

After placing this jar in said folder, the build should progress as normal, and be runnable from an emulator, phone, or on the SBC.


## GESTT: GE Speech To Text

### Brief Overview

GE Speech To Text is based around the Whisper models from OpenAI. Although the code and setup exists for local
STT, present hardware is not capable of running the models at a suitable speed (testing shows roughly 11-15 seconds
to parse a 4 second utterance). For this reason the main usecase of the library presently is a simple wrapper
around the OpenAI Whisper API (cloud based) that additionally does some android specific work to make using the STT
as easy as possible.

Example code:

```java 

STT.SpeechCallback localCallback = (transcript) -> {
    start_stanford = System.currentTimeMillis();
    //here we're just timing how long this takes, but we can run whatever code we want with the transcript
    Log.d("TIMING","STANFORD LATENCY: " + (System.currentTimeMillis() - start_stanford) + " ms");
    return true;
};

STT.SpeechCallback smarthqCallback = this::smarthqAssistantRequest; // a callback to send the transcript to the SmartHQ assistant

stt = new STT(getApplicationContext(), false, localCallback,smarthqCallback); 
//the boolean flag is for running locally or cloud STT
// we can pass as many or as few callbacks to consume the transcript, it will run them in order until one returns "true" meaning
// the transcript was successfully consumed, this way we can try to handle a command locally before falling back to the SmartHQ
// assistant

stt.listen(); // listen for speech

stt.finalizeRecording(); // stop listening, parse, and run callbacks
```

This library can be quite large because of the inclusion of whisper models. The current default is to include none,
since we're not capable of running them well enough for our purposes. This means if you build and use this library
with `local` set to `true` you'll run into errors unless you move the correct whisper model and language bin file
into the assets of this library.


## Appliance Intent Standard

### Brief Overview

The appliance intent standard is meant as a standard way to transmit an intent (action) and data related to that intent
throughout AOSP without having to convert and pack ERDs and data and then parse them on the other side. Essentially, we want
to wait until the last possible moment to convert to ERDs to ease development and integration within the system.

Each type of intent should represent a unique action to be performed. Note that some actions are related, while
still being unique, such as adding a new item to a list, or removing one from a list. Data types also receive
their own unique type per entry in a single intent, as this is how they are read from the intent on
the receiving side.

### Usage

The following enums define our various types of intents and data. The appliance type portion of this should match
our real, ERD-based appliance types for ease of use.

To toggle recording on or off, send the broadcast intent with the action `com.connected.geappliances.GECE_BACKEND_TOGGLE`

When the command engine finishes, it will send a broadcast intent with the action `com.connected.geappliances.GECE_INTENT`

The intent type will be packed in an extra with the key `APPLIANCE_INTENT_EXTRA`

The appliance type will be packed in an extra with the key `APPLIANCE_TYPE_EXTRA`

The intent will come with an array of bundles packed into the key `INTENT_DATA_EXTRA`

Each bundle will have the key `INTENT_DATA_TYPE` that describes the data stored

The data will be stored in the bundle with the key `INTENT_DATA`

The following enums show the values that are used to denote different types


```java
public enum ApplianceType {

        ANY((byte)0xFF),
        OVEN((byte)0x07),;
        private final byte type;
        ApplianceType(byte type)
        {
            this.type = type;
        }

        public byte type()
        {
            return type;
        }
    }

    public enum IntentType {

        RESERVED(0),
        LIST_ADD(0x0001),  //reserving 0x0000 for special meaning
        LIST_REMOVE(0x0002),
        LIST_QUERY(0x0003),
        LIST_CLEAR(0x0004),
        OVEN_BAKE(0x0005),
        DISPENSE(0x0006),
        TIMER(0x0007),
        TIMER_MODIFY(0x0008),
        TIMER_QUERY(0x0009),
        TIMER_STOP(0x000A),;
        private final int code;
        IntentType(int code)
        {
            this.code = code;
        }

        public int code()
        {
            return code;
        }
        public IntentType fromCode(int code)
        {
            return values()[code];
        }

    }

    public enum IntentDataType{

        RESERVED(0),
        TEMP(0x0001),
        OVEN_CAVITY(0x0002),
        OVEN_MODE(0x0003),
        LIST_ITEM(0x0004),
        FLUID_VOLUME(0x0005),
        TIME(0x0006),
        NAME(0x0007);
        private final int code;
        IntentDataType(int code)
        {
            this.code = code;
        }

        public int code()
        {
            return code;
        }

        public IntentDataType fromCode(int code)
        {
            return values()[code];
        }
    }
```

This class is meant to be packaged into a usable library, so that the integer codes will not need to be
memorized. Building an appliance intent is simple, and uses the following constructor:

```java
public ApplianceIntent(ApplianceType at, IntentType it, IntentData ...data)
```
IntentData is a simple bucket class that contains a type and string-serielized data. String serialization
was chosen for simplicity, though it loses some compactness, this shouldn't be much of a problem running
within android.

Once an ApplianceIntent is created, it can be packaged into a broadcast intent and sent out to the
rest of the system. This functionality exists within the backend application that hosts these
components.
