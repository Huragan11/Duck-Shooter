package Ducks;

import javax.swing.*;

public abstract class Duck extends JPanel {

    int hp;
    int value;
    boolean isAlive;
    int valueOfSleeping = 60;
    int score;
    boolean killed = false;

    public boolean gotKilled(){
        return killed;
    }
    public int getScore() {
        return score;
    }

    public int getHp() {
        return hp;
    }

    public int getValue() {
        return value;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public void flyFaster() {
        valueOfSleeping-=10;
    }
    public int getValueOfSleeping(){
        return valueOfSleeping;
    }

}
