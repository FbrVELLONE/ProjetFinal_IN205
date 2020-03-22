package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.BookService;
import services.BookServiceImpl;
import services.LoanService;
import services.LoanServiceImpl;
import services.MemberService;
import services.MemberServiceImpl;

/**
 * DashboardServlet responsible for making the interaction between the services and the front page
 */
public class DashboardServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    /**
     * Function responsible for showing the system dashboard and showing the values
     * of members, books and loans
     */
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = request.getServletPath();
        
        if (action == "/dashboard"){
            MemberService memberService = MemberServiceImpl.getInstance(); 
            try {
                request.setAttribute("numberOfMembers", memberService.count());
            } catch (Exception e) {
                e.printStackTrace();
            }
    
            BookService bookService = BookServiceImpl.getInstance();
            try {
                request.setAttribute("numberOfBooks", bookService.count());
            } catch (Exception e) {
                e.printStackTrace();
            }

            LoanService loanService = LoanServiceImpl.getInstance();
            try {
                request.setAttribute("numberOfLoans", loanService.count());
                request.setAttribute("currentLoans", loanService.getListCurrent());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Submit gathered information th the appropriate .jsp:
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/dashboard.jsp");
            dispatcher.forward(request, response);
        }


    }
    
}