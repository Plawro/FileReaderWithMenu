import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MainForm extends JFrame{
    private JTextArea textArea1;
    private JPanel mainPanel;

    public static void main(String[] args) {
        MainForm mainForm = new MainForm();

        JMenuBar menuBar;
        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);

        openItem.addActionListener(e-> {
            try {
                mainForm.readFromFile();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });


        mainForm.setJMenuBar(menuBar);

        mainForm.setSize(300,400);
        mainForm.setVisible(true);
        mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public MainForm(){
        setTitle("File reader w/ menu");
        setContentPane(mainPanel);

    }

    private JFileChooser chooser = new JFileChooser(".");
    String line;
    String parametry= "";

    private void readFromFile() throws FileNotFoundException {
        int result = chooser.showOpenDialog(this);
        // Klikl uživatel na "Open"? Pokud ano, zpracuj událost
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();

            try(Scanner scanner = new Scanner(new BufferedReader(new FileReader(selectedFile)))) {
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    if(parametry.equals("")){
                        parametry += line;
                    }else {
                        parametry += "\n" + line;
                    }
                }
            }catch (FileNotFoundException e){
                throw new FileNotFoundException();
            }finally {
                textArea1.setText(parametry);
            }
        }
    }





}
