import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        double sum = 0;
        for(int i = 0; i < n; i++){
            double point = sc.nextDouble();
            sum += point;
        }
        double mean = sum/n;

        if(mean >= 4.0){
            System.out.printf("%.1f\nPerfect", mean);
        } else if(mean >= 3.0){
            System.out.printf("%.1f\nGood", mean);
        } else {
            System.out.printf("%.1f\nPoor", mean);
        }

        
    }
}