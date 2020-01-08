package damage;

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

@WebServlet("/damage_infoServlet")
public class damage_infoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("gb2312");
        operateSql operate=new operateSql();
        List<damage_info> list = new ArrayList<damage_info>();
        list=operate.getDamage_info();
        //System.out.println(list.size());
        HttpSession session = request.getSession();
        session.setAttribute("damage_infoList", list);
        response.sendRedirect("damage_info.jsp");
//        request.setAttribute("damage_infoList", list);
//        request.getRequestDispatcher("damage_info.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
