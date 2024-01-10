import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = 10, sum = 0, cnt = 0, num;

        for(int i = 0; i < n; i++){
            num = sc.nextInt();
            if(0 <= num && num <= 200){
                sum += num;
                cnt++;
            }
            
        }
        System.out.printf("%d %.1f", sum, (double)sum/cnt);
    }
}