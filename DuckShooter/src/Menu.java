import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {


    JButton START = new JButton();
    ImageIcon startIcon = new ImageIcon("GraphicFiles\\StartGame.png");

    JButton HIGHSCORES = new JButton();
    ImageIcon HighscoresIcon = new ImageIcon("GraphicFiles\\HighScores.png");

    JButton EXIT = new JButton();
    ImageIcon ExitIcon = new ImageIcon("GraphicFiles\\ExitGame.png");


    ImageIcon backgrdFoto = new ImageIcon("GraphicFiles\\menu2.jpg");
    JLabel background = new JLabel();


    public Menu() {

        START.setBounds(550,250,300,80);
        START.setIcon(startIcon);
        START.setBorderPainted(true);
        START.setContentAreaFilled(false);
        START.setFocusPainted(true);
        START.setOpaque(false);
        START.addActionListener(this);


        HIGHSCORES.setBounds(550,350,300,80);
        HIGHSCORES.setIcon(HighscoresIcon);
        HIGHSCORES.setBorderPainted(true);
        HIGHSCORES.setContentAreaFilled(false);
        HIGHSCORES.setFocusPainted(true);
        HIGHSCORES.setOpaque(false);
        HIGHSCORES.addActionListener(this);

        EXIT.setBounds(550,450,300,80);
        EXIT.setIcon(ExitIcon);
        EXIT.setBorderPainted(true);
        EXIT.setContentAreaFilled(false);
        EXIT.setFocusPainted(true);
        EXIT.setOpaque(false);
        EXIT.addActionListener(this);

        background.setIcon(backgrdFoto);

        add(START);
        add(HIGHSCORES);
        add(EXIT);
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
        if (e.getSource() == START){
            dispose();
            new ChoosingDifficultyFrame();
        }
        if (e.getSource() == HIGHSCORES){
            new HighscoresFrame();
        }
        if (e.getSource() == EXIT){
            System.exit(0);
        }
    }
}
