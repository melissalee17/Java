package assignment.pkg2;

import java.util.Scanner;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import javax.swing.*;
import java.awt.Font;
import java.text.NumberFormat;

public class Main
{
   Scanner S = new Scanner(System.in);
   public static CheckingAccount C;
   public static JFrame frame;
   public static DecimalFormat fmt = new DecimalFormat("$0.00");
   public static void main (String[] args)
   {
       // defines local variables
       double I_Balance;
       String I_Bal, message;
       //  get initial balance from the user
       I_Bal = JOptionPane.showInputDialog("Enter Your Initial Balance: ");
       I_Balance = Double.parseDouble(I_Bal);
       C = new CheckingAccount(I_Balance);
       frame = new JFrame ("Checking Account actions");
       frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
       OptionPanel panel = new OptionPanel();
       frame.getContentPane().add (panel);
       frame.pack();
       frame.setVisible(true);
   }
  
   public static int getTransCode()
   {
       String Trans_Code;
       Trans_Code = JOptionPane.showInputDialog ("Enter Transaction Code: ");
       return Integer.parseInt(Trans_Code);
   }
   public static double getTransAmt()
   {
       String Trans_Amt;
       Trans_Amt = JOptionPane.showInputDialog ("Enter Transaction Amount: ");
       return Double.parseDouble(Trans_Amt);
   }
   public static void completeTransaction()
   {
       frame.setVisible(false);
       int Trans_Code = getTransCode(), counter = C.gettransCount();
       String message;
       if (Trans_Code == 0)
       {
           message = "Transaction: End\n";
       
            if (C.getBalance() < 0)
            {
                message += "Current Balance: (" + fmt.format(Math.abs(C.getBalance())) + ")\n"
                    + "Total Service Charge: " + fmt.format(C.getServiceCharge()) + "\n"
                    + "Final Balance: (" + fmt.format(Math.abs(C.getBalance() - C.getServiceCharge())) + ")";
            }
            else {
                message += "Current Balance: " + fmt.format(C.getBalance()) + "\n"
                    + "Total Service Charge: " + fmt.format(C.getServiceCharge()) + "\n"
                    + "Final Balance: " + fmt.format(C.getBalance() - C.getServiceCharge());
            }    
        JOptionPane.showMessageDialog(null, message);
        frame.setVisible(true);
        return;
       }
       double Trans_Amt = getTransAmt();
       if (counter != 0)
           counter++;
       Transaction T = new Transaction(counter, Trans_Code, Trans_Amt);
       C.addTrans(T);
       C.setBalance(T);
       frame.setVisible(true);
   }
   public static void listTransaction()
   {
       frame.setVisible(false);
       NumberFormat money = NumberFormat.getCurrencyInstance();
       float costOfItems = 0.0f;
       String message = "List All Transactions\n\n" 
               + String.format("%-10s%-20s%10s\n", "ID", "Type", "Amount");
       int counter;
       Transaction T;
       for (counter = 0; counter <= C.gettransCount(); counter++)
       {
           T = C.getTrans(counter);
           switch (T.getTransId())
           {
               case 1:
                   message += String.format("%-10s%-20s%10s\n", T.getTransNumber(),
                           "Check", money.format(T.getTransAmount()));
                   break;
               case 2:
                   message += String.format("%-10s%-20s%10s\n", T.getTransNumber(),
                           "Deposit", money.format(T.getTransAmount()));
                   break;
               case 3:
                   message += String.format("%-10s%-20s%10s\n", T.getTransNumber(),
                           "Svc. Chrg.", money.format(T.getTransAmount()));
                   break;
               default:
                   message += String.format("%-10s%-20s%10s\n", T.getTransNumber(),
                           "Bad Value", money.format(T.getTransAmount()));
           }
       }
       JTextArea text = new JTextArea(message);
       text.setBorder(null);
       text.setOpaque(false);
       text.setFont(new Font("Monospaced", Font.PLAIN, 14));
       JOptionPane.showMessageDialog(null, text);
       frame.setVisible(true);
   }
   public static void listChecks()
   {
       frame.setVisible(false);
       NumberFormat money = NumberFormat.getCurrencyInstance();
       float costOfItems = 0.0f;
       String message = "Checks Cashed\n\n" + String.format("%-15s%10s\n", 
               "ID", "Amount");
       int counter;
       Transaction T;
       for (counter = 0; counter <= C.gettransCount(); counter++)
       {
           T = C.getTrans(counter);
           if (T.getTransId() == 1)
           {
               message += String.format("%-15s%10s\n", T.getTransNumber(),
                            money.format(T.getTransAmount()));
           }
       }
       JTextArea text = new JTextArea(message);
       text.setBorder(null);
       text.setOpaque(false);
       text.setFont(new Font("Monospaced", Font.PLAIN, 14));
       JOptionPane.showMessageDialog(null, text);
       frame.setVisible(true);
   }
   public static void listDeposits()
   {
       frame.setVisible(false);
       NumberFormat money = NumberFormat.getCurrencyInstance();
       float costOfItems = 0.0f;
       String message = "Deposits Made\n\n" + String.format("%-15s%10s\n", 
               "ID", "Amount");
       int counter;
       Transaction T;
       for (counter = 0; counter <= C.gettransCount(); counter++)
       {
           T = C.getTrans(counter);
           if (T.getTransId() == 2)
           {
               message += String.format("%-15s%10s\n", T.getTransNumber(),
                            money.format(T.getTransAmount()));
           }
       }
       JTextArea text = new JTextArea(message);
       text.setBorder(null);
       text.setOpaque(false);
       text.setFont(new Font("Monospaced", Font.PLAIN, 14));
       JOptionPane.showMessageDialog(null, text);
       frame.setVisible(true);
   }
}
