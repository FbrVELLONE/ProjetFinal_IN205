package services;

import java.time.LocalDate;
import java.util.List;

import exception.ServiceException;
import model.Loan;
import model.Member;

public interface LoanService {
	public List<Loan> getList() throws ServiceException;
	public List<Loan> getListCurrent() throws ServiceException;
	public List<Loan> getListCurrentByMembre(int idMembre) throws ServiceException;
	public List<Loan> getListCurrentByLivre(int idLivre) throws ServiceException;
	public Loan getById(int id) throws ServiceException;
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException;
	public void returnBook(int id) throws ServiceException;
	public int count() throws ServiceException;
	public boolean isLivreDispo(int idLivre) throws ServiceException;
	public boolean isEmpruntPossible(Member membre) throws ServiceException ;
}
