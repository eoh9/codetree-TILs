import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt(), b = sc.nextInt();
        int cntA = a, cntB = b;

        for(int i = 1; i <= b; i++){
            cntA = 2 * i;
            for(int j = 0; j <= b - a; j++){
                System.out.print(cntB + " * " + cntA + " = " + (cntA * cntB));
                if(cntB != a){
                    System.out.print(" / ");
                }
                cntB--;
            }
            System.out.println();
            
            cntB = b;
        }
    }
}