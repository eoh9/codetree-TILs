import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(), cnt = 0;

        while(true){
            
            if(n >= 1000){
                System.out.print(cnt);
                break;
            }
            cnt++;
            if(n % 2 == 0){
                n = 3 * n +1;
            }else {
                n = 2 * n + 2;
            }
        }


    }
}