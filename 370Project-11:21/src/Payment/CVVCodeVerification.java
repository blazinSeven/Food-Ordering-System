package Payment;

import java.util.Scanner;

/**
 * This is a class to verify the validity of CVV code of the Credit Card
 * entered during payment.
 */
public class CVVCodeVerification {

    /**
     * User's Credit Card CVV code
     */
    private String CVV;

    /**
     * Standard CVV code length
     */
    public int STD_CVV_DIGITS = 3;

    /**
     * Initialize the CVV verification with the CVV code entered by user
     * @param code  The CVV code entered by user
     */
    CVVCodeVerification(String code){

        this.CVV = code;
    }


    /**
     * Checks the validity of the entered CVV code using the length of numbers entered
     * @param code  The CVV code entered by user
     * @return true if the CVV number entered is exactly 3 digits
     */
    public boolean isCVVLengthValid(CVVCodeVerification code){

        try {

            if (code.CVV.length() < STD_CVV_DIGITS)
                throw new RuntimeException();


            if (code.CVV.length() > STD_CVV_DIGITS)
                throw new RuntimeException();
        }
        catch(RuntimeException e) {

            System.out.println ("Error: CVV ENTERED IS INVALID");
        }

        return  true;

    }


    /**
     * Checks the validity of the entered CVV code by checking if the code is actually numbers
     * @param code  The CVV code entered by user
     * @return true if the CVV number entered are actual numbers
     */
    public boolean isCVVNumberValid(CVVCodeVerification code){

        try{

            Integer.parseInt(code.CVV);
        }
        catch(NumberFormatException e){

            System.out.println("Error: CVV ENTERED IS INVALID");
        }

        return true;
    }

    /**
     * A method to test the class
     */
    public  static void main(String[] args){

        Scanner input = new Scanner (System.in);
        System.out.println("CVV: ");
        String uCVV = input.nextLine();

        CVVCodeVerification CVVCode = new CVVCodeVerification(uCVV);
        CVVCode.isCVVLengthValid(CVVCode);
        CVVCode.isCVVNumberValid(CVVCode);
    }
}
