import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int width, height;
        char c;

        while(true){
            width = sc.nextInt(); height = sc.nextInt(); c = sc.next().charAt(0);
            System.out.println(width * height);

            if(c == 'C'){
                break;
            }
        }
    }
}