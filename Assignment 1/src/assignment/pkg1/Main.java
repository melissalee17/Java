/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg1;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import java.lang.*;

public class Main
{
   //global variables:
   //define a CheckingAccount object to keep trach of the
   // account information.
    Scanner S = new Scanner(System.in);
    public static CheckingAccount C;
    public static DecimalFormat fmt = new DecimalFormat("$0.00");
   public static void main (String[] args)
   {
       // defines local variables
       double I_Balance, Trans_Amt;
       int Trans_Code, counter = 0;
       String I_Bal, message;
       //  get initial balance from the user
       I_Bal = JOptionPane.showInputDialog("Enter Your Initial Balance: ");
       I_Balance = Double.parseDouble(I_Bal);
       C = new CheckingAccount(I_Balance);
       //  perform in a loop until the trans code = 0
       Trans_Code = getTransCode();
       while (Trans_Code != 0)
       {
           Trans_Amt = getTransAmt();
           switch (Trans_Code) {
               case 1:
                   processCheck(Trans_Amt, counter);
                   counter++;
                   break;
               case 2:
                   processDeposit(Trans_Amt);
                   break;
               default: 
                   break;
           }
           Trans_Code = getTransCode();
       }
       message = "Transaction: End\n";
       //if balance is negative, return true
       //if balance is positive, return false
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
       //    get the trans code from the user
       //  and process it with appropriate helper method
       //   When loop ends show final balance to user.
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
   public static void processCheck(double Trans_Amt, int count)
   {
       String message = "Transaction: Check in amount of " + fmt.format(Trans_Amt) + "\n";
       //if balance is negative, return true
       //if balance is positive, return false
       if(C.setBalance(Trans_Amt, 1))
       {
           message += "Current Balance: (" + fmt.format(Math.abs(C.getBalance())) + 
               ")\n Service Charge: Check -- charge $0.15\n";
       }
       else {
            message += "Current Balance: " + fmt.format(C.getBalance()) + 
               "\n Service Charge: Check -- charge $0.15\n";
       }
       if (C.getBalance() < 500 && count == 0)
       {
           C.setServiceCharge(C.getServiceCharge() + 5.00);
           message += "Service Charge: Below $500 -- charge $5.00\n";
       }
       if (C.getBalance() < 50)
       {
           message += "Warning: Balance below $50.00\n";
       }
       if (C.getBalance() < 0)
       {
           C.setServiceCharge(C.getServiceCharge() + 10.00);
           message += "Service Charge: Below $0 -- charge $10.00\n";
       }
       message += "Total Service Charge: " + fmt.format(C.getServiceCharge());
       JOptionPane.showMessageDialog(null, message);
   }
   public static void processDeposit(double Trans_Amt)
   {
       String message = "Transaction: Deposit in amount of " + fmt.format(Trans_Amt) + "\n";
       //if balance is negative, return true
       //if balance is positive, return false
       if (C.setBalance(Trans_Amt, 2))
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
 
}