package Payment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * This is a class to verify the validity of Expiry Date of the Credit Card
 * entered during payment.
 */
public class DateVerification {

    /**
     * Current date of day the program is being ran
     */
    private Date todaydate;

    /**
     * Users Credit Card expiry date
     */
    private Date userDate;


    /**
     * Initialize the Credit Card expiry date with the expire date entered by the user
     * @param expiry  The expiry date entered by the user
     */
    DateVerification(String expiry){

        this.todaydate = new Date();

        try{
            this.userDate = new SimpleDateFormat("MMyy").parse(expiry);
        }
        catch (ParseException e) {
            System.out.println("EXPIRY DATE ENTERED IS EITHER INVALID OR WRONG FORMAT");
        }

    }

    /**
     * Checks the validity of the expiry date entered by the user by checking if
     * it is after the current date.
     * @return
     */
    public boolean isDateValid(){

        if(userDate.before(todaydate)){

            throw new RuntimeException("CREDIT CARD HAS EXPIRED!!!");
        }
        else {
            return  true;
        }
    }

    /**
     * A method to test the class
     */
    public  static void main(String[] args){

        Scanner input = new Scanner (System.in);
        System.out.println("Expiry Date (MMYY): ");
        String uDate = input.nextLine();

        DateVerification expiryDate = new DateVerification(uDate);
        expiryDate.isDateValid();
    }
}
