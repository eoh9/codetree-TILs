import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = 10, i = 0;
        int[] a = new int[n];
        int sum1 = 0, sum2 = 0, cnt = 0;

        for(i = 0; i < n; i++){
            a[i] = sc.nextInt();
            if(i == 1 || i % 2 != 0){
                sum1 += a[i];
            }
            if(a[i] % 3 == 0){
                cnt++;
                sum2 += a[i];
            }
        }
        System.out.printf("%d %.1f", sum1, (double)sum2/cnt);


    }
}