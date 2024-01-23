import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();

        for(int i = 0; i < a; i++){
            for(int j = 1; j <= i + 1; j++){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}