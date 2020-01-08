package com01;

import JDB.JDBConnection;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import classes.damage_info;
import sun.misc.Request;

@WebServlet("/response3")
public class response3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        System.out.println("response3收到了");
        String roid= request.getParameter("roid");
        System.out.println(roid);
        JDBConnection jdbConnection=new JDBConnection();
        String sql = "select *from damage_info";
        if(roid!=null&&roid!=""){
            //搜索功能
            sql=sql+" where road_id='"+roid+"'";
        }
        //System.out.println(sql);
        ResultSet resultSet=jdbConnection.executeQuery(sql);
        ArrayList<damage_info> damage_infoList=new ArrayList<>();
        try {
            while (resultSet.next()) {
                damage_info d1=new damage_info();
                d1.setD_data(resultSet.getString(1));
                d1.setD_worker(resultSet.getString(2));
                d1.setRoad_id(resultSet.getString(3));
                d1.setRoad_name(resultSet.getString(4));
                d1.setMain_damage(resultSet.getString(5));
                d1.setMain_road(resultSet.getString(6));
                d1.setD_start(resultSet.getString(7));
                d1.setD_Alllen(resultSet.getString(8));
                d1.setD_Alllwidth(resultSet.getString(9));
                d1.setD_len(resultSet.getString(10));
                d1.setD_width(resultSet.getString(11));
                d1.setD_height(resultSet.getString(12));
                d1.setD_des(resultSet.getString(13));
                d1.setD_area(resultSet.getString(14));
                d1.setD_note(resultSet.getString(15));
                d1.setDensity(resultSet.getString(16));
                d1.setScore(resultSet.getString(17));
                d1.setMain_class(resultSet.getString(18));
//
//                String sql2="select * from summary where s_date='"+d1.getD_data()+"' and road_id='"+d1.getRoad_id()+"'";
//                //查询该路的等级
//
                damage_infoList.add(d1);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            jdbConnection.closeConnecstion();
        }

        Map<String,Object> jsonArray=new HashMap<>();
        JSONArray jsonArray1=JSONArray.fromObject(damage_infoList);
        jsonArray.put("data",jsonArray1);
        //转为JSONObject
        JSONObject j1=JSONObject.fromObject(jsonArray);
        String jsonArrayString = j1.toString();
        System.out.println(jsonArrayString);
        PrintWriter out=response.getWriter();
        out.write(jsonArrayString);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
