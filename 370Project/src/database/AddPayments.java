package database;

/**
 * Create by Yinsheng Dong Nov 9
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddPayments {
    private GoConnection connection = new GoConnection();
    public String message = "";
    public void setPaymentInfo(int order_id, String pay_methods)
    {
        float price = 0;
        connection.connect();
        if(connection.coon!=null) {
            try {

                String getPriceQuery = "SELECT subtotal, pst_tax, gst_tax, total_discount from orders where id = "+order_id;
                Statement priceStmt = connection.coon.createStatement();
                ResultSet resultSet1 = priceStmt.executeQuery(getPriceQuery);
                if(!resultSet1.next())
                {
                    message = "no total get from this order_id";
                    connection.coon.close();
                }
                else
                {
                    price = resultSet1.getFloat(1)+resultSet1.getFloat(2)+
                            resultSet1.getFloat(3)-resultSet1.getFloat(4);
                }

                if(!pay_methods.equals("credit") && !pay_methods.equals("cash"))
                {
                    message = "payment method can not be others";
                    connection.coon.close();
                }
                else {
                    String addPaymentQuery = "insert into payments (orders_id, payment_method, price) VALUES (?,?,?)";
                    PreparedStatement statement = connection.coon.prepareStatement(addPaymentQuery);
                    statement.setInt(1, order_id);
                    statement.setString(2, pay_methods);
                    statement.setFloat(3,price);
                    int value = statement.executeUpdate();
                    if (value > 0) {
                        message = "payment is in processing";
                        connection.coon.close();
                    } else {
                        message = "payment is not created";
                        connection.coon.close();
                    }
                }
            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();

            }

        }
        else
        {
            message = "Something wrong on the connection sql";
        }

    }

    public void updatePaymentInfo(int payment_id)
    {
        connection.connect();
        if(connection.coon!=null)
        {
            try {


                String getCardStatusQuery = "select success from card_payment where payment_id =" + payment_id;
                String getCashStatusQuery = "select status from cash_payment where payment_id =" + payment_id;
                String getPaymentMethodQuery = "select payment_method from payments =" + payment_id;
                String updatePaymentQuery = "update payments set status = ? where id="+payment_id;

                Statement pay_method = connection.coon.createStatement();
                ResultSet set = pay_method.executeQuery(getPaymentMethodQuery);
                if(set.getString(1).equals("credit"))
                {
                    Statement getCardStatus = connection.coon.createStatement();
                    ResultSet cardStatusResult = getCardStatus.executeQuery(getCardStatusQuery);

                        PreparedStatement update = connection.coon.prepareStatement(updatePaymentQuery);
                        if(cardStatusResult.getString(1).equals("YES"))
                        {
                            update.setString(1,"yes");
                        }
                        else
                        {
                            update.setString(1,"no");
                        }
                        int value = update.executeUpdate();
                        if(value >0)
                        {
                            message = "the payment table has been update";
                            connection.coon.close();
                        }
                        else
                        {
                            message = "the payment table has not been update";
                            connection.coon.close();
                        }

                }
                else if(set.getString(1).equals("cash"))
                {
                    Statement getCashStatus = connection.coon.createStatement();
                    ResultSet cashResult = getCashStatus.executeQuery(getCashStatusQuery);
                    PreparedStatement update = connection.coon.prepareStatement(updatePaymentQuery);
                    if(cashResult.getString(1).equals("YES"))
                    {
                        update.setString(1,"yes");
                    }
                    else
                    {
                        update.setString(1,"no");
                    }
                    int value =  update.executeUpdate();
                    if(value>0)
                    {
                        message = "the payment table has been update";
                        connection.coon.close();
                    }
                    else
                    {
                        message = "the payment of table has not been update";
                        connection.coon.close();
                    }

                }
                else
                {
                    message = "something wrong on the data";
                    connection.coon.close();
                }


            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }
    }


    public static void main(String [] arg)
    {
        AddPayments addPayments = new AddPayments();
        addPayments.setPaymentInfo(1,"credit");
        System.out.println(addPayments.message);
    }

}
