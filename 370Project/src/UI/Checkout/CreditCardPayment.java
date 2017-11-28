package UI.Checkout;

import Payment.CreditCardVerification;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import Payment.*;
import javafx.scene.layout.*;

/**
 * This is a UI class to verify the validity of the Credit Card details
 * entered during payment.
 */
<<<<<<< HEAD
public class CreditCardPayment {


    Label wel = new Label("Welcome");
    Label error = new Label();
    Label c_name = new Label("Card Holder's Name: ");
    Label c_num = new Label("Credit Card Numbers: ");
    Label e_date = new Label("Expiry Date: ");
    Label cvv = new Label("CVV Number: ");

    TextField c_field = new TextField();
    TextField num_field = new TextField();
    TextField d_field = new TextField();
    TextField n_field = new TextField();

    Button sub = new Button("SUBMIT");

    CreditCardVerification check_card;


    GridPane pane(){
        GridPane card_pane = new GridPane();

        //add all children
        card_pane.add(wel,1,0);
        card_pane.add(error,0,5);
        card_pane.add(c_name,0,2);
        card_pane.add(c_num,0,3);
        card_pane.add(n_field,1,2);
        card_pane.add(num_field,1,3);
        card_pane.add(sub,1,5);
        card_pane.add(expirydate(),0,4);
        card_pane.add(cvv(),1,4);

=======
public class CreditCardPayment extends Application {
    GridPane MainPane = new GridPane();
    Button payment = new Button("PAY");


    @Override
    public void start(Stage stage) throws Exception {


        MainPane.setPrefHeight(500);
        MainPane.setPrefWidth(800);
        payment.setPrefSize(180,60);

        /**
         * Payment information labels
         */
        Label CreditCardNumber = new Label("Credit Card Number: ");
        Label ExpiryDate = new Label("Expiry Date(MMYY): ");
        Label CVV = new Label("CVV: ");

        /**
         * Payment Information textfields
         */
        TextField CCNumber_field = new TextField();
        TextField expiryDate_field = new TextField();
        TextField cvv_field = new TextField();

        /**
         * Margins
         */
        GridPane.setMargin(CCNumber_field, new Insets(0,5,0,25));
        GridPane.setMargin(expiryDate_field, new Insets(0,5,0,25));
        GridPane.setMargin(cvv_field, new Insets(0,5,0,25));

        /**
         * Adding labels and text fields to frame
         */
        MainPane.add(CreditCardNumber, 0,1);
        MainPane.add(CCNumber_field, 1,1);

        MainPane.add(ExpiryDate, 0,3);
        MainPane.add(expiryDate_field, 1,3);

        MainPane.add(CVV, 0,5);
        MainPane.add(cvv_field, 1,5);

        MainPane.add(payment, 0,7);
        Scene scene = new Scene(MainPane);

        /**
         * Setting titles of the stage
         */
        stage.setTitle("Payment");

        /**
         * Adding scene to the stage
         */
        stage.setScene(scene);

        /**
         * Listener for PAY button
         */
        payment.setOnAction(event -> {
            String number = CCNumber_field.getText();
            String expiry = expiryDate_field.getText();
            String cvv = cvv_field.getText();

            Payment.CreditCardVerification Verify = new Payment.CreditCardVerification(number,expiry,cvv);

            if (Verify.wasSuccessful(Verify)){
                new Alert(Alert.AlertType.INFORMATION, Verify.message).showAndWait();
            }
        });
>>>>>>> c7100019d35a0d83437844e172de37eb3dc28b4f

        //card_pane.setStyle("-fx-background-color: blue");
        //set all size
        c_name.setPrefSize(325,60);
        c_num.setPrefSize(325,60);


        card_pane.setPrefSize(500,500);

        //set margin
        GridPane.setMargin(c_name,new Insets(0,0,30,15));
        GridPane.setMargin(c_num,new Insets(0,0,30,0));
        GridPane.setMargin(n_field,new Insets(0,100,30,0));
        GridPane.setMargin(num_field, new Insets(0,100,30,0));
        GridPane.setMargin(sub,new Insets(0,0,40,200));
        GridPane.setMargin(error,new Insets(0,0,40,40));
        card_pane.getColumnConstraints().add(new ColumnConstraints(260));
        card_pane.getColumnConstraints().add(new ColumnConstraints(340));

        //set padding
        c_num.setPadding(new Insets(0,0,0,105));
        c_name.setPadding(new Insets(0,0,0,105));

        setSubmit();
        return card_pane;
    }

    HBox expirydate(){
        HBox e_box = new HBox();
        e_date.setPrefSize(150,60);
        e_date.setPadding(new Insets(0,0,35,30));
        e_box.getChildren().addAll(e_date,d_field);
        return e_box;
    }

    HBox cvv(){
        HBox c_box = new HBox();
        cvv.setPrefSize(120,60);
        cvv.setPadding(new Insets(0,0,35,20));
        c_box.getChildren().addAll(cvv,c_field);
        return c_box;
    }

    void setSubmit(){
        sub.setPrefSize(100,40);
        sub.setOnAction(e->{
            check_card = new CreditCardVerification(num_field.getText(),d_field.getText(),c_field.getText());
            if (check_card.message !=null){
                error.setText(check_card.message);
            }
        });
    }

}
