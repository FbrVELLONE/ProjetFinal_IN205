package test;

import java.util.ArrayList;
import java.util.List;

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

        //testBook();
        // testMember();
        testLoan();
        
    }
    
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

    public static void testLoan() throws ServiceException{
        LoanService loanService = LoanServiceImpl.getInstance();
        // Loan loanTest = new Loan(memberTest, bookTest, LocalDate.now(), LocalDate.now().plusDays(7l));
    }
}