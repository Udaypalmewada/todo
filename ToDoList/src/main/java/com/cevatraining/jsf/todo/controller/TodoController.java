package com.cevatraining.jsf.todo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UICommand;
import javax.faces.component.UIForm;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import com.cevatraining.jsf.todo.model.Todo;
import com.cevatraining.jsf.todo.service.impl.TodoServiceImpl;
@ManagedBean
@ViewScoped
public class TodoController {
  // domain model related variables
  private List<Todo> todos;
  private Todo todo;
  private String title;
  private String description;
  private int priority;
  
  // JavaServerFaces related variables
  private UIForm form;
  private UIForm tableForm;
  private UICommand addCommand;
  
  @Inject
  private TodoServiceImpl todoService;

  public TodoController() {
    todos = new ArrayList<Todo>();
    
    // FIX: pending to solve a problem with the inialitation if the service
    todos.add(new Todo("Learn JFS", "Finish this article", 1));
    todos.add(new Todo("Stop drinking to much coffee", "Coffee is evil!", 3));
    
    // TODO create an Abstract Factory to get the instance
    todoService = new TodoServiceImpl(); 
    todos = todoService.findAllTodos();
  }

  public String addNew() {
    todo = new Todo("", "", 0);
    todo.setNewRow(true);
    List newTodos = new ArrayList(todos);
    newTodos.add(0, todo);
    this.setTodos(newTodos);
    //form.setRendered(true);
    //addCommand.setRendered(false);
    return null;
  }

  public String save() {
	
	if (this.getPriority() == 0 && this.getTitle() == null && this.getDescription() == null) {
		return null;
	}
	//
	todo = new Todo(this.getTitle(), this.getDescription(), this.getPriority());
	//
    todoService.createTodo(todo);
    //
    List newTodos = todoService.findAllTodos();
    this.setTodos(newTodos);
    //
    this.setTitle("");
	this.setPriority(0);
	this.setDescription("");
    
    //form.setRendered(false);
    //addCommand.setRendered(true);
    return null;
  }

  public String cancel() {
    //todo = null;
    //form.setRendered(false);
    //addCommand.setRendered(true);
	  todo.setNewRow(false);
    return null;
  }

  public String delete() {
    //
    todoService.delete(todo);
    //
    List newTodos = todoService.findAllTodos();
    this.setTodos(newTodos);
    return null;
  }
  
  public String edit() {
	  todo.setNewRow(true);
	  return null;
  }

  public String update() {
	    //
	    todoService.update(todo);
	    List newTodos = todoService.findAllTodos();
	    this.setTodos(newTodos);
	    return null;
  }
  
  public void displayTable(ActionEvent event) {
    if (event.getComponent().getId().equalsIgnoreCase("hide")) {
      tableForm.setRendered(false);
    } else {
      tableForm.setRendered(true);
    }
  }

  public List<SelectItem> getPriorities() {
    List<SelectItem> list = new ArrayList<SelectItem>();
    list.add(new SelectItem(1, "High"));
    list.add(new SelectItem(2, "Medium"));
    list.add(new SelectItem(3, "Low"));
    return list;
  }

  public List<Todo> getTodos() {
    return todos;
  }

  public void setTodos(List<Todo> todos) {
    this.todos = todos;
  }

  public Todo getTodo() {
    return todo;
  }

  public void setTodo(Todo todo) {
    this.todo = todo;
  }

  public UIForm getForm() {
    return form;
  }

  public void setForm(UIForm form) {
    this.form = form;
  }

  public UICommand getAddCommand() {
    return addCommand;
  }

  public void setAddCommand(UICommand addCommand) {
    this.addCommand = addCommand;
  }

  public UIForm getTableForm() {
    return tableForm;
  }

  public void setTableForm(UIForm tableForm) {
    this.tableForm = tableForm;
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
	  


} 