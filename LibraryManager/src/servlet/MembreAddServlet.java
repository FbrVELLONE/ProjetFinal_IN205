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
import model.Member.Subscription;

/**
 * MembreAddServlet
 */
public class MembreAddServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        if (action.equals("/membre_add")){
            MemberService memberService = MemberServiceImpl.getInstance();
            try {
                req.setAttribute("availableMemberList", memberService.getList());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Submit gathered information th the appropriate .jsp:
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/membre_add.jsp");
            dispatcher.forward(req, resp);
        }
    }   
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberService memberService = MemberServiceImpl.getInstance();
        LoanService loanService = LoanServiceImpl.getInstance();
        
        try {
            if (req.getParameter("nom") == null || req.getParameter("prenom") == null || req.getParameter("nom") == "" || req.getParameter("prenom") == "" ){
                throw new ServletException("Cant load because FIrst or Last were empties");
            }else{
                int id = memberService.create(req.getParameter("nom"), req.getParameter("prenom"), req.getParameter("adresse"), req.getParameter("email"), req.getParameter("telephone"), Subscription.BASIC);
                req.setAttribute("id", id);
                req.setAttribute("loanList", loanService.getListCurrentByMembre(id));
                resp.sendRedirect(req.getContextPath() + "/membre_details?id=" + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}