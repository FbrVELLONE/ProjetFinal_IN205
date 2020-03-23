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

        if (action.equals("/membre_details")){
            MemberService memberService = MemberServiceImpl.getInstance();
            LoanService loanService = LoanServiceImpl.getInstance();

            try {
                req.setAttribute("member", memberService.getById(Integer.parseInt(req.getParameter("id"))));
            } catch (Exception e) {
                new ServletException("Cant get the chosen member", e);
            }

            try {
                req.setAttribute("currentByMember", loanService.getListCurrentByMembre(Integer.parseInt(req.getParameter("id"))));
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
        LoanService loanService = LoanServiceImpl.getInstance();

        try {
            if (req.getParameter("prenom") == "" || req.getParameter("prenom") == null || req.getParameter("nom") == "" || req.getParameter("nom") == null){
                throw new ServletException("First or Last names are empties!");
            } else{
                Member adding = memberService.getById(Integer.parseInt(req.getParameter("id")));
                adding.setFirstName(req.getParameter("prenom"));
                adding.setLastName(req.getParameter("nom"));
                adding.setEmail(req.getParameter("email"));
                adding.setTelephone(req.getParameter("telephone"));
                if (req.getParameter("abonnement").equals("BASIC")){
                    adding.setSubscription(Subscription.BASIC);
                } else if (req.getParameter("abonnement").equals("PREMIUM")){
                    adding.setSubscription(Subscription.PREMIUM);
                } else if (req.getParameter("abonnement").equals("VIP")){
                    adding.setSubscription(Subscription.VIP);
                } 
                memberService.update(adding);
                req.setAttribute("id", adding.getId());
                req.setAttribute("currentByMember", loanService.getListCurrentByMembre(adding.getId()));
                
                
                resp.sendRedirect(req.getContextPath() + "/membre_details?id=" + adding.getId());
            }
        } catch (Exception e) {
            new ServletException("Error in sending update!", e);
            req.setAttribute("errorMessage", "Empty Parameter");
        }

    }
    
}