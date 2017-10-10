package Account;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Registration {
    public void Reg(){
        Stage Rewindows = new Stage();
        Rewindows.setTitle("Register");
        Rewindows.initModality(Modality.APPLICATION_MODAL);
        Rewindows.setMinHeight(200);
        Rewindows.setMinWidth(500);
        Button quit = new Button("Quit");
        quit.setOnAction(e->Rewindows.close());
        HBox open = new HBox();
        open.getChildren().add(quit);
        Scene scene = new Scene(open);
        Rewindows.setScene(scene);
        Rewindows.showAndWait();
    }
}
