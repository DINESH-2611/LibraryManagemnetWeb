package com.librarymanagement.Database;

import com.librarymanagement.model.Book;
import com.librarymanagement.model.IssueBook;
import com.librarymanagement.model.User;

import java.util.ArrayList;
import java.util.List;

public class LibraryManagementDatabase {
    static int defaultUserid =1,defaultBookId=1,defaultIssueBookid=1;
   private boolean isAdminLogin=true,isUserLogin=true;
    private static LibraryManagementDatabase libraryManagementDatabase;
    private String adminUsername="admin@gmail.com";
    private String password="admin@111";
    private static List<User> userList=new ArrayList<>();
    private static List<Book> bookList=new ArrayList<>();
    private  List<IssueBook> issueBookList=new ArrayList<>();
    private  List<IssueBook> allIssueBookList=new ArrayList<>();
    public static LibraryManagementDatabase getInstance(){
        if(libraryManagementDatabase==null){
            libraryManagementDatabase=new LibraryManagementDatabase();
            userList.add(new User(defaultUserid++,"Bala","bala@gmail.com","Bala@123",987654321));
            userList.add(new User(defaultUserid++,"Ram","ram@gmail.com","Ram@987",888888888));
            bookList.add(new Book(defaultBookId++,"JAVA BASICS","Herbert Schildt","O'Reilly Media, Inc",10));
            bookList.add(new Book(defaultBookId++,"MYSQL","Paul DuBois","Paul DuBois",10));
        }
        return libraryManagementDatabase;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public String getPassword() {
        return password;
    }

    public boolean checkAdmin(String email, String password) {
        return adminUsername.equals(email) && this.password.equals(password);
    }

    public int checkUser(String email, String password) {
        for (User user:userList){
            if(user.getEmail().equals(email) && user.getPassword().equals(password))
                return user.getId();
        }
        return -1;
    }

    public boolean adduser(String name, String email, String password, String mobile) {
        for (User user:userList){
            if(user.getEmail().equals(email) || user.getMobile()==Long.parseLong(mobile))
                return false;

        }
        userList.add(new User(defaultUserid++,name,email,password,Long.parseLong(mobile)));
        return true;
    }

    public List<User> getAllUsers() {
        return userList;
    }

    public List<Book> getAllBooks() {
        return bookList;
    }

    public boolean isAdminLogin() {
        return isAdminLogin;
    }

    public void setAdminLogin(boolean adminLogin) {
        isAdminLogin = adminLogin;
    }

    public boolean isUserLogin() {
        return isUserLogin;
    }

    public void setUserLogin(boolean userLogin) {
        isUserLogin = userLogin;
    }

    public boolean addBook(String name, String author, String publisher, String quantity) {
        for (Book book:bookList)
            if(book.getName().equals(name.toUpperCase()) && book.getAuthor().equals(author) && book.getPublisher().equals(publisher))
                return false;
        bookList.add(new Book(defaultBookId++,name.toUpperCase(),author,publisher,Integer.parseInt(quantity)));
        return true;
    }

    public List<Book> getBooksByName(String name) {
        List<Book> books=new ArrayList<>();
        for (Book book:bookList){
            if(book.getName().startsWith(name))
                books.add(book);
        }
        return books;
    }

    public User issueBook(int userId, int bookId) {
        IssueBook issueBook=new IssueBook(defaultIssueBookid++,bookId,userId,getBookName(bookId),getUserEmail(userId),"Not returned");
        issueBookList.add(issueBook);
        allIssueBookList.add(issueBook);
        User user=getUser(userId);
        Book book=getBook(bookId);
        book.setQuantity(book.getQuantity()-1);
        user.setBookCount(user.getBookCount()+1);
        user.getBookIds().add(bookId);
        return user;
    }

    private String getUserEmail(int userId) {
        for (User user:userList)
            if(user.getId()==userId)
                return user.getEmail();
        return null;
    }

    private String getBookName(int bookId) {
        for (Book book:bookList)
            if(book.getId()==bookId) return book.getName();
        return null;
    }

    private Book getBook(int bookId) {
        for (Book book:bookList){
            if(book.getId()==bookId)
                return book;
        }
        return null;
    }

    private User getUser(int userId) {
        for (User user:userList){
            if(user.getId()==userId)
                return user;
        }
        return null;
    }


    public List<IssueBook> getAllIssuedBooks() {
        return issueBookList;
    }

    public boolean validUserBook(int userId, String bookName) {
        List<Integer> bookIds=getUser(userId).getBookIds();
        Book book=null;
        boolean ok=false;
        User user=null;
        for (Integer i:bookIds){
            System.out.println(getBookName(i)+"iiiiiii");
            if(getBookName(i).contains(bookName)){
                ok=true;
                user=getUser(userId);
                book=getBook(i);

            }
        }
        if(ok){
            user.setBookCount(user.getBookCount()-1);
            book.setQuantity(book.getQuantity()+1);
            IssueBook issueBook=null;
            boolean remove=false;
            for (IssueBook issueBook1:issueBookList){
                if(issueBook1.getBookId()==book.getId() && issueBook1.getEmail().equals(user.getEmail())) {
                    issueBook = issueBook1;
                    remove=true;
                }
            }
//            if(remove) issueBookList.remove(issueBook);
            if(remove) issueBook.setStatus("Returned");
            return true;
        }
        return false;
    }

    public boolean reachLimit(int userId) {
        return getUser(userId).getBookCount()>=2;
    }

    public boolean userBookCount(int userId) {
        return getUser(userId).getBookCount()==0;
    }
}
