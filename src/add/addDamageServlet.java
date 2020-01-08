package add;

import JDB.JDBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

@WebServlet("/addDamageServlet")
public class addDamageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = null;
        Statement stmt = null;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        JDBConnection jdbConnection=new JDBConnection();
        try {
            //执行sql
            String main_damage=request.getParameter("main_damage");
            String main_road=request.getParameter("main_road");
            String main_shape=request.getParameter("main_shape");
            String main_class=request.getParameter("main_class");
            final boolean i = jdbConnection.executeUpdate("insert into main_damage values('" +main_damage+ "','" +main_road+ "','" +main_shape+ "','" +main_class+ "')");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('请尽快添加对应的损坏单项扣分！');window.location.href='damageServlet?';</script>");
           // response.sendRedirect("damageServlet?");
        }
        catch (Exception e) {
            // e.printStackTrace();
            PrintWriter out = response.getWriter();
            out.print("<script>alert('输入有误！');window.location.href='readDamage.jsp';</script>");
        }
        finally {
            jdbConnection.closeConnecstion();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
