package StopwatchPackage;

import javax.swing.*;
import java.awt.*;


public class StopwatchLabel extends JLabel {

    int minutes = 0;
    int seconds = 0;
    int mili_seconds = 0;
    String minutesString = String.format("%02d", minutes);
    String secondsString = String.format("%02d", seconds);
    String miliSecondsString = String.format("%02d", mili_seconds);
    String time = minutesString + ":" + secondsString + ":" + miliSecondsString;

    public String getTime() {
        return minutesString + ":" + secondsString + ":" + miliSecondsString;
    }

    Thread timer = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            mili_seconds = mili_seconds + 1;

            miliSecondsString = String.format("%02d", mili_seconds);
            secondsString = String.format("%02d", seconds);
            minutesString = String.format("%02d", minutes);

            if (mili_seconds == 100) {
                seconds++;
                mili_seconds = 0;
            }
            if (seconds == 60) {
                minutes++;
                seconds = 0;
                mili_seconds = 0;
            }
            time = minutesString + ":" + secondsString + ":" + miliSecondsString;
            setText(time);
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    });


    public StopwatchLabel() {

        setText(time);
        setBounds(0, 0, 300, 100);
        setFont(new Font("Verdana", Font.BOLD, 40));
        setOpaque(true);
        setHorizontalAlignment(JTextField.CENTER);
        setBackground(Color.GRAY);
    }

    public void stopwatchSTART() {
        timer.start();
    }

    public void stopwatchSTOP() {
        timer.interrupt();
    }
}

