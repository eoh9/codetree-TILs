import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] a = new int[10];
        int sum = 0;
        int cnt = 0;
  

        for(int i = 0; i < 10; i++){
            a[i] = sc.nextInt();
            if(a[i] >= 250){
                break;
            }
            sum += a[i];
            cnt++;
        }
        System.out.printf("%d %.1f", sum, (double)sum/cnt);

    }
}