package Search_Sort;

public class DistanceItem {
    int id;
    float length;
    DistanceItem(int u_id,float u_length){
        id = u_id;
        length = u_length;
    }
    public void setId(int s_id){
        id = s_id;
    }

    public int getId(){
        return id;
    }

    public void setLength(float s_length){
        length=s_length;
    }
    public float getLength(){
        return length;
    }
}
