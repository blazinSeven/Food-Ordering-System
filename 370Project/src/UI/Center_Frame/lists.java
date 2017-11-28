package UI.Center_Frame;

import Search_Sort.SortByOrdering;
import Search_Sort.SortByWaitingTime;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Create By Hao Li at Oct. 22th
 * Refactor By Hao Li at Nov.28th
 */



public class lists {
    int index = 10;
    int pic_number = 0;

    Image k;
    Image[] restaurant;
    ImageView[]  res_image;
    Label[] Address;
    Label[] Rate;
    int[] r_id;
    SortByOrdering sbt = new SortByOrdering();



    /**
     * Put the pictures in Image[] into ImageView[]
     */
    public lists(){
        sbt.sortByWaitingTime();
        index = sbt.sortByOrderingRestId.size();
        getRestaurant(sbt.sortByOrderingRestId);
        getaddress(sbt.sortByOrderingAddress);
        getRate(sbt.sortByOrderingRate);

    }



    /**
     * Put all the pictures from folder into Image[]
     * where id = pic_id
     */
    public void getRestaurant(ArrayList<Integer> res){

        restaurant  = new Image[index];
        res_image = new ImageView[restaurant.length];
        r_id = new int[restaurant.length];
        int temp_r = index;
        while (temp_r !=0){
            for (int i = 0; i < res.size();i++){
                r_id[i] = res.get(i);
                if (restaurant[i]==null){
                    k = new Image("/restaurant_pic/"+res.get(i)+".jpg");
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

    void getaddress(ArrayList address){
        Address = new Label[index];
        int temp_address = index;
        while (temp_address!=0){
            for (int i=0;i<address.size();i++){
                Label temp_l = new Label();
                temp_l.setText(address.get(i).toString());
                temp_l.setAlignment(Pos.CENTER);
                temp_l.setStyle("-fx-background-image: url(/pictures/add_background.jpg)");
                temp_l.setPrefSize(260,110);
                Address[i] = temp_l;
            }
            temp_address--;
        }

    }

    void getRate(ArrayList rate){
        Rate = new Label[index];
        int temp_rate = index;
        while (temp_rate!=0) {
            for (int i=0;i<rate.size();i++){
                Label temp_r = new Label();
                temp_r.setText(""+rate.get(i));
                temp_r.setAlignment(Pos.CENTER);
                temp_r.setStyle("-fx-background-image: url(/pictures/rate_background.jpg)");
                temp_r.setPrefSize(260,110);
                Rate[i]=temp_r;
            }
            temp_rate--;

        }
    }



}
