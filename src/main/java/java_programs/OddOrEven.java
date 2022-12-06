package java_programs;

import java.util.Scanner;

public class OddOrEven {
    public void check(int a) {
        if (a % 2 == 0) {
            System.out.println("number is even");
        } else {
            System.out.println("number is odd");
        }
    }

    public static void main(String[] args) {
        OddOrEven OddOrEven = new OddOrEven();
        Scanner sc = new Scanner(System.in);
        System.out.println("enter your number");
        int a = sc.nextInt();
        OddOrEven.check(a);

    }
}

