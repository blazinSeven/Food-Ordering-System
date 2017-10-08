package Account;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Account {
    Stage windows = new Stage();
    public void account(){

        windows.setTitle("Signed In");
        windows.initModality(Modality.APPLICATION_MODAL);
        windows.setMinHeight(200);
        windows.setMinWidth(300);
        Scene scene = new Scene(layout());
        scene.getStylesheets().add("Interface/account_style.css");
        windows.setScene(scene);
        windows.showAndWait();
    }

    private GridPane layout(){
        GridPane sign = new GridPane();
        Button submit = new Button("Submit");
        Button quit = new Button("Doesn't have an account?");
        quit.setOnAction(e->new Registration().Reg());
        Label user_name = new Label("Username: ");
        Label pass_word = new Label("Password: ");
        user_name.setPadding(new Insets(15,5,15,5));
        pass_word.setPadding(new Insets(15,5,15,10));
        TextField field1 = new TextField();
        TextField field2 = new TextField();
        Label signed_in = new Label("Welcome to FOS");
        signed_in.setFont(new Font(25));

        GridPane butt = new GridPane();
        butt.add(submit,0,1);
        butt.add(quit,1,1);

        sign.add(signed_in,1,0);
        sign.add(user_name,0,1);
        sign.add(pass_word,0,2);
        sign.add(field1,1,1);
        sign.add(field2,1,2);
        sign.add(butt,1,3);
        sign.setPadding(new Insets(30,100,15,100));
        return sign;
    }
}
