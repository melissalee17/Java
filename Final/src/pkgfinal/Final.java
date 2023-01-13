package pkgfinal;

import java.awt.event.KeyEvent;
import java.util.Timer;
import javax.swing.JFileChooser;

public class Final {

    public static void main(String[] args) {
        String str = "Hello";
        int i;
        try {
            i = Integer.parseInt(str);
        }
        catch(NumberFormatException e)
        {
            i = -1;
        }
        System.out.println(i);
    }
}
