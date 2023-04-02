package CloudPanel;

import javax.swing.*;

public class CloudLabel extends JLabel {

    JButton empty = new JButton();
    ImageIcon backgr = new ImageIcon("GraphicFiles\\doneCloud.gif");
    int positionX = 0;
    int positionY = (int) (Math.random() * 150+100);

    Thread mover = new Thread(new Runnable() {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                while (positionX < 1295) {
                    setBounds(positionX, positionY, 125, 75);
                    positionX += 3;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                while (positionX > -50) {
                    setBounds(positionX, positionY, 125, 75);
                    positionX -= 3;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    });

    public CloudLabel() {
        empty.setSize(125, 75);
        empty.setBorderPainted(false);
        empty.setContentAreaFilled(false);
        add(empty);
        setIcon(backgr);
        setBounds(-125,positionY, 125, 75);
        mover.start();
    }
}
