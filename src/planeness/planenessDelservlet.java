package planeness;

import test.operateSql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Year;
import java.util.Calendar;

@WebServlet("/planenessDelservlet")
public class planenessDelservlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        operateSql operate=new operateSql();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String date=request.getParameter("p_date");
        String road_id=request.getParameter("road_id");
        java.sql.Date p_date=java.sql.Date.valueOf(date);
        boolean f=operate.deletePlaneness(p_date,road_id);
        if(f) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(p_date);
            Year year = Year.of(cal.get(Calendar.YEAR));
            operate.deleteSummary(year,road_id);
            operate.deleteDa_info(year,road_id);
            response.sendRedirect("planenessServlet?");

        }
        else {
            PrintWriter out = response.getWriter();
            out.print("<script>alert('This message cannot be deleted: it is constrained by foreign keys!');window.location.href='planenessServlet?';</script>");
        }
    }
}
