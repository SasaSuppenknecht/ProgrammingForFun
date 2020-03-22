package GUI;

import Controller.*;

import javax.swing.JTextArea;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainMenu extends SuperFrame {

    private static WordSearch grid;
    private String recentlyLoaded;

    MainMenu() {
        super("Menü");
        setFrame(300,500);

        Panel panelNorth = new Panel();
        Panel panelSouth = new Panel();

        JTextArea area = new JTextArea(10,20);
        panelNorth.add(area);

        add(panelNorth, BorderLayout.NORTH);
        add(panelSouth, BorderLayout.SOUTH);


    }

    public static void main(String[] args) throws IOException { //can be removed at the end once exception handling is done
        new MainMenu();
    }
}
