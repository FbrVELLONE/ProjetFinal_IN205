package services;

import java.util.*;

import exception.ServiceException;
import model.Book;

/**
 * BookServiceImpl
 */
public class BookServiceImpl implements BookService{
    //Singleton
    private static BookServiceImpl instance;
    private BookServiceImpl(){};
    public static BookServiceImpl getInstance(){
        if (instance == null) instance = new BookServiceImpl();
        return instance;
    }

    /**
     * 
     */
    @Override
    public List<Book> getList() throws ServiceException{
        List<Book> totalList  = new ArrayList<Book>();

        return totalList;
    };

    /**
     * 
     */
    @Override
	public List<Book> getListDispo() throws ServiceException{
        List<Book> booksDispo  = new ArrayList<Book>();

        return booksDispo;
    };

    /**
     * 
     * @param id
     */
    @Override
	public Book getById(int id) throws ServiceException{
        Book chosenBook = new Book();

        return chosenBook;
    };

    /**
     * 
     * @param titre
     * @param auteur
     * @param isbn
     */
    @Override
	public int create(String titre, String auteur, String isbn) throws ServiceException{
        int id = -1;

        return id;
    };

    /**
     * 
     * @param livre
     */
    @Override
	public void update(Book livre) throws ServiceException{

    };

    /**
     * 
     * @param id
     */
    @Override
	public void delete(int id) throws ServiceException{

    };

    /**
     * 
     */
    @Override
	public int count() throws ServiceException{
        int total = -1;

        return total;
    };
}