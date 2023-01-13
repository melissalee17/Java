package assignment.pkg5;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OptionFrame extends JFrameL
{
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;
    private JMenu fileMenu, accountMenu, transactionMenu;
    private JMenuItem readFile, writeFile, addAccount, listTrans, listCheck, 
            listDeposit, listServiceCharges, findAccount, addTransaction;
    private JMenuBar bar;
    public OptionFrame (String title)
    {
        super(title);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        MenuListener ml = new MenuListener();
        
        fileMenu = new JMenu("File");
        
        readFile = new JMenuItem("Read from file");
        readFile.addActionListener(ml);
        fileMenu.add(readFile);
        
        writeFile = new JMenuItem("Write to file");
        writeFile.addActionListener(ml);
        fileMenu.add(writeFile);
        
        accountMenu = new JMenu("Account");
        
        addAccount = new JMenuItem("Add new account");
        addAccount.addActionListener(ml);
        accountMenu.add(addAccount);
        
        listTrans = new JMenuItem("List accounts transaction");
        listTrans.addActionListener(ml);
        accountMenu.add(listTrans);
        
        listCheck = new JMenuItem("List all checks");
        listCheck.addActionListener(ml);
        accountMenu.add(listCheck);
        
        listDeposit = new JMenuItem("List all deposits");
        listDeposit.addActionListener(ml);
        accountMenu.add(listDeposit);
        
        listServiceCharges = new JMenuItem("List all service charges");
        listServiceCharges.addActionListener(ml);
        accountMenu.add(listServiceCharges);
        
        findAccount = new JMenuItem("Find an Account");
        findAccount.addActionListener(ml);
        accountMenu.add(findAccount);
        
        transactionMenu = new JMenu("Transactions");
        
        addTransaction = new JMenuItem("Add Transactions");
        addTransaction.addActionListener(ml);
        transactionMenu.add(addTransaction);
        
        bar = new JMenuBar();
        bar.add(fileMenu);
        bar.add(accountMenu);
        bar.add(transactionMenu);
        setJMenuBar(bar);
    }
    private class MenuListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            String source = event.getActionCommand();
            if (source.equals("Read from file"))
            {
                try {
                    Main.readFile();
                } 
                catch (FileNotFoundException ex) {
                    Logger.getLogger(OptionFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if (source.equals("Write to file"))
            {
                Main.writeFile();
            }
            else if (source.equals("Add new account"))
            {
                Main.addNewAccount();
            }
            else if (source.equals("List accounts transaction"))
            {
                Main.listTransaction();
            }
            else if (source.equals("List all checks"))
            {
                Main.listChecks();
            }
            else if (source.equals("List all deposits"))
            {
                Main.listDeposits();
            }
            else if (source.equals("List all service charges"))
            {
                Main.listServiceCharges();
            }
            else if (source.equals("Find an Account"))
            {
                Main.findAccount();
            }
            else if(source.equals("Add Transactions"))
            {
                Main.completeTransaction();
            }
        }
    }
}
