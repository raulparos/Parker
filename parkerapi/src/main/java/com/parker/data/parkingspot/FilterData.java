package com.parker.data.parkingspot;

import java.io.Serializable;
import java.util.Date;

public class FilterData implements Serializable {
    private Float latitude;
    private Float longitude;
    private Date date;

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
