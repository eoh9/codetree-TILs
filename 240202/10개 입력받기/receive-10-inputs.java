import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = 10, i = 0, sum = 0;

        for(i = 0; i < n; i++){
            int num = sc.nextInt();
            if(num == 0){
                break;
            }
            sum += num;
        }
        System.out.printf("%d %.1f", sum, (double)sum/i);
    }
}