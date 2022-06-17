package com.alan.crud.controlllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alan.crud.entities.Patient;
import com.alan.crud.models.HttpResponse;
import com.alan.crud.repositories.*;
import com.alan.crud.utils.Request;

@RestController
@RequestMapping(path = "/patients")
public class PatientController {
  @Autowired
  PatientRepository patientRepository;

  @GetMapping
  public ResponseEntity list(@RequestParam(required=false) String field_name, @RequestParam(required=false) String field_value) {
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

    HttpResponse response = new HttpResponse("Lista de pacientes", 200);
    response.setData(listPatients);
    return response.extract(); 
  }

  @PostMapping
  public ResponseEntity save(@RequestBody Patient patient) {
    try{
      var request = new Request("https://viacep.com.br/ws/"+patient.cep+"/json");
      var result = request.execute().get();

      String responseBairro = result.get("bairro").toString().toLowerCase();
      if(!responseBairro.trim().equalsIgnoreCase(patient.bairro)) {
        HttpResponse response = new HttpResponse("Bairro diferente do consultado no cep", 400);
        return response.extract(); 
      }

      String responseCity = result.get("localidade").toString().toLowerCase();
      if(!responseCity.trim().equalsIgnoreCase(patient. localidade)) {
        HttpResponse response = new HttpResponse("Cidade diferente do consultado no cep", 400);
        return response.extract(); 
      }

      String responseUf = result.get("uf").toString().toLowerCase();
      if(!responseUf.trim().equalsIgnoreCase(patient.uf)) {
        HttpResponse response = new HttpResponse("Estado diferente do consultado no cep", 400);
        return response.extract(); 
      }

      patientRepository.save(patient);

      HttpResponse response = new HttpResponse("Paciente cadastrado com sucesso", 201);
      response.setData(patient);

      return response.extract(); 
    } catch(Exception e) {
      e.printStackTrace();
      HttpResponse response = new HttpResponse("Ocorreu um erro interno", 500);
      return response.extract(); 
    }
  }

  @GetMapping(path="{id}")
  public ResponseEntity get(@PathVariable Long id) {
    var patient = patientRepository.findById(id);

    if(patient.isEmpty()) {
      HttpResponse response = new HttpResponse("Paciente não encontrado", 404);
      var responseObject = response.extract(); 
      return responseObject;
    }

    HttpResponse response = new HttpResponse("Paciente não encontrado", 201);
    response.setData(patient);

    return response.extract(); 
  }

  @DeleteMapping(path="{id}")
  public ResponseEntity delete(@PathVariable Long id) {
    var patient = patientRepository.findById(id);

    if(patient.isEmpty()) {
      HttpResponse response = new HttpResponse("Paciente não encontrado", 404);
      return response.extract(); 
    }

    patientRepository.deleteById(id);

    HttpResponse response = new HttpResponse("Paciente deletado com sucesso", 200);
    return response.extract(); 
  }
}
