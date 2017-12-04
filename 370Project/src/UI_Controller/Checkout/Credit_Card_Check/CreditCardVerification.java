package UI_Controller.Checkout.Credit_Card_Check;
/**
 * Created by ror716 on 2017-10-11.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;

/**
 * This is a class to verify the validity of Expiry Date of the Credit Card
 * entered during payment.
 */
public class CreditCardVerification {

    /**
     * User's Credit Card number
     */
    private String ccNumber;

    /**
     * Standard Credit Card number length
     */
    public int stdCCNumberDigit = 16;

    /**
     * Current date of day the program is being ran
     */
    private Date todayDate;

    /**
     * Users Credit Card expiry date
     */
    private Date userDate;

    /**
     * Standard date format
     */
    DateFormat dateFormat;

    /**
     * User's Credit Card CVV code
     */
    private String CVV;

    /**
     * Standard CVV code length
     */
    public int stdCVVDigits = 3;

    /**
     * A string that carries all exception messages
     */
    public String message;

    /**
     * This is a class that verifies the validity of the credit card details entered for payment
     * by the user
     * @param number The Credit Card number entered by user
     * @param expiry The expiry date entered by the user
     * @param code The CVV code entered by user
     */
    public CreditCardVerification(String number, String expiry, String code){

        this.ccNumber = number;

        this.CVV = code;

        this.todayDate = new Date();

        this.dateFormat  = new SimpleDateFormat("MMyy");

        try{
            this.userDate = new SimpleDateFormat("MMyy").parse(expiry);
        }
        catch (ParseException e) {
            message = "EXPIRY DATE ENTERED IS EITHER INVALID OR WRONG FORMAT";
        }

        isCCNumberValid(ccNumber);
        isCVVNumberValid(CVV);
        isDateValid(userDate);
    }

    /**
     * Checks the validity of the entered Credit Card number using the length of numbers entered
     * @param ccNumber  Credit Card details entered by user
     * @return true if the Credit Card number entered is exactly 16 digits
     */
    public boolean isCCNumberLengthValid(String ccNumber){

        try {

            if (ccNumber.length() < stdCCNumberDigit)
                throw new RuntimeException();


            if (ccNumber.length() > stdCCNumberDigit)
                throw new RuntimeException();
        }

        catch(RuntimeException e) {

            message = "Error: CREDITCARD NUMBER ENTERED IS INVALID";
        }

        return  true;

    }


    /**
     * Checks the validity of the entered Credit Card number by checking if the numbers are actual integers
     * @param ccNumber Credit Card details entered by user
     * @return true if the Credit Card number entered are actual integers
     */
    public boolean isCCNumberValid(String ccNumber){

        try{

            Long.parseLong(ccNumber);
        }

        catch(NumberFormatException e){

            message = "Error: CREDITCARD NUMBER ENTERED IS INVALID";
        }

        isCCNumberLengthValid(ccNumber);
        return true;
    }

    /**
     * Checks the validity of the expiry date entered by the user by checking if
     * it is after the current date.
     * @return true if the expiry date entered by the user is after the current date
     */
    public boolean isDateValid(Date date){

        try {

            if (todayDate.after(date))
                throw new RuntimeException();
        }
        catch (RuntimeException e){

            message = "Error: EXPIRY DATE ENTERED IS INVALID";
        }

        return true;
    }

    /**
     * Checks the validity of the entered CVV number using the length of numbers entered
     * @param CVV Credit Card details entered by user
     * @return true is the creditCardVerification card verification number entered is exactly 3 digits
     */
    public boolean isCVVLengthValid(String CVV){

        try {

            if (CVV.length() < stdCVVDigits)
                throw new RuntimeException();


            if (CVV.length() > stdCVVDigits)
                throw new RuntimeException();
        }

        catch(RuntimeException e) {

            message = "Error: CVV ENTERED IS INVALID";

        }

        return  true;

    }

    /**
     * Checks the validity of the entered CVV number using the length of numbers entered
     * @param CVV Credit Card details entered by user
     * @return true is the creditCardVerification card verification number entered is exactly 3 digits
     */
    public boolean isCVVNumberValid(String CVV){

        try{

            Integer.parseInt(CVV);
        }

        catch(NumberFormatException e){

            message = "Error: CVV ENTERED IS INVALID";
        }

        isCVVLengthValid(CVV);
        return true;
    }

    /**
     * A method to test the class
     */
    public static void main(String[] args)
    {
        CreditCardVerification CCard = new CreditCardVerification("1234123412341234", "0218","123");
        System.out.println(CCard.message);

    }
}
