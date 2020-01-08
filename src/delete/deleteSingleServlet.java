package delete;


import JDB.JDBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/deleteSingleServlet")
public class deleteSingleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBConnection jdbConnection=new JDBConnection();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            //执行sql
            String main_damage=request.getParameter("main_damage");
            String main_road=request.getParameter("main_road");
            String density=request.getParameter("density");
            String sql="delete from single where main_damage ='"+main_damage+"'and main_road='"+main_road+"'and density='"+density+"'";
            System.out.println(sql);

            final boolean i = jdbConnection.executeUpdate("delete from single where main_damage ='"+main_damage+"'and main_road='"+main_road+"'and density='"+density+"'");
            response.sendRedirect("singleServlet?");
        }
        catch (Exception e) {
            e.printStackTrace();
            PrintWriter out = response.getWriter();
            out.print("<script>alert('存在外键约束，无法删除！');window.location.href='readSingle.jsp';</script>");
        }
        finally {
            jdbConnection.closeConnecstion();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
