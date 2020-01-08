package add;

import JDB.JDBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addRoadServlet")
public class addRoadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("gb2312");
        JDBConnection jdbConnection=new JDBConnection();
        try {
            //创建语句对象
            String road_name = request.getParameter("road_name");
            String road_id = request.getParameter("road_id");
            String road_zx = request.getParameter("road_zx");
            String road_qd = request.getParameter("road_qd");
            String road_zd = request.getParameter("road_zd");
            String road_sjdw = request.getParameter("road_sjdw");
            String road_sgdw = request.getParameter("road_sgdw");
            String road_dj = request.getParameter("road_dj");
            String road_ss = request.getParameter("road_ss");
            String road_kd = request.getParameter("road_kd");
            String road_cd = request.getParameter("road_cd");
            String road_mj = request.getParameter("road_mj");
            String road_aadt = request.getParameter("road_aadt");
            String road_jtl = request.getParameter("road_jtl");
            String road_xz = request.getParameter("road_xz");
            String road_glfl = request.getParameter("road_glfl");
            String road_gldw = request.getParameter("road_gldw");
            String road_yhdw = request.getParameter("road_yhdw");
            String road_jzny = request.getParameter("road_jzny");
            String road_type = request.getParameter("road_type");
            String lng = request.getParameter("lng");
            String lat = request.getParameter("lat");
            System.out.println("Longitude:" + lng + " LatitudeDouble.parseDouble:" + lat);
            String sql = "insert into road values('" + road_name + "','" + road_id + "','" + road_zx + "','" + road_qd + "','" + road_zd + "','" + road_sjdw + "','" + road_sgdw + "','" + road_dj + "','" + road_ss + "','" + road_kd + "','" + road_cd + "','" + road_mj + "','" + road_aadt + "','" + road_jtl + "','" + road_xz + "','"
                    + road_glfl + "','" + road_gldw + "','" + road_yhdw + "','" + road_jzny + "','" + road_type + "','" + Double.valueOf(lng) + "','" + Double.valueOf(lat) + "')";
            final boolean i = jdbConnection.executeUpdate(sql);
            System.out.println(sql);
            PrintWriter out = response.getWriter();
            if (i) {
                out.print("<script>;window.location.href='roadServlet';</script>");
            }else {
                out.print("<script>alert('添加失败！');window.location.href='roadServlet';</script>");
            }
        }
        catch (Exception e) {
            // e.printStackTrace();
            PrintWriter out = response.getWriter();
            out.print("<script>alert('输入有误！');window.location.href='readRoad.jsp';</script>");
        }
        finally {
            jdbConnection.closeConnecstion();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
