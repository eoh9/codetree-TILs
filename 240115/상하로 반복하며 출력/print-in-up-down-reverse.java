import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int originalN = n; // 원래의 n 값을 보존하기 위한 변수

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j % 2 == 1) {
                    System.out.print(i); // 홀수 번째에는 i 출력
                } else {
                    System.out.print(originalN); // 짝수 번째에는 originalN 출력
                }
            }
            originalN -= 1;
            System.out.println();
        }
    }
}