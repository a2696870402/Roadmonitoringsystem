package planeness;

public class planeness {
    private String p_worker;
    private String road_id;
    private String road_name;
    private double p_iri;
    private java.sql.Date p_date;

    public String getP_worker() {
        return p_worker;
    }

    public String getRoad_id() {
        return road_id;
    }

    public String getRoad_name() {
        return road_name;
    }

    public double getP_iri() {
        return p_iri;
    }

    public java.sql.Date getP_date() {
        return p_date;
    }

    public void setP_worker(String p_worker) {
        this.p_worker = p_worker;
    }

    public void setRoad_id(String road_id) {
        this.road_id = road_id;
    }

    public void setRoad_name(String road_name) {
        this.road_name = road_name;
    }

    public void setP_iri(double p_iri) {
        this.p_iri = p_iri;
    }

    public void setP_date(java.sql.Date p_date) {
        this.p_date = p_date;
    }
}
