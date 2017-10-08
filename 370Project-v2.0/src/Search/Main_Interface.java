package Search;


import javax.swing.*;
import java.awt.*;

public class Main_Interface extends JFrame{

    public Main_Interface(){
        setSize(1000,800);
        setTitle("F.O.S");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
    }


    public JPanel toop(){
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        JLabel label = new JLabel("F.O.S");
        label.setFont(new Font("Calibri",Font.BOLD,40));

        JButton submit = new JButton("submit");

        JTextField bottomLeft = new JTextField(10);
        JLabel bottomRight = new JLabel("results");
        JPanel b = new JPanel();
        b.add(bottomLeft);
        b.add(bottomLeft);

        JTextField tf = new JTextField(10);
        tf.setText("What you want to eat today?");
        //tf.setColumns(600);
        //tf.setPreferredSize(new Dimension(100,10));
        top.add(label,BorderLayout.WEST);
        top.add(tf);
        top.add(submit,BorderLayout.EAST);
        top.add(b,BorderLayout.SOUTH);
        top.setBackground(Color.blue);
        top.setPreferredSize(new Dimension(1000,100));
        return top;
    }

    public static void main(String[] args) {

        //test.main();



        Main_Interface Stg = new Main_Interface();

        Stg.add(Stg.toop());






        Stg.setVisible(true);
    }





    }
