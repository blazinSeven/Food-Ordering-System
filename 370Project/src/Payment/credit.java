package Payment;
import java.util.Date;

/**
 * Created by Kocur on 2017-10-10.
 */
public class Credit {

    private int number;
    private int cvs;
    private String cardType;
    private Date goodThru;
    private Date validFrom;

    /*
        Creator Method. In order pass card num, cvs, card type, expiration date and starting date
     */
    Credit(int num, int code, String type, Date expiry, Date startDate)
    {
        this.number = num;
        this.cvs = code;
        this.cardType = type;
        this.goodThru = expiry;
        this.validFrom = startDate;
    }

    /**
        IN: Credit Card
        OUT: Returns true if the expiration date is after the "validFrom" date
     */
    public boolean isDateValid(Credit c)
    {
        if(c.validFrom.compareTo(c.goodThru) <= 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Testing date functions
     */
    public static void main(String[] args)
    {

     Date testDate1 = new Date (1994, 3, 8 );
     Date testDate2 = new Date (1994, 3, 9);
     Credit testCard = new Credit(123, 123, "Visa", testDate2, testDate2);


     try {
         testCard.isDateValid(testCard);
     }
     catch (RuntimeException e)
        {
            System.out.print("Error: Expiration Date is invalid");

        }
    }



}
