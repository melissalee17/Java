package assignment.pkg3;

public class Check extends Transaction
{
    private int checkNumber; // check number for each check transaction
 
    public Check(int tId, double tAmt, int tCount, int checkNumber) {
        super(tCount, tId, tAmt);
        this.checkNumber = checkNumber;
    } 
    public int getCheckNumber() {
        return checkNumber;
    }
 
    public void setCheckNumber(int checkNumber) {
        this.checkNumber = checkNumber;
    }
}
