package damage;


import JDB.JDBConnection;
import test.operateSql;
import road.road;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@WebServlet( "/damage_infoAddServlet")
public class damage_infoAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBConnection jdbConnection=new JDBConnection();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("gb2312");
        try {
            List<road> list = new ArrayList<>();  //road
            final ResultSet rs = jdbConnection.executeQuery("select road_id,road_name,road_dj,road_type from road");
            while (rs.next()) {
                road ac = new road();
                ac.setRoad_id(rs.getString(1));
                ac.setRoad_name(rs.getString(2));
                ac.setRoad_dj(rs.getInt(3));
                ac.setRoad_type(rs.getString(4));
                list.add(ac);
            }
            for(int i=0;i<list.size();i++)
            {
                try {
                    Date date = Date.valueOf("2020-1-1");
                    final ResultSet rs2 = jdbConnection.executeQuery("select max(d_date) from damage_info where road_id='" + list.get(i).getRoad_id() + "'");
                    while (rs2.next()) {
                        date = rs2.getDate(1);
                    }
                    if (date == null) {
                        Calendar cal2 = Calendar.getInstance();
                        date = new Date(cal2.getTime().getTime());
                    }
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    switch (list.get(i).getRoad_dj()) {
                        case 1: {
                            cal.add(Calendar.YEAR, 2);
                            date = new Date(cal.getTime().getTime());
                            break;
                        }
                        case 2: {
                            cal.add(Calendar.YEAR, 3);
                            date = new Date(cal.getTime().getTime());
                            break;
                        }
                        case 3: {
                            cal.add(Calendar.YEAR, 4);
                            date = new Date(cal.getTime().getTime());
                            break;
                        }
                    }
                    operateSql operate = new operateSql();
                    damage_info d = new damage_info();
                    d.setD_date(date);
                    d.setD_worker("administrator");
                    d.setRoad_id(list.get(i).getRoad_id());
                    d.setRoad_name(list.get(i).getRoad_name());
                    d.setMain_damage("未添加");
                    d.setMain_road(list.get(i).getRoad_type());
                    d.setD_start("未添加");
                    d.setD_Alllen(0);
                    d.setD_Allwidth(0);
                    d.setD_len(0);
                    d.setD_width(0);
                    d.setD_height(0);
                    d.setD_des("未添加");
                    d.setD_area(0);
                    d.setD_note("未添加");
                    d.setDensity(0);
                    d.setScore(0);
                    d.setMain_class("未添加");
                    boolean flag = operate.addDamage_info(d);
                    if (flag) {
                        PrintWriter out = response.getWriter();
                        out.print("<script>window.location.href='damage_infoServlet?';</script>");
                    }else {
                        PrintWriter out = response.getWriter();
                        out.print("<script>alert('添加失败！');window.location.href='damage_infoServlet?';</script>");
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
