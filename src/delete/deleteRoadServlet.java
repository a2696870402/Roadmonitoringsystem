package delete;


import JDB.JDBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteRoadServlet")
public class deleteRoadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        JDBConnection jdbConnection=new JDBConnection();
        try {
            //执行sql
            String road_id=request.getParameter("road_id");
            final boolean i = jdbConnection.executeUpdate("delete from road where road_id ='"+road_id+"'");
            response.sendRedirect("roadServlet?");
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
