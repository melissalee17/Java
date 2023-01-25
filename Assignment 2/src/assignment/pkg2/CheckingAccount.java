package assignment.pkg2;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class CheckingAccount
{
 
      private double balance;
      private double totalServiceCharge;
      private ArrayList<Transaction> transList;  // keeps a list of Transaction objects for the account
      private int transCount;   // the count of Transaction objects and used as the ID for each transaction
      public static DecimalFormat fmt = new DecimalFormat("$0.00");
      boolean flag;
      public CheckingAccount(double initialBalance)
      {
            balance = initialBalance;
            totalServiceCharge = 0;
            transList = new ArrayList();
            transCount = 0;
            flag = true;
      }
 
      public double getBalance()
      {
            return balance;
      }
 
      public void setBalance(Transaction T)
      {
          String message = "";
          Transaction S;
          transCount = T.getTransNumber();
          switch (T.getTransId())
          {
              default:
                  JOptionPane.showMessageDialog(null, "Bad Input\n");
                  break;
              //check
              case 1:
                  message += "Transaction: Check in amount of " 
                          + fmt.format(T.getTransAmount()) + "\n";
                  balance -= T.getTransAmount();
                  totalServiceCharge += 0.15;
                  transCount++;
                  S = new Transaction(transCount, 3, 0.15);
                  addTrans(S);
                  if(balance < 0)
                  {
                    message += "Current Balance: (" + fmt.format(Math.abs(balance)) + 
                            ")\n Service Charge: Check -- charge $0.15\n";
                  }
                  else {
                    message += "Current Balance: " + fmt.format(balance) + 
                            "\n Service Charge: Check -- charge $0.15\n";
                  }
                  if (flag && balance < 500)
                  {
                    totalServiceCharge += 5;
                    transCount++;
                    flag = false;
                    S = new Transaction(transCount,3, 5);
                    addTrans(S);
                    message += "Service Charge: Below $500 -- charge $5.00\n";
                  }
                  if (balance < 50)
                  {
                    message += "Warning: Balance below $50.00\n";
                  }
                  if (balance < 0)
                  {
                    totalServiceCharge += 10;
                    transCount++;
                    S = new Transaction(transCount,3, 10);
                    addTrans(S);
                    message += "Service Charge: Below $0 -- charge $10.00\n";
                  }
                  message += "Total Service Charge: " + fmt.format(totalServiceCharge);
                  JOptionPane.showMessageDialog(null, message);
                  break;
              //deposit
              case 2:
                  balance += T.getTransAmount();
                  totalServiceCharge += 0.10;
                  transCount++;
                  S = new Transaction(transCount,3, 0.10);
                  addTrans(S);
                  message += "Transaction: Deposit in amount of " 
                          + fmt.format(T.getTransAmount()) + "\n";
                  if (balance < 0)
                  {
                    message += "Current Balance: (" + fmt.format(Math.abs(balance)) + ")\n";
                  }
                  else {
                    message += "Current Balance: " + fmt.format(balance) + "\n";
                  }
                  message += "Service Charge: Deposit -- charge $0.10\n" + 
                    "Total Service Charge: " + fmt.format(totalServiceCharge);
                  JOptionPane.showMessageDialog(null, message);
                  break;
          }
      }
 
      public double getServiceCharge()
      {
            return totalServiceCharge;
      }
 
      public void setServiceCharge(double currentServiceCharge)
      {
            totalServiceCharge = currentServiceCharge;
      }
      public void addTrans( Transaction newTrans)   // adds a transaction object to the transList
      {
          transList.add(newTrans);
      }
      public int gettransCount()  //returns the current value of transCount;
      {
          return transCount;
      }
      public Transaction getTrans(int i) // returns the i-th Transaction object in the list
      {
          return transList.get(i);
      }
}