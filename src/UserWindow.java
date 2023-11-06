import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class UserWindow extends JFrame {
    JFrame frame = this;
    UserManager userManager = UserManager.getInstance();

    private User user;

    // things I need from the user
    /*
    * User ID
    * List of followers
    * posts from people that are followed*/

    //things I need to be able to update are
    /*
    * message posted by this user*/
    public UserWindow(User user) {
        this.user = user;
        initializeComponents();
        applyColorScheme();
    }

    private void applyColorScheme() {
        Container contentPane = getContentPane();
        ColorRendering.changeButtonColors(contentPane);
        ColorRendering.changePanelBackground(contentPane);
        ColorRendering.changeLabelColors(contentPane);
        ColorRendering.changeTextFieldColors(contentPane);
        ColorRendering.changeListBackground(contentPane);
    }

    private void initializeComponents() {
        // Configure the main window properties
        setTitle("User Panel: " + user.getId());
        setSize(800, 700);
        setResizable(false);
        setLocationRelativeTo(null);

        // Wrapper panel using BoxLayout to hold top and bottom panels
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));

        // Create and add the top and bottom panels to the wrapper panel
        JPanel topPanel = createTopPanel();
        JPanel bottomPanel = createBottomPanel();
        wrapperPanel.add(topPanel);
        wrapperPanel.add(Box.createVerticalGlue()); // This pushes both to the top and bottom
        wrapperPanel.add(bottomPanel);

        // Add the wrapper panel to the frame
        getContentPane().add(wrapperPanel); // By default, it's BorderLayout.CENTER

        // Display the window
        setVisible(true);
    }

    private JPanel createTopPanel(){
        // Data for the list
        String[] dataList = userManager.getUserFollowing(user);
        // Top panel setup
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setPreferredSize(new Dimension(800, 350));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // User input panel setup
        JPanel userInputPanel = new JPanel();
        userInputPanel.setLayout(new GridLayout(1, 2, 5, 5));
        userInputPanel.setMaximumSize(new Dimension(800, 50));

        // User ID text field setup
        JTextField userIdTextField = new JTextField("User ID");

        // Follow user button setup
        JButton followUserButton = new JButton("Follow User");


        // Add components to the user input panel
        userInputPanel.add(userIdTextField);
        userInputPanel.add(followUserButton);

        // List view for followers setup
        JList<String> followersList = new JList<>(dataList);
        JScrollPane scrollPane = new JScrollPane(followersList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        followUserButton.addActionListener(e -> {
            String input = userIdTextField.getText();
            user.addFollowing(userManager.getUserRef(input));
            // Get the updated following array
            String[] newDataList = userManager.getUserFollowing(user);
            // Set the new data list as the model for the JList
            followersList.setListData(newDataList);
        });

        // Adding components to the top panel
        topPanel.add(userInputPanel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topPanel.add(scrollPane);

        return topPanel;
    }



    private JPanel createBottomPanel(){
        // Data for the list
        String[] dataList = {"Item 1", "Item 2", "Item 3", "Item 4"};

        // Top panel setup
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setPreferredSize(new Dimension(800, 350));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // User input panel setup
        JPanel userInputPanel = new JPanel();
        userInputPanel.setLayout(new GridLayout(1, 2, 5, 5));
        userInputPanel.setMaximumSize(new Dimension(800, 50));

        // User ID text field setup
        JTextField userIdTextField = new JTextField("Message Text");

        // Follow user button setup
        JButton postMessButton = new JButton("Post");

        // Add components to the user input panel
        userInputPanel.add(userIdTextField);
        userInputPanel.add(postMessButton);

        // List view for followers setup
        JList<String> followersList = new JList<>(dataList);
        JScrollPane scrollPane = new JScrollPane(followersList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Adding components to the top panel
        bottomPanel.add(userInputPanel);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bottomPanel.add(scrollPane);

        return bottomPanel;
    }
}
