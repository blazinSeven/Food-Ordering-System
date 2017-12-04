package Entities;
import database.*;

import java.time.Duration;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/**
 * Created by Kocur on 2017-10-24.
 */
public class Customer {

    private int id;
    private int user_id;
    String firstName;
    String lastName;
    String phoneNumber;
    String eMail;
    Food prefFood;

    /**
     * Creator Method:
     * In order, pass id, user_id, first_name, last_name, phone_number, eMail, and prefFood
     */
    public Customer(int identification, int userID, String fName, String lName, String phoneNumber, String email, Food prefFood)
    {
        this.id = identification;
        this.user_id = userID;
        this.firstName = fName;
        this.lastName = lName;
        this.phoneNumber =phoneNumber;
        this.prefFood = prefFood;
    }
    //No accessor
    public int getId()
    {
        return this.id;
    }
    //No accessor
    public int getUser_id()
    {
        return this.user_id;
    }



    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String newFirstName)
    {
        this.firstName = newFirstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String newLastName)
    {
        this.lastName = newLastName;
    }


    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String newNum)
    {
        this.phoneNumber = newNum;
    }


    public Food getPrefFood()
    {
        return this.prefFood;
    }

    public void setNewPrefFood(Food f)
    {
        this.prefFood = f;
    }

    public String getEmail()
    {
        return this.eMail;
    }



    //Ability to insert customer, calls method from other package
    public void insertDatabase()
    {
        AddCustomers newCustomer = new AddCustomers();
        newCustomer.setCustomerInfo(this.getFirstName(), this.getLastName(), this.getPhoneNumber(), this.getEmail(), this.getPrefFood().toString());


    }


    public static void main(String[] args)
    {
        Food tendies = new Food("Tendies", 6.9, Duration.ofHours(1), 7);
        Customer testCust = new Customer(1, 2, "Ted", "ned", "123","jimmy@jimmy.com", tendies );
        testCust.insertDatabase();
    }



}


