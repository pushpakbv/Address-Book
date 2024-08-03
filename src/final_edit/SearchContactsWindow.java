package final_edit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchContactsWindow {
    private JFrame frame;
    private JTextField searchField;
    private DefaultTableModel tableModel;
    private JTable table;
    private DatabaseHandler databaseHandler;

    public SearchContactsWindow(DatabaseHandler databaseHandler, JFrame parentFrame) {
        this.databaseHandler = databaseHandler;
        frame = new JFrame("Search Contacts");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        panel.add(new JLabel("Search:"), gbc);
        gbc.gridx++;
        searchField = new JTextField(20);
        panel.add(searchField, gbc);

        gbc.gridx++;
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchContacts();
            }
        });
        panel.add(searchButton, gbc);

        // Close button setup
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the window
            }
        });
        gbc.gridx++;
        panel.add(closeButton, gbc);

        frame.add(panel, BorderLayout.NORTH);

        String[] columnNames = {"Name", "Phone", "Address", "Birthday", "Notes"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        frame.setUndecorated(true);
        frame.setVisible(true);
    }

    private void searchContacts() {
        String search = searchField.getText();
        List<Contact> contacts = databaseHandler.searchContacts(search);
        tableModel.setRowCount(0);
        for (Contact contact : contacts) {
            tableModel.addRow(new Object[]{contact.getName(), contact.getPhone(), contact.getAddress(), contact.getBirthday(), contact.getNotes()});
        }
    }
}
