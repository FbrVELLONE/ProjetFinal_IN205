import java.time.LocalDate;
import java.util.List;

import com.excilys.librarymanager.exception.DaoException;
import model.Loan;

public interface LoanDao {
	public List<Loan> getList() throws DaoException;
	public List<Loan> getListCurrent() throws DaoException;
	public List<Loan> getListCurrentByMembre(int idMembre) throws DaoException;
	public List<Loan> getListCurrentByLivre(int idLivre) throws DaoException;
	public Loan getById(int id) throws DaoException;
	public void create(int idMembre, int idLivre, LocalDate dateLoan) throws DaoException;
	public void update(Loan Loan) throws DaoException;
	public int count() throws DaoException;
}
