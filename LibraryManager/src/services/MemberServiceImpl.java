package services;

import java.util.*;
import exception.*;
import model.*;

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
    

    public List<Member> getList() throws ServiceException{
        List<Member> loanAllList = new ArrayList<Member>();

        return loanAllList;
    };

	public List<Member> getListMembreEmpruntPossible() throws ServiceException{
        List<Member> loanList = new ArrayList<Member>();

        return loanList;
    };

    public Member getById(int id) throws ServiceException{
        Member chosenOne = new Member();

        return chosenOne;
    };
    
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws ServiceException{
        int id = -1;

        return id;
    };

	public void update(Member membre) throws ServiceException{

    };

	public void delete(int id) throws ServiceException{

    };

	public int count() throws ServiceException{
        int total = -1;

        return total;
    };
}