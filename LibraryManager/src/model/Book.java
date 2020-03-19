package model;

/**
 * Book
 */
public class Book{
    private static int ID = 0;
    private String title;
    private String author;
    private String isbn;


    public Book(){
        
    }

    /**
     * Basic constructor with autoincrement ID
     * @param title
     * @param author
     * @param isbn
     */
    public Book(String title, String author, String isbn){
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        ID++;
    }

    /**
     * Default consctrutor
     */
    public Book(){}

    /**
     * get ID current number
     * @return id
     */
    public int getID(){
        return ID;
    }

    /**
     * get book's title
     * @return title
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * get book's author
     * @return author
     */
    public String getAuthor(){
        return this.author;
    }

    /**
     * get book's isbn
     * @return isbn
     */
    public String getIsbn(){
        return this.isbn;
    }

    /**
     * set book's title
     * @param title
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * set book's author
     * @param author
     */
    public void setAuthor(String author){
        this.author = author;
    }

    /**
     * set book's ISBN
     * @param isbn
     */
    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public String toString(){
        return "\nID: " + ID +
            "\nTitle: " + this.title +
            "\nAuthor: " + this.author +
            "\nISBN: " + this.isbn;
    }
}