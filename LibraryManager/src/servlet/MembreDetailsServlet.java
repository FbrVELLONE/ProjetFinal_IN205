package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.MemberService;
import services.MemberServiceImpl;
import services.LoanService;
import services.LoanServiceImpl;
import model.Member;
import model.Member.Subscription;

/**
 * MembreDetailsServlet
 */
public class MembreDetailsServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        if (action.equals("/livre_details")){
            MemberService memberService = MemberServiceImpl.getInstance();
            LoanService loanService = LoanServiceImpl.getInstance();

            try {
                req.setAttribute("book", memberService.getById(Integer.parseInt(req.getParameter("id"))));
            } catch (Exception e) {
                new ServletException("Cant get the chosen member", e);
            }

            try {
                req.setAttribute("currentBookings", loanService.getListCurrentByLivre(Integer.parseInt(req.getParameter("id"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/View/membre_details.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberService memberService = MemberServiceImpl.getInstance();
        try {
            if (req.getParameter("titre") == "" || req.getParameter("titre") == null){
                throw new ServletException("Title is empty!");
            } else{
                Member adding = memberService.getById(Integer.parseInt(req.getParameter("id")));
                adding.setFirstName(req.getParameter("firstName"));
                adding.setLastName(req.getParameter("lastName"));
                adding.setEmail(req.getParameter("email"));
                adding.setTelephone(req.getParameter("telephone"));
                memberService.update(adding);
            }
        } catch (Exception e) {
            new ServletException("Error in sending update!", e);
            req.setAttribute("errorMessage", "Empty Parameter");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/View/member_details.jsp");
        dispatcher.forward(req, resp);
    }
    
}