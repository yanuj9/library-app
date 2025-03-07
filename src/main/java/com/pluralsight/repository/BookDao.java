package com.pluralsight.repository;

import com.pluralsight.model.Book;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.*;

public class BookDao extends AbstractDao implements Dao <Book> {

    @Override
    public Optional<Book> findById(long id) {
        Optional<Book> book = Optional.empty();
        String sql = "SELECT ID, TITLE FROM BOOK WHERE ID = ?";

        try(
                Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql);
                ) {
            prepStmt.setLong(1, id);

            try(ResultSet rset = prepStmt.executeQuery();) {
                Book resBook = new Book();
                if(rset.next()) {
                    resBook.setId(rset.getLong("id"));
                    resBook.setTitle(rset.getString("title"));
                }
                book = Optional.of(resBook);
            }

        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }

        return book;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = Collections.emptyList();

        JdbcQueryTemplate<Book> template = new JdbcQueryTemplate<Book>() {
            @Override
            public Book mapItem(ResultSet rset) throws SQLException {
                Book book = new Book();
                book.setId(rset.getLong("ID"));
                book.setTitle(rset.getString("TITLE"));
                book.setRating(rset.getInt("RATING"));
                return book;
            }
        };

        books = template.queryForList("SELECT ID, TITLE, RATING FROM BOOK");

        return books;
    }

    //    @Override
//    public List<Book> findAll() {
//        List<Book> books = Collections.emptyList();
//        String sql = "SELECT * FROM BOOK";
//
//        try(Connection con = getConnection();
//            Statement stmt = con.createStatement();
//            ResultSet rset = stmt.executeQuery(sql);
//            ) {
//
//            books = new ArrayList<>();
//
//            while(rset.next()) {
//                Book book = new Book();
//                book.setId(rset.getLong("id"));
//                book.setTitle(rset.getString("title"));
//
//                books.add(book);
//            }
//
//        } catch (SQLException sqe) {
//            sqe.printStackTrace();
//        }
//        return books;
//    }

    @Override
    public Book create(Book book) {
        String sql = "INSERT INTO BOOK (TITLE) VALUES (?)";

        try(
                Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ) {

            prepStmt.setString(1, book.getTitle());
            prepStmt.executeUpdate();

            try(ResultSet genKeys = prepStmt.getGeneratedKeys();) {
                if(genKeys.next()) {
                    book.setId(genKeys.getLong(1));
                }
            }

        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }

        return book;
    }

    @Override
    public Book update(Book book) {
        String sql = "UPDATE BOOK SET TITLE = ? WHERE ID = ?";

        try(
                Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql);
                ) {

            prepStmt.setString(1, book.getTitle());
            prepStmt.setLong(2, book.getId());
            prepStmt.executeUpdate();

        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }

        return book;
    }

    @Override
    public int[] update(List<Book> books) {
        int [] recordsAffected = {};
        String sql = "UPDATE BOOK SET TITLE = ?, RATING = ? WHERE ID = ?";

        try(
                Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql);
                ) {

            for(Book book : books) {
                prepStmt.setString(1, book.getTitle());
                prepStmt.setInt(2,book.getRating());
                prepStmt.setLong(3,book.getId());

                prepStmt.addBatch();
            }

            recordsAffected = prepStmt.executeBatch();

        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }

        return recordsAffected;
    }

    @Override
    public int delete(Book book) {
        int rowsAffected = 0;
        String sql = "DELETE FROM BOOK WHERE ID = ?";

        try(
                Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql);
                ) {

            prepStmt.setLong(1, book.getId());
            rowsAffected = prepStmt.executeUpdate();

        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }

        return rowsAffected;
    }
}
