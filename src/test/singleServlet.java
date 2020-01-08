package test;



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

@WebServlet("/singleServlet")
public class singleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        JDBConnection jdbConnection=new JDBConnection();
        try {
            List<single> list =new ArrayList<single>();
            final ResultSet rs = jdbConnection.executeQuery("select main_damage,main_road,density,koufen from single order by main_road,main_damage,density");
            while (rs.next()) {
                single ac=new single();
                ac.setMain_damage(rs.getString(1));
                ac.setMain_road(rs.getString(2));
                ac.setDensity(rs.getFloat(3));
                ac.setKoufen(rs.getInt(4));
                list.add(ac);
            }
            HttpSession session = request.getSession();
            session.setAttribute("single", list);
            response.sendRedirect("readSingle.jsp");
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
