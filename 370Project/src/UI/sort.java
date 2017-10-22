package UI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class sort extends VBox{
    CheckBox sort1 = new CheckBox();
    CheckBox sort2 = new CheckBox();
    CheckBox sort3 = new CheckBox();
    CheckBox sort4 = new CheckBox();
    public sort(){
        set();
        getChildren().addAll(sort1,sort2,sort3,sort4);
        setPrefSize(100,70);

    }

    private void set(){
        sort1.setText("Sort by Rate");
        sort2.setText("Sort by Distance");
        sort3.setText("Sort by Prices");
        sort4.setText("Sort by Items");
        sort1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                sort2.setSelected(false);
                sort3.setSelected(false);
                sort4.setSelected(false);
            }
        });
        sort2.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                sort1.setSelected(false);
                sort3.setSelected(false);
                sort4.setSelected(false);
            }
        });
        sort3.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                sort2.setSelected(false);
                sort1.setSelected(false);
                sort4.setSelected(false);
            }
        });
        sort4.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                sort2.setSelected(false);
                sort3.setSelected(false);
                sort1.setSelected(false);
            }
        });
    }
}
