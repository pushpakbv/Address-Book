package final_edit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateContactWindow {
    private JFrame frame;
    private JTextField nameField, phoneField, addressField, birthdayField, notesField;
    private DatabaseHandler databaseHandler;
    private String oldName;

    public UpdateContactWindow(DatabaseHandler databaseHandler, JFrame parentFrame, String name, String phone, String address, String birthday, String notes) {
        this.databaseHandler = databaseHandler;
        this.oldName = name;
        frame = new JFrame("Update Contact");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx++;
        nameField = new JTextField(20);
        nameField.setText(name);
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Phone:"), gbc);
        gbc.gridx++;
        phoneField = new JTextField(20);
        phoneField.setText(phone);
        panel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Address:"), gbc);
        gbc.gridx++;
        addressField = new JTextField(20);
        addressField.setText(address);
        panel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Birthday:"), gbc);
        gbc.gridx++;
        birthdayField = new JTextField(20);
        birthdayField.setText(birthday);
        panel.add(birthdayField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Notes:"), gbc);
        gbc.gridx++;
        notesField = new JTextField(20);
        notesField.setText(notes);
        panel.add(notesField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContact();
                frame.dispose();
            }
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> frame.dispose());

        buttonPanel.add(updateButton);
        buttonPanel.add(cancelButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(parentFrame);
        frame.setVisible(true);
    }

    private void updateContact() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String birthday = birthdayField.getText();
        String notes = notesField.getText();

        if (!name.isEmpty() && !phone.isEmpty() && !address.isEmpty()) {
            Contact contact = new Contact(name, phone, address, birthday, notes);
            databaseHandler.updateContact(oldName, contact);
        } else {
            JOptionPane.showMessageDialog(frame, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
