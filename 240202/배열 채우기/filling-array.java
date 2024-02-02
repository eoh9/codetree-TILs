import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] a = new int[10];
        int i = 0;

        for(i = 0; i < 10; i++){
            a[i] = sc.nextInt();
            if(a[i] ==0){
                break;
            }
        }
        for(int j = i-1; j >= 0; j--){
            System.out.print(a[j] + " ");
        }
    }
}