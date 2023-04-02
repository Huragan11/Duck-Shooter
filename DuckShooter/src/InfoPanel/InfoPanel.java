package InfoPanel;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JLabel {

    int coins = 0;
    int score = 0;
    int health = 10;
    String coins_string = Integer.toString(coins);
    String score_string = Integer.toString(score);
    String health_string = Integer.toString(health);
    String result = "Health: " + health_string+  "     " +"Coins: " + coins_string+  "     " + "Score: " + score_string;
    Thread updater = new Thread(() -> {
        while(!Thread.currentThread().isInterrupted()) {
            coins_string = Integer.toString(coins);
            score_string = Integer.toString(score);
            health_string = Integer.toString(health);
            result = "Health: " + health_string+  "     " +"Coins: " + coins_string+  "     " + "Score: " + score_string;
            setText(result);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    });

    public InfoPanel() {
        setText(result);
        setFont(new Font("Verdana",Font.BOLD,20));
        setBounds(800, 0, 559, 100);
        setOpaque(true);
        setHorizontalAlignment(JTextField.LEFT);
        setBackground(Color.GRAY);
        updater.start();
    }

    public void addValue(int i){
        coins += i;
    }
    public void addScore(int i){
        score += i;
    }
    public void item1Bought(){
        coins-=10;
        health++;
    }
    public void item2Bought(){
        coins-=50;
        health+=3;
    }
    public void loseHealth(){
        health--;
    }

    public int getCoins() {
        return coins;
    }

    public int getScore() {
        return score;
    }

    public int getHealth() {
        return health;
    }
}
