package final_edit;

import com.mongodb.client.MongoCollection;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.bson.Document;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class PDFGenerator {
    private DatabaseHandler databaseHandler;

    public PDFGenerator(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    public void generatePDF() {
        PDDocument document = new PDDocument();

        try {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(25, 750);

            List<Contact> contacts = databaseHandler.getAllContacts(true);
            for (Contact contact : contacts) {
                contentStream.showText("Name: " + contact.getName());
                contentStream.newLine();
                contentStream.showText("Phone: " + contact.getPhone());
                contentStream.newLine();
                contentStream.showText("Address: " + contact.getAddress());
                contentStream.newLine();
                contentStream.showText("Birthday: " + contact.getBirthday());
                contentStream.newLine();
                contentStream.showText("Notes: " + contact.getNotes());
                contentStream.newLine();
                contentStream.newLine();
            }

            contentStream.endText();
            contentStream.close();

            document.save("contacts.pdf");
            document.close();

            JOptionPane.showMessageDialog(null, "PDF generated successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
