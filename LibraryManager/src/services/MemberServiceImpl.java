package services;

import java.util.*;

import exception.ServiceException;
import model.Member;
import dao.MemberDao;
import dao.MemberDaoImpl;

/**
 * MemberServiceImpl is the class responsible for making the connection with the database calls, passing the rules of service to member class
 */
public class MemberServiceImpl implements MemberService {
    //Singleton
    private static MemberServiceImpl instance;
    private MemberServiceImpl(){};
    public static MemberServiceImpl getInstance(){
        if (instance == null)   instance = new MemberServiceImpl();
        return instance;
    }
    
    /**
     * Call the instances responsables for DB manipulation
     * @return The total list in DB
     */
    @Override
    public List<Member> getList() throws ServiceException{
        List<Member> loanAllList = new ArrayList<Member>();
        MemberDao memberDao = MemberDaoImpl.getInstance();

        try {
            loanAllList = memberDao.getList();

            System.out.println("All members list: " + loanAllList);
        } catch (Exception e) {
            throw new ServiceException("Can't get total list!\n", e);
        }

        return loanAllList;
    };

    /**
     * Return all loan possibles yet
     * @return The total list of possible Bookings by member
     */
    @Override
	public List<Member> getListMembreEmpruntPossible() throws ServiceException{
        List<Member> membersLoanDispo = new ArrayList<Member>();
        List<Member> members = new ArrayList<Member>();
        MemberDao memberDao = MemberDaoImpl.getInstance();
        LoanService loanService = LoanServiceImpl.getInstance();

        try {
            members = memberDao.getList();
            for (int i = 0; i < members.size(); i++){
                if (loanService.isEmpruntPossible(members.get(i))){
                    membersLoanDispo.add(members.get(i));
                }
            }

            System.out.println("Returning all loans possibles: " + membersLoanDispo);
        } catch (Exception e) {
            throw new ServiceException("Can't get possibles bookings by member!\n", e);
        }

        return membersLoanDispo;
    };

    /**
     * Get the chosen member By ID
     * @param id Chosen memberID
     * @return The choose member
     */
    @Override
    public Member getById(int id) throws ServiceException{
        Member chosenOne = new Member();
        MemberDao memberDao = MemberDaoImpl.getInstance();

        try {
            chosenOne = memberDao.getById(id);

            System.out.println("Chosen member: " + chosenOne);
        } catch (Exception e) {
            throw new ServiceException("Can't get individual member!\n", e);
        }

        return chosenOne;
    };
    
    /**
     * Create first request for intercept any issues provides before calling DB, verifying if first and last names are empties
     * @param nom
     * @param prenom
     * @param adresse
     * @param email
     * @param telephone
     * @param subscription
     * @return The created ID
     */
    @Override
	public int create(String nom, String prenom, String adresse, String email, String telephone, Member.Subscription subscription) throws ServiceException{
        int id = -1;
        MemberDao memberDao = MemberDaoImpl.getInstance();

        try {
            if (nom == null || prenom == null || nom == "" || prenom == ""){
                throw new ServiceException("First or Last names empties! Can't create");
            } else{
                nom = nom.toUpperCase();
                prenom = prenom.toUpperCase();
                
                id = memberDao.create(nom, prenom, adresse, email, telephone, subscription);
                System.out.println("Actual new member ID: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't create for a reason!\n", e);
        }


        return id;
    };

    /**
     * Requisition into DB for initialize DB update guarding any exceptions, verifying if first and last names are empties
     */
    @Override
	public void update(Member membre) throws ServiceException{
        MemberDao memberDao = MemberDaoImpl.getInstance();

        try {
            if(membre.getFirstName() == null || membre.getLastName() == null || membre.getFirstName() == "" || membre.getLastName() == ""){
                throw new ServiceException("First or Last names empties! Can't update");
            } else{
                membre.setLastName(membre.getLastName().toUpperCase());
                memberDao.update(membre);

                System.out.println("Member " + membre + "successfull updated!");
            }
        } catch (Exception e) {
            throw new ServiceException("Can't be updated!\n", e);
        }

    };

    /**
     * Delete one requisition by ID into our DB
     * @param id chosen id for delete
     */
    @Override
	public void delete(int id) throws ServiceException{
        MemberDao memberDao = MemberDaoImpl.getInstance();

        try {
            memberDao.delete(id);
            System.out.println("Member " + id + "successfull deleted!");
        } catch (Exception e) {
            throw new ServiceException("Can't be deleted!\n", e);
        }
    };

    /**
     * Return the total members into DB
     * @return One integer with total number
     */
    @Override
	public int count() throws ServiceException{
        int total = -1;
        MemberDao memberDao = MemberDaoImpl.getInstance();

        try {
            total = memberDao.count();
            System.out.println("Total members: " + total);
        } catch (Exception e) {
            throw new ServiceException("Can't count all things!\n", e);
        }

        return total;
    };
}