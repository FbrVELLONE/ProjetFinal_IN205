package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.LoanService;
import services.LoanServiceImpl;

/**
 * EmpruntReturnServelet
 */
public class EmpruntReturnServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        if(action.equals("/emprunt_return")){
            LoanService loanService = LoanServiceImpl.getInstance();
            int id = -1;

            if (req.getParameter("id") != null){
                id = Integer.parseInt(req.getParameter("id"));
            } 

            try {
                if (id != -1){
                    req.setAttribute("id", id);
                    req.setAttribute("currentLoan", loanService.getListCurrent());
                } else{
                    req.setAttribute("currentLoan", loanService.getListCurrent());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            final RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoanService loanService = LoanServiceImpl.getInstance();

        try {
            if (req.getParameter("id") == null){
                throw new ServletException("can't get id number!");
            } else{
                loanService.returnBook(Integer.parseInt(req.getParameter("id")));
                req.setAttribute("currentLoan", loanService.getListCurrent());
            }
        } catch (Exception e) {
            throw new ServletException("Cant send!", e);
        }

        resp.sendRedirect(req.getContextPath() + "/emprunt_list");
    }
    
}