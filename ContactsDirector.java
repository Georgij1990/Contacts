package contacts;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ContactsDirector implements Serializable {
    transient Scanner scanner = new Scanner(System.in);
    private static final long serialVersionUID = 1L;

    private ArrayList<Contacts> contactsArrayList = new ArrayList<>();

    public void add(Contacts contact) {
        contactsArrayList.add(contact.addContact());
        try {
            String fileName = "contacts.txt";
            SerializationUtils.serialize(contactsArrayList, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeContact() {
        if (contactsArrayList.size() > 0) {
            listContacts();
            System.out.print("Select a record: > ");
            int index = scanner.nextInt();
            contactsArrayList.remove(index - 1);
            System.out.println("The record removed!");
        } else {
            System.out.println("No records to remove!");
        }
    }

    public void edit(int index) {
        if (contactsArrayList.size() > 0) {
            contactsArrayList.get(index - 1).editContact();
        } else {
            System.out.println("No records to edit!");
        }
    }

    public void listContacts() {
        int counter = 1;
        for (Contacts contact : contactsArrayList) {
            System.out.println(counter++ + ". " +  contact.getName() + " " + contact.getSurname());

        }
        System.out.println();
        System.out.print("[list] Enter action ([number], back): > ");
        String value = scanner.nextLine();
        int index = 0;
        if (!value.equals("back")) {
            index = Integer.parseInt(value);
            System.out.println(contactsArrayList.get(index - 1));
        }
        while (true) {
            System.out.print("[record] Enter action (edit, delete, menu): > ");
            String value1 = scanner.nextLine();
            if (value1.equals("edit")) {
                edit(index);
                System.out.println(contactsArrayList.get(index - 1));
            }  else if (value1.equals("delete")) {
                removeContact();
            } else if (value1.equals("menu")) {
                break;
            } else {
                System.out.println("No such action. Please, try again.");
            }
        }
    }

    public void countContacts() {
        if (contactsArrayList.size() > 0) {
            System.out.println(contactsArrayList.size());
        } else {
            System.out.println("The Phone Book has 0 records.");
        }
    }

    public void info() {
        listContacts();
        if (contactsArrayList.size() > 0) {
            System.out.print("Enter index to show info: > ");
            int index = Integer.parseInt(scanner.nextLine());
            System.out.println(contactsArrayList.get(index - 1));
        } else {
            System.out.println("No records to show!");
        }
    }

    public void search() {
        System.out.print("Enter search query: > ");
        String string = scanner.nextLine().toLowerCase(Locale.ROOT);
        int counter = 0;
        if (string.matches(".*[a-zA-Z]+.*")) {
            Pattern pattern = Pattern.compile(".*" + string + ".*");
            for (Contacts value : contactsArrayList) {
                Matcher matcher = pattern.matcher(value.getName().toLowerCase(Locale.ROOT) + " " + value.getSurname().toLowerCase(Locale.ROOT));
                if (matcher.find()) {
                    counter++;
                }
            }
            System.out.printf("Found %d results:\n", counter);
            for (Contacts value : contactsArrayList) {
                Matcher matcher = pattern.matcher(value.getName().toLowerCase(Locale.ROOT) + " " + value.getSurname().toLowerCase(Locale.ROOT));
                if (matcher.find()) {
                    System.out.println(value.getName() + " " + value.getSurname());
                }
            }
        } else if (string.matches(".*\\d+.*")) {
            Pattern pattern = Pattern.compile(".*" + string + ".*");
            for (Contacts value : contactsArrayList) {
                Matcher matcher = pattern.matcher(value.getNumber());
                if (matcher.find()) {
                    counter++;
                }
            }
            System.out.printf("Found %d results:\n", counter);
            for (Contacts value : contactsArrayList) {
                Matcher matcher = pattern.matcher(value.getNumber());
                if (matcher.find()) {
                    System.out.println(value.getNumber());
                }
            }
        }
    }

    public void list() {
        try {
            int counter = 1;
            ArrayList<Contacts> contacts = (ArrayList<Contacts>) SerializationUtils.deserialize("contacts.txt");
            for (Contacts contact : contacts) {
                System.out.println(counter++ + ". " +  contact.getName() + " " + contact.getSurname());

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}


