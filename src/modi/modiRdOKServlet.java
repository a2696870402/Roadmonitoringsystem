package modi;



import JDB.JDBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/modiRdOKServlet")
public class modiRdOKServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBConnection jdbConnection=new JDBConnection();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            //执行sql
            String road_name=request.getParameter("road_name");
            String road_id=request.getParameter("road_id");
            String road_zx=request.getParameter("road_zx");
            String road_qd=request.getParameter("road_qd");
            String road_zd=request.getParameter("road_zd");
            String road_sjdw=request.getParameter("road_sjdw");
            String road_sgdw=request.getParameter("road_sgdw");
            String road_dj=request.getParameter("road_dj");
            String road_ss=request.getParameter("road_ss");
            String road_kd=request.getParameter("road_kd");
            String road_cd=request.getParameter("road_cd");
            String road_mj=request.getParameter("road_mj");
            String road_aadt=request.getParameter("road_aadt");
            String road_jtl=request.getParameter("road_jtl");
            String road_xz=request.getParameter("road_xz");
            String road_glfl=request.getParameter("road_glfl");
            String road_gldw=request.getParameter("road_gldw");
            String road_yhdw=request.getParameter("road_yhdw");
            String road_jzny=request.getParameter("road_jzny");
            String road_type=request.getParameter("road_type");
            final boolean i = jdbConnection.executeUpdate("update road set road_zx='" +road_zx+ "',road_qd='" +road_qd+ "',road_zd='" +road_zd+ "',road_sjdw='" +road_sjdw+ "',road_sgdw='" +road_sgdw+ "',road_dj='" +road_dj+ "',road_ss='" +road_ss+"',road_kd='" +road_kd+"',road_cd='" +road_cd+"',road_mj='" +road_mj+"',road_aadt='" +road_aadt+"',road_jtl='" +road_jtl+"',road_xz='" +road_xz+"',road_glfl='" +road_glfl+"',road_gldw='" +road_gldw+"',road_yhdw='" +road_yhdw+"',road_jzny='" +road_jzny+"',road_type='" +road_type+"'where road_id='" + road_id + "'and road_name='" + road_name + "'");
            response.sendRedirect("roadServlet?");
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
