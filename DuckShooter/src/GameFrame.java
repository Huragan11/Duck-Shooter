import CloudPanel.CloudLabel;
import Ducks.Duck;
import Ducks.EasyDuck;
import Ducks.HardDuck;
import Ducks.MediumDuck;
import InfoPanel.InfoPanel;
import Shop.ShopPanel;
import StopwatchPackage.StopwatchLabel;
import SubmitPanel.SubmitPanel;
import TreePanel.TreeLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends JFrame {


    InfoPanel infoPanel = new InfoPanel();
    ImageIcon backgrdFoto = new ImageIcon("GraphicFiles\\TLO.png");
    JLabel background = new JLabel();
    StopwatchLabel timer = new StopwatchLabel();
    ShopPanel shop = new ShopPanel();
    JLayeredPane game = new JLayeredPane();
    List<Duck> ducks = Collections.synchronizedList(new ArrayList<>());

    String runInfo = "Score: " + infoPanel.getScore() + " || " + "Time: " + timer.getTime() + " || " + "Difficulty: " + getDifficulty();

    Thread updateRunInfo = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()){
            runInfo = "Score: " + infoPanel.getScore() + " || " + "Time: " + timer.getTime() + " || " + "Difficulty: " + getDifficulty();
        }
    });

    String difficulty;

    public String getDifficulty() {
        return difficulty;
    }

    public String getRunInfo() {
        return runInfo;
    }

    Thread item2Obtained = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            if (shop.isItem2_obtained()) {
                infoPanel.item2Bought();
                ducks.clear();
                Thread.currentThread().interrupt();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    });
    Thread item1Obtained = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            if (shop.isItem1_obtained()) {
                infoPanel.item1Bought();
                Thread.currentThread().interrupt();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    });
    Thread coinChecker = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            if (infoPanel.getCoins() >= 10) {
                shop.setItem1_obtainable();
            }
            if (infoPanel.getCoins() >= 50) {
                shop.setItem2_obtainable();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

            }
        }
    });
    Timer maker = new Timer(6500, e -> {
        for (int i = 0; i < 3; i++) {
            Duck d;
            int lotto = (int) (Math.random() * 3);
            switch (lotto) {
                case 0 -> {
                    d = new EasyDuck(difficulty);
                    game.add(d, Integer.valueOf(1));
                    ducks.add(d);
                }
                case 1 -> {
                    d = new MediumDuck(difficulty);
                    game.add(d, Integer.valueOf(1));
                    ducks.add(d);
                }
                case 2 -> {
                    d = new HardDuck(difficulty);
                    game.add(d, Integer.valueOf(1));
                    ducks.add(d);

                }
            }

            System.out.println(ducks.size());
        }
    });
    Thread checker = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            for (Duck d : new CopyOnWriteArrayList<>(ducks)) {
                if (!d.isAlive()) {
                    d.setVisible(false);
                    if (d.gotKilled()) {
                        infoPanel.addValue(d.getValue());
                        infoPanel.addScore(d.getScore());
                    } else {
                        infoPanel.loseHealth();
                    }
                    game.remove(d);
                    ducks.remove(d);
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    });
    Timer treeMaker = new Timer(20000, e -> game.add(new TreeLabel(), Integer.valueOf(2)));
    Timer cloudMaker = new Timer(15000, e -> game.add(new CloudLabel(), Integer.valueOf(2)));

    public GameFrame(String diff) {

        difficulty = diff;

        game.setBounds(0, 100, 1360, 800);
        background.setSize(1360, 800);
        background.setIcon(backgrdFoto);

        updateRunInfo.start();
        timer.stopwatchSTART();
        maker.start();
        treeMaker.start();
        cloudMaker.start();
        checker.start();
        coinChecker.start();
        item1Obtained.start();
        item2Obtained.start();
        game.add(background, Integer.valueOf(0));
        add(shop);
        add(infoPanel);
        add(game);
        add(timer);

        setSize(new Dimension(1360, 900));
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        game.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK), "FIN");
        game.getActionMap().put("FIN", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                updateRunInfo.interrupt();
                timer.stopwatchSTOP();
                maker.stop();
                treeMaker.stop();
                cloudMaker.stop();
                checker.interrupt();
                coinChecker.interrupt();
                item1Obtained.interrupt();
                item2Obtained.interrupt();
                new Menu();
                new SubmitPanel(getRunInfo());
                System.out.println(getRunInfo());
            }
        });
    }
}
