
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exception.DaoException;
import model.Book;
import com.excilys.librarymanager.persistence.ConnectionManager;

public class BookDaoImpl implements BookDao {
    //Singleton
    private static BookDaoImpl instance;
    private BookDaoImpl(){};
    public static BookDaoImpl getInstance(){
        if (instance == null) instance = new BookDaoImpl();
        return instance;
    }

    private static final String SELECT_ALL = "SELECT id, titre, auteur, isbn FROM livre ";
    private static final String SELECT_ONE = "SELECT id, titre, auteur, isbn FROM livre WHERE id=?;";
    private static final String CREATE = "INSERT INTO livre(titre, auteur, isbn) VALUES (?, ?, ?);";
	private static final String UPDATE = "UPDATE livre SET titre = ?, auteur = ?, isbn = ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM livre WHERE id=?;";
    private static final String COUNT= "SELECT count(*) AS count FROM livre";

    /** 
     * request that returns list of books
    */
    @Override
    public List<Book> getList() throws DaoException{
        List<Book> listOfBooks = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
             ResultSet result = preparedStatement.executeQuery();) {

            while (result.next()){
                listOfBooks.add(new Book(result.getInt("id"), result.getString("titre"), result.getString("auteur"), result.getString("isbn")));
            }

        } catch (SQLException e) {
            throw new DaoException("Error while uploading list of books from the database", e);
        }

        return listOfBooks;
    }



    public ResultSet GetByIdStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    /**
     * request that returns one required book, fetched by its ID
     * @param id identifier of requested book
     * @return book 
     */
    @Override
    public Book getById(int id) throws DaoException{
        Book book = new Book();
        
        try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE);
            ResultSet result = GetByIdStatement(preparedStatement, id);){
            
            if (result.next()) {
                book = new Book(result.getInt("id"), result.getString("titre"), result.getString("auteur"), result.getString("isbn"));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while uploading a books whose id is " + id + " from the database", e);
        }

        return book;
    }



    public ResultSet CreateStatement(PreparedStatement preparedStatement, String titre, String auteur, String isbn ) throws SQLException {
        preparedStatement.setString(1, titre);
        preparedStatement.setString(2, auteur);
        preparedStatement.setString(3, isbn);
        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
    }
    
    /**
     * request to create a book that is created using its title, author and isbn
     * @param titre
     * @param auteur
     * @param isbn
     * @return the new book's id
     */
    @Override
	public int create (String titre, String auteur, String isbn) throws DaoException{
        int id = -1;
        
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
             ResultSet result = CreateStatement(preparedStatement, titre, auteur, isbn);) { 

            if (result.next()) {
                id = result.getInt(1);
            }

        } catch (SQLException e) {
            throw new DaoException("Error. Cannot create book" + titre, e);
        }

        return id;
    }

    /**
     * request to update existing book
     * @param book
     */
    @Override
	public void update(Book book) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);) {
            
			preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getIsbn());
            preparedStatement.setInt(4, book.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
			throw new DaoException("Error while updating a book " + book + "in the database", e);
		}
    }

    /**
     * request to delete existing book from database; book fetched by its id
     * @param id
     */
    @Override
    public void delete(int id) throws DaoException{
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE);) {
            
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            
		} catch (SQLException e) {
			throw new DaoException("Error while deleting a book whose id is " + id + " from the database", e);
		}
    }


    /**
     * request to count the number of existing books in database
     * @return the number of books
     */
    @Override
    public int count() throws DaoException{
        int nombreDeLivres = -1;

        try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(COUNT);
             ResultSet result = preparedStatement.executeQuery();) {

            if (result.next()) {
                nombreDeLivres = result.getInt(1);
                
            }
        } catch (SQLException e) {
            throw new DaoException("Error while counting the number of books in the database", e);
        } 
        
        return nombreDeLivres;
    }


}