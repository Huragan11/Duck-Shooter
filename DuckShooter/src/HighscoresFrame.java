import javax.swing.*;
import java.awt.*;
import java.io.*;


public class HighscoresFrame extends JFrame {

    String infoLine;
    boolean readNext = true;

    public HighscoresFrame(){

        DefaultListModel<String> prelist = new DefaultListModel<>();
        while (!readNext){
            try{
                FileInputStream read = new FileInputStream("Highscores.ser");
                InputStream buffer = new BufferedInputStream(read);
                ObjectInputStream inputStream = new ObjectInputStream(buffer);
                infoLine = (String) inputStream.readObject();
                if (infoLine != null){
                    prelist.addElement(infoLine);
                }else {
                    read.close();
                    inputStream.close();
                    readNext = false;
                }
            }catch (IOException | ClassNotFoundException e ){
                e.printStackTrace();
            }

        }
        System.out.println(prelist.getSize());


        JList scores = new JList(prelist);
        scores.setForeground(Color.blue);
        scores.setFont(new Font("Consolas",Font.BOLD, 20));



        add(new JScrollPane(scores));
        setLocation(550,400);
        setSize(600, 300);
        setVisible(true);
        setResizable(false);

    }

}
