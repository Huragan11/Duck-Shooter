package Ducks;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MediumDuck extends Duck implements ActionListener {

    double hp = 7;
    int value = 10;
    double score = 100;
    String difficulty;
    JButton field = new JButton();
    BufferedImage duckpic;
    File imageFile = new File("GraphicFiles\\mediumduck.png");

    BufferedImage duckpicR;
    File imageFileR = new File("GraphicFiles\\mediumduckR.png");

    int valueOfSleeping = 60;
    boolean isAlive = true;
    boolean killed = false;

    public int startingY = (int) (Math.random() * 600);

    public int positionL = 0;
    public int positionR = 1250;

    public int startingLoc = (int) (Math.random() * 2);



    Thread mover = new Thread(() -> {
        if (startingLoc == 1) {
            while (hp > 0 && positionL < 1300) {
                positionL += 5;
                setBounds(positionL, startingY, 110, 60);
                try {
                    Thread.sleep(getValueOfSleeping());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            while (hp > 0 && positionR > -60) {
                positionR -= 5;
                setBounds(positionR, startingY, 110, 60);
                try {
                    Thread.sleep(getValueOfSleeping());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        setDead();
        if (hp == 0){
            killed=true;
        }
    });
    Thread makingDucksFaster = new Thread(() -> {
        while(!Thread.currentThread().isInterrupted()){
            flyFaster();
            try{
                Thread.sleep(5000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    });

    public MediumDuck(String diff) {
        difficulty = diff;
        switch (diff){
            case "easy" -> {
                hp = (int) hp*0.75;
                score = (int) score*0.75;
            }
            case "hard" -> hp = (int) hp*1.25;
        }
        field.setFocusable(false);
        field.setBorderPainted(false);
        field.setContentAreaFilled(false);
        field.addActionListener(this);
        field.setPreferredSize((new Dimension(110, 60)));
        add(field);

        try {
            duckpic = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            duckpicR = ImageIO.read(imageFileR);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (startingLoc == 1) {
            setBounds(positionL, startingY, 110, 60);
        } else {
            setBounds(positionR, startingY, 110, 60);
        }

        mover.start();
        makingDucksFaster.start();
        setOpaque(false);


    }

    public int getValue() {
        return value;
    }

    public void setDead() {
        isAlive = false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void flyFaster() {
        if (valueOfSleeping > 0) {
            valueOfSleeping -= 10;
        }
    }

    public int getValueOfSleeping(){
        return valueOfSleeping;
    }
    public boolean gotKilled(){
        return killed;
    }

    public int getScore() {
        return (int)score;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (startingLoc == 1) {
            g.drawImage(duckpic, 0, 0, this);
        } else {
            g.drawImage(duckpicR, 0, 0, this);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == field) {
            hp--;
        }
    }
}
