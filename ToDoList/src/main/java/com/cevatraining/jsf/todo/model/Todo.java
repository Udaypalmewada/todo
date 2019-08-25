package com.cevatraining.jsf.todo.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
    @NamedQuery(name = Todo.FIND_ALL, query = "SELECT t FROM Todo t")
})
@XmlRootElement
public class Todo {
  private int id;
  private String title;
  private String description;
  private int priority;
  private Calendar dueDate;
  private boolean newRow = false;
  
  // ======================================
  // =             Constants              =
  // ======================================

  public static final String FIND_ALL = "TODO.findAll";
  
  public Todo(String title, String description, int priority) {
    this.title = title;
    this.description = description;
    this.priority = priority;
  }
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  
  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  } 
  
  public Calendar getDueDate() {
    return dueDate;
  }
  public void setDueDate(Calendar dueDate) {
    this.dueDate = dueDate;
  }


  public boolean isNewRow() {
	  return newRow;
  }
  
  public void setNewRow(boolean newRow) {
	  this.newRow = newRow;
  }  
  
} 

