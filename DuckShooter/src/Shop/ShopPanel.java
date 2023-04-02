package Shop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopPanel extends JPanel implements ActionListener{

    boolean item1_obtainable = false;
    boolean item2_obtainable = false;
    boolean item1_obtained = false;
    boolean item2_obtained = false;


    JButton item1 = new JButton();
    ImageIcon icon1 = new ImageIcon("GraphicFiles\\item1.png");
    JButton item2 = new JButton();
    ImageIcon icon2 = new ImageIcon("GraphicFiles\\item2.png");

    public ShopPanel() {

        item1.setPreferredSize(new Dimension(75,75));
        item1.setIcon(icon1);
        item1.setBorderPainted(true);
        item1.setContentAreaFilled(false);
        item1.setBorder(BorderFactory.createLineBorder(Color.RED));
        item1.setFocusPainted(true);
        item1.setOpaque(false);
        item1.addActionListener(this);
        item1.setToolTipText("Buy 1 HP - 25 coins");


        item2.setPreferredSize(new Dimension(75,75));
        item2.setIcon(icon2);
        item2.setBorderPainted(true);
        item2.setBorder(BorderFactory.createLineBorder(Color.RED));
        item2.setContentAreaFilled(false);
        item2.setFocusPainted(true);
        item2.setOpaque(false);
        item2.addActionListener(this);
        item2.setToolTipText("Buy 3 HP - 50 coins");


        add(Box.createRigidArea(new Dimension(100,100)));
        add(item1);
        add(Box.createRigidArea(new Dimension(100,100)));
        add(item2);

        setBounds(300,0,500,100);
        setBackground(Color.gray);
        setOpaque(true);
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
    }

    public boolean isItem1_obtainable() {
        return item1_obtainable;
    }

    public void setItem1_obtainable() {
        this.item1_obtainable = true;
        item1.setBorder(BorderFactory.createLineBorder(Color.GREEN));

    }

    public boolean isItem2_obtainable() {
        return item2_obtainable;
    }

    public void setItem2_obtainable() {
        this.item2_obtainable = true;
        item2.setBorder(BorderFactory.createLineBorder(Color.GREEN));

    }

    public boolean isItem1_obtained() {
        return item1_obtained ;
    }

    public boolean isItem2_obtained() {
        return item2_obtained;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == item1){
            if (isItem1_obtainable()){
                item1.setText("SOLD");
                item1.setForeground(Color.ORANGE);
                item1.setFont(new Font("Verdana",Font.BOLD,20));
                item1_obtained = true;


            }
        }
        if (e.getSource() == item2){
            if (isItem2_obtainable()){
                item2.setText("SOLD");
                item2.setForeground(Color.ORANGE);
                item2.setFont(new Font("Verdana",Font.BOLD,20));
                item2_obtained = true;



            }
        }
    }
}
