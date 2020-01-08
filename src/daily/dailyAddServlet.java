package daily;


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

@WebServlet("/dailyAddServlet")
public class dailyAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("gb2312");
        JDBConnection jdbConnection=new JDBConnection();
        try {
            //
            List<road> list = new ArrayList<>();
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
                    final ResultSet rs2 = jdbConnection.executeQuery("select max(daily_date) from daily where road_id='" + list.get(i).getRoad_id() + "'");
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
                            cal.add(Calendar.DATE, 1);
                            date = new Date(cal.getTime().getTime());
                            break;
                        }
                        case 2: {
                            cal.add(Calendar.DATE, 2);
                            date = new Date(cal.getTime().getTime());
                            break;
                        }
                        case 3: {
                            cal.add(Calendar.DATE, 3);
                            date = new Date(cal.getTime().getTime());
                            break;
                        }
                    }
                    operateSql operate = new operateSql();
                    daily d = new daily();
                    d.setDaily_date(date);
                    d.setRoad_id(list.get(i).getRoad_id());
                    d.setRoad_name(list.get(i).getRoad_name());
                    d.setDaily_worker("administrator");
                    d.setMain_damage("未添加");
                    d.setMain_road(list.get(i).getRoad_type());
                    d.setDaily_note("未添加");
                    d.setDaily_des("未添加");
                    if (operate.addDaily(d)) {
                        PrintWriter out = response.getWriter();
                        out.print("<script>window.location.href='dailyServlet?';</script>");
                    }else {
                        PrintWriter out = response.getWriter();
                        out.print("<script>alert('添加失败!');window.location.href='dailyServlet?';</script>");
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
