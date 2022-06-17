package com.alan.crud.controlllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;

import com.alan.crud.entities.Exam;
import com.alan.crud.models.HttpResponse;
import com.alan.crud.repositories.*;

@RestController
@RequestMapping(path = "/exams")
public class ExamController {
  @Autowired
  ExamRepository examRepository;

  @GetMapping
  public ResponseEntity list(@RequestParam(required=false) String field_name, @RequestParam(required=false) String field_value) {
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

    HttpResponse response = new HttpResponse("Lista de exames", 200);
    response.setData(listExams);
    return response.extract(); 
  }

  @PostMapping
  public ResponseEntity save(@RequestBody Exam exam) {
    examRepository.save(exam);

    HttpResponse response = new HttpResponse("Exame cadastrado com sucesso", 200);
    response.setData(exam);
    return response.extract(); 
  }

  @GetMapping(path="{id}")
  public ResponseEntity get(@PathVariable Long id) {
    var exam = examRepository.findById(id);

    if(exam.isEmpty()) {
      HttpResponse response = new HttpResponse("Exame não encontrado", 404);
      response.setData(exam);
      return response.extract(); 
    }

    HttpResponse response = new HttpResponse("Lista de exames", 200);
    response.setData(exam);
    return response.extract(); 
  }
}
