import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for(int i = 0; i < 2 * n; i++){
            if(i % 2 != 0){
                for(int j = 0; j < 1 + (i / 2); j++){
                    System.out.print("* ");
                }
            } else {
                for(int j = 0; j < n - (i / 2); j++){
                    System.out.print("* ");
                }
            }

            System.out.println();
        }
    }
}