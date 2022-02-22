package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class OrganisationContact extends Contacts implements Serializable {
    transient Scanner scanner = new Scanner(System.in);
    private static final long serialVersionUID = 1L;;

    LocalDateTime ldt;
    String organizationName;
    String address;

    OrganisationContact() {
        ldt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    public LocalDateTime getLdt() {
        return ldt;
    }

    public String getName() {
        return organizationName;
    }

    @Override
    public String getSurname() {
        return "";
    }

    @Override
    public String getBirthDate() {
        return null;
    }

    @Override
    public String getGender() {
        return null;
    }

    public String getAddress() {
        return address;
    }

    public void setLdt(LocalDateTime ldt) {
        this.ldt = ldt;
    }

    public void setName(String organizationName) {
        this.organizationName = organizationName;
    }

    @Override
    public void setSurname(String surname) {

    }

    @Override
    public void setBirthDate(String birthDate) {

    }

    @Override
    public void setGender(String gender) {

    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format(
                "Organization name: %s\nAddress: %s\nNumber: %s\nTime created: %s\nTime last edit: %s\n",
                getName(), getAddress(), getNumber(), getLdt(), LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)
        );
    }

    @Override
    public OrganisationContact addContact() {
        OrganisationContact contact = new OrganisationContact();
        System.out.print("Enter the organization name: > ");
        contact.setName(scanner.nextLine());
        System.out.print("Enter the address: > ");
        contact.setAddress(scanner.nextLine());
        System.out.print("Enter the number: > ");
        contact.setNumber(scanner.nextLine());
        System.out.println("The record added.");
        return contact;
    }

    @Override
    public void editContact() {
        System.out.print("Select a field (name, address, number): > ");
        String field = scanner.nextLine();
        if (field.equals("name")) {
            System.out.print("Enter name: > ");
            setName(scanner.nextLine());
        } else if (field.equals("address")) {
            System.out.print("Enter surname > ");
            setAddress(scanner.nextLine());
        }else if (field.equals("number")) {
            setNumber(scanner.nextLine());
        }
        System.out.println("Saved");
    }
}
