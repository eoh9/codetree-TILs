import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);

       int a, b, prod = 1;
       a = sc.nextInt(); b = sc.nextInt();

       for(int i = 1; i <= b; i++){
        if(i % a == 0){
            prod *= i;
        }
       }
       System.out.print(prod);
    }
}