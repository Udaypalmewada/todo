package com.cevatraining.jsf.todo.service.impl;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import com.cevatraining.jsf.todo.model.Todo;

@Stateful
public class TodoServiceImpl {
    
    @PersistenceContext(unitName = "applicationTodoPU", type = PersistenceContextType.EXTENDED) 
    private EntityManager em;

    public Todo createTodo(Todo todo) {
        if (todo != null) {    
            em.persist(todo);
        }
        return todo;
    }
    
    public List<Todo> findAllTodos() {
        TypedQuery<Todo> typedQuery = em.createNamedQuery(Todo.FIND_ALL, Todo.class);
        return typedQuery.getResultList();
    }
}