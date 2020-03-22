package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.librarymanager.utils.FillDatabase;

import dao.*;
import exception.DaoException;
import model.*;


/**
 * DaoTest class to carry out tests related to the implemented daos
 */
public class DaoTest{
	public static void main(String[] args) throws Exception {
        FillDatabase.main(args);
        
        //testMember();
        //testBook();
    }	   

    public static void testMember() throws DaoException {
        MemberDao memberDao = MemberDaoImpl.getInstance();
        
        System.out.println("\n\tMembers tests\n");
        List<Member> totalList = new ArrayList<Member>();
        totalList = memberDao.getList();
        System.out.println("Total list utilized " + totalList);

        Member memberByID = new Member();
        memberByID = memberDao.getById(1);
        System.out.println("\n\tBy ID: " + memberByID);

        int idMember = memberDao.create("Vellone", "Fabricio", "Allée des techniques avancées", "fabricio.vellone@ensta-paris.fr", "+330766625959", Member.Subscription.VIP);
        System.out.println("\n\tNew id initialized: " + idMember);

        Member memberTest = new Member(12, "Araujo", "Jaojao", "joao.araujo@ensta-paris.fr", "+330762669559", "Allée des techniques avancées", Member.Subscription.PREMIUM);
        memberDao.update(memberTest);
        totalList = memberDao.getList();
        System.out.println("\n\tTotal list updated " + totalList);

        memberDao.delete(6);
        totalList = memberDao.getList();
        System.out.println("\n\tTotal list updated " + totalList);

        int totalMembers = memberDao.count();
        System.out.println("\n\tTotal members in current DB " + totalMembers);
    }

    public static void testBook() throws DaoException{
        BookDao bookDao = BookDaoImpl.getInstance();

        List<Book> bookList = new ArrayList<Book>();
        bookList = bookDao.getList();
        System.out.println("Total book list: " + bookList);

        Book bookByID = new Book();
        bookByID = bookDao.getById(3);
        System.out.println("\n\tBy ID: " + bookByID);

        int idTest = bookDao.create("The art of programation", "Joseph", "555331");
        System.out.println("\n\tNew id initialized: " + idTest);

        Book bookTest = new Book(4, "The art of programation", "Joseph", "555331");
        bookDao.update(bookTest);
        bookList = bookDao.getList();
        System.out.println("\n\tTotal book updated!: " + bookList);

        bookDao.delete(1);
        bookList = bookDao.getList();
        System.out.println("\n\tTotal book updated by delete!: " + bookList);

        int totalCurrentBooks = bookDao.count();
        System.out.println("\n\tTotal books in DB: " + totalCurrentBooks);
    }


}