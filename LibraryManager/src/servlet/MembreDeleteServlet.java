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
 * MembreDeleteServlet
 */
public class MembreDeleteServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        if (action.equals("/membre_delete")){
            MemberService memberService = MemberServiceImpl.getInstance();
            int id = -1;
            if (req.getParameter("id") != null){    
                id = Integer.parseInt(req.getParameter("id"));
            }

            try {
                if (id != -1){
                    req.setAttribute("membre", memberService.getById(id));
                    req.setAttribute("id", id);
                } else{

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/View/membre_delete.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberService memberService = MemberServiceImpl.getInstance();

        try {
            memberService.delete(Integer.parseInt(req.getParameter("id")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/membre_list");
    }
    
}