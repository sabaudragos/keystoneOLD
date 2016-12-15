package keystone.domain;

public class Temperature extends SensorType {
    public Temperature() {
    }

    public Temperature(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }
}
