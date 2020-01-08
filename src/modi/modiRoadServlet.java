package modi;



import JDB.JDBConnection;
import road.road;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet("/modiRoadServlet")
public class modiRoadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBConnection jdbConnection=new JDBConnection();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            //执行sql
            String road_id=request.getParameter("road_id");
            String str="select * from road where road_id ='"+road_id+"'";
            ResultSet resultSet = jdbConnection.executeQuery(str);
            road r=new road();
            while(resultSet.next())
            {
                r.setRoad_name(resultSet.getString(1));
                r.setRoad_id(resultSet.getString(2));
                r.setRoad_zx(resultSet.getString(3));
                r.setRoad_qd(resultSet.getString(4));
                r.setRoad_zd(resultSet.getString(5));
                r.setRoad_sjdw(resultSet.getString(6));
                r.setRoad_sgdw(resultSet.getString(7));
                r.setRoad_dj(resultSet.getInt(8));
                r.setRoad_ss(resultSet.getString(9));
                r.setRoad_kd(resultSet.getString(10));
                r.setRoad_cd(resultSet.getString(11));
                r.setRoad_mj(resultSet.getString(12));
                r.setRoad_aadt(resultSet.getString(13));
                r.setRoad_jtl(resultSet.getString(14));
                r.setRoad_xz(resultSet.getString(15));
                r.setRoad_glfl(resultSet.getString(16));
                r.setRoad_gldw(resultSet.getString(17));
                r.setRoad_yhdw(resultSet.getString(18));
                r.setRoad_jzny(resultSet.getDate(19));
                r.setRoad_type(resultSet.getString(20));
            }
            HttpSession session = request.getSession();
            session.setAttribute("upRoad", r);
            response.sendRedirect("modiRoad.jsp");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            jdbConnection.closeConnecstion();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
