package planeness;

import test.operateSql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/planenessServlet")
public class planenessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        operateSql operate=new operateSql();
        List<planeness> list = new ArrayList<planeness>();
        list=operate.getPlaneness();
        HttpSession session = request.getSession();
        session.setAttribute("planessList", list);
        response.sendRedirect("planeness.jsp");
//        request.setAttribute("planessList", list);
//        request.getRequestDispatcher("planeness.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
