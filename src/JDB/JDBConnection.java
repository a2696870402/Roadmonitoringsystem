package JDB;

import java.sql.*;

public class JDBConnection {
    private final String dbDrive = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/data?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
    private final String user = "root";                 //数据库账户名
    private final String Passwd = "123456";       //数据库密码
    private Connection con = null;

    public JDBConnection() {
        try {
            Class.forName(dbDrive).newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("数据库加载失败!");
        }
        //System.out.println("数据库加载成功!");
    }

    public boolean creatConnection() {
        try {
            con = DriverManager.getConnection(url, user, Passwd);
            con.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return true;
    }

    public boolean executeUpdate(String sql) {      //对数据库进行增加，修改，删除
        if (con == null) {
            creatConnection();
        }
        try {
            Statement stmt = con.createStatement();
            int iCount = stmt.executeUpdate(sql);
            //System.out.println("操作成功,所影响的记录数为" + String.valueOf(iCount));
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    public ResultSet executeQuery(String sql)   //对数据库进行查询操作
    {
        ResultSet rs;
        try {
            if (con == null) {
                creatConnection();
            }
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            return null;
        }
        return rs;
    }

    public void closeConnecstion() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
