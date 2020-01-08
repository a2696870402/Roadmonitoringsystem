package classes;

public class daily {
    private String date;
    private String worker;
    private String main_damage;
    private String main_road;
    private String road_id;
    private String road_name;
    private String daily_des;
    private String daily_note;
    private String road_level;


    public String getRoad_level() {
        return road_level;
    }

    public void setRoad_level(String road_level) {
        this.road_level = road_level;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getMain_damage() {
        return main_damage;
    }

    public void setMain_damage(String main_damage) {
        this.main_damage = main_damage;
    }

    public String getMain_road() {
        return main_road;
    }

    public void setMain_road(String main_road) {
        this.main_road = main_road;
    }

    public String getRoad_id() {
        return road_id;
    }

    public void setRoad_id(String road_id) {
        this.road_id = road_id;
    }

    public String getRoad_name() {
        return road_name;
    }

    public void setRoad_name(String road_name) {
        this.road_name = road_name;
    }

    public String getDaily_des() {
        return daily_des;
    }

    public void setDaily_des(String daily_des) {
        this.daily_des = daily_des;
    }

    public String getDaily_note() {
        return daily_note;
    }

    public void setDaily_note(String daily_note) {
        this.daily_note = daily_note;
    }
}
