package services;

import java.util.*;

import dao.BookDao;
import dao.BookDaoImpl;
import exception.ServiceException;
import model.Book;

/**
 * BookServiceImpl is the class responsible for making the connection with the database calls, passing the rules of service to Book class
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
     * Getting all book list inserted in DB
     * @return The total list
     */
    @Override
    public List<Book> getList() throws ServiceException{
        List<Book> totalList  = new ArrayList<Book>();
        BookDao bookDao = BookDaoImpl.getInstance();

        try {
            totalList = bookDao.getList();

            System.out.println("\n\tAll books gets: " + totalList);
        } catch (Exception e) {
            throw new ServiceException("Can't get total list!\n", e);
        }

        return totalList;
    };

    /**
     * Get the list of all disponibles books in our DB, by searching into total
     * amount and trying to find wich one is disponible
     * @return Current list of books disponibles
     */
    @Override
	public List<Book> getListDispo() throws ServiceException{
        List<Book> booksDispo  = new ArrayList<Book>();
        BookDao bookDao = BookDaoImpl.getInstance();
        
        try {
            LoanService loanService = LoanServiceImpl.getInstance();
            List<Book> allBooks  = new ArrayList<Book>();
            allBooks = bookDao.getList();
            
            for (int i = 0; i < allBooks.size(); i++) {
                if (loanService.isLivreDispo(allBooks.get(i).getId()))
                    booksDispo.add(allBooks.get(i));
            }
            
            System.out.println("\n\tAll books disponibles for booking: " + booksDispo);
        } catch (Exception e) {
            throw new ServiceException("Can't get disponible list!\n", e);
        }

        return booksDispo;
    };

    /**
     * Choose one book by your id
     * @param id 
     * @return The book
     */
    @Override
	public Book getById(int id) throws ServiceException{
        Book chosenBook = new Book();
        BookDao bookDao = BookDaoImpl.getInstance();

        try {
            chosenBook = bookDao.getById(id);

            System.out.println("\n\tThe chosen book was: " + chosenBook);
        } catch (Exception e) {
            throw new ServiceException("Can't get the chosen book!\n", e);
        }

        return chosenBook;
    };

    /**
     * Create one ligne in our DB with the given parameters
     * @param titre
     * @param auteur
     * @param isbn
     * @return The created ID
     */
    @Override
	public int create(String titre, String auteur, String isbn) throws ServiceException{
        int id = -1;
        BookDao bookDao = BookDaoImpl.getInstance();

        try {
            if (titre == null || titre == ""){
                throw new ServiceException("Empty Title! Can't create");
            } else{
                id = bookDao.create(titre, auteur, isbn);
                System.out.println("\n\tNew book instance created id: " + id);
            }

        } catch (Exception e) {
            throw new ServiceException("Can't create the specific book!\n", e);
        }

        return id;
    };

    /**
     * Update our book DB with the given one as parameter
     * @param livre
     */
    @Override
	public void update(Book livre) throws ServiceException{
        BookDao bookDao = BookDaoImpl.getInstance();

        try {
            if (livre.getTitle() == null || livre.getTitle() == ""){
                throw new ServiceException("Empty Title! Can't update");
            } else{
                bookDao.update(livre);
                System.out.println("\n\tThe book " + livre + " was successfully updated!");
            }
            
        } catch (Exception e) {
            throw new ServiceException("Can't be updated!\n", e);
        }
    };

    /**
     * Delete one tuple of our DB by selected given ID
     * @param id
     */
    @Override
	public void delete(int id) throws ServiceException{
        BookDao bookDao = BookDaoImpl.getInstance();

        try {
            bookDao.delete(id);

            System.out.println("\n\tThe book with id: " + id + " was deleted!");
        } catch (Exception e) {
            throw new ServiceException("Can't be deleted!\n", e);
        }
    };

    /**
     * Function which counts all books in DB
     * @return Total number of books
     */
    @Override
	public int count() throws ServiceException{
        int total = -1;
        BookDao bookDao = BookDaoImpl.getInstance();

        try {
            total = bookDao.count();

            System.out.println("\n\tNumber of books currently in the data base: " + total);
        } catch (Exception e) {
            throw new ServiceException("Can't be counted!\n", e);
        }

        return total;
    };
}