import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;


public class UserWindow extends JFrame {
    UserManager userManager = UserManager.getInstance();

    private final User user;

    //
    JList<String> followersList;
    JList<String> feedList;

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
        getContentPane().add(wrapperPanel);

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
        followersList = new JList<>(dataList);
        JScrollPane scrollPane = new JScrollPane(followersList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        followUserButton.addActionListener(e -> {
            String input = userIdTextField.getText();
            followNewUser(input);
        });

        // Adding components to the top panel
        topPanel.add(userInputPanel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topPanel.add(scrollPane);

        return topPanel;
    }

    private JPanel createBottomPanel(){
        // Data for the list
        String[] dataList = user.getFeed();

        // Bottom panel setup
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setPreferredSize(new Dimension(800, 350));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // User input panel setup
        JPanel userInputPanel = new JPanel();
        userInputPanel.setLayout(new GridLayout(1, 3, 5, 5));
        userInputPanel.setMaximumSize(new Dimension(800, 50));

        // User ID text field setup
        JTextField postTextField = new JTextField("Message Text");

        // Post button setup
        JButton postMessButton = new JButton("Post");

        // refresh button setup
        JButton refreshButton = new JButton("Refresh");

        // Add components to the user input panel
        userInputPanel.add(postTextField);
        userInputPanel.add(postMessButton);
        userInputPanel.add(refreshButton);

        // List view for feed
        feedList = new JList<>(dataList);
        JScrollPane scrollPane = new JScrollPane(feedList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // add post to user
        postMessButton.addActionListener(e -> {
            String input = postTextField.getText();
            postNewMessage(input);
        });
        // refresh feed when button is pressed
        refreshButton.addActionListener(e -> {
            refreshFeed();
        });

        // Adding components to the top panel
        bottomPanel.add(userInputPanel);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bottomPanel.add(scrollPane);

        return bottomPanel;
    }

    private void followNewUser(String input){
        // input validation
        if (input == null || input.isEmpty()) {
            // Show some error message
            JOptionPane.showMessageDialog(null, "User ID cannot be empty.", "Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        // check if the user exists
        if (!userManager.isUserExist(input)){
            JOptionPane.showMessageDialog(null, "User ID doesn't exist", "Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        user.addFollowing(userManager.getUserRef(input));
        // Get the updated following array
        String[] newDataList = userManager.getUserFollowing(user);
        // Set the new data list as the model for the JList
        followersList.setListData(newDataList);
    }

    private void postNewMessage(String input){
        // input validation
        if (input == null || input.isEmpty()) {
            // Show some error message
            JOptionPane.showMessageDialog(null, "Post text cannot be empty.", "Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        user.addPost(input);
        String[] newDataList = user.getFeed();
        feedList.setListData(newDataList);
    }
    private void refreshFeed(){
        String[] newDataList = user.getFeed();
        feedList.setListData(newDataList);
    }
}
