import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = 8;
        double sum = 0;
        for(int i = 0; i < n; i++){
            double num = sc.nextDouble();
            sum += num;
        }
        System.out.printf("%.1f", sum/n);
    }
}