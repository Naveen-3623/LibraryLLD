package model;

import java.time.LocalDate;

public class BorrowRecord {

    private int bookId;
    private int memberId;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    public BorrowRecord(int bookId, int memberId) {

        this.bookId = bookId;
        this.memberId = memberId;

        borrowDate = LocalDate.now();
        dueDate = borrowDate.plusDays(14);
    }

    public int getBookId() { return bookId; }

    public int getMemberId() { return memberId; }

    public LocalDate getBorrowDate() { return borrowDate; }

    public LocalDate getDueDate() { return dueDate; }
}