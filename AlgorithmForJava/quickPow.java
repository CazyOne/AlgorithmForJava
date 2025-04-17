package AlgorithmForJava;

import java.util.Scanner;

public class quickPow {

        static long M = 2146516019;
        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            long ans = 0;
            for(int i = 1; i <= 233333333; i++){
                ans ^= quickPow(i,M-2);
            }

            System.out.println(ans);
            scan.close();
        }

        public static long quickPow(long a, long b){
            long res = 1;
            a = a % M;

            while(b > 0){
                if((b & 1) == 1){
                    res = res * a % M;
                }

                b >>= 1;
                a = a * a % M;
            }

            return res;
        }
}
