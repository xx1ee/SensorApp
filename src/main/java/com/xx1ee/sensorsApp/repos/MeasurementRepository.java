package com.xx1ee.sensorsApp.repos;

import com.xx1ee.sensorsApp.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findMeasurementsByRainingIsTrue();
}
