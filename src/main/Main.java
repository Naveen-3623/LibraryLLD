package main;

import java.util.Scanner;
import service.LibraryService;

public class Main {

    public static void main(String[] args) {

        LibraryService service = new LibraryService();
        Scanner scan = new Scanner(System.in);

        while(true) {

            System.out.println("\nLibrary Management System");
            System.out.println("1.Add Book");
            System.out.println("2.Register Member");
            System.out.println("3.Borrow Book");
            System.out.println("4.Return Book");
            System.out.println("5.Search Book");
            System.out.println("6.Member Report");
            System.out.println("7.Book Report");
            System.out.println("8.Top Borrowers");
            System.out.println("Enter the Choice:");
            int choice = scan.nextInt();
            switch(choice) {
                case 1:
                	service.addBook(); break;
                case 2:
                	service.registerMember(); break;
                case 3: 
                	service.borrowBook(); break;
                case 4: 
                	service.returnBook(); break;
                case 5: 
                	service.searchBook(); break;
                case 6: 
                	service.memberReport(); break;
                case 7: 
                	service.bookReport(); break;
                case 8: 
                	service.topBorrowers(); break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}