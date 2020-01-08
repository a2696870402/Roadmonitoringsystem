package daily;

import daily.daily;
import test.operateSql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/dailyModServlet")
public class dailyModServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("gb2312");
//        response.setCharacterEncoding("gb2312");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("gb2312");
        String date=request.getParameter("daily_date");
        String road_id=request.getParameter("road_id");
        String road_name=request.getParameter("road_name");
        String daily_worker=request.getParameter("daily_worker");
        String main_damage=request.getParameter("main_damage");
        String main_road=request.getParameter("main_road");
        String daily_des=request.getParameter("daily_des");
        String daily_note=request.getParameter("daily_note");
        java.sql.Date daily_date=java.sql.Date.valueOf(date);
        daily d= new daily();
        d.setDaily_date(daily_date);
        d.setRoad_id(road_id);
        d.setRoad_name(road_name);
        d.setDaily_worker(daily_worker);
        d.setMain_damage(main_damage);
        d.setMain_road(main_road);
        d.setDaily_des(daily_des);
        d.setDaily_note(daily_note);
        operateSql operate=new operateSql();
        boolean f=operate.updateDaily(d);
        if(f) {
            PrintWriter out = response.getWriter();
            out.print("<script>alert('修改成功!');window.location.href='dailyServlet';</script>");
        }
        else {
            PrintWriter out = response.getWriter();
            out.print("<script>alert('存在重复!');window.location.href='dailyMod.jsp';</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String date=request.getParameter("daily_date");
        String road_id=request.getParameter("road_id");
        String road_name=request.getParameter("road_name");
        String daily_worker=request.getParameter("daily_worker");
        String main_damage=request.getParameter("main_damage");
        String main_road=request.getParameter("main_road");
        String daily_des=request.getParameter("daily_des");
        String daily_note=request.getParameter("daily_note");
        java.sql.Date daily_date=java.sql.Date.valueOf(date);
        daily d= new daily();
        d.setDaily_date(daily_date);
        d.setRoad_id(road_id);
        d.setRoad_name(road_name);
        d.setDaily_worker(daily_worker);
        d.setMain_damage(main_damage);
        d.setMain_road(main_road);
        d.setDaily_des(daily_des);
        d.setDaily_note(daily_note);
        HttpSession session = request.getSession();
        session.setAttribute("daily", d);
        response.sendRedirect("dailyMod.jsp");
    }
}
