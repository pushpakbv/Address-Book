package final_edit;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewContactsWindow {
    private JFrame frame;
    private DefaultTableModel tableModel;
    private JTable table;
    private DatabaseHandler databaseHandler;

    public ViewContactsWindow(DatabaseHandler databaseHandler, JFrame parentFrame) {
        this.databaseHandler = databaseHandler;
        frame = new JFrame("View Contacts");

        String[] columnNames = {"Name", "Phone", "Address", "Birthday", "Notes"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton sortByNameButton = new JButton("Sort by Name");
        sortByNameButton.addActionListener(e -> loadContacts(true));

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteSelectedContact());

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> updateSelectedContact());

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> frame.dispose());

        buttonPanel.add(sortByNameButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(closeButton);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true);
        frame.setVisible(true);

        loadContacts(false);
    }

    private void loadContacts(boolean sortByName) {
        tableModel.setRowCount(0);
        java.util.List<Contact> contacts = databaseHandler.getAllContacts(sortByName);
        for (Contact contact : contacts) {
            tableModel.addRow(new Object[]{contact.getName(), contact.getPhone(), contact.getAddress(), contact.getBirthday(), contact.getNotes()});
        }
    }

    private void deleteSelectedContact() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String name = (String) tableModel.getValueAt(selectedRow, 0);
            databaseHandler.deleteContact(name);
            loadContacts(false);
        } else {
            JOptionPane.showMessageDialog(frame, "No contact selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateSelectedContact() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String name = (String) tableModel.getValueAt(selectedRow, 0);
            String phone = (String) tableModel.getValueAt(selectedRow, 1);
            String address = (String) tableModel.getValueAt(selectedRow, 2);
            String birthday = (String) tableModel.getValueAt(selectedRow, 3);
            String notes = (String) tableModel.getValueAt(selectedRow, 4);
            new UpdateContactWindow(databaseHandler, frame, name, phone, address, birthday, notes);
        } else {
            JOptionPane.showMessageDialog(frame, "No contact selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
