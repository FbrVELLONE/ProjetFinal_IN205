package test;

import java.time.LocalDate;


import com.excilys.librarymanager.utils.FillDatabase;

import exception.ServiceException;
import model.*;
import services.*;

/**
 * ServiceTest class intended for testing the implemented services
 */
public class ServiceTest {
    public static void main(String[] args) throws Exception{
        FillDatabase.main(args);

        testBook();
        testMember();
        testLoan();
        
    }
    
    /**
     * Tests for book service class
     * @throws ServiceException
     */
    public static void testBook() throws ServiceException{
        BookService bookService = BookServiceImpl.getInstance();

        bookService.getList();
        bookService.getListDispo();
        bookService.getById(10);
        bookService.create("Halloween", "auteur", "215151651");
        // bookService.create("", "auteur", "215151651");
        Book bookTest = new Book(2, "The art of programation", "Joseph", "555331");
        bookService.update(bookTest);
        // Book bookTest1 = new Book(2, "", "Joseph", "555331");
        // bookService.update(bookTest1);
        bookService.delete(10);
        bookService.count();
    }

    /**
     * Tests for member service class
     * @throws ServiceException
     */
    public static void testMember() throws ServiceException{
        MemberService memberService = MemberServiceImpl.getInstance();

        memberService.getList();
        memberService.getListMembreEmpruntPossible();
        memberService.getById(6);
        memberService.create("Vellone", "Fabricio", "fabricio.vellone@ensta-paris.fr", "+330766625959", "Allée des techniques avancées", Member.Subscription.VIP);
        // memberService.create("Vellone", "", "fabricio.vellone@ensta-paris.fr", "+330766625959", "Allée des techniques avancées", Member.Subscription.VIP);
        // memberService.create("", "Fabricio", "fabricio.vellone@ensta-paris.fr", "+330766625959", "Allée des techniques avancées", Member.Subscription.VIP);
        Member memberTest = new Member(4, "Vellone", "Fabricio", "fabricio.vellone@ensta-paris.fr", "+330766625959", "Allée des techniques avancées", Member.Subscription.VIP);
        memberService.update(memberTest);
        // Member memberTest1 = new Member(4, "", "Fabricio", "fabricio.vellone@ensta-paris.fr", "+330766625959", "Allée des techniques avancées", Member.Subscription.VIP);
        // memberService.update(memberTest1);
        memberService.delete(4);
        memberService.count();
    }

    /**
     * Tests for loan service class
     * @throws ServiceException
     */
    public static void testLoan() throws ServiceException{
        LoanService loanService = LoanServiceImpl.getInstance();

        loanService.getList();
        loanService.getListCurrent();
        loanService.getListCurrentByMembre(4);
        loanService.getListCurrentByLivre(1);
        loanService.getById(3);
        loanService.create(3, 2, LocalDate.now());
        loanService.returnBook(4);
        loanService.count();
        loanService.isLivreDispo(4);
        // loanService.isEmpruntPossible(membre);
    }
}