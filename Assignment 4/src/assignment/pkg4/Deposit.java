package assignment.pkg4;

import java.io.Serializable;

public class Deposit extends Transaction
{
    private double cash;
    private double check;
    public Deposit (int tId, double tAmt, int tCount, double tCash, double tCheck)
    {
        super(tCount, tId, tAmt);
        cash = tCash;
        check = tCheck;
    }
    public double getCash()
    {
        return cash;
    }
    public void setCash(double tCash)
    {
        cash = tCash;
    }
    public double getCheck()
    {
        return check;
    }
    public void setCheck(double tCheck)
    {
        cash = tCheck;
    }
}