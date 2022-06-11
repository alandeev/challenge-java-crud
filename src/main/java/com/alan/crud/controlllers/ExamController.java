package com.alan.crud.controlllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;

import com.alan.crud.entities.Exam;
import com.alan.crud.repositories.*;

@RestController
@RequestMapping(path = "/exams")
public class ExamController {
  @Autowired
  ExamRepository examRepository;

  @GetMapping
  public List<Exam> list(@RequestParam(required=false) String field_name, @RequestParam(required=false) String field_value) {
    Iterable<Exam> exams =  examRepository.findAll();
    List<Exam> listExams = (List)exams;

    if(field_value != null && field_value != null) {
      listExams.removeIf(item -> {
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

    return listExams;
  }

  @PostMapping
  public Exam save(@RequestBody Exam exam) {
    examRepository.save(exam);
    return exam;
  }

  @GetMapping(path="{id}")
  public Object get(@PathVariable Long id) {
    var patient = examRepository.findById(id);

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
}
