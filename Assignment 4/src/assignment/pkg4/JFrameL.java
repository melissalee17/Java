package assignment.pkg4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JFrameL extends JFrame {
    public JFrameL(String title) {
        super(title);
        FrameListener fl = new FrameListener();
        addWindowListener(fl);
    }
    private class FrameListener extends WindowAdapter {
        public void windowClosing(WindowEvent e)
        {
            setVisible(false);
            int confirm;
            if (!Main.saved)
            {
               String  message = "Changes have not been saved.\n" + 
                   "Would you like to save before exiting?";
               confirm = JOptionPane.showConfirmDialog (null, message);
               if (confirm == JOptionPane.YES_OPTION)
                  Main.writeCheckingAccount();
               System.exit(0);
            }
        }
    }
}
