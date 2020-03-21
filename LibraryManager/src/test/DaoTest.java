package test;
import java.time.LocalDate;

import dao.*;
import exception.DaoException;
import model.*;
public class DaoTest{
	public static void main(String[] args) throws DaoException {
        
        // init dao 
        LoanDao loanDao = LoanDaoImpl.getInstance();
        BookDao bookDao = BookDaoImpl.getInstance();
        MemberDao memberDao = MemberDaoImpl.getInstance();
        
        // getlist
		//loanDao.getList();
		//System.out.println("aqui1.  \n");
		////loanDao.getListCurrent();
		//System.out.println("\n");
        //
        //
        //bookDao.getList();
		//System.out.println("aqui \n");
        //
        //memberDao.getList();
        //System.out.println("aqui \n");
        
        loanDao.getById(3);
    }	   
}