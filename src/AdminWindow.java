import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class AdminWindow extends JFrame {
    UserManager userManager = UserManager.getInstance();
    // private value for singleton pattern
    private static AdminWindow instance = null;

    private Object selectedObject;


    private AdminWindow() {
        initializeComponents();
        applyColorScheme();
    }

    public static AdminWindow getInstance(){
        if(instance == null){
            instance = new AdminWindow();
        }
        return instance;
    }

    private void applyColorScheme() {
        Container contentPane = getContentPane();
        ColorRendering.changeButtonColors(contentPane);
        ColorRendering.changePanelBackground(contentPane);
        ColorRendering.changeLabelColors(contentPane);
        ColorRendering.changeTextFieldColors(contentPane);
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
        // Left panel for tree view
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(new JLabel("Tree View"));
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black,1,true));
        leftPanel.setPreferredSize(new Dimension(300, 700));
        // build users and groups
        // init the users
        User user1 = new User("dany0801");
        User user2 = new User("fluffy23");
        User user3 = new User("ace12");
        User user4 = new User("cryptoKing200");

        user1.addFollowing(user2);
        user1.addFollowing(user3);

        // create the groups
        Groups root = new Groups("Root");
        Groups csMajor = new Groups("CS majors");
        Groups Athletes = new Groups("Athletes");

        csMajor.add(user1);
        csMajor.add(user2);
        Athletes.add(user3);

        root.add(csMajor);
        root.add(Athletes);
        root.add(user4);
        // Build the tree model from the rootComponent
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(root.getId());
        buildTree(root, rootNode);



        // Create the JTree and add it to a JScrollPane
        JTree tree = new JTree(rootNode);
        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (selectedNode != null) {
                selectedObject = selectedNode.getUserObject();  // Store the selected user or group
            }
        });
        JScrollPane treeScrollPane = new JScrollPane(tree);
        leftPanel.add(treeScrollPane, BorderLayout.CENTER);

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

        openUserPanelButton.addActionListener(e -> {
            // Debugging output
            System.out.println("Selected Object: " + selectedObject);
            User users = userManager.getUserRef((String) selectedObject);

            if (users instanceof User) {
                UserWindow frame2 = new UserWindow(users);  // Open the window with the selected User
            } else {
                // Provide more detailed error information
                if (selectedObject == null) {
                    JOptionPane.showMessageDialog(this, "No item is currently selected.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "The selected item is not a user.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

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

    private void buildTree(UserGroupComponent component, DefaultMutableTreeNode node) {
        if (component instanceof Groups) {
            Groups group = (Groups) component;
            for (UserGroupComponent childComponent : group.getComponents()) {
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(childComponent.getId());
                node.add(childNode);
                buildTree(childComponent, childNode);
            }
        }
        // Users are leaves and have no children, so no action needed for them
        // If you want to handle Users differently, add an else block here
    }
}

