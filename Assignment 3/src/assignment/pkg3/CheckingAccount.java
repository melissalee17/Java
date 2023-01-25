package assignment.pkg3;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CheckingAccount extends Account
{
 
      private double totalServiceCharge;
      private final ArrayList<Transaction> transList;  // keeps a list of Transaction objects for the account
      private int transCount;   // the count of Transaction objects and used as the ID for each transaction
      public static DecimalFormat fmt = new DecimalFormat("$0.00");
      public CheckingAccount(String accountOwner,double initialBalance)
      {
            super(accountOwner, initialBalance);
            totalServiceCharge = 0;
            transList = new ArrayList();
            transCount = 0;
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
          transCount++;
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