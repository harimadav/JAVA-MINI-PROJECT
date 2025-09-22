import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> books;
    private List<BorrowRecord> records;

    public Library() {
        books = new ArrayList<>();
        records = new ArrayList<>();

        // Sample books
        books.add(new Book(101, "Java Programming", "Herbert Schildt"));
        books.add(new Book(102, "Python Basics", "Guido van Rossum"));
        books.add(new Book(103, "C++ Fundamentals", "Bjarne Stroustrup"));
        books.add(new Book(104, "Database Systems", "Ramez Elmasri"));
    }

    // Display all books
    public void displayBooks() {
        System.out.println("\n--- Available Books ---");
        System.out.println("+-----+-------------------------+---------------------+----------+");
        System.out.printf("| %-3s | %-23s | %-19s | %-8s |%n", "ID", "Title", "Author", "Status");
        System.out.println("+-----+-------------------------+---------------------+----------+");
        for (Book book : books) {
            System.out.printf("| %-3d | %-23s | %-19s | %-8s |%n",
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    (book.isBorrowed() ? "Borrowed" : "Available"));
        }
        System.out.println("+-----+-------------------------+---------------------+----------+");
    }

    // Borrow a book
    public void borrowBook(String userName, int bookId, int days) {
        for (Book book : books) {
            if (book.getId() == bookId && !book.isBorrowed()) {
                book.borrowBook();
                BorrowRecord record = new BorrowRecord(userName, book, days);
                records.add(record);
                System.out.println("\n✅ Book borrowed successfully for " + days + " days.");
                System.out.printf("\nUser Details:\n%-15s %-25s %-15s %-10s%n", 
                        record.getUserName(), record.getBook().getTitle(), record.getBorrowDate(), record.getBorrowDays());
                return;
            }
        }
        System.out.println("❌ Book not available or already borrowed.");
    }

    // Return a book
    public void returnBook(String userName, int bookId) {
        for (BorrowRecord record : records) {
            if (record.getBook().getId() == bookId &&
                record.getUserName().equalsIgnoreCase(userName) &&
                record.getReturnDate() == null) {
                record.getBook().returnBook();
                record.markReturned();
                System.out.println("\n✅ Book returned successfully!");
                if (record.isOverdue()) {
                    System.out.println("⚠️  Warning: Book was returned late!");
                }
                return;
            }
        }
        System.out.println("❌ No matching borrow record found.");
    }

    // Renew a book
    public void renewBook(String userName, int bookId, int extraDays) {
        for (BorrowRecord record : records) {
            if (record.getBook().getId() == bookId &&
                record.getUserName().equalsIgnoreCase(userName) &&
                record.getReturnDate() == null) {
                record.extendDays(extraDays);
                System.out.println("\n✅ Book renewed successfully for " + extraDays + " extra days.");
                return;
            }
        }
        System.out.println("❌ No matching borrow record found for renewal.");
    }

    // Records → currently borrowed
    public void viewCurrentlyBorrowed() {
        System.out.println("\n--- Currently Borrowed Books ---");
        System.out.printf("%-15s %-25s %-15s %-10s %-12s%n", "User", "Book Title", "Borrow Date", "Days", "Due Date");
        boolean found = false;
        for (BorrowRecord record : records) {
            if (record.getReturnDate() == null) {
                System.out.printf("%-15s %-25s %-15s %-10d %-12s%n",
                        record.getUserName(),
                        record.getBook().getTitle(),
                        record.getBorrowDate(),
                        record.getBorrowDays(),
                        record.getDueDate() + (record.isOverdue() ? " ⚠️" : ""));
                found = true;
            }
        }
        if (!found) System.out.println("No books are currently borrowed.");
    }

    // Records → returned
    public void viewReturnedBooks() {
        System.out.println("\n--- Returned Books ---");
        System.out.printf("%-15s %-25s %-15s %-15s %-12s%n", "User", "Book Title", "Borrow Date", "Return Date", "Days");
        boolean found = false;
        for (BorrowRecord record : records) {
            if (record.getReturnDate() != null) {
                System.out.printf("%-15s %-25s %-15s %-15s %-12d%n",
                        record.getUserName(),
                        record.getBook().getTitle(),
                        record.getBorrowDate(),
                        record.getReturnDate(),
                        record.getBorrowDays());
                found = true;
            }
        }
        if (!found) System.out.println("No books have been returned yet.");
    }

    // Records → complete history
    public void viewCompleteHistory() {
        System.out.println("\n--- Complete Borrow Records ---");
        if (records.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        System.out.printf("%-15s %-25s %-15s %-15s %-12s%n", "User", "Book Title", "Borrow Date", "Return Date", "Days");
        for (BorrowRecord record : records) {
            System.out.printf("%-15s %-25s %-15s %-15s %-12d%n",
                    record.getUserName(),
                    record.getBook().getTitle(),
                    record.getBorrowDate(),
                    (record.getReturnDate() == null ? "Not Returned" : record.getReturnDate()),
                    record.getBorrowDays());
        }
    }
}