import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int x = 'A';

        for(int i = n; i > 0; i--){
            for(int j = 0; j <= n-i; j++){
                System.out.print((char)x);
                x++;
            }
            System.out.println();
        }
    }
}