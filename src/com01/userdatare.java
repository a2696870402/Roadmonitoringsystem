package com01;

import JDB.JDBConnection;
import classes.daily;
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
import classes.userdata;

@WebServlet("/userdatare")
public class userdatare extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        //传回user的数据
        System.out.println("usedata_response收到了");
        JDBConnection jdbConnection=new JDBConnection();
        String sql = "select per from workers";
        ResultSet resultSet=jdbConnection.executeQuery(sql);
        ArrayList<userdata> arrayList=new ArrayList<>();
        try {
            while (resultSet.next()) {
                userdata d1=new userdata();
                d1.setName(resultSet.getString(1));
                arrayList.add(d1);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            jdbConnection.closeConnecstion();
        }
        Map<String,Object> jsonArray=new HashMap<>();
        //把list 转化为 jsonArray
        //再存入到map中
        JSONArray jsonArray1=JSONArray.fromObject(arrayList);
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
