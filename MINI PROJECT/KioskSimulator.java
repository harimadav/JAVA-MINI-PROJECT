import java.util.Scanner;

public class KioskSimulator {

    public static void main(String[] args) {

        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Book Lending Kiosk ---");
            System.out.println("1. Display Available Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Records");
            System.out.println("5. Renew Book"); // NEW FEATURE
            System.out.println("6. Exit"); // shifted Exit to 6
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    String borrower = sc.nextLine();
                    System.out.print("Enter Book ID to borrow: ");
                    int borrowId = sc.nextInt();
                    System.out.print("Enter number of days to borrow: "); // NEW FEATURE
                    int days = sc.nextInt();
                    library.borrowBook(borrower, borrowId, days);
                    break;
                case 3:
                    System.out.print("Enter your name: ");
                    String returner = sc.nextLine();
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    library.returnBook(returner, returnId);
                    break;
                case 4:
                    while (true) {
                        System.out.println("\n--- Records Menu ---");
                        System.out.println("1. View Currently Borrowed Books");
                        System.out.println("2. View Returned Books");
                        System.out.println("3. View Complete History");
                        System.out.println("4. Back to Main Menu");
                        System.out.print("Enter your choice: ");
                        int recChoice = sc.nextInt();
                        sc.nextLine(); // consume newline

                        switch (recChoice) {
                            case 1: library.viewCurrentlyBorrowed(); break;
                            case 2: library.viewReturnedBooks(); break;
                            case 3: library.viewCompleteHistory(); break;
                            case 4: System.out.println("Returning to Main Menu..."); break;
                            default: System.out.println("Invalid choice. Try again.");
                        }
                        if (recChoice == 4) break;
                    }
                    break;
                case 5: // NEW FEATURE: Renew book
                    System.out.print("Enter your name: ");
                    String renewUser = sc.nextLine();
                    System.out.print("Enter Book ID to renew: ");
                    int renewId = sc.nextInt();
                    System.out.print("Enter extra days to extend: ");
                    int extraDays = sc.nextInt();
                    library.renewBook(renewUser, renewId, extraDays);
                    break;
                case 6:
                    System.out.println("Thank you for using the kiosk!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

