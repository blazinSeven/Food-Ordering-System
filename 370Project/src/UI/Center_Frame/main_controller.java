package UI.Center_Frame;


import Sort.SortByDistanceWithLogin;
import Sort.SortByPrice;
import Sort.SortByRating;
import Sort.SortByWaitingTime;
import UI.Checkout.Orders;
import database.SearchRestaurants;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Create By Hao Li at Oct. 22th
 * Modified by Yuecheng Rong
 * Refactor By Hao Li at Nov.28th
 */

public class main_controller extends HBox {
    SortByWaitingTime sbt = new SortByWaitingTime("Saskatoon","SK");
    SortByRating sbr = new SortByRating("Saskatoon","SK");
    SortByPrice sbp = new SortByPrice("Saskatoon","SK");
    SearchRestaurants sr = new SearchRestaurants();
    public menus menus = new menus();
    public Orders orders = new Orders();
    public user_account user_account = new user_account();
    // for search use
    public TextField search_field = new TextField();
    // Label blank = new Label("                           ");
    //Button searchDish = new Button("Dish");
    Button search = new Button("SEARCH");
    Button reset = new Button("RESET");

    CheckBox s_w = new CheckBox();
    CheckBox s_a = new CheckBox();

    CheckBox s_p = new CheckBox();


    //SearchDishes searchDishes = new SearchDishes();

    public center_lists res_center_lists = new center_lists();
    Button pre = new Button("Previous");
    Button next = new Button("Next");
    Label name = new Label("Restaurants");
    Button rate = new Button("Rate");
    Label address = new Label("Address");


    VBox restaurant_list;
    VBox rate_list;
    VBox address_list;
    int page_number = 0;

    private ArrayList<Label> ordered_food = new ArrayList<>();
    private ArrayList<Integer> ordered_num = new ArrayList<>();
    private boolean duplication_check = false;

    // main accessing for all main list
    public main_controller(){
        setPrefSize(620,450);
        getChildren().addAll(res_name(),res_rate(),res_address());
        setPadding(new Insets(10,10,10,10));
        setResListener();
        orders.checkout.setOnAction(e->{
            orders.orders();
            orders.place_order.UpdateChose(ordered_food);
            orders.place_order.Updatenumber(ordered_num);
        });
        setStyle("-fx-background-image: url(/pictures/background.jpg)");
    }

    /**
     * Following three functions are the three vertical center_lists appear at the right of the main_frame
     * @return
     */
    // main layout for restaurant name list
    private VBox res_name(){
        restaurant_list = new VBox();
        restaurant_list.setPrefSize(260,450);
        name.setPrefSize(260,30);
        name.setAlignment(Pos.CENTER);
        name.setStyle("-fx-background-color: lightsteelblue");
        restaurant_list.getChildren().add(name);
        for (int i = page_number; i< res_center_lists.res_image.length; i++){
            if (res_center_lists.res_image[i]!=null&&restaurant_list.getChildren().size()<5){
                restaurant_list.getChildren().add(res_center_lists.res_image[i]);
            }
        }

        return restaurant_list;
    }


    // main layout for restaurant rate list
    private VBox res_rate(){
        rate_list = new VBox();
        rate_list.setPrefSize(140,450);

        rate.setPrefSize(140,30);
        rate.setAlignment(Pos.CENTER);
        rate.setStyle("-fx-background-color: lightsteelblue");


        rate_list.getChildren().addAll(rate);
        for (int i = 0; i< res_center_lists.Rate.length; i++){
            if (res_center_lists.Rate[i]!=null && rate_list.getChildren().size()<5){
                rate_list.getChildren().add(res_center_lists.Rate[i]);
            }
        }
        return rate_list;
    }



    // main layout for restaurant address list
    private VBox res_address(){
        address_list = new VBox();
        address_list.setPrefSize(200,450);

        address.setPrefSize(200,30);
        address.setAlignment(Pos.CENTER);
        address.setStyle("-fx-background-color: lightsteelblue");
       // address_list.setStyle("-fx-background-color: deepskyblue");

        address_list.getChildren().add(address);
        for (int i = 0; i< res_center_lists.Address.length; i++){
            if(res_center_lists.Address[i]!=null && address_list.getChildren().size()<5){
                address_list.getChildren().add(res_center_lists.Address[i]);
            }
        }
        return address_list;
    }


    /**
     * Search Function
     *
     * @return
     */
    public VBox search(){
        VBox s_box = new VBox();
        setPrefSize(200,60);
        s_box.getChildren().addAll(search_field,s_holder());
        return s_box;
    }

    private HBox s_holder(){
        HBox holder = new HBox();
        //listener for search button and search field
        /**
         * Same thing can be done when ENTER is pressed
         */
        search_field.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode()==KeyCode.ENTER) {
                    if(!search_field.getText().isEmpty()){
                        sr.SearchRestaurant(search_field.getText().toUpperCase());
                        if (sr.message.equals("Found")){
                            setsearch(sr.res_id,sr.res_address,sr.rate);
                        }
                    }

                }
            }
        });
        search.setOnAction(e->{
            if(!search_field.getText().isEmpty()){
                sr.SearchRestaurant(search_field.getText().toUpperCase());
                if (sr.message.equals("Found")){
                    setsearch(sr.res_id,sr.res_address,sr.rate);
                }
            }
        });
        reset.setOnAction(e->{
            search_field.clear();
            rate_list.getChildren().clear();
            address_list.getChildren().clear();
            setLists();

        });
        //reset.setPrefWidth(100);
        Label blank = new Label("           ");
        holder.getChildren().addAll(reset, blank,search);
        return holder;
    }





    /**
     * Preview/Next Button
     *
     * @return
     */
    public HBox main_Button(){
        HBox button_box = new HBox();
        button_box.getStylesheets().add("css/page.css");
        set_button();
        button_box.getChildren().addAll(pre,next);
        return button_box;
    }
    // Edited: input has been changed
    private void set_button(){

        pre.setPrefSize(90,30);
        next.setPrefSize(90,30);
        pre.setOnAction(e->{
            if (page_number>=4){
                page_number=page_number-4;
                setLists();
            }
            else {
                System.out.println("pre");
            }
        });
        next.setOnAction(e->{
            if ((page_number+4)<restaurant_list.getChildren().size()){
                page_number=page_number+4;
                setLists();
            }
            else {
                System.out.println("next");
            }
        });

    }


    /**
     * Helper functions
     */

    private void setLists(){
        restaurant_list.getChildren().clear();
        rate_list.getChildren().clear();
        address_list.getChildren().clear();
        restaurant_list.getChildren().add(name);
        for (int i = page_number; i< res_center_lists.res_image.length; i++){
            if (res_center_lists.res_image[i]!=null&&restaurant_list.getChildren().size()<5){
                restaurant_list.getChildren().add(res_center_lists.res_image[i]);
            }
        }
        address_list.getChildren().add(address);
        for (int i = page_number; i< res_center_lists.Address.length; i++){
            if(res_center_lists.Address[i]!=null && address_list.getChildren().size()<5){
                address_list.getChildren().add(res_center_lists.Address[i]);
            }
        }
        rate_list.getChildren().add(rate);
        for (int i = page_number; i< res_center_lists.Rate.length; i++){
            if (res_center_lists.Rate[i]!=null && rate_list.getChildren().size()<5){
                rate_list.getChildren().add(res_center_lists.Rate[i]);
            }
        }
        setResListener();

    }

    public VBox setsort(){
        VBox sort_hold = new VBox();
        s_a.setText("Sort By Rating");
        user_account.signedIn.s_d.setText("Sort By Distance");
        s_p.setText("Sort By Price");
        s_w.setText("Sort By Waiting Time");
        s_a.getStylesheets().add("css/checkbox.css");
        s_w.getStylesheets().add("css/checkbox.css");
        user_account.signedIn.s_d.getStylesheets().add("css/checkbox.css");
        s_p.getStylesheets().add("css/checkbox.css");

        s_p.setOnAction(e->{
            if (s_p.isSelected()) {
                s_a.setSelected(false);
                s_w.setSelected(false);

                user_account.signedIn.s_d.setSelected(false);

                res_center_lists.getaddress(sbp.getSortByPriceWithoutLogin());
                res_center_lists.getRate(sbp.getSortByPriceWithoutLogin());
                res_center_lists.getRestaurant(sbp.getSortByPriceWithoutLogin());
                setLists();
                setResListener();
            }else {
                s_p.setSelected(false);
            }
        });

        s_w.setOnAction(e->{
            if (s_w.isSelected()) {
                s_a.setSelected(false);
                s_p.setSelected(false);
                user_account.signedIn.s_d.setSelected(false);

                res_center_lists.getaddress(sbt.getSortByWaitingTimeWithoutLogin());
                res_center_lists.getRate(sbt.getSortByWaitingTimeWithoutLogin());
                res_center_lists.getRestaurant(sbt.getSortByWaitingTimeWithoutLogin());
                setLists();
                setResListener();
            }else {
                s_w.setSelected(false);
            }
        });

        s_a.setOnAction(e->{
            if (s_a.isSelected()){
                s_w.setSelected(false);
                s_p.setSelected(false);
                user_account.signedIn.s_d.setSelected(false);
                res_center_lists.getaddress(sbr.getSortByRatingWithoutLogin());
                res_center_lists.getRate(sbr.getSortByRatingWithoutLogin());
                res_center_lists.getRestaurant(sbr.getSortByRatingWithoutLogin());
                setLists();
                setResListener();
            }
            else{
                s_a.setSelected(false);
            }

        });

        user_account.signedIn.s_d.setOnAction(e->{
            if (user_account.signedIn.s_d.isSelected()){
                s_w.setSelected(false);
                s_p.setSelected(false);
                s_a.setSelected(false);
                SortByDistanceWithLogin sbd = new SortByDistanceWithLogin(user_account.signedIn.u_id.user_id,"Saskatoon","SK");
                res_center_lists.getaddress(sbd.getDistanceSort());
                res_center_lists.getRate(sbd.getDistanceSort());
                res_center_lists.getRestaurant(sbd.getDistanceSort());
                setLists();
                setResListener();

            }
            else {
                user_account.signedIn.s_d.setSelected(false);
            }
        });

        //restaurant_list.getChildren().add(res_center_lists.res_image[n]);
        sort_hold.setPrefSize(100,70);
        sort_hold.getChildren().addAll(s_a,s_p,user_account.signedIn.s_d,s_w);
        return sort_hold;
    }

    private void setsearch(int n, String add, float s_rate){
        Label temp_add = new Label();
        Label temp_rate = new Label();
        for (int i = 0; i< res_center_lists.Rate.length; i++){
            if (Objects.equals(res_center_lists.Rate[i].getText(), ""+s_rate)){
                temp_rate = res_center_lists.Rate[i];
            }
        }
        for (int i = 0; i< res_center_lists.Address.length; i++){
            if (Objects.equals(res_center_lists.Address[i].getText(), add)){
                temp_add = res_center_lists.Address[i];
            }
        }

        ImageView temp_res = new ImageView(new Image("/restaurant_pic/"+n+".jpg"));
        temp_res.setFitWidth(260);
        temp_res.setFitHeight(110);

        restaurant_list.getChildren().clear();
        rate_list.getChildren().clear();
        address_list.getChildren().clear();

        restaurant_list.getChildren().add(name);
        address_list.getChildren().add(address);
        rate_list.getChildren().add(rate);

        restaurant_list.getChildren().add(temp_res);
        address_list.getChildren().add(temp_add);
        rate_list.getChildren().add(temp_rate);
        setResListener();
    }

    private void setResListener(){
        for (int i = 0; i< res_center_lists.res_image.length; i++){
            int finalI = i;
            res_center_lists.res_image[i].setOnMousePressed((MouseEvent e) -> {
                menus.menus(res_center_lists.r_id[finalI]);
                set();
            });

        }
    }

    private void set(){
        for (int i=0;i<menus.items.length;i++){
            int finalI = i;
            menus.items[i].getButton().setOnAction(e->{
                check_number(menus.items[finalI].getLabel());
                if (!duplication_check){
                    Label temp = new Label(menus.items[finalI].getLabel().getText());
                    ordered_food.add(temp);
                    ordered_num.add(1);
                }
            });

        }
    }

    private void check_number(Label label){
        for (int i=0;i<ordered_food.size();i++){
            if (label.getText().equals(ordered_food.get(i).getText())){
                int temp  = ordered_num.get(i)+1;
                ordered_num.remove(i);
                ordered_num.add(i,temp);
                duplication_check = true;
            }
            else {
                duplication_check = false;
            }
        }

    }

}
