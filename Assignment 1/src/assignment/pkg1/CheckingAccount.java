package assignment.pkg1;

import javax.swing.JOptionPane;

public class CheckingAccount
{
 
      private double balance;
      private double totalServiceCharge;
 
      public CheckingAccount(double initialBalance)
      {
            balance = initialBalance;
            totalServiceCharge = 0;
      }
 
      public double getBalance()
      {
            return balance;
      }
 
      public boolean setBalance(double transAmt, int tCode)
      {
        switch (tCode) {
              case 1:
                  balance -= transAmt;
                  totalServiceCharge += 0.15;
                  break;
              case 2:
                  balance += transAmt;
                  totalServiceCharge += 0.10;
                  break;
              default:
                  JOptionPane.showMessageDialog(null, "Bad Transaction Code.\n");
                  break;
          }
        //if balance is negative, return true
        //if balance is positive, return false
        if (balance < 0)
            return true;
        else
            return false;
      }
 
      public double getServiceCharge()
      {
            return totalServiceCharge;
      }
 
        public void setServiceCharge(double currentServiceCharge)
      {
            totalServiceCharge = currentServiceCharge;
      }
}