package com.nps.devassessment;

import java.util.List;

public class Algorithm {

    /*The Luhn algorithm or Luhn formula, also known as the "modulus 10" or "mod 10" algorithm

    The following is a credit card check algorithm:

    From the last digit moving backwards, double every second digit


    If the doubled number is greater than 9, add its two digits together


    Sum all card digits together


    If the total ends in a zero, then the number is a valid credit card
*/
    public static void main(String[] args) {

        String number1 = "4242424242426742";
        String number2 = "1111111111111111";
        //if(isNumberValid(number1))
        // System.out.println(Number );
    }

    public static Boolean isNumberValid(String number) {

        char[] charArray = number.toCharArray();

        int[] ints = number.chars().map(i -> i - '0').toArray();

        //System.out.println(ints[0]+","+ints[1]);
        int sum = 0;
        //String number1 = "4242424242426742";
        for (int i = ints.length -1 ; i >= 0; i--) {
            System.out.println(ints[i]);
            if (i % 2 == 0) {
                int m = ints[i] * 2;
                if (m > 9) {
                    m = m - 9;
                    sum = sum + m;
                    System.out.println("summ= "+sum);

                }
                else {
                    sum = sum + m;
                    System.out.println("summ= " + sum);
                }

            }
            if (i % 2 == 1) {
                sum = sum + ints[i];
                System.out.println("summ= "+sum);
            }
        }

        return (sum % 10 ==0);
    }


}
