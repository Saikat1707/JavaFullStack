package com.todo.service;

import java.util.List;

import com.todo.dao.TodoDao;
import com.todo.model.Todo;

public class TodoService {

    private TodoDao todoDao = new TodoDao();

    public void createTodo(Todo todo) {
        todoDao.createToDo(todo);
    }

    public List<Todo> getAllTodos() {
        return todoDao.getAllTodos();
    }

    public void deleteTodo(int id) {
        todoDao.deleteToDo(id);
    }

    public void updateTodo(Todo todo) {
        todoDao.updateTodo(todo);
    }

    public void markComplete(int id) {
        todoDao.markCompleteToDo(id);
    }

	public Todo getTodoById(int id) {
		return todoDao.getTodoById(id);
	}
}