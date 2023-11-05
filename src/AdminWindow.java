import javax.swing.*;
import java.awt.*;

public class AdminWindow extends JFrame {
    private String panelColor = "#222828";
    private String buttonColor = "#2954a9";
    private String fieldColor = "#4974b2";
    private String labelColor = "#fcfdfc";


    public AdminWindow() {
        initializeComponents();

        changeButtonColors(this.getContentPane(), Color.decode(buttonColor), Color.WHITE);
        changePanelBackground(this.getContentPane(), Color.decode(panelColor));
        changeLabelColors(this.getContentPane(), Color.decode(labelColor));
        changeTextFieldColors(this.getContentPane(), Color.decode(fieldColor), Color.WHITE);
    }

    private void initializeComponents() {
        // Configure the main window properties
        setTitle("The Amazing CPP Social Network");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setResizable(false);
        setLocationRelativeTo(null);

        // Initialize and configure panels
        JPanel leftPanel = createLeftPanel();
        JPanel rightPanel = createRightPanel();

        // Add panels to the frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);


        // Display the window
        setVisible(true);
    }

    private JPanel createLeftPanel() {
        // Left panel for tree view or similar navigation
        JPanel leftPanel = new JPanel();
        leftPanel.add(new JLabel("Tree View"));
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black,1,true));

        leftPanel.setPreferredSize(new Dimension(300, 700));
        return leftPanel;
    }

    private JPanel createRightPanel() {
        // Right panel to contain user control and statistics panels
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(700, 700));



        // Add subpanels to right panel
        rightPanel.add(createUserControlPanel());
        rightPanel.add(createStatisticsPanel());

        return rightPanel;
    }

    private JPanel createUserControlPanel() {
        // Top right panel for user control inputs and actions
        JPanel userControlPanel = new JPanel();
        userControlPanel.setLayout(new BoxLayout(userControlPanel, BoxLayout.Y_AXIS));
        userControlPanel.setMaximumSize(new Dimension(700, 600));
        userControlPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        userControlPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));



        JLabel userControlLabel = new JLabel("User Control");
        userControlLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Components for user operations
        userControlPanel.add(userControlLabel);
        userControlPanel.add(createUserInputPanel());
        userControlPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Button to open a separate user panel
        JButton openUserPanelButton = new JButton("Open User Panel");
        openUserPanelButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        userControlPanel.add(openUserPanelButton);

        return userControlPanel;
    }

    private JPanel createUserInputPanel() {
        // Panel for user and group ID input with GridLayout
        JPanel userInputPanel = new JPanel(new GridLayout(2, 2, 5, 5)); // 2x2 grid with 5px hgap and vgap
        userInputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        userInputPanel.setMaximumSize(new Dimension(700,100));

        // User and group ID input fields
        JTextField userIDTextField = new JTextField("User ID");

        JTextField groupIdTextField = new JTextField("Group ID");

        // Buttons for adding users and groups
        JButton addUserButton = new JButton("Add User");
        JButton addGroupButton = new JButton("Add Group");

        // Add components to the panel
        userInputPanel.add(userIDTextField);
        userInputPanel.add(addUserButton);
        userInputPanel.add(groupIdTextField);
        userInputPanel.add(addGroupButton);

        return userInputPanel;
    }



    private JPanel createStatisticsPanel() {
        // Bottom right panel for displaying statistics
        JPanel statisticsPanel = new JPanel();
        statisticsPanel.setLayout(new BoxLayout(statisticsPanel, BoxLayout.Y_AXIS));
        statisticsPanel.setMaximumSize(new Dimension(700, 100));
        statisticsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        statisticsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // Grid panel for statistic buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        // Buttons for showing various statistics
        JButton showUserTotalButton = new JButton("Show User Total");

        JButton showGroupTotalButton = new JButton("Show Group Total");
        JButton showMessagesTotalButton = new JButton("Show Messages Total");
        JButton showPositiveTotalButton = new JButton("Show Positive Total");

        // Add buttons to grid panel
        buttonPanel.add(showGroupTotalButton);
        buttonPanel.add(showUserTotalButton);
        buttonPanel.add(showMessagesTotalButton);
        buttonPanel.add(showPositiveTotalButton);

        // Add button panel to statistics panel
        statisticsPanel.add(buttonPanel);

        return statisticsPanel;
    }

    private void changeButtonColors(Container container, Color background, Color textColor) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.setBackground(background);
                button.setForeground(textColor); // Set text color
                button.setOpaque(true);
                button.setBorderPainted(false);
            } else if (comp instanceof Container) {
                changeButtonColors((Container) comp, background, textColor);
            }
        }
    }
    private void changeTextFieldColors(Container container, Color background, Color textColor) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JTextField) {
                JTextField field = (JTextField) comp;
                field.setBackground(background);
                field.setForeground(textColor); // Set text color
                field.setOpaque(true);
                field.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            } else if (comp instanceof Container) {
                changeTextFieldColors((Container) comp, background, textColor);
            }
        }
    }
    private void changeLabelColors(Container container, Color textColor) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                label.setForeground(textColor); // Set text color
            } else if (comp instanceof Container) {
                changeLabelColors((Container) comp, textColor);
            }
        }
    }
    private void changePanelBackground(Container container, Color backgroundColor) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                panel.setBackground(backgroundColor);
                panel.setOpaque(true); // Ensure the panel is opaque
                panel.repaint(); // Repaint to show the new color
            }
            // The check is made here to include the case when comp is a JPanel
            // as JPanel itself is a Container, and could contain other panels.
            if (comp instanceof Container) {
                changePanelBackground((Container) comp, backgroundColor);
            }
        }
    }


}

