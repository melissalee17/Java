package assignment.pkg3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OptionPanel extends JPanel
{
    private JLabel prompt;
    private JRadioButton one, two, three, four, five;
    public OptionPanel()
    {
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        prompt = new JLabel("Choose Action:");
        prompt.setFont(new Font ("Helvetica", Font.BOLD, 24));
        prompt.setPreferredSize(new Dimension(400, 30));
        one = new JRadioButton ("Enter Transaction");
        one.setBackground(Color.YELLOW);
        two = new JRadioButton ("List All Transactions");
        two.setBackground(Color.yellow);
        three = new JRadioButton ("List All Checks");
        three.setBackground(Color.yellow);
        four = new JRadioButton ("List All Deposits");
        four.setBackground(Color.yellow);
        five = new JRadioButton ("List All Service Charges");
        five.setBackground(Color.yellow);
        ButtonGroup group = new ButtonGroup();
        group.add(one);
        group.add(two);
        group.add(three);
        group.add(four);
        group.add(five);
        EOptionListener listener = new EOptionListener();
        one.addActionListener (listener);
        two.addActionListener (listener);
        three.addActionListener (listener);
        four.addActionListener(listener);
        five.addActionListener(listener);
        add(prompt);
        add (one);
        add (two);
        add (three);
        add(four);
        add (five);
        setBackground (Color.yellow);
        setPreferredSize (new Dimension(400, 130));
    }
    private class EOptionListener implements ActionListener
   {
      
      public void actionPerformed (ActionEvent event)
      {
         Object source = event.getSource();
 
         if (source == one)
         {
             Main.completeTransaction();
         }
         else if (source == two)
        {
            Main.listTransaction();
        }
         else if (source == three)
         {
             Main.listChecks();
         }
         else if (source == four)
         {
             Main.listDeposits();
         }
         else 
         {
             Main.listServiceCharges();
         }
      }
   }
}