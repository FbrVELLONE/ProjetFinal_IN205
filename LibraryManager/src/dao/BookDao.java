package dao;
import java.util.List;

import exception.DaoException;
import model.Book;

public interface BookDao {
	public List<Book> getList() throws DaoException;
	public Book getById(int id) throws DaoException;
	public int create(String titre, String auteur, String isbn) throws DaoException;
	public void update(Book Book) throws DaoException;
	public void delete(int id) throws DaoException;
	public int count() throws DaoException;
}
