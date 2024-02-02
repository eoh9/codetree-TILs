import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int cnt = 0;

        for(int i = 0 ; i < n; i++){
            int sum = 0;
            for(int j = 0; j < 4; j++){
                int num = sc.nextInt();
                sum += num;
            }
            if(sum/4 < 60){
                System.out.println("fail");
                
            } else {
                System.out.println("pass");
                cnt++;
            }      
        }
        System.out.print(cnt);
    }
}