package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.MemberService;
import services.MemberServiceImpl;

/**
 * MembreListServlet
 */
public class MembreListServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        if (action.equals("/membre_list")){
            MemberService memberService = MemberServiceImpl.getInstance();
            try {
                req.setAttribute("memberList", memberService.getList());
            } catch (Exception e) {
                e.printStackTrace();
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/membre_list.jsp");
            dispatcher.forward(req, resp);
        }
    }

    
}