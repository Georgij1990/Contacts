package contacts;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactsDirector director = new ContactsDirector();
        System.out.println();
        while (true) {
            System.out.print("[menu] Enter action (add, list, search, count,  exit): > ");
            String action = scanner.nextLine();
            System.out.println();
            if (action.equals("add")) {
                System.out.print("Enter the type (person, organization): > ");
                String type = scanner.nextLine();
                if (type.equals("person")) {
                    director.add(new PersonContact());
                    System.out.println();
                } else {
                    director.add(new OrganisationContact());
                    System.out.println();
                }
            } else if (action.equals("search")) {
                director.search();
                System.out.println();
            } else if (action.equals("count")) {
                director.countContacts();
                System.out.println();
            } else if (action.equals("list")) {
                director.listContacts();
                System.out.println();
            } else if (action.equals("exit")) {
                break;
            } else {
                System.out.println("Here isn't such action");
            }
        }
    }
}
