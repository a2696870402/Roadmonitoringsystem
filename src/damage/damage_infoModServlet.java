package damage;


import JDB.JDBConnection;
import test.calculate;
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
import java.sql.ResultSet;


@WebServlet("/damage_infoModServlet")
public class damage_infoModServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf8");
            response.setCharacterEncoding("gb2312");
            operateSql operate=new operateSql();
            String date = request.getParameter("d_date");
            String road_id = request.getParameter("road_id");
            String d_worker = request.getParameter("d_worker");
            String road_name = request.getParameter("road_name");
            String main_damage = request.getParameter("main_damage");
            String main_road = request.getParameter("main_road");
            String d_start = request.getParameter("d_start");
            String Alllen = request.getParameter("d_Alllen");
            String Allwidth = request.getParameter("d_Allwidth");
            String len = request.getParameter("d_len");
            String width = request.getParameter("d_width");
            String height = request.getParameter("d_height");
            String d_des = request.getParameter("d_des");
            String d_note = request.getParameter("d_note");
            Date d_date= Date.valueOf(date);
            double d_Alllen=Double.valueOf(Alllen);
            double d_Allwidth=Double.parseDouble(Allwidth);
            double d_len=Double.parseDouble(len);
            double d_width=Double.parseDouble(width);
            double d_height=Double.parseDouble(height);
            double area;    //损坏面积
            double area_jc; //检查面积
            double density; //密度
            area_jc=d_Alllen*d_Allwidth;
            calculate c=new calculate();
            area=c.getArea(main_road,main_damage,d_len,d_width,d_height);
            density=area/area_jc;
            double score=c.getDpij(main_road,main_damage,density);
            String main_class=operate.getMain_class(main_damage,main_road);
            damage_info d=new damage_info();
            d.setD_date(d_date);
            d.setD_worker(d_worker);
            d.setRoad_id(road_id);
            d.setRoad_name(road_name);
            d.setMain_damage(main_damage);
            d.setMain_road(main_road);
            d.setD_start(d_start);
            d.setD_Alllen(d_Alllen);
            d.setD_Allwidth(d_Allwidth);
            d.setD_len(d_len);
            d.setD_width(d_width);
            d.setD_height(d_height);
            d.setD_des(d_des);
            d.setD_area(area);
            d.setD_note(d_note);
            d.setDensity(density);
            d.setScore(score);
            d.setMain_class(main_class);
            if ((d.getD_Alllen() < d.getD_len() )|| (d.getD_Allwidth() < d.getD_width())) {
                PrintWriter out = response.getWriter();
                out.print("<script>alert('The length of the damaged part cannot exceed the length of the inspected part!');window.location.href='damage_infoMod.jsp';</script>");
            } else {
                boolean f = operate.updateDamage_info(d);
                if (f) {
                    HttpSession session = request.getSession();
                    session.setAttribute("damage_info", d);
                    response.sendRedirect("damage_infoServlet?");
                    PrintWriter out=response.getWriter();
                    out.print("<script>window.location.href='damage_infoMod.jsp';</script>");
                } else {
                    PrintWriter out = response.getWriter();
                    out.print("<script>alert('Duplicate damage type!');window.location.href='damage_infoMod.jsp';</script>");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            PrintWriter out = response.getWriter();
            out.print("<script>alert('Error in input!');window.location.href='damage_infoMod.jsp';</script>");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        String date = request.getParameter("d_date");
        String road_id = request.getParameter("road_id");
        String d_main_damage=request.getParameter("main_damage");
        Date d_date = Date.valueOf(date);
        JDBConnection jdbConnection=new JDBConnection();
        try {
            //执行sql
            String str = "select * from damage_info where road_id ='" + road_id + "' and d_date='" + d_date + "' and main_damage='"+d_main_damage+"'";
            ResultSet rest = jdbConnection.executeQuery(str);
            damage_info d = new damage_info();
            while (rest.next()) {
                d.setD_date(rest.getDate(1));
                d.setD_worker(rest.getString(2));
                d.setRoad_id(rest.getString(3));
                d.setRoad_name(rest.getString(4));
                d.setMain_damage(rest.getString(5));
                d.setMain_road(rest.getString(6));
                d.setD_start(rest.getString(7));
                d.setD_Alllen(rest.getFloat(8));
                d.setD_Allwidth(rest.getFloat(9));
                d.setD_len(rest.getFloat(10));
                d.setD_width(rest.getFloat(11));
                d.setD_height(rest.getFloat(12));
                d.setD_des(rest.getString(13));
                d.setD_area(rest.getDouble(14));
                d.setD_note(rest.getString(15));
                d.setDensity(rest.getDouble(16));
                d.setScore(rest.getDouble(17));
                d.setMain_class(rest.getString(18));
            }
            HttpSession session = request.getSession();
            session.setAttribute("damage_info", d);
            PrintWriter out=response.getWriter();
            out.print("<script>window.location.href='damage_infoMod.jsp'</script>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbConnection.closeConnecstion();
        }
    }
}
