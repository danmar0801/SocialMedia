import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;

public class AdminWindow extends JFrame {
    UserManager userManager = UserManager.getInstance();
    // private value for singleton pattern
    private static AdminWindow instance = null;

    // object to pass the selected user from the tree to the openwindow function
    private Object selectedObject;

    private JTree tree;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode rootNode;

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



        rootNode = new DefaultMutableTreeNode(userManager.getRootGroup().getId());
        treeModel = new DefaultTreeModel(rootNode);
        // Build the tree model from the rootComponent
        buildTree(userManager.getRootGroup(), rootNode);

        // Create the JTree and add it to a JScrollPane
        tree = new JTree(treeModel);
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

        // Add sub-panels to right panel
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
            openUserPanel();
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

        // functions for buttons
        addUserButton.addActionListener(e->{
            addNewUser(userIDTextField.getText());
        });
        addGroupButton.addActionListener(e->{
            addNewGroup(groupIdTextField.getText());
        });

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

        //functions for buttons
        // total user count
        showUserTotalButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "The total user count is: " + userManager.getTotalUsers(),
                    "User Count", JOptionPane.INFORMATION_MESSAGE);
        });
        // total group count
        showGroupTotalButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "The total group count is: " + userManager.getTotalGroups(),
                    "Group Count", JOptionPane.INFORMATION_MESSAGE);
        });
        //total message count
        showMessagesTotalButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "The total message count is: " + userManager.getPostCount(),
                    "Message Count", JOptionPane.INFORMATION_MESSAGE);
        });
        // total positivity
        showPositiveTotalButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "The positivity score is: " + userManager.getPositivityScore(),
                    "Positivity Score", JOptionPane.INFORMATION_MESSAGE);
        });
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
    }

    public void openUserPanel(){
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

    }

    public void addNewUser(String ID){
        // Validate the ID (pseudo-code, implement this according to your validation rules)
        if (ID == null || ID.isEmpty()) {
            // Show some error message
            JOptionPane.showMessageDialog(null, "User ID cannot be empty.");
            return;
        }
        if (userManager.isUserExist(ID)) {
            // Show some error message
            JOptionPane.showMessageDialog(null, "User ID already exists.");
            return;
        }
        User newUser = new User(ID);
        userManager.getRootGroup().add(newUser);

        // Add the new user to the rootNode
        DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(newUser.getId());
        rootNode.add(userNode);

        // Notify the tree model
        treeModel.reload(rootNode);

        // Ensure the new user node is visible
        tree.scrollPathToVisible(new TreePath(userNode.getPath()));
    }
    public void addNewGroup(String ID){
        // Validate the ID (pseudo-code, implement this according to your validation rules)
        if (ID == null || ID.isEmpty()) {
            // Show some error message
            JOptionPane.showMessageDialog(null, "User ID cannot be empty.");
            return;
        }
        Groups newGroup = new Groups(ID);
        userManager.getRootGroup().add(newGroup);
        // Add the new user to the rootNode
        DefaultMutableTreeNode GroupNode = new DefaultMutableTreeNode(newGroup.getId());
        rootNode.add(GroupNode);
        // Notify the tree model
        treeModel.reload(rootNode);

        // Ensure the new user node is visible
        tree.scrollPathToVisible(new TreePath(GroupNode.getPath()));
    }
}

