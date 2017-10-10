package Search;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class test extends JFrame {

    public test(){
        setSize(300,400);
        setTitle("hehe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
    }



    public static void main() {

        // Basic


        // Basic option panel
        //JOptionPane.showMessageDialog(null,"nimabi!");


        // Gather import from user
        //String value = JOptionPane.showInputDialog("123", "hehe");
        //System.out.println(value);


        // Gather import from user
        //String value = JOptionPane.showInputDialog(null,"123" );
        //System.out.println(value);


        // Show the message to the user
        //JOptionPane.showMessageDialog(null,"123","456",JOptionPane.ERROR_MESSAGE);


        // down-pull menu
        //String D = (String) JOptionPane.showInputDialog(null,"Select the next Option",
        //            "Option selection", JOptionPane.QUESTION_MESSAGE,null,
        //            new String[]{"Balance","Deposit","Exit"}, "Exit");
        //System.out.println(D);




        JFrame haha = new test();



        // JComponent

        JButton hi = new JButton("hi");
        JButton feedback = new JButton("what you write");

        JLabel label = new JLabel("hello!");

        JTextField textfield = new JTextField(10);
        textfield.setText("" + 5.0);
        // Double a = parseDouble(textfield.getText());

        JTextArea area = new JTextArea();
        area.setText("" + 6.0);
        area.append("hehe");




        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        panel.add(hi);
        panel.add(feedback);
        panel.add(label);
        haha.add(panel);
        panel2.add(textfield);
        panel2.add(area);
        haha.add(panel2);



        // Listener
        class HiListener implements ActionListener{
            public void actionPerformed(ActionEvent event){
                JOptionPane.showMessageDialog(null,"click nimabi!");
            }
        }


        ActionListener listener_to_hi = new HiListener();
        hi.addActionListener(listener_to_hi);

        class FeedbackListener implements ActionListener{
            public void actionPerformed(ActionEvent event){
                JOptionPane.showMessageDialog(null,textfield.getText());
            }
        }

        ActionListener listener_to_feedback = new FeedbackListener();
        feedback.addActionListener(listener_to_feedback);





        haha.setVisible(true);      // Must be at last

















    }
}
