package com.wusui.weatherreporter.model;

/**
 * Created by fg on 2016/6/9.
 */

public class City {

    private String city;
    private String cnty;
    private String city_id;
    private String province;
    private double lon;
    private double lat;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }

    public String getCityId() {
        return city_id;
    }

    public void setCityId(String id) {
        this.city_id  = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "city_info [city=" + city + ",cnty=" + cnty +
                ",id=" + city_id + ",lat=" + lat + ",lon=" + lon + ",prov=" + province + "]";
    }
}
