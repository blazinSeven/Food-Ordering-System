package Payment; /**
 * Created by ror716 on 2017-10-11.
 */
import java.util.Date;

/**
 * Created by Kocur on 2017-10-10.
 */
public class creditCardVerification {

    private String ccNumber;
    private String CCV;
    private String cardType;
    private Date goodThru;
    private Date validFrom;
    public int STD_CCN_DIGITS = 16;
    public int STD_CCV_DIGITS = 3;

    /*
        Creator Method. In order pass card num, cvs, card type, expiration date and starting date
     */
    creditCardVerification(String num, String code, String type, Date expiry, Date startDate)
    {
        this.ccNumber = num;
        this.CCV = code;
        this.cardType = type;
        this.goodThru = expiry;
        this.validFrom = startDate;
    }

    /**
     IN: Credit Card
     OUT: Returns true if the expiration date is after the "validFrom" date
     */
    public boolean isDateValid(creditCardVerification c)
    {
        if(c.validFrom.compareTo(c.goodThru) <= 0)
        {
            throw new  RuntimeException("Error:  Credit Card expiration date is invalid");
        }
        else
        {
            return true;
        }
    }


    /**
     * Test the validity of the entered creditCardVerification card number using the length of numbers entered
     * @param c
     * @return true is the creditCardVerification card number entered is exactly 16 digits
     */

    public boolean isCCNumberValid(creditCardVerification c){

        if(c.ccNumber.length() < STD_CCN_DIGITS){
            throw new RuntimeException("Error: CreditCard Number is INVALID, MotherFucker!!!");
        }

        else if(c.ccNumber.length() > STD_CCN_DIGITS){
            throw new RuntimeException("Error: CreditCard Number is INVALID, MotherFucker!!!");
        }

        else {
            return  true;
        }
    }

    /**
     * Test the validity of the entered creditCardVerification card verification number using the length of numbers entered
     * @param c
     * @return true is the creditCardVerification card verification number entered is exactly 3 digits
     */
    public boolean isCCVNumberValid(creditCardVerification c){

        if(c.CCV.length() < STD_CCV_DIGITS){
            throw new RuntimeException("Error: CCV Number is INVALID, MotherFucker!!!");
        }

        else if(c.CCV.length() > STD_CCV_DIGITS){
            throw new RuntimeException("Error: CCV Number is INVALID, MotherFucker!!!");
        }

        else {
            return  true;
        }
    }


    public static void main(String[] args)
    {

        Date testDate1 = new Date (1994, 3, 8 );
        Date testDate2 = new Date (1994, 3, 9);

        creditCardVerification testCard = new creditCardVerification("1234567890123456", "123","Visa", testDate1, testDate2);

        /**
         * Testing date functions
         */
        testCard.isDateValid(testCard);

        // Testing Validity of the Credit Card Number
        testCard.isCCNumberValid(testCard);

        // Testing Validity of the Credit Card Verification Number
        testCard.isCCVNumberValid(testCard);
    }



}
