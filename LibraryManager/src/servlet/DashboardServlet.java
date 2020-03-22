package servlet;

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
    protected void doGet (final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final String action = request.getServletPath();

        if (action.equals("/dashboard")) {
            final MemberService memberService = MemberServiceImpl.getInstance();
            try {
                request.setAttribute("numberOfMembers", memberService.count());
            } catch (final Exception e) {
                e.printStackTrace();
            }

            final BookService bookService = BookServiceImpl.getInstance();
            try {
                request.setAttribute("numberOfBooks", bookService.count());
            } catch (final Exception e) {
                e.printStackTrace();
            }

            final LoanService loanService = LoanServiceImpl.getInstance();
            try {
                request.setAttribute("numberOfLoans", loanService.count());
                request.setAttribute("currentLoans", loanService.getListCurrent());
            } catch (final Exception e) {
                e.printStackTrace();
            }

            // Submit gathered information th the appropriate .jsp:
            final RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/dashboard.jsp");
            dispatcher.forward(request, response);
            
        }


    }
    
}