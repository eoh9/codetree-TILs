import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int x = 'A';

        for(int i = 0; i < n; i++){
            
            for(int j = 0; j < i; j++){
                System.out.print("  ");
            }
            for(int k = 0; k < n-i; k++){
                System.out.print((char)x + " ");
                x++;
                if(x > 'Z'){
                    x = 'A';
                }
            }
            
            System.out.println();
        }
    }
}