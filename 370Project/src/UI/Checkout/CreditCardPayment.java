package UI.Checkout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

/**
 * This is a UI class to verify the validity of the Credit Card details
 * entered during payment.
 */
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

        /**
         * Displaying the contents of the stage
         */
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();

    }
}
