import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        boolean sat = false;

        if(n > 1 && n != 2 && n % 2 == 0){
            sat = true;
        }
        if(sat == true){
            System.out.print("C");
        } else {
            System.out.print("N");
        }
    }
}