package modi;


import JDB.JDBConnection;
import damage.damage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet("/modiDamageServlet")
public class modiDamageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBConnection jdbConnection=new JDBConnection();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            //执行sql
            String main_damage=request.getParameter("main_damage");
            String main_road=request.getParameter("main_road");
            String str="select * from main_damage where main_damage ='"+main_damage+"'and main_road='"+main_road+"'";
            ResultSet resultSet = jdbConnection.executeQuery(str);
            damage r=new damage();
            while(resultSet.next())
            {
                r.setMain_damage(resultSet.getString(1));
                r.setMain_road(resultSet.getString(2));
                r.setMain_shape(resultSet.getString(3));
                r.setMain_class(resultSet.getString(4));
            }
            HttpSession session = request.getSession();
            session.setAttribute("upDamage", r);
            response.sendRedirect("modiDamage.jsp");
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
