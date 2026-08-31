package com.todo.controller;

import java.io.IOException;

import com.todo.model.Todo;
import com.todo.service.TodoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/todo")
public class TodoServlet extends HttpServlet {

    private TodoService todoService = new TodoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("edit".equals(action)) {

            int id = Integer.parseInt(req.getParameter("id"));

            Todo todo = todoService.getTodoById(id);

            req.setAttribute("editTodo", todo);
        }

        req.setAttribute("todoList", todoService.getAllTodos());

        req.getRequestDispatcher("/index.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        // CREATE
        if ("create".equals(action)) {

            String title = req.getParameter("title");
            String description = req.getParameter("description");

            Todo todo = new Todo();

            todo.setTitle(title);
            todo.setDescription(description);
            todo.setCompleted(false);

            todoService.createTodo(todo);

        }

        // DELETE
        else if ("delete".equals(action)) {

            int id = Integer.parseInt(req.getParameter("id"));

            todoService.deleteTodo(id);

        }

        // MARK COMPLETE
        else if ("complete".equals(action)) {

            int id = Integer.parseInt(req.getParameter("id"));

            todoService.markComplete(id);

        }

        // UPDATE
        else if ("update".equals(action)) {

            int id = Integer.parseInt(req.getParameter("id"));
            String title = req.getParameter("title");
            String description = req.getParameter("description");

            Todo todo = new Todo();

            todo.setId(id);
            todo.setTitle(title);
            todo.setDescription(description);

            todoService.updateTodo(todo);
        }

        // Go back to /todo
        resp.sendRedirect(req.getContextPath() + "/todo");
    }
}