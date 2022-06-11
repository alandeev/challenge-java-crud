package com.alan.crud.controlllers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.websocket.MessageHandler.Partial;

import org.hibernate.jdbc.Expectation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;

import com.alan.crud.entities.Patient;
import com.alan.crud.repositories.*;

@RestController
@RequestMapping(path = "/patients")
public class PatientController {
  @Autowired
  PatientRepository patientRepository;

  @GetMapping
  public List<Patient> list(@RequestParam(required=false) String field_name, @RequestParam(required=false) String field_value) {
    Iterable<Patient> patients =  patientRepository.findAll();
    List<Patient> listPatients = (List)patients;

    if(field_value != null && field_value != null) {
      listPatients.removeIf(item -> {
        try {
          var classObject = item.getClass();
          var value = classObject.getDeclaredField(field_name).get(item);
          return !value.equals(field_value);
        } catch (Exception ex) {
          System.out.print(ex);
          return true;
        }
      });
    }

    return listPatients;
  }

  @PostMapping
  public Patient save(@RequestBody Patient patient) {
    patientRepository.save(patient);
    return patient;
  }

  @GetMapping(path="{id}")
  public Object get(@PathVariable Long id) {
    var patient = patientRepository.findById(id);

    JSONObject response = new JSONObject();

    if(patient.isEmpty()) {
      response.put("status", "error");
      response.put("message", "id not found");
      return response;
    }

    response.put("status", "success");
    response.put("data", patient);

    return response;
  }

  @DeleteMapping(path="{id}")
  public Object delete(@PathVariable Long id) {
    var patient = patientRepository.findById(id);

    JSONObject response = new JSONObject();
    if(patient.isEmpty()) {
      response.put("status", "error");
      response.put("message", "id not found");
      return response;
    }

    patientRepository.deleteById(id);

    response.put("status", "success");
    response.put("message", "patient removed with success");
    return response;
  }
}
