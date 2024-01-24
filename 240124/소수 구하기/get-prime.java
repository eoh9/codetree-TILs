import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        

        for(int i = 2; i <= n; i++){
            int cnt = 0;
            for(int j = 1; j <= n; j++){
                if(i % j == 0){
                    cnt++;
                }
            }
            if(cnt == 2){
                System.out.print(i + " ");
            }
        }
    }
}