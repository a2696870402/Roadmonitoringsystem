package modi;



import JDB.JDBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/modiSiOKServlet")
public class modiSiOKServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBConnection jdbConnection=new JDBConnection();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            //执行sql
            String main_damage=request.getParameter("main_damage");
            String main_road=request.getParameter("main_road");
            String density=request.getParameter("density");
            String koufen=request.getParameter("koufen");
            final boolean i = jdbConnection.executeUpdate("update single set koufen='" +koufen+ "'where main_damage='" + main_damage + "'and main_road='" + main_road + "'and density='" + density + "'");
            response.sendRedirect("singleServlet");
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
