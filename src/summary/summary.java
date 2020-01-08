package summary;

import java.time.Year;

public class summary {

    private Year s_date;
    private String road_id;
    private String road_name;
    private float PQI;
    private float RQI;
    private float PCI;
    private String PQI_DJ;
    private String RQI_DJ;
    private String PCI_DJ;

    public String getPQI_DJ() {
        return PQI_DJ;
    }

    public void setPQI_DJ(String PQI_DJ) {
        this.PQI_DJ = PQI_DJ;
    }

    public String getRQI_DJ() {
        return RQI_DJ;
    }

    public void setRQI_DJ(String RQI_DJ) {
        this.RQI_DJ = RQI_DJ;
    }

    public String getPCI_DJ() {
        return PCI_DJ;
    }

    public void setPCI_DJ(String PCI_DJ) {
        this.PCI_DJ = PCI_DJ;
    }

    public summary(){
    }

    public Year getS_date() {
        return s_date;
    }

    public void setS_date(Year s_date) {
        this.s_date = s_date;
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

    public float getPQI() {
        return PQI;
    }

    public void setPQI(float PQI) {
        this.PQI = PQI;
    }

    public float getRQI() {
        return RQI;
    }

    public void setRQI(float RQI) {
        this.RQI = RQI;
    }

    public float getPCI() {
        return PCI;
    }

    public void setPCI(float PCI) {
        this.PCI = PCI;
    }
}