# :book: Library-Catalog-System :- 


## :star:  Overview :-
This project implements a simple library catalog system where users can add books, search books by title or author, and list all books in the catalog. It uses Java and demonstrates object-oriented programming principles like encapsulation, methods, and classes.

## :sparkles: Features :- 
1) _Add a Book_: Allows the user to input a book's title and author to add it to the catalog.
2) _Search Books_: Searches for books based on a keyword that can match either the title or author.
3) _List All Books_: Displays all the books currently available in the catalog.
4) _Exit_: Closes the system.

## 🏛️: Classes :-
1) 👉 _**Book**_:
- Represents a book with attributes title and author.
- _**Constructor**_: Initializes the title and author.
- _**Getter methods**_: getTitle() and getAuthor() to retrieve book information.
- _**toString() method**_: Returns a string representation of the book in the format Title: <title>, Author:<author> .

2) 👉 _**LibraryCatalog**_:
- Manages a list of books and allows operations like adding a book, searching for books, and listing all books.
- _**addBook(String title, String author)**_: Adds a book to the catalog.
- _**searchBooks(String keyword)**_: Searches for books based on a keyword (title or author).
- _**listBooks()**_: Lists all books currently in the catalog.

3) 👉 _**LibrarySystem**_:
- The main class that provides the user interface for interacting with the library catalog. It prompts the user to perform various actions through a menu-driven approach.


## ⚙️ Code Usage :-
1) _**Add a Book**_: Choose option 1, then enter the book's title and author when prompted.
3) _**Search Books**_: Choose option 2, then enter a keyword (could be a part of the title or author name).
4) _**List All Books**_: Choose option 3 to see all books in the catalog.
5) _**Exit**_: Choose option 4 to exit the program.


## 💻 Example Interaction :-

_**Library Catalog System**_
1. Add a Book
2. Search Books
3. List All Books
4. Exit
- Enter your choice: 1
- Enter book title: The Great Gatsby
- Enter book author: F. Scott Fitzgerald
- Book added successfully!

_**Library Catalog System**_
1. Add a Book
2. Search Books
3. List All Books
4. Exit
- Enter your choice: 2
- Enter keyword to search (title/author): Gatsby
- Search results for: Gatsby
Title: The Great Gatsby, Author: F. Scott Fitzgerald
