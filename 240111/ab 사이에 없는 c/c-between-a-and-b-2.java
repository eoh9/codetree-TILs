import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a, b, c;
        boolean sat = true;
        a = sc.nextInt(); b = sc.nextInt(); c = sc.nextInt();

        for(int i = a; i <= b; i++){
            if(i % c == 0){
                sat = false;
            }
        }
        if(sat == true){
            System.out.print("YES");
        } else {
            System.out.print("NO");
        }
    }
}