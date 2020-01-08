package com01;

import JDB.JDBConnection;

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

import classes.daily;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/response2")
public class response2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        JDBConnection jdbConnection=new JDBConnection();
        JDBConnection jdbConnection1=new JDBConnection();
        String sql = "select *from daily";
        String roid=request.getParameter("roid");
        if(roid!=null&&roid!="") {
            sql = sql + " where road_id='" + roid + "'";
            //检查该道路的日检信息，实现查询功能
        }
        System.out.println(sql);
        ResultSet resultSet=jdbConnection.executeQuery(sql);
        ArrayList<daily> arrayList=new ArrayList<>();
        try {
            while (resultSet.next()) {
                daily d1=new daily();
                d1.setDate(resultSet.getString(1));
                d1.setWorker(resultSet.getString(2));
                d1.setRoad_id(resultSet.getString(3));
                d1.setRoad_name(resultSet.getString(4));
                d1.setMain_damage(resultSet.getString(5));
                d1.setMain_road(resultSet.getString(6));
                d1.setDaily_des(resultSet.getString(7));
                d1.setDaily_note(resultSet.getString(8));
                //获取路线等级，通过道路的编号获得
                String sql1="select road_dj from road where road_id='"+d1.getRoad_id()+"'";
                ResultSet resultSet1=jdbConnection1.executeQuery(sql1);
                resultSet1.next();
                d1.setRoad_level(resultSet1.getString(1));
                arrayList.add(d1);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            jdbConnection.closeConnecstion();
            jdbConnection1.closeConnecstion();
        }


        Map<String,Object> jsonArray=new HashMap<>();
        //把list 转化为 jsonArray
        //再存入到map中
        JSONArray jsonArray1=JSONArray.fromObject(arrayList);
        jsonArray.put("data",jsonArray1);
        //转为JSONObject
        JSONObject j1=JSONObject.fromObject(jsonArray);
        String jsonArrayString = j1.toString();
        PrintWriter out=response.getWriter();
        out.write(jsonArrayString);
        //System.out.println(jsonArrayString);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
