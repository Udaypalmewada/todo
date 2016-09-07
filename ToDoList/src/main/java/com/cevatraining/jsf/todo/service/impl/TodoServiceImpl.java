package com.cevatraining.jsf.todo.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cevatraining.jsf.todo.model.Todo;

@Stateless
public class TodoServiceImpl implements com.cevatraining.jsf.todo.service.TodoService {
    
    @Inject
    private EntityManager em;

    @Override
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