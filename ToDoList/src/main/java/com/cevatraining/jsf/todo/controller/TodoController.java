package com.cevatraining.jsf.todo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UICommand;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;
import javax.faces.component.UISelectOne;
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
	private int selectedId;
	// JavaServerFaces related variables
	private UIForm form;
	private UIForm tableForm;
	private UICommand addCommand,editTodo,updateTodo,cancelEdit;
	private UIOutput selectPriorityOut,inputTitleOut,textareaDescOut;
	private UIInput inputTitle,textareaDesc;
	private UISelectOne selectPriority;

	@Inject
	private TodoServiceImpl todoService;

	@PostConstruct
	public void init() {
		todos=todoService.findAllTodos();
		
		selectedId = 0;
	}

	public TodoController() {
		todos = new ArrayList<Todo>();

		// FIX: pending to solve a problem with the inialitation if the service
		todos.add(new Todo("Learn JFS", "Finish this article", 1));
		todos.add(new Todo("Stop drinking to much coffee", "Coffee is evil!", 3));
		
		// TODO create an Abstract Factory to get the instance
		// todoService = new TodoServiceImpl();
		// todos = todoService.findAllTodos();
	}

	public String addNew() {
		todo = new Todo("", "", 3);
		form.setRendered(true);
		addCommand.setRendered(false);
		return null;
	}

	public String save() {

		todoService.createTodo(todo);
		todos=todoService.findAllTodos();
		form.setRendered(false);
		addCommand.setRendered(true);
		return null;
	}

	public String cancel() {
		todo = null;
		form.setRendered(false);
		addCommand.setRendered(true);
		return null;
	}

	public void delete(Todo t) {
		System.out.println("del call"+t.toString());
	    todoService.delete(t);
	    todos= todoService.findAllTodos();
	    
	   
	  }
	public void edit(){
		selectedId = todo.getId();
		//updateTodo.setRendered(true);
		//cancelEdit.setRendered(true);
		editTodo.setRendered(false);
		//selectPriorityOut.setRendered(false);
		//inputTitleOut.setRendered(false);
		//textareaDescOut.setRendered(false);
		
	}
	public void update(){
		todoService.update(todo);
		selectedId  = 0;
		//updateTodo.setRendered(false);
		//cancelEdit.setRendered(false);
		editTodo.setRendered(true);
	}
	public void updateCancel(){
		selectedId  = 0;
		editTodo.setRendered(true);
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

	public int getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(int selectedId) {
		this.selectedId = selectedId;
	}

	public UICommand getEditTodo() {
		return editTodo;
	}

	public void setEditTodo(UICommand editTodo) {
		this.editTodo = editTodo;
	}

	public UICommand getUpdateTodo() {
		return updateTodo;
	}

	public void setUpdateTodo(UICommand updateTodo) {
		this.updateTodo = updateTodo;
	}

	public UICommand getCancelEdit() {
		return cancelEdit;
	}

	public void setCancelEdit(UICommand cancelEdit) {
		this.cancelEdit = cancelEdit;
	}

	public UIOutput getSelectPriorityOut() {
		return selectPriorityOut;
	}

	public void setSelectPriorityOut(UIOutput selectPriorityOut) {
		this.selectPriorityOut = selectPriorityOut;
	}

	public UIOutput getInputTitleOut() {
		return inputTitleOut;
	}

	public void setInputTitleOut(UIOutput inputTitleOut) {
		this.inputTitleOut = inputTitleOut;
	}

	public UIOutput getTextareaDescOut() {
		return textareaDescOut;
	}

	public void setTextareaDescOut(UIOutput textareaDescOut) {
		this.textareaDescOut = textareaDescOut;
	}

	public UIInput getInputTitle() {
		return inputTitle;
	}

	public void setInputTitle(UIInput inputTitle) {
		this.inputTitle = inputTitle;
	}

	public UIInput getTextareaDesc() {
		return textareaDesc;
	}

	public void setTextareaDesc(UIInput textareaDesc) {
		this.textareaDesc = textareaDesc;
	}

	public UISelectOne getSelectPriority() {
		return selectPriority;
	}

	public void setSelectPriority(UISelectOne selectPriority) {
		this.selectPriority = selectPriority;
	}

	public TodoServiceImpl getTodoService() {
		return todoService;
	}

	public void setTodoService(TodoServiceImpl todoService) {
		this.todoService = todoService;
	}
	
}