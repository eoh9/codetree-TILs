import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int a[] = new int[n];
        int i = 0;

        for(i = 0; i < n; i++){
            a[i] = sc.nextInt();
        }
        for(int j = i-1; j >= 0; j--){
            if(a[j] % 2 == 0){
                System.out.print(a[j] + " ");
            } 
        }
    }
}