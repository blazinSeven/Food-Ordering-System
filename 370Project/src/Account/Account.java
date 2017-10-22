package Account;

import database.AddUser;
import database.Login;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Account {

    Stage pop = new Stage();
    Login login = new Login();
    Stage windows = new Stage();
    Registration registration = new Registration();
    public Label info = new Label("Log In");
    public Label address = new Label("Signed Up");
    int check = 0;
    public Account(){
        info.setPrefSize(100,20);
        address.setPrefSize(100,20);
        setlog();

    }

    void setlog(){
        if (info.getText().equals("Log In")&&getCheck()==0){
            info.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.print(check);
                    info();
                }
            });
        }
        if (address.getText().equals("Signed Up")&&getCheck()==0){
            address.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    registration.Reg();
                }
            });
        }

    }

    void setinfo(){
        if (getCheck()==1&&info.getText().equals("Hi, "+login.name)){
            info.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("name");
                }
            });
        }
        if (getCheck()==1&&address.getText().equals("Address: 110 Science Place")){
            address.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("address");
                }
            });
        }
    }
    void setCheck(){
        check=1;
    }
    int getCheck(){
        return check;
    }

    public void info(){

        windows.setTitle("Signed In");
        windows.setMinHeight(200);
        windows.setMinWidth(300);
        Scene scene = new Scene(layout());
        scene.getStylesheets().add("css/account_style.css");
        windows.setScene(scene);
        windows.show();
    }

    private GridPane layout(){
        GridPane sign = new GridPane();
        Button submit = new Button("Submit");
        Label user_name = new Label("Username: ");
        Label pass_word = new Label("Password: ");
        Label error = new Label("");
        user_name.setPadding(new Insets(15,5,15,5));
        pass_word.setPadding(new Insets(15,5,15,10));
        TextField username = new TextField();
        PasswordField password = new PasswordField();
        Label signed_in = new Label("Welcome to FOS");
        signed_in.setFont(new Font(25));
        sign.add(signed_in,1,0);
        sign.add(user_name,0,1);
        sign.add(pass_word,0,2);
        sign.add(error,1,3);
        sign.add(submit,3,4);
        sign.add(username,1,1);
        sign.add(password,1,2);
        sign.setPadding(new Insets(30,100,15,100));
        submit.setOnAction(ActionEvent->{
            login.login(username.getText(),password.getText());
            if(login.message.equals("true"))
            {
                //pop();
                info.setText("Hi, "+login.name);
                address.setText("Address: 110 Science Place");
                setCheck();
                setinfo();

                windows.close();


            }
            else {
                error.setText("Wrong username or password");
                error.setWrapText(true);
                error.setTextFill(Color.RED);
            }
        });
        return sign;
    }

    private Stage pop() {
        pop.setMinWidth(200);
        VBox vBox = new VBox();
        Label messageLabel = new Label("You are successfully Login");
        //check=messageLabel.getText();
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

}