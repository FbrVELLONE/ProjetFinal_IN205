package test;
import java.time.LocalDate;

import dao.BookDao;
import dao.LoanDao;
import dao.MemberDao;
import dao.BookDaoImpl;
import dao.LoanDaoImpl;
import dao.MemberDaoImpl;
import exception.DaoException;
import model.Book;
import model.Loan;
import model.Member;
import model.Member.Subscription;
public class DaoTest{
	public static void main(String[] args) throws DaoException {

		// init member - Member(String lastName, String firstName, String email, String telephone, String adress, Subscription subscription)
        Member m = new Member("CAMPOS", "Joao", "joaocampos@ensta.fr", "078888999", "rue de printemps", Subscription.PREMIUM);
        
        //Book(String title, String author, String isbn)
        Book l = new Book("brothers karamazov", "dostoievsky", "9999999");
        
        //Loan(Member mMember, Book mBook, LocalDate loanDate, LocalDate returnDate)
		Loan loan = new Loan(m, l,  LocalDate.of(2020, 2, 1), LocalDate.now());
        
        // init dao 
        LoanDao loanDao = LoanDaoImpl.getInstance();
        
        // getlist
		loanDao.getList();
		System.out.println("aqui \n");
		loanDao.getListCurrent();
		System.out.println("\n");
		
    }	   
}