package dao;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import model.Loan;
import exception.DaoException;
import com.excilys.librarymanager.persistence.ConnectionManager;

/**
 * LoanDaoImpl
 */
public class LoanDaoImpl implements LoanDao{
    //Singleton
    private static LoanDaoImpl instance;
    private LoanDaoImpl(){};
    public static LoanDaoImpl getInstance(){
        if (instance == null)   instance = new LoanDaoImpl();
        return instance;
    }

    //Requisition Strings
    private final String _SelectAllQuery = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre ORDER BY dateRetour DESC;";
    private final String _SelectNoReturnedQuery = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour S NULL;";
    private final String _SelectNotReturnedMemQuery = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND membre.id = ?;";
	private final String _SelectNotReturnedLivQuery = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND livre.id = ?;";
	private final String _SelectOneQuery = "SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE e.id = ?;";
	private final String _CreateQuery = "INSERT INTO Emprunt (idMembre, idLivre, dateEmprunt, dateRetour) VALUES (?, ?, ?, ?);";
	private final String _UpdateQuery = "UPDATE Emprunt SET idMembre=?, idLivre=?,dateEmprunt=?, dateRetour=? WHERE id=?;";
    private final String _CountQuery = "SELECT COUNT(*) AS count FROM emprunt WHERE idMembre IN (SELECT id FROM membre) and idLivre IN (SELECT id FROM livre);";
    
    /**
     * Get all list of Loans dones
     */
    @Override
    public List<Loan> getList() throws DaoException{
        List<Loan> loans = new ArrayList<Loan>();

        //Try to open connection
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to prepare statement query
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(_SelectAllQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Try for exec the request
        ResultSet rst = null;
        try {
            rst = stmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to get all requests
        try {
            MemberDao memberDao = MemberDaoImpl.getInstance();
            BookDao bookDao = BookDaoImpl.getInstance();
            while(rst.next()){
                loans.add(new Loan(memberDao.getById(rst.getInt("idMembre")), bookDao.getById(rst.getInt("idLivre")), rst.getDate("dateEmprunt").toLocalDate(), rst.getDate("dateRetour") == null ? null : rst.getDate("dateRetour").toLocalDate()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return loans;
    };

    /**
     * Get currents lists of loans not yet returned
     */
    @Override
	public List<Loan> getListCurrent() throws DaoException{
        List<Loan> currentLoans = new ArrayList<Loan>();

        //Try to open connection
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to prepare statement query
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(_SelectNoReturnedQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try for exec the request
        ResultSet rst = null;
        try {
            rst = stmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to get all requests
        try {
            MemberDao memberDao = MemberDaoImpl.getInstance();
            BookDao bookDao = BookDaoImpl.getInstance();
            while (rst.next()) {
                currentLoans.add(new Loan(memberDao.getById(rst.getInt("idMembre")), bookDao.getById(rst.getInt("idLivre")), rst.getDate("dateEmprunt").toLocalDate(), rst.getDate("dateRetour") == null ? null : rst.getDate("dateRetour").toLocalDate()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return currentLoans;
    };

    /**
     * Get currents lists of loans not yet returned by one specific member
     * @param idMembre ID required
     */
    @Override
	public List<Loan> getListCurrentByMembre(int idMembre) throws DaoException{
        List<Loan> loanByMembre = new ArrayList<Loan>();

        //Try to open connection
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to prepare statement query
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(_SelectNotReturnedMemQuery);
            stmt.setInt(0, idMembre);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try for exec the request
        ResultSet rst = null;
        try {
            rst = stmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to get all requests
        try {
            MemberDao memberDao = MemberDaoImpl.getInstance();
            BookDao bookDao = BookDaoImpl.getInstance();
            while (rst.next()) {
                loanByMembre.add(new Loan(memberDao.getById(rst.getInt("idMembre")), bookDao.getById(rst.getInt("idLivre")), rst.getDate("dateEmprunt").toLocalDate(), rst.getDate("dateRetour") == null ? null : rst.getDate("dateRetour").toLocalDate()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return loanByMembre;
    };

    /**
     * List all currents Loands by books
     * @param idLivre ID required
     */
    @Override
	public List<Loan> getListCurrentByLivre(int idLivre) throws DaoException{
        List<Loan> loanByBooks = new ArrayList<Loan>();
        
        //Try to open connection
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to prepare statement query
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(_SelectNotReturnedLivQuery);
            stmt.setInt(1, idLivre);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try for exec the request
        ResultSet rst = null;
        try {
            rst = stmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to get all requests
        try {
            MemberDao memberDao = MemberDaoImpl.getInstance();
            BookDao bookDao = BookDaoImpl.getInstance();
            while (rst.next()) {
                loanByBooks.add(new Loan(memberDao.getById(rst.getInt("idMembre")), bookDao.getById(rst.getInt("idLivre")), rst.getDate("dateEmprunt").toLocalDate(), rst.getDate("dateRetour") == null ? null : rst.getDate("dateRetour").toLocalDate()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return loanByBooks;
    };

    /**
     * Retrieve  the loan list by one ID
     * @param ID Required by book
     */
    @Override
	public Loan getById(int id) throws DaoException{
        Loan chosenLoan = new Loan();

        //Try to open connection
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to prepare statement query
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(_SelectOneQuery);
            stmt.setInt(1, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try for exec the request
        ResultSet rst = null;
        try {
            rst = stmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to get all requests
        try {
            MemberDao memberDao = MemberDaoImpl.getInstance();
            BookDao bookDao = BookDaoImpl.getInstance();
            if (rst.next()) {
                chosenLoan = new Loan(rst.getInt("id"), memberDao.getById(rst.getInt("idMembre")), bookDao.getById(rst.getInt("idLivre")), rst.getDate("dateEmprunt").toLocalDate(), rst.getDate("dateRetour") == null ? null : rst.getDate("dateRetour").toLocalDate());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return chosenLoan; 
    };

    /**
     * Creat one list by ordre of itens given
     * @param idMembre Member id
     * @param idLivre Book id
     * @param dateLoan Loan date
     */
    @Override
	public void create(int idMembre, int idLivre, LocalDate dateLoan) throws DaoException{
        //Try to open connection
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to prepare statement query
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(_CreateQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, idMembre);
            stmt.setInt(2, idLivre);
            stmt.setString(3, dateLoan+"");
            stmt.setString(4, dateLoan.plusDays(7l)+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Try for exec the request
        try {
            stmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

    };

    /**
     * Updates into chosen loan
     * @param Loan ID LOAN
     */
    @Override
	public void update(Loan Loan) throws DaoException{
        //Try to open connection
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to prepare statement query
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(_UpdateQuery);
            stmt.setInt(1, Loan.getMember().getId());
            stmt.setInt(2, Loan.getBook().getId());
            stmt.setString(3, Loan.getLoanDate()+"");
            stmt.setString(4, Loan.getReturnDate()+"");
            stmt.setInt(5, Loan.getId());
                        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Try for exec the request
        try {
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    };

    /**
     * Return the number of loans existents 
     */
    @Override
	public int count() throws DaoException{
        int totalLoans = -1;

        //Try to open connection
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to prepare statement query
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(_CountQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Try for exec the request
        ResultSet rst = null;
        try {
            rst = stmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to get all requests
        try {
            if(rst.next()){
                totalLoans = rst.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalLoans;
    };
}