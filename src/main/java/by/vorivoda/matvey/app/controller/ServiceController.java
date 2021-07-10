package by.vorivoda.matvey.app.controller;

import by.vorivoda.matvey.app.model.entity.sensor.Sensor;
import by.vorivoda.matvey.app.model.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sensors")
public class ServiceController {

    private final SensorService sensorService;

    @Autowired
    public ServiceController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping
    public List<Sensor> getSensors(@RequestParam(required = false) String searchCondition,
                                   @RequestParam(required = false) Integer pageIndex,
                                   @RequestParam(required = false) Integer amountOnPage) {

        searchCondition = searchCondition == null ? "" : searchCondition;
        if (pageIndex == null || amountOnPage == null) return sensorService.getAll();

        List<Sensor> sensors = sensorService.getAll(searchCondition);
        return sensors.subList(pageIndex * amountOnPage, Math.min((pageIndex + 1) * amountOnPage, sensors.size()));
    }

    @GetMapping(params = {"can-modify"})
    public Boolean canChange() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .anyMatch(value -> value.getAuthority().equals("ROLE_ADMINISTRATOR"));
    }

    @GetMapping(path = "/count")
    public Integer getSensorsCount(@RequestParam(required = false) String searchCondition) {
        return sensorService.getAll(searchCondition == null ? "" : searchCondition).size();
    }

    @GetMapping(path = "/{sensorId}")
    public Sensor getSensor(@PathVariable Long sensorId) {
        return sensorService.get(sensorId);
    }

    @GetMapping(path = "/types")
    public List<String> getSensorTypes() {
        return List.of("Pressure", "Voltage", "Temperature", "Humidity");
    }

    @GetMapping(path = "/units")
    public List<String> getSensorUnitTypes() {
        return List.of("bar", "voltage", "℃", "%");
    }

    @PostMapping
    public Boolean addSensor(@RequestBody Sensor sensor) {
        sensor.setId(null);
        return sensorService.add(sensor);
    }

    @PostMapping(path = "/{sensorId}")
    public Boolean updateSensor(@PathVariable Long sensorId, @RequestBody Sensor sensor) {
        sensor.setId(sensorId);
        return sensorService.update(sensor);
    }

    @DeleteMapping(path = "/{sensorId}")
    public Boolean deleteSensor(@PathVariable Long sensorId) {
        return sensorService.delete(sensorId);
    }
}
