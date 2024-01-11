import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n, cnt = 0;
        while(true){
            n = sc.nextInt();
            if(n % 2 == 0){
                System.out.println(n / 2);
                cnt++;
                if(cnt == 3){
                    break;
                }
            } else {
                continue;
            }
        }
    }
}