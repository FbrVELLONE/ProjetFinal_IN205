import java.util.List;

import com.excilys.librarymanager.exception.DaoException;
import model.Member;

public interface MemberDao {
	public List<Member> getList() throws DaoException;
	public Member getById(int id) throws DaoException;
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException;
	public void update(Member Member) throws DaoException;
	public void delete(int id) throws DaoException;
	public int count() throws DaoException;
}
