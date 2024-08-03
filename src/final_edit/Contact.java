package final_edit;
public class Contact {
    private String name;
    private String phone;
    private String address;
    private String birthday;
    private String notes;

    public Contact(String name, String phone, String address, String birthday, String notes) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getNotes() {
        return notes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
