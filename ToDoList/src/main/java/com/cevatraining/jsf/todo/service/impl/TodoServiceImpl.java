package com.cevatraining.jsf.todo.service.impl;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import com.cevatraining.jsf.todo.model.Todo;
import com.cevatraining.jsf.todo.service.TodoService;

@Stateful
public class TodoServiceImpl {
    
    @PersistenceContext(unitName = "applicationTodoPU", type = PersistenceContextType.EXTENDED) 
    private EntityManager em;

    public Todo createTodo(Todo todo) {
    	if (todo != null) {
    	TypedQuery<Todo> typedQuery = em.createNamedQuery(Todo.FIND_ALL, Todo.class);
        List list= typedQuery.getResultList();
        int size = list.size();
        todo.setId(size+1);
            
            em.persist(todo);
        }
        
        return todo;
    }
    
    public List<Todo> findAllTodos() {
        TypedQuery<Todo> typedQuery = em.createNamedQuery(Todo.FIND_ALL, Todo.class);
        return typedQuery.getResultList();
    }

	
	public void delete(Todo todo) {
		todo = em.find(Todo.class, todo.getId());
		em.remove(todo);
		
	}

	
	public void update(Todo todo) {
		// TODO Auto-generated method stub
		
	}
    
}