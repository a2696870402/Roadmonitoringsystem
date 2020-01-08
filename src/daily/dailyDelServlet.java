package daily;

import test.operateSql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet( "/dailyDelServlet")
public class dailyDelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        operateSql operate=new operateSql();
        String date=request.getParameter("daily_date");
        String road_id=request.getParameter("road_id");
        String main_damage=request.getParameter("main_damage");
        java.sql.Date daily_date=java.sql.Date.valueOf(date);
        boolean f=operate.deleteDaily(daily_date,road_id,main_damage);
        if(f) {
            response.sendRedirect("dailyServlet?");
        }
        else {
            PrintWriter out = response.getWriter();
            out.print("<script>alert('This message cannot be deleted: it is constrained by foreign keys!');window.location.href='dailyServlet?';</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
