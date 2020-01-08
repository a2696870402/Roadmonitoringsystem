package com01;

import JDB.JDBConnection;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/delete_nianjian")
public class delete_nianjian extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        String d_data=request.getParameter("d_data");
        String road_id=request.getParameter("road_id");
        String main_damage=request.getParameter("main_damage");
        System.out.println("来到了年鉴删除");
        JDBConnection jdbConnection=new JDBConnection();
        String sql="delete from damage_info where d_date='"+d_data+"' and road_id='"+road_id+"' and main_damage='"+main_damage+"'";
        System.out.println(sql);
        PrintWriter out=response.getWriter();
        try {
            if (jdbConnection.executeUpdate(sql)) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("data","success");
                System.out.println(jsonObject.toString());
                out.write(jsonObject.toString());
                out.flush();
                out.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        //删除 该id
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
