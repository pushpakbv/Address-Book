package final_edit;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressBookUI implements ActionListener {
    private static final Logger logger = LoggerFactory.getLogger(AddressBookUI.class);

    private JFrame mainFrame;
    private JButton addButton, viewButton, searchButton, printButton;
    private DatabaseHandler databaseHandler;

    public AddressBookUI() {
        databaseHandler = new DatabaseHandler();

        // Set Nimbus Look and Feel for modern UI
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize main frame
        mainFrame = new JFrame("Address Book");
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full-screen mode
//        mainFrame.setUndecorated(true); // Remove window decorations

        addButton = new JButton("Add Contact");
        viewButton = new JButton("View Contacts");
        searchButton = new JButton("Search Contacts");
        printButton = new JButton("Print Contacts");

        Color buttonColor = new Color(249, 218, 208);
        addButton.setBackground(buttonColor);
        viewButton.setBackground(buttonColor);
        searchButton.setBackground(buttonColor);
        printButton.setBackground(buttonColor);

        Font myFont = new Font("Times New Roman", Font.BOLD, 25);
        addButton.setFont(myFont);
        viewButton.setFont(myFont);
        searchButton.setFont(myFont);
        printButton.setFont(myFont);

        addButton.addActionListener(this);
        viewButton.addActionListener(this);
        searchButton.addActionListener(this);
        printButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 50));
        buttonPanel.setBorder(new EmptyBorder(60, 120, 60, 120));
        buttonPanel.setBackground(new Color(186, 217, 162)); // Set background color

        // Add buttons to the panel
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(printButton);

        // Set main frame properties
        mainFrame.getContentPane().setBackground(new Color(173, 191, 151));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridBagLayout()); // Use GridBagLayout for centering

        // Add button panel to the center of the main frame
        mainFrame.add(buttonPanel, new GridBagConstraints());

        mainFrame.setSize(400, 300); // Set initial size (adjust as needed)
        mainFrame.setLocationRelativeTo(null); // Center the frame on the screen
        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            new AddContactWindow(databaseHandler, mainFrame);
        } else if (e.getSource() == viewButton) {
            new ViewContactsWindow(databaseHandler, mainFrame);
        } else if (e.getSource() == searchButton) {
            new SearchContactsWindow(databaseHandler, mainFrame);
        } else if (e.getSource() == printButton) {
            PDFGenerator pdfGenerator = new PDFGenerator(databaseHandler);
            pdfGenerator.generatePDF();
        }
    }

    public static void main(String[] args) {
        // Show splash screen for 3 seconds (3000 milliseconds)
        SplashScreen splash = new SplashScreen(3000);
        splash.showSplash();

        // Launch the main application
        SwingUtilities.invokeLater(AddressBookUI::new);
    }
}
