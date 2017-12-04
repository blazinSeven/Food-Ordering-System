package UI.Checkout.Credit_Card_Check;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * This is a class to verify the validity of Expiry Date of the Credit Card
 * entered during payment.
 */
public class DateVerification {

    /**
     * Current date of day the program is being ran
     */
    private LocalDate todaydate;

    /**
     * Users Credit Card expiry date
     */
    private LocalDate userDate;


    /**
     * Initialize the Credit Card expiry date with the expire date entered by the user
     * @param expiry  The expiry date entered by the user
     */
    public DateVerification(String expiry){

        this.todaydate = LocalDate.now();
        this.userDate = LocalDate.parse(expiry,DateTimeFormatter.ofPattern("yyyy-MM-dd"));



    }

    /**
     * Checks the validity of the expiry date entered by the user by checking if
     * it is after the current date.
     * @return
     */
    public boolean isDateValid(){

        if(userDate.isBefore(todaydate)){

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
