import javax.swing.*;
import java.awt.*;


/* the objective of this class is minimizing the code need to style the different components across the admin window and user window
* this class will change the colors and borders of buttons, text fields, panels,  and labels.
* other styling elements will be implemented in their respective class */

public class ColorRendering {

    // Define color constants for different UI elements
    private static final Color PANEL_COLOR = Color.decode("#222828");
    private static final Color BUTTON_COLOR = Color.decode("#2954a9");
    private static final Color FIELD_COLOR = Color.decode("#4974b2");
    private static final Color LABEL_COLOR = Color.decode("#fcfdfc");
    private static final Color TEXT_COLOR = Color.WHITE;
    public static void changeButtonColors(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.setBackground(BUTTON_COLOR);
                button.setForeground(TEXT_COLOR); // Set text color
                button.setOpaque(true);
                button.setBorderPainted(false);
            } else if (comp instanceof Container) {
                changeButtonColors((Container) comp);
            }
        }
    }

    public static void changeTextFieldColors(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JTextField) {
                JTextField field = (JTextField) comp;
                field.setBackground(FIELD_COLOR);
                field.setForeground(TEXT_COLOR); // Set text color
                field.setOpaque(true);
                field.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            } else if (comp instanceof Container) {
                changeTextFieldColors((Container) comp);
            }
        }
    }

    public static void changeLabelColors(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                label.setForeground(LABEL_COLOR); // Set text color
            } else if (comp instanceof Container) {
                changeLabelColors((Container) comp);
            }
        }
    }

    public static void changePanelBackground(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                panel.setBackground(PANEL_COLOR);
                panel.setOpaque(true); // Ensure the panel is opaque
                panel.repaint(); // Repaint to show the new color
            }
            // The check is made here to include the case when comp is a JPanel
            // as JPanel itself is a Container, and could contain other panels.
            if (comp instanceof Container) {
                changePanelBackground((Container) comp);
            }
        }
    }
}
