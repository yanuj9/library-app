package com.pluralsight;

import com.pluralsight.model.Book;
import com.pluralsight.repository.BookDao;
import com.pluralsight.repository.Dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class App {
    public static void main( String[] args ) {
        Dao<Book> bookDao = new BookDao();
        List<Book> books = bookDao.findAll();
        for (Book book : books) {

            System.out.println("Id: " + book.getId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Rating: " + book.getRating());
            System.out.println("=====================================");
        }

//        Optional<Book> optBook = bookDao.findById(1);
//
//        if(optBook.isPresent()) {
//            Book book = optBook.get();
//
//            System.out.println("Id: " + book.getId());
//            System.out.println("Title: " + book.getTitle());
//
//            book.setTitle("Effective Java: Second Edition");
//
//            bookDao.update(book);
//        }
//
//        Book newBook = new Book();
//        newBook.setTitle("The River Why");
//        newBook = bookDao.create(newBook);
//
//        System.out.println("Id: " + newBook.getId());
//        System.out.println("Title: " + newBook.getTitle());
//
//        int numDeleted = bookDao.delete(newBook);
//
//        System.out.println("Number of records Deleted: " + numDeleted);

//        books = bookDao.findAll();
//
//        List<Book> updatedEntries =
//                books.stream()
//                        .peek(b -> b.setRating(5))
//                        .collect(Collectors.toList());
//
//        bookDao.update(updatedEntries);

    }
}
