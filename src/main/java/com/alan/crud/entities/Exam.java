package com.alan.crud.entities;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@Table(name="exams")
public class Exam {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public long    id;
  public String    patient_id;
  public String  type;
  public String  description;
  public String  comments;
  public String  result;
}