package dao;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Member;
import exception.DaoException;
import com.excilys.librarymanager.persistence.ConnectionManager;

/**
 * MemberDaoImpl
 */
public class MemberDaoImpl implements MemberDao{
    //Singleton
    private static MemberDaoImpl instance;
    private MemberDaoImpl(){};
    public static MemberDaoImpl getInstance(){
        if (instance == null)   instance = new MemberDaoImpl();
        return instance;
    }

    //Requisition Strings
    private final String _SelectAllQuery = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre ORDER BY nom, prenom;";
	private final String _SelectIDQuery = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre WHERE id=?;";
	private final String _CreateQuery = "INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, ?);";
	private final String _UpdateQuery = "UPDATE membre SET nom=?, prenom=?, adresse=?, email=?, telephone=?, abonnement=? WHERE id=?;";
	private final String _DeleteQuery = "DELETE FROM membre WHERE id=?;";
	private final String _CounterQuery = "SELECT COUNT(id) AS count FROM membre;";

    /**
     * Function responsible for returning all members in the database
     */
    @Override
    public List<Member> getList() throws DaoException{
        List<Member> members = new ArrayList<Member>();

        //Try to open connection
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to prepare statement query
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(_SelectAllQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Try for exec the request
        ResultSet rst = null;
        try {
            rst = stmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to get all requests
        try {
            while(rst.next()){
                members.add(new Member(rst.getString("nom"), rst.getString("prenom"), rst.getString("adresse"), rst.getString("email"), rst.getString("telephone"), Member.Subscription.valueOf(rst.getString("abonnement"))));
            }

            System.out.println("All members list: " + members);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return members;
    };

    /**
     * Get one specific member of the list with his ID
     * @param id number specific of member
     */
    @Override
	public Member getById(int id) throws DaoException{
        Member memberById = new Member();

        //Try to open connection
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to prepare statement query
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(_SelectIDQuery);
            stmt.setInt(1, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Try for exec the request
        ResultSet rst = null;
        try {
            rst = stmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to get all requests
        try {
            if(rst.next()){
                memberById = new Member(rst.getString("nom"), rst.getString("prenom"), rst.getString("email"), rst.getString("telephone"), rst.getString("adresse"), Member.Subscription.valueOf(rst.getString("abonnement")));
            }

            System.out.println("Chosen member: " + memberById);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return memberById;
    };

    /**
     * Make a new MemberFile into our DB
     * @param nom
     * @param prenom
     * @param adresse
     * @param email
     * @param telephone
     * @param subscription
     */
    @Override
	public int create(String nom, String prenom, String adresse, String email, String telephone, Member.Subscription subscription) throws DaoException{
        int id = -1;

        //Try to open connection
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to prepare statement query
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(_CreateQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, adresse);
            stmt.setString(4, email);
            stmt.setString(5, telephone);
            stmt.setString(6, subscription+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Try for exec the request
        ResultSet rst = null;
        try {
            rst = stmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to get all requests
        try {
            if(rst.next()){
                id = rst.getInt(1);
            }

            System.out.println("Actual new member ID: " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    };

    /**
     * Update a member file with new values
     * @param Member
     */
    @Override
	public void update(Member Member) throws DaoException{
        //Try to open connection
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to prepare statement query
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(_UpdateQuery);
            stmt.setString(1, Member.getLastName());
            stmt.setString(2, Member.getFirstName());
            stmt.setString(3, Member.getAdress());
            stmt.setString(4, Member.getEmail());
            stmt.setString(5, Member.getTelephone());
            stmt.setString(6, Member.getSubscription()+"");
            stmt.setInt(7, Member.getId());

            System.out.println("Member " + Member + "successfull updated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Try for exec the request
        try {
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    };

    /**
     * Delete one line of DB
     */
    @Override
	public void delete(int id) throws DaoException{
        //Try to open connection
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to prepare statement query
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(_DeleteQuery);
            stmt.setInt(1, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Try for exec the request
        try {
            stmt.executeUpdate();
            System.out.println("Member " + id + "successfull deleted!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    };


    /**
     * Make the sum of all members (total)
     */
    @Override
	public int count() throws DaoException{
        int totalMembers = -1;

        //Try to open connection
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to prepare statement query
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(_CounterQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Try for exec the request
        ResultSet rst = null;
        try {
            rst = stmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Try to get all requests
        try {
            if(rst.next()){
                totalMembers = rst.getInt(1);
            }

            System.out.println("Total members: " + totalMembers);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalMembers;
    };
    
}