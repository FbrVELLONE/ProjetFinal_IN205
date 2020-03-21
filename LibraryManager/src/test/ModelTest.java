package test;

import java.time.LocalDate;

import model.*;
/**
 * ModelTest Class concerning the implementation tests of the book, member and loan models
 */
public class ModelTest {

    public static void main(String[] args) {
        Book bookTest = new Book("The art of programation", "Joseph", "555331");
        Member memberTest = new Member("Vellone", "Fabricio", "fabricio.vellone@ensta-paris.fr", "+330766625959", "Allée des techniques avancées", Member.Subscription.VIP);
        Loan loanTest = new Loan(memberTest, bookTest, LocalDate.now(), LocalDate.now().plusDays(7l));
        
        Book bookTest1 = new Book("The art of programation 2", "Joseph show", "22659");
        Member memberTest1 = new Member("Araujo", "Jaojao", "joao.araujo@ensta-paris.fr", "+330762669559", "Allée des techniques avancées", Member.Subscription.PREMIUM);
        Loan loanTest1 = new Loan(memberTest1, bookTest1, LocalDate.now(), LocalDate.now().plusDays(7l));
        
        Book bookTest2 = new Book("The art of programation 3", "Lucas", "123456789");
        Loan loanTest2 = new Loan(memberTest, bookTest2, LocalDate.now(), LocalDate.now().plusDays(7l));
        
        System.out.println("\tMembers test:\n");
        System.out.println("Member: " + memberTest);
        System.out.println("Member: " + memberTest1);
        
        System.out.println("\tBooks test:\n");
        System.out.println("book: " + bookTest);
        System.out.println("book: " + bookTest1);
        System.out.println("book: " + bookTest2);

        System.out.println("\tLoan tests\n");
        System.out.println("Loan informations: " + loanTest);
        System.out.println("Loan informations: " + loanTest1);
        System.out.println("Loan informations: " + loanTest2);
    }
    
}