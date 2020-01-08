package damage;

import java.sql.Date;

public class damage_info {
    Date d_date;
    String d_worker;
    String road_id;
    String road_name;
    String main_damage;
    String main_road;
    String d_start;
    double d_Alllen;
    double d_Allwidth;
    double d_len;
    double d_width;
    double d_height;
    String d_des;
    double d_area;
    String d_note;
    double density;
    double score;
    String main_class;
    public Date getD_date() {
        return d_date;
    }

    public String getD_worker() {
        return d_worker;
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

    public String getD_start() {
        return d_start;
    }

    public double getD_Alllen() {
        return d_Alllen;
    }

    public double getD_Allwidth() {
        return d_Allwidth;
    }

    public double getD_len() {
        return d_len;
    }

    public double getD_width() {
        return d_width;
    }

    public double getD_height() {
        return d_height;
    }

    public String getD_des() {
        return d_des;
    }

    public double getD_area() {
        return d_area;
    }

    public String getD_note() {
        return d_note;
    }

    public double getScore() {
        return score;
    }

    public void setD_date(Date d_date) {
        this.d_date = d_date;
    }

    public void setD_worker(String d_worker) {
        this.d_worker = d_worker;
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

    public void setD_start(String d_start) {
        this.d_start = d_start;
    }

    public void setD_Alllen(double d_Alllen) {
        this.d_Alllen = d_Alllen;
    }

    public void setD_Allwidth(double d_Allwidth) {
        this.d_Allwidth = d_Allwidth;
    }

    public void setD_len(double d_len) {
        this.d_len = d_len;
    }

    public void setD_width(double d_width) {
        this.d_width = d_width;
    }

    public void setD_height(double d_height) {
        this.d_height = d_height;
    }

    public void setD_des(String d_des) {
        this.d_des = d_des;
    }

    public void setD_area(double d_area) {
        this.d_area = d_area;
    }

    public void setD_note(String d_note) {
        this.d_note = d_note;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getMain_class() {
        return main_class;
    }

    public void setMain_class(String main_class) {
        this.main_class = main_class;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }
}