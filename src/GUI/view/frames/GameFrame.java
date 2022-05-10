package GUI.view.frames;

import GUI.view.panels.*;
import GUI.view.view.View;
import main.com.teamalfa.blindvirologists.city.City;
import main.com.teamalfa.blindvirologists.turn_handler.Game;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;
import main.com.teamalfa.blindvirologists.virologist.Virologist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameFrame extends JFrame implements ActionListener, Notifiable {
    private final JPanel statusPanel;
    private JPanel inventoryPanel;
    private JPanel wornEquipmentPanel;
    private JPanel mapPanel;
    private WhatHappenedPanel whatHappenedPanel;
    private ArrayList<View> views;
    public static GameFrame instance;

    public GameFrame(int numberOfPlayers){
        //Starting the game
        this.startGame(numberOfPlayers);
        //And setting up the basics...
        this.setTitle("Blind Virologist");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1280,720);
        ImageIcon imageIcon = new ImageIcon("resources/logo.png");
        this.setIconImage(imageIcon.getImage());
        this.getContentPane().setBackground(new Color(141,120,120));

        //LayeredPane panel, map will be in the back and the rest front
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,1280,720);

        // Creating the status panel
        statusPanel = new StatusPanel();
        statusPanel.setBounds(0,0,300,100);
        statusPanel.setBackground(Color.black);

        //Creating worn equipment panel
        wornEquipmentPanel = new WornEquipmentPanel();
        wornEquipmentPanel.setBounds(960,5, 300,110);

        //Creating the inventory panel
        inventoryPanel = new InventoryPanel();
        inventoryPanel.setBounds(960,115,300,300);

        //Creating what happened panel
        whatHappenedPanel = new WhatHappenedPanel();
        whatHappenedPanel.setBounds(960,415,300,260);

        //Creating map panel
        mapPanel = new MapPanel();
        mapPanel.setBounds(0,80,1000,550);

        this.setLayout(null);
        this.setVisible(true);
        layeredPane.add(mapPanel,Integer.valueOf(0));//Default Layer(bottom)
        layeredPane.add(whatHappenedPanel,Integer.valueOf(1));//The rest will go to the front
        layeredPane.add(wornEquipmentPanel,Integer.valueOf(1));
        layeredPane.add(statusPanel,Integer.valueOf(1));
        layeredPane.add(inventoryPanel,Integer.valueOf(1));
        this.add(layeredPane);
        this.repaint();
    }

    public void logEvent(String message) {
        whatHappenedPanel.logOnPanel(message);
        this.updateView();
    }

    public void startGame (int numberOfPlayers) {
        Game.getInstance().startGame();
        //Adding players to the turnhandler
        for(int i = 1 ; i <= numberOfPlayers ; i++){
            Virologist virologist = new Virologist("Player"+i);
            virologist.setField(City.getInstance().getAllFields().get(0));
            TurnHandler.accept(virologist);
            System.out.println("Player"+i+" created");
        }
    }

    public void updateView() {
        for (View view : views) {
            view.update();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO
    }

    @Override
    public void creativeNotify(String massage) {
        // TODO
    }
}