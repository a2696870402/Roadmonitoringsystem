package test;

public class single {
    private String main_damage;
    private String main_road;
    private float density;
    private int koufen;

    public single(){
    }

    public single(String main_damage, String main_road, float density, int koufen) {
        this.main_damage = main_damage;
        this.main_road = main_road;
        this.density = density;
        this.koufen = koufen;
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

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public int getKoufen() {
        return koufen;
    }

    public void setKoufen(int koufen) {
        this.koufen = koufen;
    }
}

