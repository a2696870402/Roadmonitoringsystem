package com01;

import JDB.JDBConnection;
import classes.damage_info;
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

@WebServlet("/return_nianjian_id")
public class return_nianjian_id extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        String d_date=request.getParameter("d_date");
        String d_worker=request.getParameter("d_worker");
        System.out.println(d_date+","+d_worker);
        //去查询该id下的年鉴报告
        String sql = "select *from damage_info where d_date='"+d_date+"'and d_worker='"+d_worker+"'";
        System.out.println(sql);
        JDBConnection jdbConnection=new JDBConnection();
        ResultSet resultSet=jdbConnection.executeQuery(sql);
        ArrayList<damage_info> damage_infoList=new ArrayList<>();
        try {
           if(resultSet.next()) {
                damage_info d1=new damage_info();
                d1.setD_data(resultSet.getString(1));
                d1.setD_worker(resultSet.getString(2));
                d1.setRoad_id(resultSet.getString(3));
                d1.setRoad_name(resultSet.getString(4));
                d1.setD_start(resultSet.getString(5));
                d1.setD_Alllen(resultSet.getString(6));
                d1.setD_Alllwidth(resultSet.getString(7));
                d1.setMain_damage(resultSet.getString(8));
                d1.setMain_road(resultSet.getString(9));
                d1.setD_len(resultSet.getString(10));
                d1.setD_width(resultSet.getString(11));
                d1.setD_height(resultSet.getString(12));
                d1.setD_des(resultSet.getString(13));
                d1.setD_area(resultSet.getString(14));
                d1.setD_note(resultSet.getString(15));
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
