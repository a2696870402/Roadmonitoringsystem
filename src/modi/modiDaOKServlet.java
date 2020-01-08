package modi;



import JDB.JDBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/modiDaOKServlet")
public class modiDaOKServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBConnection jdbConnection=new JDBConnection();
        try {
            //执行sql
            String main_damage=request.getParameter("main_damage");
            String main_road=request.getParameter("main_road");
            String main_shape=request.getParameter("main_shape");
            String main_class=request.getParameter("main_class");
            final boolean i = jdbConnection.executeUpdate("update main_damage set main_shape='" +main_shape+ "',main_class='" +main_class+ "'where main_damage='" + main_damage + "'and main_road='" + main_road + "'");
            response.sendRedirect("damageServlet");
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
