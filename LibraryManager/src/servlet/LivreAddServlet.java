package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.BookService;
import services.BookServiceImpl;

/**
 * LivreAddServlet
 */
public class LivreAddServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        if (action.equals("/livre_add")){
            BookService bookService = BookServiceImpl.getInstance();
            try {
                req.setAttribute("availableBookList", bookService.getListDispo());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Submit gathered information th the appropriate .jsp:
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/livre_add.jsp");
            dispatcher.forward(req, resp);
        }
    }   
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookService bookService = BookServiceImpl.getInstance();
        
        try {
            if (req.getParameter("titre") == null){
                throw new ServletException("Cant load because titre was empty");
            }else{
                int id = bookService.create(req.getParameter("titre"), req.getParameter("auteur"), req.getParameter("isbn"));
                req.setAttribute("id", id);

                resp.sendRedirect(req.getContextPath() + "/livre_details?id=" + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}