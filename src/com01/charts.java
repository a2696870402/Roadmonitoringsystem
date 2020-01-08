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
import classes.analysisdata;

@WebServlet("/charts")
public class charts extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        System.out.println("来到了charts加载serlvet");
        //得到综合评价指数、平整度、破损状况的PQI 和检查时间
        JDBConnection jdbConnection=new JDBConnection();
        String sql = "select * from summary";
        ResultSet resultSet=jdbConnection.executeQuery(sql);

        ArrayList<analysisdata> arrayList=new ArrayList<>();
        try {
            if(resultSet!=null) {
                while (resultSet.next()) {
                    analysisdata userdata = new analysisdata();
                    userdata.setZonghe_PQI(resultSet.getString(4));
                    userdata.setPinzheng_RQI(resultSet.getString(6));
                    userdata.setPoshun_PCI(resultSet.getString(8));
                    userdata.setData(resultSet.getString(1));
                    if (resultSet.getString(7) != null) {
                        userdata.setPci_dj(resultSet.getString(5));
                        userdata.setRqi_dj(resultSet.getString(7));
                        userdata.setPci_dj(resultSet.getString(9));
                        arrayList.add(userdata);
                    }
                    //添加了损坏的才存在等级结果 否则不显示
                }
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

    }
}
