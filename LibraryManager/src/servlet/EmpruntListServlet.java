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
 * EmpruntListServlet
 */
public class EmpruntListServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        
        if (action.equals("/emprunt_list")) {
            LoanService loanService = LoanServiceImpl.getInstance();
            
            try {
                if (request.getParameter("show") != null && request.getParameter("show").contains("all")){
                    request.setAttribute("loanList", loanService.getList());
                    request.setAttribute("show", "all");
                }else {
                    request.setAttribute("loanList", loanService.getListCurrent());
                    request.setAttribute("show", "current");
                }
                    
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Submit gathered information th the appropriate .jsp:
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp");
            dispatcher.forward(request, response);

        }
    } 
    
}