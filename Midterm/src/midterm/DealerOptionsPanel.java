/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midterm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author melissalee
 */
public class DealerOptionsPanel extends JPanel{
    private JLabel lbl;
    private JRadioButton rb1, rb2, rb3;
    private ButtonGroup bg;
    public DealerOptionsPanel()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        lbl = new JLabel ("Choose your option?");
        lbl.setFont(new Font("Helvetica", Font.BOLD, 24));
        rb1 = new JRadioButton ("New Car Sales");
        rb1.setBackground(Color.white);
        rb2 = new JRadioButton ("Used Car Sales");
        rb2.setBackground(Color.white);
        rb3 = new JRadioButton ("Service Dept.");
        rb3.setBackground(Color.white);
        bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        bg.add(rb3);
        DealerOptionListener listener = new DealerOptionListener();
        rb1.addActionListener (listener);
        rb2.addActionListener (listener);
        rb3.addActionListener (listener);
        add (lbl);
        add (rb1);
        add (rb2);
        add (rb3);
        setBackground (Color.white);
        setPreferredSize (new Dimension(250, 100));
    }
    
    private class DealerOptionListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            
        }
    }
}
