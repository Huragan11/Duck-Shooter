package SubmitPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SubmitPanel extends JFrame implements ActionListener, Serializable {


    JButton submitButton = new JButton("Submit this run!");
    JTextField name = new JTextField();
    FileOutputStream save;
    OutputStream buffer;
    ObjectOutputStream toBeSaved;
    String returnedName;
    String runInfo;
    String fullInfo;


    public SubmitPanel(String info) {
        runInfo = info;


        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        name.setPreferredSize(new Dimension(250,40));
        submitButton.addActionListener(this);
        add(submitButton);
        add(name);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

    }

    public String getReturnedName() {
        return returnedName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton){
            returnedName = name.getText();
            fullInfo = "Name: " + getReturnedName() + " || " + runInfo;
            try {
                save = new FileOutputStream("Highscores.ser");
                buffer = new BufferedOutputStream(save);
                toBeSaved = new ObjectOutputStream(buffer);
                toBeSaved.writeObject(fullInfo);
                toBeSaved.flush();
                toBeSaved.close();
                save.close();
            }catch (IOException a){
                a.printStackTrace();
            }
            System.out.println(fullInfo);
        }

    }
}

