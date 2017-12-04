package Sort;

/**
 * Create by Yinsheng Dong Nov 27
 */

import java.sql.Time;
import java.util.Comparator;

public class SortInfo implements Comparable {
    private float distance = 0;
    private int rest_id = 0;
    private String rest_name = "";
    private String rest_address = "";
    private Time rest_time = null;
    private float rest_rate = 0;
    private float rest_avg_price = 0;


    public float getRest_avg_price()
    {
        return rest_avg_price;
    }

    public void setRest_avg_price(float rest_avg_price)
    {
        this.rest_avg_price = rest_avg_price;
    }
    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getRest_id() {
        return rest_id;
    }

    public void setRest_id(int rest_id) {
        this.rest_id = rest_id;
    }

    public String getRest_name() {
        return rest_name;
    }

    public void setRest_name(String rest_name) {
        this.rest_name = rest_name;
    }

    public String getRest_address() {
        return rest_address;
    }

    public void setRest_address(String rest_address) {
        this.rest_address = rest_address;
    }

    public Time getRest_time() {
        return rest_time;
    }

    public void setRest_time(Time rest_time) {
        this.rest_time = rest_time;
    }

    public float getRest_rate() {
        return rest_rate;
    }

    public void setRest_rate(float rest_rate) {
        this.rest_rate = rest_rate;
    }

    @Override
    public String toString()
    {
        return getRest_id() +" " + getRest_name()+" "+ getRest_address()+" "+getRest_time()+ " "+getRest_rate()+ " "+getDistance()+
                " "+ getRest_avg_price();
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public static Comparator<SortInfo> distanceComparator = new Comparator<SortInfo>() {
        @Override
        public int compare(SortInfo o1, SortInfo o2) {
            Float d1 = o1.getDistance();
            Float d2 = o2.getDistance();
            return d1.compareTo(d2);
        }
    };

    public static Comparator<SortInfo> rateComparator = new Comparator<SortInfo>() {
        @Override
        public int compare(SortInfo o1, SortInfo o2) {
            Float r1 = o1.rest_rate;
            Float r2 = o2.rest_rate;
            return r1.compareTo(r2);
        }
    };

    public static Comparator<SortInfo> priceComparator = new Comparator<SortInfo>() {
        @Override
        public int compare(SortInfo o1, SortInfo o2) {
            Float p1 = o1.rest_avg_price;
            Float p2 = o2.rest_avg_price;
            return p1.compareTo(p2);
        }
    };

    public static Comparator<SortInfo> timeComparator = new Comparator<SortInfo>() {
        @Override
        public int compare(SortInfo o1, SortInfo o2) {
            Time t1 = o1.rest_time;
            Time t2 = o2.rest_time;
            return t1.compareTo(t2);
        }
    };

    public SortInfo(int rest_id, String rest_name, String rest_address, Time rest_time, float rest_rate, float rest_avg_price)
    {
        this.rest_id = rest_id;
        this.rest_name = rest_name;
        this.rest_address = rest_address;
        this.rest_rate = rest_rate;
        this.rest_avg_price = rest_avg_price;
        this.rest_time = rest_time;
    }

}
