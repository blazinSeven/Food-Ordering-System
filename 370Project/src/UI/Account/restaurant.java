package UI.Account;

/**
 * Create By Hao Li at Nov.23th
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class restaurant {
    Stage res_windows = new Stage();
    Button res_info = new Button("Restaurant Information");
    Button change_password = new Button("Change Password");
    Button Menu = new Button("Menu");
    Button res_quit = new Button("LOG OUT");
    Button res_sub = new Button("SUBMIT");


    Label message = new Label();
    PasswordField r_old_field = new PasswordField();
    PasswordField r_confirm_field = new PasswordField();
    PasswordField r_new_field = new PasswordField();

    Label r_name = new Label();
    Label r_address = new Label();
    Label r_email = new Label();
    Label r_phone = new Label();
    Label r_otime = new Label();
    Label r_ctim = new Label();
    Label r_state = new Label();




    void RS(){
        BorderPane res_pane = new BorderPane();

        res_quit.getStylesheets().add("css/quit.css");
        res_pane.setStyle("-fx-background-image: url(/pictures/rightcenter.jpg)");
        res_pane.setPrefSize(600,400);
        res_pane.setLeft(res_holder());
        Scene scene = new Scene(res_pane);
        res_windows.setScene(scene);
        res_windows.setMinWidth(600);
        res_windows.setMinHeight(400);
    }
    VBox res_holder(){
        VBox holder = new VBox();
        holder.setPrefSize(180,390);
        holder.setStyle("-fx-background-image: url(/pictures/leftbackground.jpeg)");
        holder.getStylesheets().add("css/personal.css");
        return holder;
    }


    private GridPane restaurant_info(){
        GridPane r_info = new GridPane();
        Label name = new Label("Restaurant Name: ");
        Label phone = new Label("Phone Number: ");
        Label email = new Label("E-mail Address: ");
        Label o_time = new Label("Open Time: ");
        Label c_time = new Label("Close Time: ");
        Label address = new Label("Address: ");
        Label state = new Label("STATES: ");
        Label welcome = new Label("Welcome");

        r_info.setPrefSize(400,400);
        r_info.add(welcome,1,0);
        r_info.add(name,0,1);
        r_info.add(r_name,1,1);
        r_info.add(phone,0,2);
        r_info.add(r_phone,0,3);
        r_info.add(o_time,0,4);
        r_info.add(r_otime,0,5);
        r_info.add(address,0,6);
        r_info.add(r_address,0,7);
        r_info.add(email,1,2);
        r_info.add(r_email,1,3);
        r_info.add(c_time,1,4);
        r_info.add(r_ctim,1,5);
        r_info.add(state,1,6);
        r_info.add(r_state,1,7);


        return r_info;
    }


    //change password frame
    private GridPane change_password(){
        //creation
        GridPane change = new GridPane();
        //change.setStyle("-fx-background-image: url(/pictures/background.jpg)");
        Label r_old = new Label("Old Password: ");
        Label r_new = new Label("New Password: ");
        Label r_confirm = new Label("Confirm Password: ");
        //Label blank = new Label("                                     ");


        //set up size
        change.setPrefSize(400,400);
        change.add(r_old,0,0);
        change.add(r_old_field,1,0);
        change.add(r_new,0,1);
        change.add(r_new_field,1,1);
        change.add(r_confirm,0,2);
        change.add(r_confirm_field,1,2);
        //change.add(blank,0,3);
        change.add(res_sub,1,3);
        change.add(message,1,4);

        //set up padding
        change.setAlignment(Pos.CENTER_LEFT);
        r_old.setPadding(new Insets(15,25,15,0));
        r_new.setPadding(new Insets(15,25,15,0));
        r_confirm.setPadding(new Insets(15,25,15,0));
        GridPane.setMargin(res_sub,new Insets(0,0,0,120));
        change.getStylesheets().add("css/personal.css");
        return change;
    }

}
