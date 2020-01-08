package damage;

public class damage {
    private String main_road;
    private String main_class;
    private String main_damage;
    private String main_shape;
    public damage(){
    }

    public damage(String main_road, String main_class, String main_damage, String main_shape) {
        this.main_road = main_road;
        this.main_class = main_class;
        this.main_damage = main_damage;
        this.main_shape = main_shape;
    }

    public String getMain_road() {
        return main_road;
    }

    public void setMain_road(String main_road) {
        this.main_road = main_road;
    }

    public String getMain_class() {
        return main_class;
    }

    public void setMain_class(String main_class) {
        this.main_class = main_class;
    }

    public String getMain_damage() {
        return main_damage;
    }

    public void setMain_damage(String main_damage) {
        this.main_damage = main_damage;
    }

    public String getMain_shape() {
        return main_shape;
    }

    public void setMain_shape(String main_shape) {
        this.main_shape = main_shape;
    }
}
