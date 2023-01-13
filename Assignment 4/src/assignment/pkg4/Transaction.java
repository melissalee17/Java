package assignment.pkg4;

import java.io.Serializable;

public class Transaction implements Serializable
{
  
    private int transNumber; //counter
    private int transId; //ID with 1 = check, 2 = deposit, 3 = service charge
    private double transAmt;
   
    public Transaction(int number, int id, double amount)
    {
        transNumber = number;
        transId = id;
        transAmt = amount;
    }
   
    public int getTransNumber()
    {
        return transNumber;
    }
   
    public int getTransId()
    {
        return transId;
    }
   
    public double getTransAmount()
    {
        return transAmt;
    }
}