package services;

import java.time.LocalDate;
import java.util.*;

import dao.LoanDao;
import dao.LoanDaoImpl;
import exception.ServiceException;
import model.*;

/**
 * LoanServiceImpl
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
     */
    @Override
    public List<Loan> getList() throws ServiceException{
        List<Loan> allLoans = new ArrayList<Loan>();
        LoanDao loanDao = LoanDaoImpl.getInstance();

        try {
            allLoans = loanDao.getList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allLoans;
    };

    /**
     * Return current list of loan
     */
    @Override
	public List<Loan> getListCurrent() throws ServiceException{
        List<Loan> loanList = new ArrayList<Loan>();
        LoanDao loanDao = LoanDaoImpl.getInstance();

        try {
            loanList = loanDao.getListCurrent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loanList;
    };

    /**
     * Choose the loan list by member
     * @param idMembre
     */
    @Override
	public List<Loan> getListCurrentByMembre(int idMembre) throws ServiceException{
        List<Loan> currentLists = new ArrayList<Loan>();
        LoanDao loanDao = LoanDaoImpl.getInstance();

        try {
            currentLists = loanDao.getListCurrentByMembre(idMembre);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return currentLists;
    };

    /**
     * Choose the loan list by book
     * @param idLivre
     */
    @Override
	public List<Loan> getListCurrentByLivre(int idLivre) throws ServiceException{
        List<Loan> loanList = new ArrayList<Loan>();
        LoanDao loanDao = LoanDaoImpl.getInstance();

        try {
            loanList = loanDao.getListCurrentByLivre(idLivre);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loanList;
    };

    /**
     * Get the list by ID
     * @param id
     */
    @Override
    public Loan getById(int id) throws ServiceException{
        Loan chosenLoan = new Loan();
        LoanDao loanDao = LoanDaoImpl.getInstance();

        try {
            chosenLoan = loanDao.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    };
    
    /**
     * Set book to returned status
     * @param id
     */
    @Override
	public void returnBook(int id) throws ServiceException{
        LoanDao loanDao = LoanDaoImpl.getInstance();

        try {
            loanDao.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    /**
     * 
     */
    @Override
	public int count() throws ServiceException{
        int total = -1;

        return total;
    };

    /**
     * Check if need Loan is possible to happens or not
     * @param idLivre
     */
    @Override
	public boolean isLivreDispo(int idLivre) throws ServiceException{
        boolean disponible = false;

        return disponible;
    };

    /**
     * Check if need Loan is possible to happens or not (for members)
     * @param membre
     */
    @Override
	public boolean isEmpruntPossible(Member membre) throws ServiceException{
        boolean disponible = false;

        return disponible;
    };
}