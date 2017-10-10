package Search;

public class menu {

     class Restaurant {
        String name;
        class Food {
            String name;
            double price;
            int rate;
        }
        Food a = new Food();
        Food b = new Food();
        Food c = new Food();
        Food d = new Food();
    }



    Restaurant r1 = new Restaurant();
    Restaurant r2 = new Restaurant();
    Restaurant r3 = new Restaurant();
    Restaurant r4 = new Restaurant();
    Restaurant r5 = new Restaurant();
    Restaurant r6 = new Restaurant();
    Restaurant r7 = new Restaurant();
    Restaurant r8 = new Restaurant();
    Restaurant r9 = new Restaurant();
    Restaurant r10 = new Restaurant();




    public menu(){


        // restaurant 1
        r1.name = "McDonalds";

        r1.a.name = "Burger";
        r1.a.price = 7.5;
        r1.a.rate = 5;

        r1.b.name = "Chicken nuggets";
        r1.b.price = 6;
        r1.b.rate = 4;

        r1.c.name = "Poutine";
        r1.c.price = 6.5;
        r1.c.rate = 3;

        r1.d.name = "Wraps";
        r1.d.price = 8;
        r1.d.rate = 4;





        // restaurant 2
        r2.name = "KFC";
        // Cheaper and bad rate
        r2.a.name = "Burger";
        r2.a.price = 6;
        r2.a.rate = 3;

        r2.b.name = "Deep Fried Chicken";
        r2.b.price = 6;
        r2.b.rate = 5;

        r2.c.name = "Fries";
        r2.c.price = 4;
        r2.c.rate = 5;

        r2.d.name = "Salad";
        r2.d.price = 2.5;
        r2.d.rate = 4;





        // restaurant 3
        r3.name = "Asian Garden";

        r3.a.name = "Fried Rice";
        r3.a.price = 7.75;
        r3.a.rate = 4;

        r3.b.name = "Ginger Beef";
        r3.b.price = 12;
        r3.b.rate = 4;

        r3.c.name = "Chicken Wings";
        r3.c.price = 10;
        r3.c.rate = 5;

        r3.d.name = "Vermicelli Noodle";
        r3.d.price = 10;
        r3.d.rate = 5;





        // restaurant 4
        r4.name = "Delight+";

        r4.a.name = "Tofu";
        r4.a.price = 9;
        r4.a.rate = 4;
        // more expensive but good rate
        r4.b.name = "Fried Rice";
        r4.b.price = 8;
        r4.b.rate = 5;
        // more expensive and bad rate
        r4.c.name = "Vermicelli Noodle";
        r4.c.price = 11;
        r4.c.rate = 3;

        r4.d.name = "Chow Mein";
        r4.d.price = 8.25;
        r4.d.rate = 4;





        // restaurant 5
        r5.name = "Taco Time";

        r5.a.name = "Burritos";
        r5.a.price = 5.99;
        r5.a.rate = 4;
        // cheaper and good rate
        r5.b.name = "Wrapss";
        r5.b.price = 6.99;
        r5.b.rate = 5;

        r5.c.name = "Tacos";
        r5.c.price = 6.5;
        r5.c.rate = 5;

        r5.d.name = "Salad";
        r5.d.price = 10;
        r5.d.rate = 3;




        // restaurant 6
        r6.name = "Burger King";

        r6.a.name = "Burger";
        r6.a.price = 12;
        r6.a.rate = 5;

        r6.b.name = "Salad";
        r6.b.price = 9;
        r6.b.rate = 2;

        r6.c.name = "Onion Rings";
        r6.c.price = 5;
        r6.c.rate = 3;

        r6.d.name = "Poutine";
        r6.d.price = 9;
        r6.d.rate = 4;




        // restaurant 7
        r7.name = "Taco Bell";

        r7.a.name = "Burritos";
        r7.a.price = 7.99;
        r7.a.rate = 3;

        r7.b.name = "Tacos";
        r7.b.price = 8.75;
        r7.b.rate = 4;

        r7.c.name = "Fries";
        r7.c.price = 6.99;
        r7.c.rate = 2;

        r7.d.name = "Pizza";
        r7.d.price = 10;
        r7.d.rate = 3;




        // restaurant 8
        r8.name = "Pizza Hut";

        r8.a.name = "Pizza";
        r8.a.price = 14.99;
        r8.a.rate = 5;

        r8.b.name = "Chicken Wings";
        r8.b.price = 8.99;
        r8.b.rate = 3;

        r8.c.name = "Breadsticks";
        r8.c.price = 4;
        r8.c.rate = 3;

        r8.d.name = "PanAlicious";
        r8.d.price = 7;
        r8.d.rate = 4;




        // restaurant 9
        r9.name = "Boston Pizza";

        r9.a.name = "Chicken Wings";
        r9.a.price = 7.99;
        r9.a.rate = 4;

        r9.b.name = "Pizza";
        r9.b.price = 12.99;
        r9.b.rate = 4;

        r9.c.name = "Pasta";
        r9.c.price = 8.7;
        r9.c.rate = 2;

        r9.d.name = "Burger";
        r9.d.price = 8.99;
        r9.d.rate = 4;




        // restaurant 10
        r10.name = "Mings Kitchen";

        r10.a.name = "Fried Rice";
        r10.a.price = 6.65;
        r10.a.rate = 3;

        r10.b.name = "Chow Mein";
        r10.b.price = 7.75;
        r10.b.rate = 5;

        r10.c.name = "Ginger beef";
        r10.c.price = 12;
        r10.c.rate = 4;

        r10.d.name = "Deep Fried Shrimp";
        r10.d.price = 14;
        r10.d.rate = 5;





    }
}
