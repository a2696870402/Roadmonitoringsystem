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
import java.sql.Date;

@WebServlet("/dailyAddNext")
public class dailyAddNext extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        Date daily_date= Date.valueOf(date);
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
        boolean f=operate.addDaily(d);
        PrintWriter out = response.getWriter();
        if(f) {
            HttpSession session = request.getSession();
            d.setDaily_worker("administrator");
            d.setMain_damage("未添加");
            session.setAttribute("daily", d);
            out.print("<script>window.location.href='dailyAddNext.jsp';</script>");
        }
        else {
            out.print("<script>alert('修改失败,可能是重复了,请修改损坏类型!');window.location.href='dailyAddNext.jsp';</script>");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
