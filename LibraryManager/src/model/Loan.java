package model;

import java.time.LocalDate;
/**
 * Loan
 */
public class Loan{
    private static int ID = 0;
    private Member mMember;
    private Book mBook;
    private LocalDate loanDate, returnDate;
    private int id;

    /**
     * Basic constructor with autoincrement ID
     * @param mMember
     * @param mBook
     * @param loanDate
     * @param returnDate
     */
    public Loan(Member mMember, Book mBook, LocalDate loanDate, LocalDate returnDate){
        this.mBook = mBook;
        this.mMember = mMember;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        ID++;
        this.id = ID;
    }
    
    /**
     * Default constructor
     */
    public Loan(){}

    /**
     * Get Loan's member
     * @return member
     */
    public Member getMember(){
        return this.mMember;
    }

    /**
     * Get Loan's book
     * @return the book
     */
    public Book getBook(){
        return this.mBook;
    }

    /**
     * Get Loan's starts date
     * @return loan date
     */
    public LocalDate getLoanDate(){
        return this.loanDate;
    }

    /**
     * get ID current number
     * @return id
     */
    public int getID(){
        return ID;
    }

    /**
     * Return loan fixed ID
     * @return
     */
    public int getId(){
        return this.id;
    }

    /**
     * Get Loan's book return date
     * @return return date
     */
    public LocalDate getReturnDate(){
        return this.returnDate;
    }

    /**
     * set member
     * @param mMember
     */
    public void setMember(Member mMember){
        this.mMember = mMember;
    }

    /**
     * set loan's book
     * @param mBook
     */
    public void setBook(Book mBook){
        this.mBook = mBook;
    }

    /**
     * set loanDate
     * @param mLoanDate
     */
    public void setLoanDate(LocalDate mLoanDate){
        this.loanDate = mLoanDate;
    }

    /**
     * set return date
     * @param mReturnDate
     */
    public void setReturnDate(LocalDate mReturnDate){
        this.returnDate = mReturnDate;
    }

    public String toString(){
        return "\nID: " + this.id +
            "\nMember ID: " + this.mMember.getID() +
            "\nBook ID: " + this.mBook.getID() +
            "\nLoanDate: " + this.loanDate +
            "\nReturnDate: " + this.returnDate +
            "\n";
    }
}