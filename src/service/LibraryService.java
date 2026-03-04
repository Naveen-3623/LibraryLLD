package service;

import model.*;
import java.util.*;
import java.time.LocalDate;

public class LibraryService {

    Map<Integer, Book> books = new HashMap<>();
    Map<Integer, Member> members = new HashMap<>();
    List<BorrowRecord> records = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    // Add Book
    public void addBook() {
        System.out.println("Enter Book ID:");
        int id = scan.nextInt();
        scan.nextLine();
        if (books.containsKey(id)) {
            System.out.println("Book ID already exists");
            return;
        }
        System.out.println("Enter Title:");
        String title = scan.nextLine();
        System.out.println("Enter Author:");
        String author = scan.nextLine();

        System.out.println("Enter Copies:");
        int copies = scan.nextInt();

        books.put(id, new Book(id, title, author, copies));

        System.out.println("Book added successfully");
    }
    public void registerMember() {

        System.out.println("Enter Member ID:");
        int id = scan.nextInt();
        scan.nextLine();

        if (members.containsKey(id)) {
            System.out.println("Member already exists");
            return;
        }

        System.out.println("Enter Name:");
        String name = scan.nextLine();

        members.put(id, new Member(id, name));

        System.out.println("Member registered");
    }
    public void borrowBook() {
        System.out.println("Enter Member ID:");
        int memberId = scan.nextInt();
        System.out.println("Enter Book ID:");
        int bookId = scan.nextInt();
        if (!members.containsKey(memberId)) {
            System.out.println("Member not found");
            return;
        }
        if (!books.containsKey(bookId)) {
            System.out.println("Book not found");
            return;
        }
        Book book = books.get(bookId);
        if (book.getCopies() == 0) {
            System.out.println("No copies available");
            return;
        }
        long borrowedCount = records.stream()
                .filter(r -> r.getMemberId() == memberId)
                .count();
        if (borrowedCount >= 3) {
            System.out.println("Member reached max borrow limit");
            return;
        }
        for (BorrowRecord r : records) {
            if (r.getBookId() == bookId && r.getMemberId() == memberId) {
                System.out.println("Member already borrowed this book");
                return;
            }
        }
        records.add(new BorrowRecord(bookId, memberId));
        book.setCopies(book.getCopies() - 1);
        members.get(memberId).increaseBorrowCount();
        System.out.println("Book borrowed successfully");
    }
    public void returnBook() {
        System.out.println("Enter Member ID:");
        int memberId = scan.nextInt();
        System.out.println("Enter Book ID:");
        int bookId = scan.nextInt();
        BorrowRecord found = null;
        for (BorrowRecord r : records) {
            if (r.getBookId() == bookId && r.getMemberId() == memberId) {
                found = r;
                break;
            }
        }
        if (found == null) {
            System.out.println("Borrow record not found");
            return;
        }
        LocalDate today = LocalDate.now();
        if (today.isAfter(found.getDueDate())) {
            long days = found.getDueDate().until(today).getDays();
            int fine = (int) days * 2;
            System.out.println("Fine: Rs." + fine);
        }
        books.get(bookId).setCopies(
                books.get(bookId).getCopies() + 1
        );
        records.remove(found);
        System.out.println("Book returned");
    }
    // Search Book
    public void searchBook() {
        scan.nextLine();
        System.out.println("Enter keyword:");
        String key = scan.nextLine().toLowerCase();

        for (Book b : books.values()) {

            if (b.getTitle().toLowerCase().contains(key)
                    || b.getAuthor().toLowerCase().contains(key)) {

                System.out.println(b.getBookId() + " " + b.getTitle() + " " + b.getAuthor());
            }
        }
    }
    // Member Report
    public void memberReport() {
        System.out.println("Enter Member ID:");
        int id = scan.nextInt();
        for (BorrowRecord r : records) {
            if (r.getMemberId() == id) {
                Book b = books.get(r.getBookId());
                System.out.println(b.getTitle() +
                        " Borrowed:" + r.getBorrowDate() +
                        " Due:" + r.getDueDate());
            }
        }
    }
    // Book Report
    public void bookReport() {
        System.out.println("Enter Book ID:");
        int id = scan.nextInt();
        for (BorrowRecord r : records) {
            if (r.getBookId() == id) {
                Member m = members.get(r.getMemberId());
                System.out.println("Borrowed by: " + m.getName());
            }
        }
    }
    // Top Borrowers
    public void topBorrowers() {
        List<Member> list = new ArrayList<>(members.values());
        list.sort((a, b) -> b.getTotalBorrowed() - a.getTotalBorrowed());
        for (Member m : list) {
            System.out.println(m.getName() +
                    " had borrowed  " + m.getTotalBorrowed() +"  books");
        }
    }
}