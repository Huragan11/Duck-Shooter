import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoosingDifficultyFrame extends JFrame implements ActionListener {

    JButton easy = new JButton();
    ImageIcon EASYicon = new ImageIcon("GraphicFiles\\easy.png");

    JButton normal = new JButton();
    ImageIcon NORMALicon = new ImageIcon("GraphicFiles\\normal.png");

    JButton hard = new JButton();
    ImageIcon HARDicon = new ImageIcon("GraphicFiles\\hard.png");

    ImageIcon backgrdFoto = new ImageIcon("GraphicFiles\\menu2.jpg");
    JLabel background = new JLabel();

    public ChoosingDifficultyFrame(){

        easy.setBounds(550,250,300,50);
        easy.setIcon(EASYicon);
        easy.setBorderPainted(true);
        easy.setContentAreaFilled(false);
        easy.setFocusPainted(true);
        easy.setOpaque(false);
        easy.addActionListener(this);

        normal.setBounds(550,350,300,50);
        normal.setIcon(NORMALicon);
        normal.setBorderPainted(true);
        normal.setContentAreaFilled(false);
        normal.setFocusPainted(true);
        normal.setOpaque(false);
        normal.addActionListener(this);

        hard.setBounds(550,450,300,50);
        hard.setIcon(HARDicon);
        hard.setBorderPainted(true);
        hard.setContentAreaFilled(false);
        hard.setFocusPainted(true);
        hard.setOpaque(false);
        hard.addActionListener(this);

        background.setIcon(backgrdFoto);

        add(easy);
        add(normal);
        add(hard);
        add(background);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1360, 800);
        setVisible(true);
        setResizable(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == easy){
            dispose();
            new GameFrame("easy");
        }else if(e.getSource() == normal){
            dispose();
            new GameFrame("normal");
        }else{
            dispose();
            new GameFrame("hard");
        }
    }
}
