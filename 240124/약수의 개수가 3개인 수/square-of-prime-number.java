import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int final_cnt = 0;

        for(int i = n; i <= m; i++){
            int cnt = 0;
            for(int j = 1; j <= i; j++){
                if(i % j == 0){
                    cnt++;
                }
            }
            if(cnt == 3){
                final_cnt++;
            }
        }
        System.out.print(final_cnt);
    }
}