import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Book {
    private final String title;
    private final String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author;
    }
}


class LibraryCatalog {
    private final List<Book> books;

    public LibraryCatalog() {
        books = new ArrayList<>();
    }

    
    public void addBook(String title, String author) {
        books.add(new Book(title, author));
        System.out.println("Book added successfully!");
    }

   
    public void searchBooks(String keyword) {
        System.out.println("Search results for: " + keyword);
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found matching the keyword.");
        }
    }

   
    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the catalog.");
        } else {
            System.out.println("Listing all books:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
}


public class LibrarySystem {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            LibraryCatalog catalog = new LibraryCatalog();
            int choice;
            
            do {
                System.out.println("\nLibrary Catalog System");
                System.out.println("1. Add a Book");
                System.out.println("2. Search Books");
                System.out.println("3. List All Books");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); 
                
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter book title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter book author: ");
                        String author = scanner.nextLine();
                        catalog.addBook(title, author);
                    }
                    case 2 -> {
                        System.out.print("Enter keyword to search (title/author): ");
                        String keyword = scanner.nextLine();
                        catalog.searchBooks(keyword);
                    }
                    case 3 -> catalog.listBooks();
                    case 4 -> System.out.println("Exiting the system. Goodbye!");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 4);
        }
    }
}
