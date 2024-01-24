import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        

        for(int i = 0; i < n; i++){
            int cnt = 0;
            int m = sc.nextInt();

            for(int j = 0; j < 1000; j++){
                if(m == 1){
                    System.out.println(cnt);
                    break;
                }
                if(m % 2 == 0){
                    m /= 2;
                } else {
                    m = 3 * m + 1;
                }
                cnt++;
            }

        }


    }
}