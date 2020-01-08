package com01;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@WebServlet("/response")
public class response extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //传回数据
//        System.out.println("收到了？？？？？？！！！");
        Integer[] values = {1,2,3,4,5,6,7,8};
        for(int i =0;i<8;i++){
            Random random=new Random();
            values[i]= (random.nextInt(100)*10+10);
        }
        String[] categories = {"第一", "第二","第三","第四","第五","第六","第七","第八"};
        Map<String,Object> json=new HashMap<>();
        //添加数据
        json.put("categories",categories);
        json.put("values",values);
        String jsonStr="";
        //把map转换为json
        JSONObject jsonObject = JSONObject.fromObject(json);
        jsonStr = jsonObject.toString();

//        System.out.println(jsonStr);

        out.write(jsonStr);
        out.flush();
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
