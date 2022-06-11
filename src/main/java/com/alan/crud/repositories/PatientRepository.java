package com.alan.crud.repositories;

import com.alan.crud.entities.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {
}
