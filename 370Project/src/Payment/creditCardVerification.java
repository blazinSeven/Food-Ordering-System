package Payment; /**
 * Created by ror716 on 2017-10-11.
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Kocur on 2017-10-10.
 */
public class CreditCardVerification {

    private String ccNumber;
    private String CCV;
    private String cardType;
    private LocalDate goodThru;
    private LocalDate validFrom;
    public int STD_CCN_DIGITS = 16;
    public int STD_CCV_DIGITS = 3;

    /*
        Creator Method. In order pass card num, cvs, card type, expiration date and starting date
     */
    public CreditCardVerification(String num, String code, String type, LocalDate expiry, LocalDate startDate)
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
    public boolean isDateValid(CreditCardVerification c)
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
     * Test the validity of the entered CreditCardVerification card number using the length of numbers entered
     * @param c
     * @return true is the CreditCardVerification card number entered is exactly 16 digits
     */

    public boolean isCCNumberValid(CreditCardVerification c){

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
     * Test the validity of the entered CreditCardVerification card verification number using the length of numbers entered
     * @param c
     * @return true is the CreditCardVerification card verification number entered is exactly 3 digits
     */
    public boolean isCCVNumberValid(CreditCardVerification c){

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

        LocalDate testDate1 = LocalDate.parse ("1994/03/08", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate testDate2 = LocalDate.parse ("1994/03/09", DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        CreditCardVerification testCard = new CreditCardVerification("1234567890123456", "123","Visa", testDate1, testDate2);

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
