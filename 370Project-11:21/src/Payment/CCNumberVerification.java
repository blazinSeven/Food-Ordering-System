package Payment;

import java.util.Scanner;

/**
 * This is a class to verify the validity of the Credit Card
 * number entered during payment.
 */
public class CCNumberVerification {

    /**
     * User's Credit Card number
     */
    private String CCNumber;

    /**
     * Standard Credit Card number length
     */
    public int STD_CCN_DIGITS = 16;

    /**
     * Initialize the Credit Card number verification with the number entered by user
     * @param number  The Credit Card number entered by user
     */
    CCNumberVerification(String number){

        this.CCNumber = number;
    }

    /**
     * Checks the validity of the entered Credit Card number using the length of numbers entered
     * @param number The Credit Card number entered by user
     * @return true if the Credit Card number entered is exactly 16 digits
     */
    public boolean isCCNumberLengthValid(CCNumberVerification number){

        try {

            if (number.CCNumber.length() < STD_CCN_DIGITS)
                throw new RuntimeException();


            if (number.CCNumber.length() > STD_CCN_DIGITS)
                throw new RuntimeException();
        }

        catch(RuntimeException e) {

            System.out.println ("Error: CREDITCARD NUMBER ENTERED IS INVALID");
        }

        return  true;

    }


    /**
     * Checks the validity of the entered Credit Card number by checking if the numbers are actual integers
     * @param number  The Credit Card number entered by user
     * @return true if the Credit Card number entered are actual integers
     */
    public boolean isCCNumberValid(CCNumberVerification number){

        try{

            Long.parseLong(number.CCNumber);
        }

        catch(NumberFormatException e){

            System.out.println("Error: CREDITCARD NUMBER ENTERED IS INVALID");
        }

        return true;
    }


    /**
     * A method to test the class
     */
    public  static void main(String[] args){

        Scanner input = new Scanner (System.in);
        System.out.println("CreditCard Number: ");
        String uCCNumber = input.nextLine();

        CCNumberVerification CCardNumber = new CCNumberVerification(uCCNumber);
        CCardNumber.isCCNumberLengthValid(CCardNumber);
        CCardNumber.isCCNumberValid(CCardNumber);
    }
}
