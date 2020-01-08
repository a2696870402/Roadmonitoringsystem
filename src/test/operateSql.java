package test;



import JDB.JDBConnection;
import daily.daily;
import damage.damage_info;
import planeness.planeness;
import summary.summary;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class operateSql {
    public boolean deleteDa_info(Year year, String road_id) { // 删除
        String sql = "DELETE FROM damage_info WHERE road_id='"+road_id+"' and YEAR(d_date)='"+year+"'"; // 删除的SQL语句，根据ID删除
        System.out.println(sql);
        JDBConnection jdbConnection=new JDBConnection();
        try {
            boolean count = jdbConnection.executeUpdate(sql);
            return count; // 是否删除的判断
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return false;
    }
    public boolean deleteSummary(Year year,String road_id)
    {
        String sql="delete from summary where s_date='"+year+"' and road_id='"+ road_id+"'";
        System.out.println(sql);
        JDBConnection jdbConnection=new JDBConnection();
        try {
            boolean count =jdbConnection.executeUpdate(sql);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return false;
    }
    public List<planeness> getPlaneness() { // 查询所有信息
        List<planeness> list = new ArrayList<planeness>(); // 创建集合
        JDBConnection jdbConnection=new JDBConnection();
        String sql = "select * from planeness"; // SQL查询语句
        try {
            ResultSet rest = jdbConnection.executeQuery(sql);
            while (rest.next()) {
                planeness p=new planeness();
                p.setP_date(rest.getDate(1));
                p.setRoad_id(rest.getString(2));
                p.setRoad_name(rest.getString(3));
                p.setP_worker(rest.getString(4));
                p.setP_iri(rest.getFloat(5));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return list;
    }
    public boolean addPlaneness(planeness p)
    {
        String sql = "INSERT INTO planeness VALUES ('"+p.getP_date()+"','"+p.getRoad_id()+"','"+p.getRoad_name()+"','"+p.getP_worker()+"','"+p.getP_iri()+"')";
        JDBConnection jdbConnection=new JDBConnection();
        try {
            boolean count = jdbConnection.executeUpdate(sql);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return false;
    }
    public boolean updatePlaneness(planeness p) { // 修改p_date=?,road_id=? ,road_name=? , p_worker=? ,
        String sql = "update planeness set p_iri='"+p.getP_iri()+"', p_worker='"+p.getP_worker()+"' WHERE p_date ='"+p.getP_date()+"' and road_id='"+p.getRoad_id()+"'";
        JDBConnection jdbConnection=new JDBConnection();
        try {
            boolean count = jdbConnection.executeUpdate(sql);
            return count; // 是否修改的判断
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return false;
    }
    public boolean deletePlaneness(Date p_date, String road_id) { // 删除
        String sql = "delete from planeness where p_date='"+p_date+"' and road_id='"+road_id+"' "; // 删除的SQL语句，根据ID删除
        JDBConnection jdbConnection=new JDBConnection();
        try {
            boolean count = jdbConnection.executeUpdate(sql);
            return count; // 是否删除的判断
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return false;
    }
    public List<daily> getDaily() { // 查询所有信息
        List<daily> list = new ArrayList<daily>(); // 创建集合
        JDBConnection jdbConnection=new JDBConnection();
        String sql = "select * from daily"; // SQL查询语句
        try {
            ResultSet rest = jdbConnection.executeQuery(sql);
            while (rest.next()) {
                daily d=new daily();
                d.setDaily_date(rest.getDate(1));
                d.setDaily_worker(rest.getString(2));
                d.setRoad_id(rest.getString(3));
                d.setRoad_name(rest.getString(4));
                d.setMain_damage(rest.getString(5));
                d.setMain_road(rest.getString(6));
                d.setDaily_des(rest.getString(7));
                d.setDaily_note(rest.getString(8));
                list.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return list;
    }
    public boolean addDaily(daily d)
    {
        String sql = "INSERT INTO daily VALUES ('"+d.getDaily_date()+"','"+d.getDaily_worker()+"','"+d.getRoad_id()+"','"+d.getRoad_name()+"','"+d.getMain_damage()+"','"+d.getMain_road()+"','"+d.getDaily_des()+"','"+d.getDaily_note()+"')";
        System.out.println(sql);
        JDBConnection jdbConnection=new JDBConnection();
        try {
            boolean count1 = jdbConnection.executeUpdate(sql);
            return count1;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return false;
    }
    public boolean updateDaily(daily d) { // 修改
        //String sql = "update daily set daily_worker=? , main_damage=? ,daily_des=?,daily_note=? WHERE daily_date =?";
        String sql="update daily set daily_worker='"+d.getDaily_worker()+"',main_damage='"+d.getMain_damage()+
                "',daily_des='"+ d.getDaily_des() +"',daily_note='"+d.getDaily_note()+"' where daily_date='"+d.getDaily_date() +
                "' and road_id='"+d.getRoad_id()+
                "' and main_damage='"+d.getMain_damage()+"'";
        System.out.println(sql);
        JDBConnection jdbConnection=new JDBConnection();
        try {
            boolean count = jdbConnection.executeUpdate(sql);
            return count ; // 是否修改的判断
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            jdbConnection.closeConnecstion();
        }
      //  return false;
    }
    public boolean deleteDaily(Date daily_date, String road_id, String main_damage) { // 删除
        String sql = "delete from daily where daily_date='"+daily_date+"' and road_id='"+road_id+"' and main_damage='"+main_damage+"' "; // 删除的SQL语句，根据ID删除
        System.out.println(sql);
        JDBConnection jdbConnection=new JDBConnection();
        try {
            boolean count = jdbConnection.executeUpdate(sql);
            return count; // 是否删除的判断
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return false;
    }
    public List<damage_info> getDamage_info() { // 查询所有信息
        List<damage_info> list = new ArrayList<damage_info>(); // 创建集合
        JDBConnection jdbConnection=new JDBConnection();
        String sql = "select * from damage_info"; // SQL查询语句
        try {
            ResultSet rest = jdbConnection.executeQuery(sql);
            while (rest.next()) {
                damage_info d=new damage_info();
                d.setD_date(rest.getDate(1));
                d.setD_worker(rest.getString(2));
                d.setRoad_id(rest.getString(3));
                d.setRoad_name(rest.getString(4));
                d.setMain_damage(rest.getString(5));
                d.setMain_road(rest.getString(6));
                d.setD_start(rest.getString(7));
                d.setD_Alllen(rest.getDouble(8));
                d.setD_Allwidth(rest.getDouble(9));
                d.setD_len(rest.getDouble(10));
                d.setD_width(rest.getDouble(11));
                d.setD_height(rest.getDouble(12));
                d.setD_des(rest.getString(13));
                d.setD_area(rest.getDouble(14));
                d.setD_note(rest.getString(15));
                d.setScore(rest.getDouble(16));
                list.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return list;
    }
    public boolean addDamage_info(damage_info d)
    {
        String sql = "INSERT INTO damage_info VALUES ('"+d.getD_date()+"','"+d.getD_worker()+"','"+d.getRoad_id()+"','"+d.getRoad_name()+"','"+d.getMain_damage()+"','"+d.getMain_road()+"','"+d.getD_start()+"','"+d.getD_Alllen()+"','"+d.getD_Allwidth()+"','"+d.getD_len()+"','"+d.getD_width()+"','"+d.getD_height()+"','"+d.getD_des()+"','"+d.getD_area()+"','"+d.getD_note()+"','"+d.getDensity()+"','"+d.getScore()+"','"+d.getMain_class()+"')";
        JDBConnection jdbConnection=new JDBConnection();
        System.out.println(sql);
        try {
            boolean count = jdbConnection.executeUpdate(sql);
            return count ;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return false;
    }
    public boolean updateDamage_info(damage_info d) { // 修改p_date=?,road_id=? ,road_name=? , p_worker=? ,
        String sql="update damage_info set d_worker='"+d.getD_worker()+"',main_damage='"+d.getMain_damage()+
                "',d_start='"+ d.getD_start() +"',d_Alllen="+d.getD_Alllen()+",d_Allwidth="+d.getD_Allwidth()+
                ",d_len="+d.getD_len()+",d_width="+d.getD_width()+",d_height="+d.getD_height()+",d_area="+d.getD_area()+
                ",d_des='"+d.getD_des()+"',d_note='"+d.getD_note()+"',score='"+d.getScore()+"',density='"+d.getDensity() +"',main_class='"+d.getMain_class()+"'" +
                "where d_date='"+d.getD_date() + "' and road_id='"+d.getRoad_id()+"' and main_damage='"+d.getMain_damage()+"'";
        JDBConnection jdbConnection=new JDBConnection();
        System.out.println(sql);
        try {
            boolean count = jdbConnection.executeUpdate(sql);
            //System.out.println(count);
            return count; // 是否修改的判断
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return false;
    }
    public boolean deleteDamage_info(Date d_date, String road_id, String main_damage) { // 删除
        String sql = "delete from damage_info where d_date='"+d_date+"' and road_id='"+ road_id+"' and main_damage='"+main_damage+"'"; // 删除的SQL语句，根据ID删除
        JDBConnection jdbConnection=new JDBConnection();
        try {
            boolean count = jdbConnection.executeUpdate(sql);
            return count; // 是否删除的判断
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return false;
    }
    public List<single> getSingle(String main_damage, String main_road) { // 查询所有信息
        List<single> list = new ArrayList<single>(); // 创建集合
        JDBConnection jdbConnection=new JDBConnection();
        String sql = "select * from single where main_damage='"+main_damage+"' and main_road='"+main_road+"'"; // SQL查询语句
        try {
            ResultSet rest = jdbConnection.executeQuery(sql);
            while (rest.next()) {
                single s=new single();
                s.setMain_damage(rest.getString(1));
                s.setMain_road(rest.getString(2));
                s.setDensity(rest.getFloat(3));
                s.setKoufen(rest.getInt(4));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return list;
    }
    public boolean addSummary(Year year,String road_id,String road_name,double rqi)
    {
        String sql="insert into summary(s_date,road_id,road_name,RQI) values ("+year+",'"+road_id+"','"+road_name+"',"+rqi+")";
        JDBConnection jdbConnection=new JDBConnection();
        try {
            boolean count =jdbConnection.executeUpdate(sql);

            return count ; // 是否增加的判断
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return false;
    }
    public String getDateAndID(Year year,String road_id)
    {//SELECT p_iri FROM planeness,summary WHERE YEAR(p_date)=summary.s_date AND planeness.road_id=summary.road_id
        String result="";
        JDBConnection jdbConnection=new JDBConnection();
        //SELECT distinct d_date,road_id FROM damage_info WHERE YEAR(d_date)=2021 AND road_id='r_1002'
        String sql = "select d_date,road_id FROM damage_info where road_id='"+road_id+"'and YEAR(d_date)="+year;
        try {
            ResultSet rest = jdbConnection.executeQuery(sql);
            while (rest.next()) {
                String date=rest.getString(1);
                String id=rest.getString(2);
                result=date+","+id;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return result;
    }
    public String getMain_Road(Year year,String road_id)
    {//SELECT p_iri FROM planeness,summary WHERE YEAR(p_date)=summary.s_date AND planeness.road_id=summary.road_id
        String result="";
        JDBConnection jdbConnection=new JDBConnection();
        //SELECT distinct main_road FROM damage_info WHERE YEAR(d_date)=2021 AND road_id='r_1002'
        String sql = "select main_road FROM damage_info where road_id='"+road_id+"' and YEAR(d_date)="+year;
        try {
            ResultSet rest = jdbConnection.executeQuery(sql);
            while (rest.next()) {
                result=rest.getString(1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return result;
    }
    public int getDj(String road_id)
    {
        int dj=0;
        JDBConnection jdbConnection=new JDBConnection();
        //SELECT road_dj FROM road WHERE road_id=''
        String sql = "select road_dj FROM road where road_id='"+road_id+"'";
        try {
            ResultSet rest = jdbConnection.executeQuery(sql);
            while (rest.next()) {
                dj=rest.getInt(1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return dj;
    }
    public List<String> getMain_classArray(Date date,String road_id)
    {
        String result=null;
        List<String> list = new ArrayList<String>();
        JDBConnection jdbConnection=new JDBConnection();
        String sql = "select distinct main_class from damage_info where d_date='"+date+"' and road_id='"+road_id+"'";
        try {
            ResultSet rest = jdbConnection.executeQuery(sql);
            while (rest.next()) {
                String s=rest.getString(1);
                list.add(s);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return list;
    }
    public List<damage_info> getDamage_infoArrry(String main_class,String main_road) { // 根据损害大类查询信息
        List<damage_info> list = new ArrayList<damage_info>();
        JDBConnection jdbConnection=new JDBConnection();
        String sql = "select * from damage_info where main_class='"+main_class+"' and main_road='"+main_road+"'"; // SQL查询语句
        try {
            ResultSet rest = jdbConnection.executeQuery(sql);
            while (rest.next()) {
                damage_info d=new damage_info();
                d.setD_date(rest.getDate(1));
                d.setD_worker(rest.getString(2));
                d.setRoad_id(rest.getString(3));
                d.setRoad_name(rest.getString(4));
                d.setMain_damage(rest.getString(5));
                d.setMain_road(rest.getString(6));
                d.setD_start(rest.getString(7));
                d.setD_Alllen(rest.getDouble(8));
                d.setD_Allwidth(rest.getDouble(9));
                d.setD_len(rest.getDouble(10));
                d.setD_width(rest.getDouble(11));
                d.setD_height(rest.getDouble(12));
                d.setD_des(rest.getString(13));
                d.setD_area(rest.getDouble(14));
                d.setD_note(rest.getString(15));
                d.setDensity(rest.getDouble(16));
                d.setScore(rest.getDouble(17));
                d.setMain_class(rest.getString(18));
                list.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return list;
    }
    public double getIRI(Year year,String road_id)
    {//SELECT p_iri FROM planeness,summary WHERE YEAR(p_date)=summary.s_date AND planeness.road_id=summary.road_id
        double result=-1;
        JDBConnection jdbConnection=new JDBConnection();
        //SELECT p_iri FROM planeness WHERE YEAR(p_date)=2021 AND road_id='r_1002'
        String sql = "select p_iri from planeness where road_id='"+road_id+"' and YEAR(p_date)="+year;
        try {
            ResultSet rest = jdbConnection.executeQuery(sql);
            while (rest.next()) {
                result = rest.getDouble(1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return result;
    }
    public boolean addSummary(Year year,String road_id,String road_name)
    {
        String sql="insert into summary(s_date,road_id,road_name) values ("+year+",'"+road_id+"','"+road_name+"')";
        JDBConnection jdbConnection=new JDBConnection();
        try {
            boolean count =jdbConnection.executeUpdate(sql);
            return count; // 是否增加的判断
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return false;
    }
    public boolean updateSummary(Year year,String road_id,double PQI,String PQI_DJ,double RQI,String RQI_DJ,double PCI,String PCI_DJ)
    {
        String sql="update summary set PQI="+PQI+", PQI_DJ='"+PQI_DJ+"', RQI="+RQI+",RQI_DJ='"+RQI_DJ+"',PCI="+PCI+",PCI_DJ='"+PCI_DJ+"' WHERE s_date ="+year+" and road_id='"+road_id+"'";
        System.out.println(sql);
        JDBConnection jdbConnection=new JDBConnection();
        try {
            boolean count =jdbConnection.executeUpdate(sql);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return false;
    }
    public List<summary> getSummary()
    {
        List<summary> list = new ArrayList<summary>();
        JDBConnection jdbConnection=new JDBConnection();
        String sql ="select * from summary";
        try{
            ResultSet rs = jdbConnection.executeQuery(sql);
            while (rs.next()) {
                summary ac=new summary();
                Date date=rs.getDate(1);
                Calendar cal=Calendar.getInstance();
                cal.setTime(date);
                Year year= Year.of(cal.get(Calendar.YEAR));
                ac.setS_date(year);
                ac.setRoad_id(rs.getString(2));
                ac.setRoad_name(rs.getString(3));
                ac.setPQI(rs.getFloat(4));
                ac.setRQI(rs.getFloat(5));
                ac.setPCI(rs.getFloat(6));
                list.add(ac);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return list;
    }
    public String getMain_class(String main_damage,String main_road)
    {
        String result=null;
        JDBConnection jdbConnection=new JDBConnection();
        String sql = "select main_class from main_damage where main_damage='"+main_damage+"' and main_road='"+main_road+"'";
        try {
            ResultSet rest = jdbConnection.executeQuery(sql);
            rest.next();
            result = rest.getString(1);
            System.out.println("当前主要类型为："+result);
            return result;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbConnection.closeConnecstion();
        }
        return "未添加";
    }

}