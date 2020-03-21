package services;

import java.time.LocalDate;
import java.util.*;

import dao.LoanDao;
import dao.LoanDaoImpl;
import exception.ServiceException;
import model.*;

/**
 * LoanServiceImpl is the class responsible for making the connection with the database calls, passing the rules of service to Loan class
 */
public class LoanServiceImpl implements LoanService{
    //Singleton
    private static LoanServiceImpl instance;
    private LoanServiceImpl(){};
    public static LoanServiceImpl getInstance(){
        if (instance == null)   instance = new LoanServiceImpl();
        return instance;
    }

    /**
     * Get all loans existents 
     * @return The total list
     */
    @Override
    public List<Loan> getList() throws ServiceException{
        List<Loan> allLoans = new ArrayList<Loan>();
        LoanDao loanDao = LoanDaoImpl.getInstance();

        try {
            allLoans = loanDao.getList();

            System.out.println("All loans list: " + allLoans);
        } catch (Exception e) {
            throw new ServiceException("Can't get total list!\n", e);
        }

        return allLoans;
    };

    /**
     * Return current list of loan actives
     * @return The current list
     */
    @Override
	public List<Loan> getListCurrent() throws ServiceException{
        List<Loan> loanList = new ArrayList<Loan>();
        LoanDao loanDao = LoanDaoImpl.getInstance();

        try {
            loanList = loanDao.getListCurrent();

            System.out.println("All currents loans not returneds yet: " + loanList);
        } catch (Exception e) {
            throw new ServiceException("Can't get current list!\n", e);
        }

        return loanList;
    };

    /**
     * Choose the loan list by member
     * @param idMembre
     * @return The list grouped by members
     */
    @Override
	public List<Loan> getListCurrentByMembre(int idMembre) throws ServiceException{
        List<Loan> currentLists = new ArrayList<Loan>();
        LoanDao loanDao = LoanDaoImpl.getInstance();

        try {
            currentLists = loanDao.getListCurrentByMembre(idMembre);

            System.out.println("All currents loans not returneds yet by member: " + currentLists);
        } catch (Exception e) {
            throw new ServiceException("Can't get current list by member!\n", e);
        }

        return currentLists;
    };

    /**
     * Choose the loan list by book
     * @param idLivre
     * @return The list grouped by books
     */
    @Override
	public List<Loan> getListCurrentByLivre(int idLivre) throws ServiceException{
        List<Loan> loanList = new ArrayList<Loan>();
        LoanDao loanDao = LoanDaoImpl.getInstance();

        try {
            loanList = loanDao.getListCurrentByLivre(idLivre);

            System.out.println("All currents loans not returneds yet by books: " + loanList);
        } catch (Exception e) {
            throw new ServiceException("Can't get current list by book!\n", e);
        }

        return loanList;
    };

    /**
     * Get the list by ID
     * @param id
     * @return The choose Loan
     */
    @Override
    public Loan getById(int id) throws ServiceException{
        Loan chosenLoan = new Loan();
        LoanDao loanDao = LoanDaoImpl.getInstance();

        try {
            chosenLoan = loanDao.getById(id);

            System.out.println("Current loan not returned yet: " + chosenLoan);
        } catch (Exception e) {
            throw new ServiceException("Can't get this specific booking!\n", e);
        }
        return chosenLoan;
    };


    /**
     * Create access interface to DB requisitions
     */
    @Override
    public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException{
        LoanDao loanDao = LoanDaoImpl.getInstance();

        try {
            loanDao.create(idMembre, idLivre, dateEmprunt);

            System.out.println("\nCreate new loan sucessfully!\n");
        } catch (Exception e) {
            throw new ServiceException("Can't be created!\n", e);
        }
    };
    
    /**
     * Set localTime as returned time of chosen Loan
     * @param id
     */
    @Override
	public void returnBook(int id) throws ServiceException{
        LoanDao loanDao = LoanDaoImpl.getInstance();

        try {
            Loan update = loanDao.getById(id);
            update.setReturnDate(LocalDate.now());
            loanDao.update(update);

            System.out.println("Loan " + update + "successfull updated! Book returned!");
        } catch (Exception e) {
            throw new ServiceException("Can't be returned yet!\n", e);
        }
    };

    /**
     * Function responsable for initialize and fiscalize the rules of counter
     * @return The total in DB
     */
    @Override
	public int count() throws ServiceException{
        int total = -1;
        LoanDao loanDao = LoanDaoImpl.getInstance();

        try {
            total = loanDao.count();

            System.out.println("Total members: " + total);
        } catch (Exception e) {
            throw new ServiceException("Can't be counted!\n", e);
        }
        return total;
    };

    /**
     * Check if a book can or cannot be borrowed
     * @param idLivre
     * @return true if is disponible and false otherwise
     */
    @Override
	public boolean isLivreDispo(int idLivre) throws ServiceException{
        boolean disponible = false;
        LoanDao loanDao = LoanDaoImpl.getInstance();

        try {
            disponible = loanDao.getListCurrentByLivre(idLivre).isEmpty();
            System.out.println("Status of chosen Book: " + disponible);

            return disponible;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return disponible;
    };

    /**
     * Check whether or not a member can borrow books
     * @param membre
     * @return true if is disponible and false otherwise
     */
    @Override
	public boolean isEmpruntPossible(Member membre) throws ServiceException{
        boolean disponible = false;
        LoanDao loanDao = LoanDaoImpl.getInstance();

        try {
            disponible = loanDao.getListCurrentByMembre(membre.getID()).isEmpty();
            System.out.println("The member can get another book?: " + disponible);

            return disponible;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return disponible;
    };
}