package planeness;

import test.operateSql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Year;
import java.util.Calendar;

@WebServlet("/planenessModServlet")
public class planenessModServlet extends HttpServlet {
    double sta_iri;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("gb2312");
        String date = request.getParameter("p_date");
        String road_id = request.getParameter("road_id");
        String road_name = request.getParameter("road_name");
        String p_worker = request.getParameter("p_worker");
        String p_iri = request.getParameter("p_iri");
        java.sql.Date p_date = java.sql.Date.valueOf(date);
        double iri = Float.parseFloat(p_iri);
        planeness p = new planeness();
        p.setP_date(p_date);
        p.setRoad_id(road_id);
        p.setRoad_name(road_name);
        p.setP_worker(p_worker);
        p.setP_iri(iri);
        // System.out.println(p.getP_iri());
        operateSql operate = new operateSql();
        operate.updatePlaneness(p);
        System.out.println(sta_iri);
        if (sta_iri == 0.0) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(p_date);
            Year year = Year.of(cal.get(Calendar.YEAR));
            boolean f = operate.addSummary(year, road_id, road_name);
            response.sendRedirect("planenessServlet?");
        } else {
            response.sendRedirect("planenessServlet?");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        String date=request.getParameter("p_date");
        String road_id=request.getParameter("road_id");
        String road_name=request.getParameter("road_name");
        String p_worker=request.getParameter("p_worker");
        String p_iri=request.getParameter("p_iri");
        java.sql.Date p_date=java.sql.Date.valueOf(date);
        sta_iri=Double.parseDouble(p_iri);
        planeness p=new planeness();
        p.setP_date(p_date);
        p.setRoad_id(road_id);
        p.setRoad_name(road_name);
        p.setP_worker(p_worker);
        p.setP_iri(sta_iri);
        HttpSession session = request.getSession();
        session.setAttribute("planeness", p);
        response.sendRedirect("planenessMod.jsp");
//        request.setAttribute("planeness",p);
//        request.getRequestDispatcher("planenessMod.jsp").forward(request, response);
    }
}
