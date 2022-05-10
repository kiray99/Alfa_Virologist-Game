package GUI.view.panels;

import GUI.view.view.View;

import javax.swing.*;
import java.awt.*;

public class BaseBagPanel extends JPanel implements View {
    // Ezt nem absztraktra csináltam, hogy reprezentálhassa az üres paneleket az inventoryban.

    public BaseBagPanel() {
        setOpaque(false);
        setSize(100, 50);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
    }

    @Override
    public void update() {    }

    @Override
    public void onClick() {    }

    @Override
    /**
     * Print a black rectangle with rounded corners. This method can be called from the paint methods of classes, who inherit from this class,
     * when putting an image on top of it.
     */
    public void paint(Graphics g) {
        setOpaque(false);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.BLACK);
        g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
        g2.dispose();
        super.paint(g);
    }
}