package services;

import java.util.List;

import exception.ServiceException;
import model.Member;

public interface MemberService {

	public List<Member> getList() throws ServiceException;
	public List<Member> getListMembreEmpruntPossible() throws ServiceException;
	public Member getById(int id) throws ServiceException;
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws ServiceException;
	public void update(Member membre) throws ServiceException;
	public void delete(int id) throws ServiceException;
	public int count() throws ServiceException;

}
