import java.util.List;

import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.modele.Livre;

public interface LivreService {

	public List<Livre> getList() throws ServiceException;
	public List<Livre> getListDispo() throws ServiceException;
	public Livre getById(int id) throws ServiceException;
	public int create(String titre, String auteur, String isbn) throws ServiceException;
	public void update(Livre livre) throws ServiceException;
	public void delete(int id) throws ServiceException;
	public int count() throws ServiceException;
}
