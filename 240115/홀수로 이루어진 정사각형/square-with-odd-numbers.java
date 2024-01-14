import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        // 시작할 홀수 값 (11부터 시작)
        int startOdd = 11;

        // n * n 정사각형 출력
        for (int i = 0; i < n; i++) {
            int odd = startOdd + 2 * i; // 행마다 시작 홀수 값 증가
            for (int j = 0; j < n; j++) {
                System.out.print(odd + " ");
                odd += 2; // 다음 홀수 값
            }
            System.out.println();
        }

        scanner.close();
    }
}