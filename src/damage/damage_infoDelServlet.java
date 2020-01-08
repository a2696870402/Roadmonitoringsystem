package damage;

import test.operateSql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet( "/damage_infoDelServlet")
public class damage_infoDelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        operateSql operate=new operateSql();
        String date=request.getParameter("d_date");
        String road_id=request.getParameter("road_id");
        String main_damage=request.getParameter("main_damage");
        java.sql.Date daily_date=java.sql.Date.valueOf(date);
        boolean f=operate.deleteDamage_info(daily_date,road_id,main_damage);
        if(f) {
            response.sendRedirect("damage_infoServlet?");
        }
        else {
            PrintWriter out = response.getWriter();
            out.print("<script>alert('This message cannot be deleted: it is constrained by foreign keys!');window.location.href='damage_infoServlet?';</script>");
        }
    }
}
