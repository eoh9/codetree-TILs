import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int b = 0, temp = 0, cnt = 1;

        for(int i = 1; i <= n; i++){
            
            b = n / i;
            temp = n;
            n = b;
            if(b <= 1){
                break;
            }
            cnt++;
            
        }
        System.out.print(cnt);
    }
}