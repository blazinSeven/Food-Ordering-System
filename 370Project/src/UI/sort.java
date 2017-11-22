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
         * Set actions for those ratio button
         * For each button there is a different action, will call different function
         */
        // Sort by rate
        sort1.setOnAction(e->{
            sort2.setSelected(false);
            sort3.setSelected(false);
            sort4.setSelected(false);
            centre.page_number = 0;
            // If start new search
            if (centre.SearchDishBoolean == false && centre.SearchRestaurantBoolean == false ){
                st.SortByRate(centre.search_field.getText(),centre.SearchDishBoolean,centre.searchDishResult);
                // Change the main frame
                centre.setSort(st.k);
                centre.setRate(st.rate,st.waiting_time);
                centre.clearAddress();
                // Change the action of pre/next button
                centre.set_button(1, st.k,st.rate,st.waiting_time, new ArrayList<>());
            }
            // If the input is restaurant
            else if(centre.SearchDishBoolean == false) {
                // sort
                st.SortByRate(centre.search_field.getText(),centre.SearchDishBoolean,centre.searchRestaurantResult);
                // change the main frame
                centre.setSortByDistace(centre.searchRestaurantResult);
                // Change the action of pre/next button
                centre.set_button(3,new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),centre.searchRestaurantResult);
            } else {    // else the input is dish
                // sort
                st.SortByRate(centre.search_field.getText(),centre.SearchDishBoolean,centre.searchDishResult);
                // change the main frame
                centre.setSortByDistace(centre.searchDishResult);
                // Change the action of pre/next button
                centre.set_button(3,new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),centre.searchDishResult);
            }
        });
        // Sort by distance
        sort2.setOnAction(e->{
            sort1.setSelected(false);
            sort3.setSelected(false);
            sort4.setSelected(false);
            centre.page_number = 0;
            // If start new search
            if (!centre.SearchDishBoolean && !centre.SearchRestaurantBoolean){
                // sort
                st.SortByDistance(centre.search_field.getText(),centre.searchDishResult,1);
                // Change the main frame
                centre.setSortByDistace(st.result);
                centre.set_button(3,new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),st.result);
            }
            // If the input is restaurant
            else if(centre.SearchDishBoolean == false) {
                // sort
                st.SortByDistance(centre.search_field.getText(),centre.searchRestaurantResult,1);
                System.out.println(centre.searchRestaurantResult);
                // change the main frame
                centre.setSortByDistace(centre.searchRestaurantResult);
                // Change the action of pre/next button
                centre.set_button(3,new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),centre.searchRestaurantResult);
            } else {    // else the input is dish
                // sort
                st.SortByDistance(centre.search_field.getText(),centre.searchDishResult,1);
                // change the main frame
                centre.setSortByDistace(centre.searchDishResult);
                // Change the action of pre/next button
                centre.set_button(3,new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),centre.searchDishResult);
            }
        });
        // Sort by price
        sort3.setOnAction(e->{
            sort1.setSelected(false);
            sort2.setSelected(false);
            sort4.setSelected(false);
            centre.page_number = 0;
            // sort
            st.SortByPrice(centre.searchDishResult,centre.SearchDishBoolean);
            // change the main frame
            centre.setSortByDistace(st.result);
            //centre.setSortBySuperArray(st.result);
            //centre.setRateSuper(st.result);
            // Change the action of pre/next button
            centre.set_button(3, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), st.result);
        });
        // Sort by waiting time
        sort4.setOnAction(e->{
            sort1.setSelected(false);
            sort2.setSelected(false);
            sort3.setSelected(false);
            centre.page_number = 0;
            // If start new search
            if (centre.SearchDishBoolean == false && centre.SearchRestaurantBoolean == false ){
                st.SortByWaitingTime(centre.search_field.getText(),centre.SearchDishBoolean,centre.searchDishResult);
                // Change the main frame
                centre.setSort(st.k);
                centre.setRate(st.rate,st.waiting_time);
                centre.clearAddress();
                // Change the action of pre/next button
                centre.set_button(1, st.k,st.rate,st.waiting_time, new ArrayList<>());
            }
            // If the input is restaurant
            else if(centre.SearchDishBoolean == false) {
                // sort
                st.SortByWaitingTime(centre.search_field.getText(),centre.SearchDishBoolean,centre.searchRestaurantResult);
                System.out.println(centre.searchRestaurantResult);
                // change the main frame
                centre.setSortByDistace(centre.searchRestaurantResult);
                // Change the action of pre/next button
                centre.set_button(3,new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),centre.searchRestaurantResult);
            } else {    // else the input is dish
                // sort
                st.SortByWaitingTime(centre.search_field.getText(),centre.SearchDishBoolean,centre.searchDishResult);
                // change the main frame
                centre.setSortByDistace(st.result);
                // Change the action of pre/next button
                centre.set_button(3,new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),st.result);
            }
        });
    }

}
