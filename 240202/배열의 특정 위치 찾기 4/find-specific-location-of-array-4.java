import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = 10, sum = 0, num = 0;

        for(int i = 0; i < n; i++){
            int point = sc.nextInt();

            if(point == 0){
                break;
            }
            if(point % 2 == 0){
                sum += point;
                num++;
            }
        }
        System.out.print(num + " " + sum);



    }
}