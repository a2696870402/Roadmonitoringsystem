package daily;

import java.sql.Date;

public class daily {
    private Date daily_date;
    private String daily_worker;
    private String road_id;
    private String road_name;
    private String main_damage;
    private String main_road;
    private String daily_des;
    private String daily_note;

    public void setDaily_date(Date daily_date) {
        this.daily_date = daily_date;
    }

    public void setDaily_worker(String daily_worker) {
        this.daily_worker = daily_worker;
    }

    public void setRoad_id(String road_id) {
        this.road_id = road_id;
    }

    public void setRoad_name(String road_name) {
        this.road_name = road_name;
    }

    public void setMain_damage(String main_damage) {
        this.main_damage = main_damage;
    }

    public void setMain_road(String main_road) {
        this.main_road = main_road;
    }

    public void setDaily_des(String daily_des) {
        this.daily_des = daily_des;
    }

    public void setDaily_note(String daily_note) {
        this.daily_note = daily_note;
    }

    public Date getDaily_date() {
        return daily_date;
    }

    public String getDaily_worker() {
        return daily_worker;
    }

    public String getRoad_id() {
        return road_id;
    }

    public String getRoad_name() {
        return road_name;
    }

    public String getMain_damage() {
        return main_damage;
    }

    public String getMain_road() {
        return main_road;
    }

    public String getDaily_des() {
        return daily_des;
    }

    public String getDaily_note() {
        return daily_note;
    }
}
