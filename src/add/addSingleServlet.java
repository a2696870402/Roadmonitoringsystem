package add;

import JDB.JDBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addSingleServlet")
public class addSingleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        JDBConnection jdbConnection=new JDBConnection();
        try {
            //执行sql
            String main_damage=request.getParameter("main_damage");
            String main_road=request.getParameter("main_road");
            String density=request.getParameter("density");
            String koufen=request.getParameter("koufen");
            final boolean i = jdbConnection.executeUpdate("insert into single values('" +main_damage+ "','" +main_road+ "','" +density+ "','" +koufen+ "')");
            response.sendRedirect("singleServlet?");
        }
        catch (Exception e) {
            // e.printStackTrace();
            PrintWriter out = response.getWriter();
            out.print("<script>alert('输入有误！');window.location.href='readSingle.jsp';</script>");
        }
        finally {
            jdbConnection.closeConnecstion();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
