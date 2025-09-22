import java.time.LocalDate;

public class BorrowRecord {

    private String userName;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate dueDate; // NEW FEATURE
    private LocalDate returnDate;
    private int borrowDays; // NEW FEATURE

    public BorrowRecord(String userName, Book book, int borrowDays) { // updated constructor
        this.userName = userName;
        this.book = book;
        this.borrowDate = LocalDate.now();
        this.borrowDays = borrowDays; // NEW FEATURE
        this.dueDate = borrowDate.plusDays(borrowDays); // NEW FEATURE
        this.returnDate = null;
    }

    public void markReturned() { this.returnDate = LocalDate.now(); }

    // NEW FEATURE: Extend book
    public void extendDays(int extraDays) {
        this.dueDate = this.dueDate.plusDays(extraDays);
        this.borrowDays += extraDays;
    }

    public boolean isOverdue() {
        return returnDate == null && LocalDate.now().isAfter(dueDate);
    }

    public String getUserName() { return userName; }
    public Book getBook() { return book; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; } // NEW FEATURE
    public LocalDate getReturnDate() { return returnDate; }
    public int getBorrowDays() { return borrowDays; } // NEW FEATURE
}


