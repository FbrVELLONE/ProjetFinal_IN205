package servlet;

import java.io.IOException;
import java.time.LocalDate;

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
 * EmpruntAddServlet class responsible for building the relationship between the interface and the DB
 */
public class EmpruntAddServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        
        if (action == "/emprunt_add"){
            MemberService memberService = MemberServiceImpl.getInstance();
            try {
                request.setAttribute("availableMemberList", memberService.getListMembreEmpruntPossible());
            } catch (Exception e) {
                e.printStackTrace();
            }

            BookService bookService = BookServiceImpl.getInstance();
            try {
                request.setAttribute("availableBookList", bookService.getListDispo());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Submit gathered information th the appropriate .jsp:
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        LoanService loanService = LoanServiceImpl.getInstance();

        try {
            if (request.getParameter("idMembre") == null || request.getParameter("idLivre") == null){
                throw new ServletException("Cant add a new loan, some data hasn't been received");
            } else{
                loanService.create(Integer.parseInt(request.getParameter("idMembre")), Integer.parseInt(request.getParameter("idLivre")), LocalDate.now());
						
				request.setAttribute("loanList", loanService.getListCurrent());
				request.setAttribute("show", "all");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp");
				dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        MemberService memberService = MemberServiceImpl.getInstance();        
        try {
            request.setAttribute("availableMemberList", memberService.getListMembreEmpruntPossible());
        } catch (Exception e) {
            e.printStackTrace();
        }

        BookService bookService = BookServiceImpl.getInstance();     
        try {
            request.setAttribute("availableBookList", bookService.getListDispo());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
        dispatcher.forward(request, response);
    }
}