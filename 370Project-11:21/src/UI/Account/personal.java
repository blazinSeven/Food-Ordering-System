package UI.Account;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Create By Hao Li at Oct. 18th
 */
public class personal{

    //Creation
    private Stage per_windows = new Stage();
    private Label separate = new Label("|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|");
    Label info = new Label("Log In");
    Label address = new Label("Signed Up");
    private Label name = new Label();
    private Label info_address = new Label();
    private Button p_info = new Button("Personal Information");
    private Button c_password = new Button("Change Password");
    private Button f_food = new Button("Favorite");
    private Button q = new Button("Log Out");


    //check for info & address listener
    private boolean log_out = true;


    // make frame for personal information
    void PA(){
        // create pane, cant be static, I already tried it
        BorderPane borderPane = new BorderPane();
        HBox hleft = new HBox();

        // listener for buttons
        q.setOnAction(e->{
            per_windows.close();
            info.setText("Log In");
            address.setText("Signed Up");
            setCheck(true);
        });
        p_info.setOnAction(e->borderPane.setCenter(information()));
        c_password.setOnAction(e->borderPane.setCenter(change_password()));


        // add make up
        borderPane.setStyle("-fx-background-image: url(/pictures/rightcenter.jpg)");
        q.getStylesheets().add("css/quit.css");


        // set size
        separate.setPrefSize(10,400);
        borderPane.setPrefSize(600,400);
        hleft.setPrefSize(180,400);
        per_windows.setMinWidth(600);
        per_windows.setMinHeight(400);


        //add all
        hleft.getChildren().addAll(vBox(),separate);
        borderPane.setLeft(hleft);
        Scene scene = new Scene(borderPane);
        per_windows.setScene(scene);

        // show frame
        per_windows.setTitle("Personal Signed_In");
        per_windows.show();
    }


    // left side of main frame
    private VBox vBox(){
        VBox vleft = new VBox();
        name.setText(info.getText());
        info_address.setText(address.getText());
        name.setAlignment(Pos.BOTTOM_CENTER);
        info_address.setAlignment(Pos.CENTER);

        // set size
        vleft.setPrefSize(180,390);
        name.setPrefSize(180,80);
        info_address.setPrefSize(180,40);
        p_info.setPrefSize(180,80);
        c_password.setPrefSize(180,80);
        f_food.setPrefSize(180,80);
        q.setPrefSize(180,40);
        vleft.setPadding(new Insets(0,10,10,10));

        //set makeup and add all
        vleft.setStyle("-fx-background-image: url(/pictures/leftbackground.jpeg)");
        vleft.getStylesheets().add("css/personal.css");
        vleft.getChildren().addAll(name, info_address,p_info,c_password,f_food,q);
        return vleft;
    }


    // personal information frame
    VBox information(){

        // creation
        /* hard code for information
          need change listener by database **/
        VBox vBox = new VBox();
        Label n = new Label("Name: Eric");
        Label ad = new Label("Address: 110 Science Place");
        Label ph = new Label("Phone Number: (306)306-3066");
        Label em = new Label("E-mail: hal333@gmail.com");

        //set up size
        n.setPrefSize(260,90);
        ad.setPrefSize(260,90);
        ph.setPrefSize(260,90);
        em.setPrefSize(260,90);
        vBox.setPrefSize(400,400);

        // set up padding
        vBox.getChildren().addAll(n,ad,ph,em);
        vBox.setPadding(new Insets(40,70,40,70));
        vBox.getStylesheets().add("css/personal.css");
        return vBox;
    }


    //change password frame
    private GridPane change_password(){
        //creation
        GridPane change = new GridPane();
        //change.setStyle("-fx-background-image: url(/pictures/background.jpg)");
        Label old = new Label("Old Password: ");
        Label nword = new Label("New Password: ");
        Label confirm = new Label("Confirm Password: ");
        //Label blank = new Label("                                     ");
        Button c_button = new Button("Submit");
        PasswordField old_field = new PasswordField();
        PasswordField nword_field = new PasswordField();
        PasswordField confirm_field = new PasswordField();

        //set up size
        change.setPrefSize(400,400);
        change.add(old,0,0);
        change.add(old_field,1,0);
        change.add(nword,0,1);
        change.add(nword_field,1,1);
        change.add(confirm,0,2);
        change.add(confirm_field,1,2);
        //change.add(blank,0,3);
        change.add(c_button,1,3);

        //set up padding
        change.setAlignment(Pos.CENTER_LEFT);
        old.setPadding(new Insets(15,25,15,0));
        nword.setPadding(new Insets(15,25,15,0));
        confirm.setPadding(new Insets(15,25,15,0));
        GridPane.setMargin(c_button,new Insets(0,0,0,120));
        change.getStylesheets().add("css/personal.css");
        return change;
    }



    // check access
    boolean getCheck(){
        return log_out;
    }
    void setCheck(boolean n){
        log_out=n;
    }


}
