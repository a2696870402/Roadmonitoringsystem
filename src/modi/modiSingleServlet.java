package modi;



import JDB.JDBConnection;
import test.single;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet("/modiSingleServlet")
public class modiSingleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBConnection jdbConnection=new JDBConnection();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            //执行sql
            String main_damage=request.getParameter("main_damage");
            String main_road=request.getParameter("main_road");
            String density=request.getParameter("density");
            String str="select * from single where main_damage ='"+main_damage+"'and main_road='"+main_road+"'and density='"+density+"'";
            ResultSet resultSet = jdbConnection.executeQuery(str);
            single r=new single();
            while(resultSet.next())
            {
                r.setMain_damage(resultSet.getString(1));
                r.setMain_road(resultSet.getString(2));
                r.setDensity(resultSet.getFloat(3));
                r.setKoufen(resultSet.getInt(4));

            }
            HttpSession session = request.getSession();
            session.setAttribute("upSingle", r);
            response.sendRedirect("modiSingle.jsp");
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
