import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int age, sum = 0, cnt = 0;

        while(true){
            age = sc.nextInt();
            
            if(age < 20 || 29 < age){
                System.out.printf("%.2f", (double)sum/cnt);
                break;
            }
            sum += age;
            cnt++;
        }
    }
}