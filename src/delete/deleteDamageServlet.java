package delete;


import JDB.JDBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deleteDamageServlet")
public class deleteDamageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf8");
        request.setCharacterEncoding("utf8");
        JDBConnection jdbConnection=new JDBConnection();
        try {
            //执行sql
            String main_damage=request.getParameter("main_damage");
            String main_road=request.getParameter("main_road");
            final boolean i = jdbConnection.executeUpdate("delete from main_damage where main_damage ='"+main_damage+"'and main_road='"+main_road+"'");
            response.sendRedirect("damageServlet?");
        }
        catch (Exception e) {
            e.printStackTrace();
            PrintWriter out = response.getWriter();
            out.print("<script>alert('存在外键约束，无法删除！');window.location.href='readDamage.jsp';</script>");
        }
        finally {
            jdbConnection.closeConnecstion();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
