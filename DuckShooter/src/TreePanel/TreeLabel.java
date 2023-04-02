package TreePanel;

import javax.swing.*;

public class TreeLabel extends JLabel {

    ImageIcon backgr = new ImageIcon("GraphicFiles\\doneTree3.png");
    JButton empty = new JButton();


    public TreeLabel(){

        empty.setSize(160,260);
        empty.setBorderPainted(false);
        empty.setContentAreaFilled(false);
        add(empty);


        setBounds((int)(Math.random()*1200),450,160,260);
        setIcon(backgr);
    }



}
