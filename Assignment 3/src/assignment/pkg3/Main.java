package assignment.pkg3;

import java.awt.BorderLayout;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import javax.swing.*;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.NumberFormat;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;


public class Main
{
   Scanner S = new Scanner(System.in);
   public static CheckingAccount C;
   public static boolean flag;
   public static JFrame frame;
   public static DecimalFormat fmt = new DecimalFormat("$0.00");
   public static void main (String[] args)
   {
       // defines local variables
       double I_Balance;
       String I_Bal, message, User_Name;
       flag = true;
       User_Name = JOptionPane.showInputDialog("Enter Your Account Name: ");
       I_Bal = JOptionPane.showInputDialog("Enter Your Initial Balance: ");
       I_Balance = Double.parseDouble(I_Bal);
       C = new CheckingAccount(User_Name, I_Balance);
       frame = new JFrame ("Checking Account actions");
       frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
       OptionPanel panel = new OptionPanel();
       frame.getContentPane().setLayout(new BorderLayout());
       frame.getContentPane().add (panel);
       frame.pack();
       frame.setVisible(true);
   }
  
   public static int getTransCode()
   {
       String Trans_Code;
       Trans_Code = JOptionPane.showInputDialog ("Enter Transaction Code "
               + "('1' for check, '2' for deposit, '0' for end of session): ");
       return Integer.parseInt(Trans_Code);
   }
   public static double getTransAmt(int Trans_Code)
   {
       String Trans_Amt;
       if (Trans_Code == 1)
           Trans_Amt = JOptionPane.showInputDialog ("Enter Check Transaction Amount: ");
       
       else 
           Trans_Amt = JOptionPane.showInputDialog("Enter Transaction Amount: ");
       return Double.parseDouble(Trans_Amt);
   }
   public static void processCheck(Check Ch, int counter)
   {
       String message = C.getName() + "'s account-\n" + "Transaction: Check #" 
               + Ch.getCheckNumber() + " in amount of " + fmt.format(Ch.getTransAmount()) + "\n";
       C.setBalance(C.getBalance() - Ch.getTransAmount());
       C.setServiceCharge(C.getServiceCharge() + 0.15);
       counter++;
       ServiceCharge SC = new ServiceCharge(counter,3, 0.15);
       C.addTrans(SC);
       if(C.getBalance() < 0)
       {
           message += "Current Balance: (" + fmt.format(Math.abs(C.getBalance())) + 
               ")\n Service Charge: Check -- charge $0.15\n";
       }
       else {
            message += "Current Balance: " + fmt.format(C.getBalance()) + 
               "\n Service Charge: Check -- charge $0.15\n";
       }
       if (C.getBalance() < 500 && flag)
       {
           C.setServiceCharge(C.getServiceCharge() + 5.00);
           counter++;
           SC = new ServiceCharge(counter,3,5.00);
           C.addTrans(SC);
           message += "Service Charge: Below $500 -- charge $5.00\n";
           flag = false;
       }
       if (C.getBalance() < 50)
       {
           message += "Warning: Balance below $50.00\n";
       }
       if (C.getBalance() < 0)
       {
           C.setServiceCharge(C.getServiceCharge() + 10.00);
           counter++;
           SC = new ServiceCharge(counter,3,10.00);
           C.addTrans(SC);
           message += "Service Charge: Below $0 -- charge $10.00\n";
       }
       message += "Total Service Charge: " + fmt.format(C.getServiceCharge());
       JOptionPane.showMessageDialog(null, message);
   }
   public static void processDeposit(Deposit D, int counter)
   {
       String message = C.getName() + "'s account-\n" +"Transaction: Deposit in amount of " 
               + fmt.format(D.getTransAmount()) + "\n";
       C.setBalance(D.getTransAmount() + C.getBalance());
       counter++;
       C.setServiceCharge(C.getServiceCharge()+ 0.10);
       ServiceCharge SC = new ServiceCharge(counter, 3, 0.10);
       C.addTrans(SC);
       if (C.getBalance() < 0)
       {
           message += "Current Balance: (" + fmt.format(Math.abs(C.getBalance())) + ")\n";
       }
       else {
       message += "Current Balance: " + fmt.format(C.getBalance()) + "\n";
       }
       message += "Service Charge: Deposit -- charge $0.10\n" + 
               "Total Service Charge: " + fmt.format(C.getServiceCharge());
       JOptionPane.showMessageDialog(null, message);
   }
   public static void completeTransaction()
   {
       frame.setVisible(false);
       int Trans_Code = getTransCode(), counter = C.gettransCount();
       String message;
       double Trans_Amt;
       if (Trans_Code == 0)
       {
           message = C.getName() + "'s account-\n Transaction: End\n";
       
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
//       if (counter != 0)
//           counter++;
       if (Trans_Code == 1)
       {
           String Check_Num;
           Check_Num = JOptionPane.showInputDialog ("Enter Check Number: ");
           Trans_Amt = getTransAmt(1);
           Transaction Ch = new Check(1, Trans_Amt, counter, Integer.parseInt(Check_Num));
           C.addTrans(Ch);
           processCheck((Check)Ch,counter);
       }
       else if (Trans_Code == 2)
       {
           double cash, credit;
           JTextField Cash = new JTextField(20);
           JTextField Check = new JTextField(20);
           JPanel Deposit = new JPanel();
           Deposit.setLayout(new GridLayout(4,1));
           Deposit.add(new JLabel("Cash"));
           Deposit.add(Cash);
           Deposit.add(new JLabel("Check"));
           Deposit.add(Check);
           Cash.addAncestorListener(new SetFocus());
           int result = JOptionPane.showConfirmDialog(null, Deposit, "Deposit", JOptionPane.OK_CANCEL_OPTION);
           if (result == JOptionPane.OK_OPTION){
               cash = Double.parseDouble(Cash.getText());
               credit = Double.parseDouble(Check.getText());
               Trans_Amt = cash + credit;
           }
           else{
               Trans_Amt = 0;
               cash = 0;
               credit = 0;
           }
           Transaction D = new Deposit(2, Trans_Amt, counter, cash, credit);
           C.addTrans(D);
           processDeposit((Deposit)D, counter);
       }
       frame.setVisible(true);
   }
   public static void listTransaction()
   {
       frame.setVisible(false);
       NumberFormat money = NumberFormat.getCurrencyInstance();
       float costOfItems = 0.0f;
       String message = "List Transactions for\n Name:" + C.getName() + "\n\n" 
               + String.format("%-10s%-20s%10s\n", "ID", "Type", "Amount");
       int counter;
       Transaction T;
       for (counter = 0; counter < C.gettransCount(); counter++)
       {
           T = C.getTrans(counter);
           switch (T.getTransId())
           {
               case 1:
                   Check Ch = (Check)T;
                   message += String.format("%-10s%-20s%10s\n", Ch.getTransNumber(),
                           "Check", money.format(Ch.getTransAmount()));
                   break;
               case 2:
                   Deposit D = (Deposit)T;
                   message += String.format("%-10s%-20s%10s\n", D.getTransNumber(),
                           "Deposit", money.format(D.getTransAmount()));
                   break;
               case 3:
                   ServiceCharge SC = (ServiceCharge)T;
                   message += String.format("%-10s%-20s%10s\n", SC.getTransNumber(),
                           "Svc. Chrg.", money.format(SC.getTransAmount()));
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
       String message = "Listing all Checks for "+ C.getName() +": \n\n" 
               + String.format("%-10s%-10s%10s\n", "ID", "Check", "Amount");
       int counter;
       Transaction T;
       for (counter = 0; counter < C.gettransCount(); counter++)
       {
           T = C.getTrans(counter);
           if (T.getTransId() == 1)
           {
               Check Ch = (Check)T;
               message += String.format("%-10s%-10s%10s\n", Ch.getTransNumber(),
                            Ch.getCheckNumber(), money.format(Ch.getTransAmount()));
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
       String message = "Listing all Deposits for " + C.getName() + ":\n\n" + 
               String.format("%-10s%-10s%-10s%10s\n", "ID", "Check", "Cash", "Amount");
       int counter;
       Transaction T;
       for (counter = 0; counter < C.gettransCount(); counter++)
       {
           T = C.getTrans(counter);
           if (T.getTransId() == 2)
           {
               Deposit D = (Deposit)T;
               message += String.format("%-10s%-10s%-10s%10s\n", D.getTransNumber(),
                            money.format(D.getCheck()), money.format(D.getCash()),
                            money.format(D.getTransAmount()));
           }
       }
       JTextArea text = new JTextArea(message);
       text.setBorder(null);
       text.setOpaque(false);
       text.setFont(new Font("Monospaced", Font.PLAIN, 14));
       JOptionPane.showMessageDialog(null, text);
       frame.setVisible(true);
   }
   public static void listServiceCharges()
   {
       frame.setVisible(false);
       NumberFormat money = NumberFormat.getCurrencyInstance();
       float costOfItems = 0.0f;
       String message = "Listing all Service Charges for "+ C.getName() +": \n\n" 
               + String.format("%-10s%10s\n", "ID", "Amount");
       int counter;
       Transaction T;
       for (counter = 0; counter < C.gettransCount(); counter++)
       {
           T = C.getTrans(counter);
           if (T.getTransId() == 3)
           {
               ServiceCharge SC = (ServiceCharge)T;
               message += String.format("%-10s%10s\n", SC.getTransNumber(),
                       money.format(SC.getTransAmount()));
           }
       }
       message += String.format("%-10s%10s\n", " ", "-----") + String.format("%-10s%10s\n", 
               "Total", money.format(C.getServiceCharge()));
       JTextArea text = new JTextArea(message);
       text.setBorder(null);
       text.setOpaque(false);
       text.setFont(new Font("Monospaced", Font.PLAIN, 14));
       JOptionPane.showMessageDialog(null, text);
       frame.setVisible(true);
   }
   private static class SetFocus implements AncestorListener {
       @Override
       public void ancestorAdded(AncestorEvent e) {
           JComponent component = e.getComponent();
           component.requestFocusInWindow();
       }
       public void ancestorMoved(AncestorEvent e){}
       public void ancestorRemoved(AncestorEvent e){}
   }
}