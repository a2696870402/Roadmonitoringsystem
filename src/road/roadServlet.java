package road;



import JDB.JDBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/roadServlet")
public class roadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("gb2312");
        JDBConnection jdbConnection=new JDBConnection();
        try {
            List<road> list =new ArrayList<road>();
            final ResultSet rs = jdbConnection.executeQuery("select road_id,road_name,road_dj,road_type from road");
            while (rs.next()) {
                road ac=new road();
                ac.setRoad_id(rs.getString(1));
                ac.setRoad_name(rs.getString(2));
                ac.setRoad_dj(rs.getInt(3));
                ac.setRoad_type(rs.getString(4));
                list.add(ac);
            }
            HttpSession session = request.getSession();
            session.setAttribute("road", list);
            response.sendRedirect("readRoad.jsp");
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
