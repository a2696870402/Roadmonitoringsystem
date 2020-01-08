package summary;



import JDB.JDBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@WebServlet("/summaryServlet")
public class summaryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        JDBConnection jdbConnection = new JDBConnection();
        try {
            List<summary> list = new ArrayList<>();
            final ResultSet rs = jdbConnection.executeQuery("select * from summary");
            while (rs.next()) {
                summary ac = new summary();
                Date date1 = rs.getDate(1);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date1);
                Year year = Year.of(cal.get(Calendar.YEAR));
                ac.setS_date(year);
                ac.setRoad_id(rs.getString(2));
                ac.setRoad_name(rs.getString(3));
                ac.setPQI(rs.getFloat(4));
                ac.setPQI_DJ(rs.getString(5));
                ac.setRQI(rs.getFloat(6));
                ac.setRQI_DJ(rs.getString(7));
                ac.setPCI(rs.getFloat(8));
                ac.setPCI_DJ(rs.getString(9));
                list.add(ac);
                System.out.println("pci="+ac.getPCI());
                System.out.println("rqi="+ac.getRQI());
                System.out.println("rpi="+ac.getPQI());

            }
            HttpSession session = request.getSession();
            session.setAttribute("summary", list);
            response.sendRedirect("readSummary.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbConnection.closeConnecstion();
        }
    }
    //}
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
