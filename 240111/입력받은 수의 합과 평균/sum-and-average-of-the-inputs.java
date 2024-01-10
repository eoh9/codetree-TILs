import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       int n = sc.nextInt();
       int sum = 0, num;

       for(int i = 0; i < n; i++){
        num = sc.nextInt();
        sum += num;
       }System.out.printf("%d %.1f", sum, (double)sum/n);
    }
}