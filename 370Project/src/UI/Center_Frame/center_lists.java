package UI.Center_Frame;

import Sort.SortByOrdering;
import Sort.SortInfo;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Create By Hao Li at Oct. 22th
 * Refactor By Hao Li at Nov.28th
 */



public class center_lists {
    int index = 10;
    int pic_number = 0;

    Image k;
    Image[] restaurant;
    ImageView[]  res_image;
    Label[] Address;
    Label[] Rate;
    int[] r_id;
    SortByOrdering sbt = new SortByOrdering("Saskatoon","SK");



    /**
     * Put the pictures in Image[] into ImageView[]
     */
    public center_lists(){
        index = sbt.getSortByOrderWithoutLogin().size();
        getRestaurant(sbt.getSortByOrderWithoutLogin());
        getaddress(sbt.getSortByOrderWithoutLogin());
        getRate(sbt.getSortByOrderWithoutLogin());

    }



    /**
     * Put all the pictures from folder into Image[]
     * where id = pic_id
     */
    public void getRestaurant(ArrayList<SortInfo> res){

        restaurant  = new Image[index];
        res_image = new ImageView[restaurant.length];
        r_id = new int[restaurant.length];
        int temp_r = index;
        while (temp_r !=0){
            for (int i = 0; i < res.size();i++){
                r_id[i] = res.get(i).getRest_id();
                if (restaurant[i]==null){
                    k = new Image("/restaurant_pic/"+res.get(i).getRest_id()+".jpg");
                    restaurant[i] = k;
                }
                pic_number++;
            }
            temp_r--;
        }
        // Now Image[] is fulfilled with pictures
        for (Image aRestaurant : restaurant) {
            for (int n = 0; n < res_image.length; n++) {
                if (res_image[n] == null) {
                    ImageView temp = new ImageView(aRestaurant);
                    temp.setFitWidth(260);
                    temp.setFitHeight(110);
                    res_image[n] = temp;
                    break;
                }
            }
        }

    }

    void getaddress(ArrayList<SortInfo> address){
        Address = new Label[index];
        int temp_address = index;
        while (temp_address!=0){
            for (int i=0;i<address.size();i++){
                Label temp_l = new Label();
                temp_l.setText(address.get(i).getRest_address());
                temp_l.setAlignment(Pos.CENTER);
                temp_l.setStyle("-fx-background-image: url(/pictures/cornsilk.png)");
                temp_l.setPrefSize(260,110);
                Address[i] = temp_l;
            }
            temp_address--;
        }

    }

    void getRate(ArrayList<SortInfo> rate){
        Rate = new Label[index];
        int temp_rate = index;
        while (temp_rate!=0) {
            for (int i=0;i<rate.size();i++){
                Label temp_r = new Label();
                temp_r.setText(""+rate.get(i).getRest_rate());
                temp_r.setAlignment(Pos.CENTER);
                temp_r.setStyle("-fx-background-image: url(/pictures/lightcyan.png)");
                temp_r.setPrefSize(260,110);
                Rate[i]=temp_r;
            }
            temp_rate--;

        }
    }



}
