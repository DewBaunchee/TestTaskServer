package by.vorivoda.matvey.app.model.service;

import by.vorivoda.matvey.app.model.entity.sensor.Sensor;
import by.vorivoda.matvey.app.model.repository.sensor.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Secured({"ROLE_VIEWER"})
public class SensorService extends AbstractService<Long, Sensor> {

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        repository = sensorRepository;
    }

    @Secured({"ROLE_ADMINISTRATOR"})
    @Transactional
    public boolean add(Sensor sensor) {
        return super.add(sensor);
    }

    @Transactional
    public Sensor get(Long id) {
        return super.get(id);
    }

    @Transactional
    public List<Sensor> getAll() {
        return super.getAll();
    }

    @Transactional
    public List<Sensor> getAll(String condition) {
        return ((SensorRepository) repository).getAllByCondition(condition);
    }

    @Secured({"ROLE_ADMINISTRATOR"})
    @Transactional
    public boolean update(Sensor sensor) {
        return super.update(sensor);
    }

    @Secured({"ROLE_ADMINISTRATOR"})
    @Transactional
    public boolean delete(Long id) {
        return super.delete(id);
    }
}
