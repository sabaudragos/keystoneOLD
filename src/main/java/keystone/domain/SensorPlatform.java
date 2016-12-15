package keystone.domain;

import java.util.Calendar;
import java.util.List;

public class SensorPlatform {
    private long id;
    private List<SensorType> sensorTypeList;
    private Location location;
    private Calendar timeOfTheObservation;

    public Calendar getTimeOfTheObservation() {
        return timeOfTheObservation;
    }

    public void setTimeOfTheObservation(Calendar timeOfTheObservation) {
        this.timeOfTheObservation = timeOfTheObservation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<SensorType> getSensorTypeList() {
        return sensorTypeList;
    }

    public void setSensorTypeList(List<SensorType> sensorTypeList) {
        this.sensorTypeList = sensorTypeList;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
