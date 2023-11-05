import javax.swing.*;
import java.awt.*;

public class AdminWindow extends JFrame {

    public AdminWindow() {
        // set the title of the window
        setTitle("My Window");

        // set the default close operation to exit the application
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set the layout manager
        getContentPane().setLayout(new BorderLayout());

        // Panel on the left
        JPanel leftPanel = new JPanel();
        leftPanel.add(new JLabel("Tree View"));
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPanel.setPreferredSize(new Dimension(300, getHeight())); // Set preferred width

        // Top right panel
        JPanel topRightPanel = new JPanel();
        topRightPanel.add(new JLabel("Control"));
        topRightPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        // Bottom right panel
        JPanel bottomRightPanel = new JPanel();
        bottomRightPanel.setLayout(new BoxLayout(bottomRightPanel, BoxLayout.Y_AXIS));
        bottomRightPanel.add(new JLabel("Stats Center"));
        bottomRightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        Button userTotal = new Button("Show User Total");
        Button groupTotal = new Button("Show Group Total");
        Button messTotal = new Button("Show Messages Total");
        Button positiveTotal = new Button("Show Positive Total");
        bottomRightPanel.add(userTotal);
        bottomRightPanel.add(groupTotal);
        bottomRightPanel.add(messTotal);
        bottomRightPanel.add(positiveTotal);

        // Right panel containing the top and bottom panels
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(topRightPanel);
        rightPanel.add(bottomRightPanel);

        // Set the resize behavior
        topRightPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        bottomRightPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        topRightPanel.setPreferredSize(new Dimension(getWidth() - leftPanel.getPreferredSize().width, getHeight() / 2));
        bottomRightPanel.setPreferredSize(new Dimension(getWidth() - leftPanel.getPreferredSize().width, getHeight() / 2));

        // Add panels to the frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        // set the size of the window
        setSize(1000, 700);
        // set the location of the window
        setLocationRelativeTo(null);
        // make the window visible
        setVisible(true);
    }

}
