package final_edit;

import com.mongodb.client.*;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;

public class DatabaseHandler {
    private MongoCollection<Document> collection;

    public DatabaseHandler() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("addressBook");
        collection = database.getCollection("contacts");
    }


    public void addContact(Contact contact) {
        Document doc = new Document("name", contact.getName())
                .append("phone", contact.getPhone())
                .append("address", contact.getAddress())
                .append("birthday", contact.getBirthday())
                .append("notes", contact.getNotes());
        collection.insertOne(doc);
    }

    public List<Contact> getAllContacts(boolean sortByName) {
        List<Contact> contacts = new ArrayList<>();
        FindIterable<Document> documents = sortByName ? collection.find().sort(ascending("name")) : collection.find();
        for (Document doc : documents) {
            contacts.add(new Contact(
                    doc.getString("name"),
                    doc.getString("phone"),
                    doc.getString("address"),
                    doc.getString("birthday"),
                    doc.getString("notes")
            ));
        }
        return contacts;
    }

    public List<Contact> searchContacts(String search) {
        List<Contact> contacts = new ArrayList<>();
        FindIterable<Document> documents = collection.find(or(
                regex("name", search, "i"),
                regex("phone", search, "i"),
                regex("address", search, "i")
        ));
        for (Document doc : documents) {
            contacts.add(new Contact(
                    doc.getString("name"),
                    doc.getString("phone"),
                    doc.getString("address"),
                    doc.getString("birthday"),
                    doc.getString("notes")
            ));
        }
        return contacts;
    }

    public void deleteContact(String name) {
        collection.deleteOne(eq("name", name));
    }

    public void updateContact(String oldName, Contact newContact) {
        collection.updateOne(eq("name", oldName),
                new Document("$set", new Document("name", newContact.getName())
                        .append("phone", newContact.getPhone())
                        .append("address", newContact.getAddress())
                        .append("birthday", newContact.getBirthday())
                        .append("notes", newContact.getNotes())));
    }
}
