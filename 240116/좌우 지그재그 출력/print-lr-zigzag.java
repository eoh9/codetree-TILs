import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int cnt = 1;

        for(int i = 0; i < n; i++){
            if(i % 2 != 0){
                cnt += n;
            }
            for(int j = 1; j <= n; j++){
                if(i % 2 == 0){
                    System.out.print(cnt + " ");
                    cnt++;
                } else {
                    System.out.print(cnt - j + " "); 
                }
            }
            System.out.println();
        }
    }
}