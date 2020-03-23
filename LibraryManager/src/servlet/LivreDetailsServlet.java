package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
import services.BookService;
import services.BookServiceImpl;
import services.LoanService;
import services.LoanServiceImpl;

/**
 * LivreDetailsServlet
 */
public class LivreDetailsServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        if (action.equals("/livre_details")){
            BookService bookService = BookServiceImpl.getInstance();
            LoanService loanService = LoanServiceImpl.getInstance();

            try {
                req.setAttribute("book", bookService.getById(Integer.parseInt(req.getParameter("id"))));
            } catch (Exception e) {
                new ServletException("Cant get the chosen book", e);
            }

            try {
                req.setAttribute("currentBookings", loanService.getListCurrentByLivre(Integer.parseInt(req.getParameter("id"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/View/livre_details.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookService bookService = BookServiceImpl.getInstance();
        LoanService loanService = LoanServiceImpl.getInstance();
        try {
            if (req.getParameter("titre") == "" || req.getParameter("titre") == null){
                throw new ServletException("Title is empty!");
            } else{
                Book adding = bookService.getById(Integer.parseInt(req.getParameter("id")));
                adding.setAuthor(req.getParameter("auteur"));
                adding.setTitle(req.getParameter("titre"));
                adding.setIsbn(req.getParameter("isbn"));
                bookService.update(adding);
                req.setAttribute("id", adding.getId());
                req.setAttribute("currentBookings", loanService.getListCurrentByLivre(adding.getId()));
        
                resp.sendRedirect(req.getContextPath() + "/livre_details?id=" + adding.getId());
            }
        } catch (Exception e) {
            new ServletException("Error in sending update! Title is empty!", e);
            req.setAttribute("errorMessage", "Title is empty!");
        }
    }
    
}