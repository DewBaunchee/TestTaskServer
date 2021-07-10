package by.vorivoda.matvey.app.model.entity.sensor;

import by.vorivoda.matvey.app.model.entity.IDaoEntity;

import javax.persistence.*;

@Entity
@Table(name = "sensor")
public class Sensor implements IDaoEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false, length = 30)
    private String name;
    @Column(name = "model", nullable = false, length = 15)
    private String model;
    @Column(name = "range_from")
    private Integer rangeFrom;
    @Column(name = "range_to")
    private Integer rangeTo;
    @Column(name = "sensor_type", columnDefinition = "enum('Pressure', 'Voltage', 'Temperature', 'Humidity')")
    private String sensorType;
    @Column(name = "unit_type", columnDefinition = "enum('bar', 'voltage', '℃', '%')")
    private String unitType;
    @Column(name = "location", length = 40)
    private String location;
    @Column(name = "description", length = 200)
    private String description;

    public Sensor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getRangeFrom() {
        return rangeFrom;
    }

    public void setRangeFrom(Integer rangeFrom) {
        this.rangeFrom = rangeFrom;
    }

    public Integer getRangeTo() {
        return rangeTo;
    }

    public void setRangeTo(Integer rangeTo) {
        this.rangeTo = rangeTo;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{ " +
                "id: " + id + ", " +
                "name: \"" + name + "\", " +
                "model: \"" + model + "\", " +
                "rangeFrom: " + rangeFrom + ", " +
                "rangeTo: " + rangeTo + ", " +
                "sensorType: \"" + sensorType + "\", " +
                "unitType: \"" + unitType + "\", " +
                "location: \"" + location + "\", " +
                "description: \"" + description +
                "\" }";
    }
}