package by.vorivoda.matvey.app.model.service;

import by.vorivoda.matvey.app.model.entity.sensor.Sensor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SensorServiceTest {

    private SensorService sensorService;

    @Autowired
    public void setSensorService(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Test
    public void crudTest() {
        Sensor sensor = new Sensor();
        sensor.setName("Sensor");
        sensor.setModel("S-II");
        sensor.setRangeFrom(1);
        sensor.setRangeTo(3);
        sensor.setSensorType("Humidity");
        sensor.setUnitType("%");
        sensor.setLocation("Location");
        sensor.setDescription("Description");

        sensorService.add(sensor);
        System.out.println(sensorService.getAll());

        System.out.println(sensorService.get(sensor.getId()));

        sensor.setDescription("New description");
        sensorService.update(sensor);
        System.out.println(sensorService.getAll());

        sensorService.delete(sensor.getId());
        System.out.println(sensorService.getAll());
    }
}
