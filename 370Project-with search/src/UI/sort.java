package UI;

import Search_Sort.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import UI.Center_Frame.c_main_hbox;

import java.util.ArrayList;

/**
 * Create By Hao Li at Oct. 15th
 * Modified By Yuecheng Rong at Oct.30
 */
public class sort extends VBox{
    CheckBox sort1 = new CheckBox();
    CheckBox sort2 = new CheckBox();
    CheckBox sort3 = new CheckBox();
    CheckBox sort4 = new CheckBox();

    Sort st = new Sort();

    public sort(c_main_hbox centre){
        set(centre);
        getChildren().addAll(sort1,sort2,sort3,sort4);
        setPrefSize(100,70);
    }

    private void set(c_main_hbox centre){
        sort1.setText("Sort by Rate");
        sort2.setText("Sort by Distance");
        sort3.setText("Sort by Prices");
        sort4.setText("Sort by Waiting Time");


        sort1.setSelected(false);
        sort2.setSelected(false);
        sort3.setSelected(false);
        sort4.setSelected(false);



        /**
        sort1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                sort2.setSelected(false);
                sort3.setSelected(false);
                sort4.setSelected(false);
                st.SortByRate(str);
                centre.setSort(st.k);
            }
        });


        sort2.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                sort1.setSelected(false);
                sort3.setSelected(false);
                sort4.setSelected(false);
                //st.SortByDistance(str);
                //centre.setSort(st.k);
            }
        });
        sort3.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                sort2.setSelected(false);
                sort1.setSelected(false);
                sort4.setSelected(false);
                //st.SortByPrice(str);
                //centre.setSort(st.k);
            }
        });
        sort4.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                sort2.setSelected(false);
                sort3.setSelected(false);
                sort1.setSelected(false);
               // st.SortByWaitingTime(str);
               // centre.setSort(st.k);
            }
        });

        */

        /**
         * Set actions for those ratio button
         * For each button there is a different action, will call different function
         */
        sort1.setOnAction(e->{
            sort1.setSelected(true);
            sort2.setSelected(false);
            sort3.setSelected(false);
            sort4.setSelected(false);
            // sort
            st.SortByRate(centre.search_field.getText(),centre.SearchDishBoolean,centre.searchDishResult);
            if(centre.SearchDishBoolean == false) {
                // Change the main frame
                centre.setSort(st.k);
                // Change the action of pre/next button
                centre.set_button(1, st.k, new ArrayList<>());
            } else {
                // change the main frame
                centre.setSortBySuperArray(st.result);
                // Change the action of pre/next button
                centre.set_button(2,new ArrayList<>(),st.result);
            }
        });
        sort2.setOnAction(e->{
            sort1.setSelected(false);
            sort2.setSelected(true);
            sort3.setSelected(false);
            sort4.setSelected(false);
            // sort
            st.SortByDistance(centre.search_field.getText());
            // Change the main frame
            centre.setSort(st.k);
        });
        sort3.setOnAction(e->{
            sort1.setSelected(false);
            sort2.setSelected(false);
            sort3.setSelected(true);
            sort4.setSelected(false);
            // sort
            st.SortByPrice(centre.searchDishResult,centre.SearchDishBoolean);
            // change the main frame
            centre.setSortBySuperArray(st.result);
            // Change the action of pre/next button
            centre.set_button(2,new ArrayList<>(),st.result);
        });
        sort4.setOnAction(e->{
            sort1.setSelected(false);
            sort2.setSelected(false);
            sort3.setSelected(false);
            sort4.setSelected(true);
            // sort
            st.SortByWaitingTime(centre.search_field.getText(),centre.SearchDishBoolean,centre.searchDishResult);
            if(centre.SearchDishBoolean == false) {
                // Change the main frame
                centre.setSort(st.k);
                // Change the action of pre/next button
                centre.set_button(1, st.k, new ArrayList<>());
            } else {
                // change the main frame
                centre.setSortBySuperArray(st.result);
                // Change the action of pre/next button
                centre.set_button(2,new ArrayList<>(),st.result);
            }
        });
    }
}
