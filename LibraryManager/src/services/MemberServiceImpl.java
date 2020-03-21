package services;

import java.util.*;

import exception.ServiceException;
import model.Member;
import dao.MemberDao;
import dao.MemberDaoImpl;

/**
 * MemberServiceImpl
 */
public class MemberServiceImpl implements MemberService {
    //Singleton
    private static MemberServiceImpl instance;
    private MemberServiceImpl(){};
    public MemberServiceImpl getInstance(){
        if (instance == null)   instance = new MemberServiceImpl();
        return instance;
    }
    
    /**
     * Call the instances responsables for DB manipulation
     */
    @Override
    public List<Member> getList() throws ServiceException{
        List<Member> loanAllList = new ArrayList<Member>();
        MemberDao memberDao = MemberDaoImpl.getInstance();

        try {
            loanAllList = memberDao.getList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loanAllList;
    };

    /**
     * Return all loan possibles yet
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return membersLoanDispo;
    };

    /**
     * Get the chosen member By ID
     * @param id Chosen memberID
     */
    @Override
    public Member getById(int id) throws ServiceException{
        Member chosenOne = new Member();
        MemberDao memberDao = MemberDaoImpl.getInstance();

        try {
            chosenOne = memberDao.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
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
     */
    @Override
	public int create(String nom, String prenom, String adresse, String email, String telephone, Member.Subscription subscription) throws ServiceException{
        int id = -1;
        MemberDao memberDao = MemberDaoImpl.getInstance();

        try {
            if (nom == null || prenom == null || nom == "" || prenom == ""){
                throw new ServiceException("Nom ou Prenom null ou vide");
            } else{
                nom = nom.toUpperCase();
                prenom = prenom.toUpperCase();
                
                id = memberDao.create(nom, prenom, adresse, email, telephone, subscription);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                throw new ServiceException("Nom ou Prenom null ou vide");
            } else{
                membre.setLastName(membre.getLastName().toUpperCase());
                memberDao.update(membre);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    /**
     * Return the total members into DB
     */
    @Override
	public int count() throws ServiceException{
        int total = -1;
        MemberDao memberDao = MemberDaoImpl.getInstance();

        try {
            total = memberDao.count();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    };
}