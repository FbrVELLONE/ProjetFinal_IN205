package model;

/**
 * Book
 */
public class Book{
    private static int ID = 0;
    private String title;
    private String author;
    private String isbn;
    private int id;


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
        this.id = ID;
    }

    /**
     * Basic constructor with given ID
     * @param id
     * @param title
     * @param author
     * @param isbn
     */
    public Book(int id, String title, String author, String isbn){
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.id = id;
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
     * Return book fixed ID
     * @return
     */
    public int getId(){
        return this.id;
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
        return "\nID: " + this.id +
            "\nTitle: " + this.title +
            "\nAuthor: " + this.author +
            "\nISBN: " + this.isbn +
            "\n";
    }
}