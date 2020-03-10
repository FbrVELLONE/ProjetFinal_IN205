import java.util.List;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.modele.Membre;

public interface MembreDao {
	public List<Membre> getList() throws DaoException;
	public Membre getById(int id) throws DaoException;
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException;
	public void update(Membre membre) throws DaoException;
	public void delete(int id) throws DaoException;
	public int count() throws DaoException;
}
