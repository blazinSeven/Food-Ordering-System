package UI;


import Account.Account;
import Account.Registration;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class user_account extends VBox{
    //check status, replace later
    Registration registration = new Registration();
    Account account = new Account();
    //AddUser user = new AddUser();

    public user_account() {
        checkBox();
        setPrefHeight(100);
        setPrefWidth(100);
        getStylesheets().add("css/acc_main.css");
        getChildren().addAll(account.info,account.address, checkBox());
    }




    private VBox checkBox(){
        VBox checkbox = new VBox();
        CheckBox deliver = new CheckBox();
        CheckBox pick_up = new CheckBox();
        CheckBox reservation = new CheckBox();
        deliver.setText("Deliver");
        pick_up.setText("Pick Up");
        reservation.setText("Reservation");
        deliver.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                pick_up.setSelected(false);
                reservation.setSelected(false);
            }
        });
        pick_up.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                deliver.setSelected(false);
                reservation.setSelected(false);
            }
        });
        reservation.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                deliver.setSelected(false);
                pick_up.setSelected(false);

            }
        });
        checkbox.getChildren().addAll(deliver,pick_up,reservation);
        return checkbox;
    }

}
