package model;

/**
 * Members
 */
public class Member {

    /**
     * Enum type related to existing types of Subscription
     */
    public enum Subscription{
        BASIC,
        PREMIUM,
        VIP
    }

    private static int ID = 0;
    private String lastName, firstName, email, telephone, adress;
    private Subscription mSubscription;
    private int id;

    /**
     * Basic constructor which inscrements amount of ID
     * @param lastName
     * @param firstName
     * @param email
     * @param telephone
     * @param adress
     * @param subscription
     */
    public Member(String lastName, String firstName, String email, String telephone, String adress, Subscription subscription){
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.telephone = telephone;
        this.adress = adress;
        ID++;
        this.id = ID;
        this.mSubscription = subscription;
    }

    /**
     * Basic constructor which we gave one id
     * @param id
     * @param lastName
     * @param firstName
     * @param email
     * @param telephone
     * @param adress
     * @param subscription
     */
    public Member(int id, String lastName, String firstName, String email, String telephone, String adress, Subscription subscription){
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.telephone = telephone;
        this.adress = adress;
        this.id = id;
        this.mSubscription = subscription;
    }
    
    /**
     * Default consctrutor
     */
    public Member(){}

    /**
     * Get last ID number
     * @return actual ID
     */
    public int getID(){
        return ID;
    }

    /**
     * Return member fixed ID
     * @return
     */
    public int getId(){
        return this.id;
    }

    /**
     * Get last name
     * @return last name
     */
    public String getLastName(){
        return this.lastName;
    }

    /**
     * Get first name
     * @return first name
     */
    public String getFirstName(){
        return this.firstName;
    }

    /**
     * Get email adress
     * @return email adress
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * Get tel number
     * @return mobile number
     */
    public String getTelephone(){
        return this.telephone;
    }

    /**
     * Get member's adress
     * @return adress
     */
    public String getAdress(){
        return this.adress;
    }

    /**
     * Get member's subscription type
     * @return
     */
    public Subscription getSubscription(){
        return this.mSubscription;
    }


    /**
     * Set last name
     * @param lastName
     */
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    /**
     * Set first name
     * @param firstName
     */
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    /**
     * Set email adress
     * @param email
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Set mobile number
     * @param telephone
     */
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    /**
     * Set member's adress
     * @param adress
     */
    public void setAdress(String adress){
        this.adress = adress;
    }

    /**
     * Set member's Subscription type
     * @param subscription
     */
    public void setSubscription(Subscription subscription){
        this.mSubscription = subscription;
    }

    /**
     * Change of toString method to reference all class variables in output
     */
    public String toString(){
        return "\nId: " + this.id +
        "\nLast name: " + this.lastName +
        "\nFirst name: " + this.firstName +
        "\nAddress: " + this.adress +
        "\nEmail: " + this.email +
        "\nPhone number: " + this.telephone +
        "\nSubscription: " + this.mSubscription +
        "\n";
    }
}