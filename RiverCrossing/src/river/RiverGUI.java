package river;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import river.GameEngine.Item;
import river.GameEngine.Location;

/**
 * Graphical interface for the River application
 * 
 * @author Gregory Kulczycki
 */
public class RiverGUI extends JPanel implements MouseListener {

    // ==========================================================
    // Private Fields
    // ==========================================================
    
    private GameEngine engine;
    
    // ==========================================================
    // Constructor
    // ==========================================================
    
    public RiverGUI() {
        
        engine = new GameEngine();
        
    }
    
    // ==========================================================
    // Paint Methods
    // ==========================================================

    @Override
    public void paintComponent(Graphics g) {
        
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        paintObjectsOnLeft(g);
        paintObjectsOnRight(g);
        paintObjectsOnBoat(g);
    }
    
    public void paintObjectsOnLeft(Graphics g) {

        if (engine.getLocation(Item.PLAYER) == Location.START) {
            g.setColor(Color.MAGENTA);
            g.fillRect(80, 215, 50, 50);
        }
        if (engine.getLocation(Item.TOP) == Location.START) {
            g.setColor(Color.CYAN);
            g.fillRect(20, 215, 50, 50);
            paintStringInRectangle("W", 20, 215, 50, 50, g);
        }
        if (engine.getLocation(Item.MID) == Location.START) {
            g.setColor(Color.CYAN);
            g.fillRect(20, 275, 50, 50);
            paintStringInRectangle("G", 20, 275, 50, 50, g);
        }
        if (engine.getLocation(Item.BOTTOM) == Location.START) {
            g.setColor(Color.CYAN);
            g.fillRect(80, 275, 50, 50);
            paintStringInRectangle("B", 80, 275, 50, 50, g);
        }
    }
    
    public void paintObjectsOnRight(Graphics g) {

        if (engine.getLocation(Item.PLAYER) == Location.FINISH) {
            g.setColor(Color.MAGENTA);
            g.fillRect(730, 215, 50, 50);
        }
        if (engine.getLocation(Item.TOP) == Location.FINISH) {
            g.setColor(Color.CYAN);
            g.fillRect(670, 215, 50, 50);
            paintStringInRectangle("W", 670, 215, 50, 50, g);
        }
        if (engine.getLocation(Item.MID) == Location.FINISH) {
            g.setColor(Color.CYAN);
            g.fillRect(670, 275, 50, 50);
            paintStringInRectangle("G", 670, 275, 50, 50, g);
        }
        if (engine.getLocation(Item.BOTTOM) == Location.FINISH) {
            g.setColor(Color.CYAN);
            g.fillRect(730, 275, 50, 50);
            paintStringInRectangle("B", 730, 275, 50, 50, g);
        }
    }
    
    public void paintObjectsOnBoat(Graphics g) {
        if (engine.getCurrentLocation() == Location.START) {
            g.setColor(Color.ORANGE);
            g.fillRect(140, 275, 110, 50);
            if (engine.getLocation(Item.PLAYER) == Location.BOAT) {
                g.setColor(Color.MAGENTA);
                g.fillRect(140, 215, 50, 50);
            }
            if (engine.getLocation(Item.TOP) == Location.BOAT) {
                g.setColor(Color.CYAN);
                g.fillRect(200, 215, 50, 50);
                paintStringInRectangle("W", 200, 215, 50, 50, g);
            } else if (engine.getLocation(Item.MID) == Location.BOAT) {
                g.setColor(Color.CYAN);
                g.fillRect(200, 215, 50, 50);
                paintStringInRectangle("G", 200, 215, 50, 50, g);               
            } else if (engine.getLocation(Item.BOTTOM) == Location.BOAT) {
                g.setColor(Color.CYAN);
                g.fillRect(200, 215, 50, 50);
                paintStringInRectangle("B", 200, 215, 50, 50, g);               
            }
        }
        if (engine.getCurrentLocation() == Location.FINISH) {
            g.setColor(Color.ORANGE);
            g.fillRect(550, 275, 110, 50);
            if (engine.getLocation(Item.PLAYER) == Location.BOAT) {
                g.setColor(Color.MAGENTA);
                g.fillRect(550, 215, 50, 50);
            }
            if (engine.getLocation(Item.TOP) == Location.BOAT) {
                g.setColor(Color.CYAN);
                g.fillRect(610, 215, 50, 50);
                paintStringInRectangle("W", 200, 215, 50, 50, g);
            } else if (engine.getLocation(Item.MID) == Location.BOAT) {
                g.setColor(Color.CYAN);
                g.fillRect(610, 215, 50, 50);
                paintStringInRectangle("G", 200, 215, 50, 50, g);               
            } else if (engine.getLocation(Item.BOTTOM) == Location.BOAT) {
                g.setColor(Color.CYAN);
                g.fillRect(610, 215, 50, 50);
                paintStringInRectangle("B", 200, 215, 50, 50, g);               
            }
        }
    }
    
    public void paintStringInRectangle(String str, int x, int y, int width, int height, Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 36));
        FontMetrics fm = g.getFontMetrics();
        int strXCoord = x + width/2 - fm.stringWidth(str)/2;
        int strYCoord = y + height/2 + 36/2 - 4;
        g.drawString(str, strXCoord, strYCoord);
    }
    
    // ==========================================================
    // Startup Methods
    // ==========================================================
    
    /**
     * Create the GUI and show it.  For thread safety, 
     * this method should be invoked from the 
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        
        // Create and set up the window
        JFrame frame = new JFrame("RiverCrossing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane
        RiverGUI newContentPane = new RiverGUI();
        newContentPane.setOpaque(true);        
        frame.setContentPane(newContentPane);
        
        // Display the window
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(RiverGUI::createAndShowGUI);
    }

    // ==========================================================
    // MouseListener Methods
    // ==========================================================
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }

}
