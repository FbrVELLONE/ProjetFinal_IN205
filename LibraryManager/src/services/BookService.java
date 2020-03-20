package services;

import java.util.List;

import exception.ServiceException;
import model.Book;

public interface BookService {

	public List<Book> getList() throws ServiceException;
	public List<Book> getListDispo() throws ServiceException;
	public Book getById(int id) throws ServiceException;
	public int create(String titre, String auteur, String isbn) throws ServiceException;
	public void update(Book livre) throws ServiceException;
	public void delete(int id) throws ServiceException;
	public int count() throws ServiceException;
}
