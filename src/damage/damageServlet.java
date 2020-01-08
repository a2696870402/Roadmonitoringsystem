package damage;


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

@WebServlet("/damageServlet")
public class damageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        JDBConnection jdbConnection=new JDBConnection();
        try {
            List<damage> list = new ArrayList<damage>();
            final ResultSet rs = jdbConnection.executeQuery("select main_damage,main_road,main_shape,main_class from main_damage order by main_road,main_class");
            while (rs.next()) {
                damage ac=new damage();
                ac.setMain_damage(rs.getString(1));
                ac.setMain_road(rs.getString(2));
                ac.setMain_shape(rs.getString(3));
                ac.setMain_class(rs.getString(4));
                list.add(ac);
            }
            HttpSession session = request.getSession();
            session.setAttribute("damage", list);
            response.sendRedirect("readDamage.jsp");
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
