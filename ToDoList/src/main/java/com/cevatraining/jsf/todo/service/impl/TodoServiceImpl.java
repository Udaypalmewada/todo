package com.cevatraining.jsf.todo.service.impl;

import java.util.List;
import javax.persistence.EntityManagerFactory;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.Persistence;


import com.cevatraining.jsf.todo.model.Todo;

@Stateful
public class TodoServiceImpl {
    
    @PersistenceContext(unitName = "applicationTodoPU") 
	
    private EntityManager em;

    public Todo createTodo(Todo todo) {
    	em = getEntityManager();
        if (todo != null) {    
        	em.getTransaction().begin();
            em.persist(todo);
            em.getTransaction().commit();

        }
        return todo;
    }
    
    public List<Todo> findAllTodos() {
    	em = getEntityManager();
        TypedQuery<Todo> typedQuery = em.createNamedQuery(Todo.FIND_ALL, Todo.class);
        return typedQuery.getResultList();
    }
    
    public void delete(Todo todo) {
    	em = getEntityManager();
        if (todo != null) {   
        	Todo todoBean = em.find(Todo.class, todo.getId());
        	em.getTransaction().begin();
        	Todo target = em.merge(todoBean);
        	em.remove(target);
        	em.getTransaction().commit();
        }
    }
    
    public void update(Todo todo) {
    	em = getEntityManager();
        if (todo != null) {    
        	Todo todoBean = em.find(Todo.class, todo.getId());
        	em.getTransaction().begin();
        	  todoBean.setPriority(todo.getPriority());
        	  todoBean.setDescription(todo.getDescription());
        	  todoBean.setTitle(todo.getTitle());
        	  em.getTransaction().commit();
        }
    }
    
    protected EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("applicationTodoPU");
        EntityManager ecm = emf.createEntityManager(); 
        return ecm;
    }    
}