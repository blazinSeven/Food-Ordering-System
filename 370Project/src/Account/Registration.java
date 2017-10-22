package Account;

import database.AddCustomers;
import database.AddRestaurants;
import database.AddUser;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Time;

public class Registration {
    Stage Reg_windows = new Stage();
    Stage restaurantReg = new Stage();
    Stage customerReg = new Stage();
    Stage pop = new Stage();
    AddUser addUser = new AddUser();

    public Stage Reg() {
        //new stage of registration

        Reg_windows.setTitle("Register");
        Reg_windows.setMinHeight(200);
        Reg_windows.setMinWidth(100);
        HBox check = new HBox();


        //Buttons
        Button customer_check = new Button("Register as Customer");
        Button restaurant_check = new Button("Register as Restaurant");
        //Button signUp = new Button("SUBMIT ");
        customer_check.setMinSize(50, 200);
        restaurant_check.setMinSize(50, 200);
        check.getChildren().addAll(customer_check, restaurant_check);
        //reg_pane.add(quit,2,9);


        //functions
        customer_check.pressedProperty().addListener(observable -> {
            customerReg();
        });
        restaurant_check.pressedProperty().addListener(observable -> {
            restaurantReg();
        });

        /*
        signUp.setOnAction(actionEvent->{
            username = account_field.getText();
            spassword = password_field.getText();
            addUser.addUser(username,spassword,status);
            pop();
        });
        */
        Scene scene = new Scene(check);
        Reg_windows.setScene(scene);
        Reg_windows.show();
        return Reg_windows;
    }


    Stage pop() {
        pop.setMinWidth(200);
        VBox vBox = new VBox();
        Label messageLabel = new Label("You are successfully register");
        messageLabel.setTextFill(Color.ORANGE);
        Label quit = new Label("Click to close");
        quit.setTextFill(Color.RED);
        quit.setFont(new Font(18));
        quit.setOnMousePressed(e -> pop.close());
        vBox.getChildren().addAll(messageLabel, quit);
        Scene scene = new Scene(vBox);
        pop.setScene(scene);
        pop.show();
        return pop;
    }


    Stage restaurantReg() {
        //addRestaurants.addUser = addUser;

        //restaurantReg.initModality(Modality.APPLICATION_MODAL);
        restaurantReg.setTitle("RESTAURANT REGISTER");
        restaurantReg.setMinHeight(550);
        restaurantReg.setMinWidth(450);
        GridPane res_pane = new GridPane();

        // Labels
        Label reg = new Label("Welcome to FOS");
        Label res_name = new Label("Restaurant Name: ");
        Label lic_num = new Label("License ID: ");
        Label open_time = new Label("Open Time: ");
        Label close_time = new Label("Close Time: ");
        Label email = new Label("Email: ");
        Label phone_num = new Label("Phone Number: ");
        Label account = new Label("Username: ");
        Label password = new Label("Password: ");
        Label error = new Label(" ");
        error.setWrapText(true);


        //TextFields
        TextField restName_field = new TextField();
        TextField licenseId_field = new TextField();
        TextField openTime_field = new TextField("HH:MM:SS");
        TextField closeTime_field = new TextField("HH:MM:SS");
        TextField email_field = new TextField();
        TextField phoneNum_field = new TextField();
        TextField account_field = new TextField();
        PasswordField password_field = new PasswordField();

        //Button
        Button submit = new Button("SUBMIT");

        //Layout
        res_pane.add(reg, 1, 0);
        res_pane.add(res_name, 0, 1);
        res_pane.add(restName_field, 1, 1);
        res_pane.add(lic_num, 0, 2);
        res_pane.add(licenseId_field, 1, 2);
        res_pane.add(open_time, 0, 3);
        res_pane.add(openTime_field, 1, 3);
        res_pane.add(close_time, 0, 4);
        res_pane.add(closeTime_field, 1, 4);
        res_pane.add(email, 0, 5);
        res_pane.add(email_field, 1, 5);
        res_pane.add(phone_num, 0, 6);
        res_pane.add(phoneNum_field, 1, 6);
        res_pane.add(account, 0, 7);
        res_pane.add(account_field, 1, 7);
        res_pane.add(password, 0, 8);
        res_pane.add(password_field, 1, 8);
        res_pane.add(error, 1, 9);
        res_pane.add(submit, 2, 10);


        //padding
        reg.setPadding(new Insets(0, 5, 25, 0));
        res_name.setPadding(new Insets(10, 5, 10, 85));
        lic_num.setPadding(new Insets(10, 5, 10, 85));
        open_time.setPadding(new Insets(10, 5, 10, 85));
        close_time.setPadding(new Insets(10, 5, 10, 85));
        email.setPadding(new Insets(10, 5, 10, 85));
        phone_num.setPadding(new Insets(10, 5, 10, 85));
        account.setPadding(new Insets(10, 5, 10, 85));
        password.setPadding(new Insets(10, 5, 10, 85));
        error.setPadding(new Insets(10, 5, 10, 85));
        res_pane.setPadding(new Insets(50, 80, 100, 30));


        submit.setOnAction(ActionEvent -> {
            AddRestaurants addRestaurants = new AddRestaurants();
            addUser.addUser(account_field.getText(), password_field.getText(), "r");
            if (addUser.message == "You have successfully signed up") {
                addRestaurants.addUser = addUser;
                addRestaurants.setRestaurantInfo(restName_field.getText(), licenseId_field.getText(), Time.valueOf(openTime_field.getText()), Time.valueOf(closeTime_field.getText()), phoneNum_field.getText(), email_field.getText());
                if (addRestaurants.message == "The restaurant information has been saved") {
                    restaurantReg.close();
                    Reg_windows.close();
                    pop();
                }
            } else {
                error.setTextFill(Color.RED);
                error.setText(addUser.message);
            }
        });

        Scene scene = new Scene(res_pane);
        scene.getStylesheets().add("css/register_style.css");
        restaurantReg.setScene(scene);
        restaurantReg.show();
        return restaurantReg;
    }


    Stage customerReg() {
        //addCustomers.addUser = addUser;
        //customerReg.initModality(Modality.APPLICATION_MODAL);
        customerReg.setTitle("CUSTOMER REGISTER");
        customerReg.setMinHeight(550);
        customerReg.setMinWidth(450);

        //Buttons
        Button signUp = new Button("SUBMIT ");

        //Labels
        Label reg = new Label("Welcome to FOS");
        Label account = new Label("Username: ");
        Label password = new Label("Password: ");
        Label first_name = new Label("First Name: ");
        Label last_name = new Label("Last name: ");
        Label phone_number = new Label("Phone#: ");
        Label email = new Label("E-mail: ");
        Label pref_food = new Label("Pref Food: ");
        Label error = new Label(" ");
        error.setWrapText(true);

        //Fields
        TextField firstName_field = new TextField();
        TextField lastName_field = new TextField();
        TextField phoneNum_field = new TextField();
        TextField email_field = new TextField();
        TextField pref_field = new TextField();
        TextField account_field = new TextField();
        PasswordField password_field = new PasswordField();


        GridPane reg_pane = new GridPane();
        reg_pane.add(reg, 1, 0);
        reg_pane.add(first_name, 0, 2);
        reg_pane.add(firstName_field, 1, 2);
        reg_pane.add(last_name, 0, 3);
        reg_pane.add(lastName_field, 1, 3);
        reg_pane.add(email, 0, 4);
        reg_pane.add(email_field, 1, 4);
        reg_pane.add(phone_number, 0, 5);
        reg_pane.add(phoneNum_field, 1, 5);
        reg_pane.add(account, 0, 6);
        reg_pane.add(account_field, 1, 6);
        reg_pane.add(password, 0, 7);
        reg_pane.add(password_field, 1, 7);
        reg_pane.add(pref_food, 0, 8);
        reg_pane.add(pref_field, 1, 8);
        reg_pane.add(error, 1, 9);
        reg_pane.add(signUp, 2, 10);
        //reg_pane.add(quit,2,9);


        //padding
        reg.setPadding(new Insets(0, 5, 25, 0));
        first_name.setPadding(new Insets(10, 5, 10, 85));
        last_name.setPadding(new Insets(10, 5, 10, 85));
        email.setPadding(new Insets(10, 5, 10, 85));
        phone_number.setPadding(new Insets(10, 5, 10, 85));
        account.setPadding(new Insets(10, 5, 10, 85));
        password.setPadding(new Insets(10, 5, 10, 85));
        pref_food.setPadding(new Insets(10, 5, 10, 85));
        error.setPadding(new Insets(10, 5, 10, 85));
        reg_pane.setPadding(new Insets(50, 80, 100, 50));


        //Font
        reg.setFont(new Font(18));
        first_name.setFont(new Font(12));
        last_name.setFont(new Font(12));
        email.setFont(new Font(12));
        phone_number.setFont(new Font(12));
        account.setFont(new Font(12));
        password.setFont(new Font(12));
        pref_food.setFont(new Font(12));


        signUp.setOnAction(ActionEvent -> {
            AddCustomers addCustomers = new AddCustomers();
            addUser.addUser(account_field.getText(), password_field.getText(), "c");
            if (addUser.message == "You have successfully signed up") {
                addCustomers.addUser = addUser;
                addCustomers.setCustomerInfo(firstName_field.getText(), lastName_field.getText(), phoneNum_field.getText(), email_field.getText(), pref_field.getText());
                if (addCustomers.message == "The customer information has been saved") {
                    customerReg.close();
                    Reg_windows.close();
                    pop();
                }
            } else {
                error.setTextFill(Color.RED);
                error.setText(addUser.message);
            }

        });
        Scene scene = new Scene(reg_pane);
        scene.getStylesheets().add("css/register_style.css");
        customerReg.setScene(scene);
        customerReg.show();
        return customerReg;
    }

}
