package test;

import JDB.JDBConnection;
import classes.maps;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/getMap")
public class getMap extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        JDBConnection jdbConnection=new JDBConnection();
        try
        {
            String sql="select road_id,road_name,lng,lat from road";
            ResultSet rs=jdbConnection.executeQuery(sql);
            ArrayList<maps> mapList=new ArrayList<maps>();
            while (rs.next())
            {
                maps m=new maps();
                m.setRoad_id(rs.getString(1));
                m.setRoad_name(rs.getString(2));
                m.setLng(Double.valueOf(rs.getString(3)));
                m.setLat(Double.valueOf(rs.getString(4)));
                mapList.add(m);
            }
            Map<String,Object> jsonMap=new HashMap<>();
            JSONArray jsonArray=JSONArray.fromObject(mapList);
            jsonMap.put("res", jsonArray);
            JSONObject jsonObject=JSONObject.fromObject(jsonMap);
            String jsonString=jsonObject.toString();
            System.out.println(jsonString);
            PrintWriter out=response.getWriter();
            out.write(jsonString);
            out.flush();
            out.close();
        }catch (Exception e)
        {
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
