package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonContact extends Contacts implements Serializable {
    transient Scanner scanner = new Scanner(System.in);
    private static final long serialVersionUID = 1L;;

    private LocalDateTime ldt;
    private String name;
    private String surname;
    private String birthDate;
    private String gender;

    public PersonContact() {
        ldt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    public LocalDateTime getLdt1() {
        return ldt;
    }

    public String getName() {
        if (name != null) {
            return name;
        } else {
            return "[no data]";
        }
    }

    public String getSurname() {
        if (surname != null) {
            return surname;
        } else {
            return "[no data]";
        }
    }

    public String getBirthDate() {
        if (birthDate != null) {
            return birthDate;
        } else {
            return "[no data]";
        }
    }

    public String getGender() {
        if (gender != null) {
            return gender;
        } else {
            return "[no data]";
        }
    }

    public void setLdt1(LocalDateTime ldt1) {
        this.ldt = ldt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(String birthDate) {
        Pattern pattern = Pattern.compile("\\s+(?:0[1-9]|1[012])[-/.](?:0[1-9]|[12][0-9]|3[01])[-/.](?:19\\d{2}|20[01][0-9]|2022)\\b");
        Matcher matcher = pattern.matcher(birthDate);
        if (matcher.matches()) {
            this.birthDate = birthDate;
        } else {
            this.birthDate = null;
            System.out.println("Bad birth date!");
        }
    }

    public void setGender(String gender) {
        if (gender.equals("M") || gender.equals("F")) {
            this.gender = gender;
        } else {
            System.out.println("Bad gender!");
        }
    }

    @Override
    public void setAddress(String address) {
        return;
    }

    @Override
    public String toString() {
        return String.format(
                "Name: %s \nSurname: %s\nBirth date: %s\nGender: %s\nNumber: %s\nTime created: %s\nTime last edit: %s\n",
                getName(), getSurname(), getBirthDate(), getGender(), getNumber(), getLdt1(), LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)
        );
    }

    @Override
    public PersonContact addContact() {
        PersonContact contact = new PersonContact();
        System.out.print("Enter the name: > ");
        contact.setName(scanner.nextLine());
        System.out.print("Enter the surname: > ");
        contact.setSurname(scanner.nextLine());
        System.out.print("Enter the birth date: > ");
        contact.setBirthDate(scanner.nextLine());
        System.out.print("Enter the gender (M, F): > ");
        contact.setGender(scanner.nextLine().toUpperCase(Locale.ROOT));
        System.out.print("Enter the number: > ");
        contact.setNumber(scanner.nextLine());
        System.out.println("The record added.");
        return contact;
    }

    @Override
    public void editContact() {
        System.out.print("Select a field (name, surname, birth, gender, number): > ");
        String field = scanner.nextLine();
        if (field.equals("name")) {
            System.out.print("Enter name: > ");
            setName(scanner.nextLine());
        } else if (field.equals("surname")) {
            System.out.print("Enter surname > ");
            setSurname(scanner.nextLine());
        }else if (field.equals("birth")) {
            setBirthDate(scanner.nextLine());
        }else if (field.equals("gender")) {
            System.out.print("Enter gender: > ");
            setGender(scanner.nextLine().toUpperCase(Locale.ROOT));
        } else if (field.equals("number" )) {
            System.out.print("Enter number: > ");
            setNumber(scanner.nextLine());
        }
        System.out.println("Saved");
    }
}
