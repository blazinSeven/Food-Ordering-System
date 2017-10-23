package UI.Center_Frame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * Create By Hao Li at Oct. 22th
 */



public class lists {
    int num_image = 10;
    int pic_number = 0;
    Image k;
    Image[] restaurant = new Image[num_image];
    ImageView[]  res_image = new ImageView[restaurant.length];
    public lists(){
        getRestaurant();
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

    public void getRestaurant(){

        //if (id = pic_id)
        while (num_image!=0){
            for (int i = 0; i < restaurant.length/*should be number of pic/restaurant*/;i++){
                if (restaurant[i]==null&&pic_number<num_image){
                    k = new Image("/restaurant_pic/"+pic_number+".jpg");
                    restaurant[i] = k;
                }
                pic_number++;
            }
            num_image--;
        }
    }



}
