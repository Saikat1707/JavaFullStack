<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.todo.model.Todo" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>My Todo List</title>

    <style>

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: Arial, sans-serif;
            background: #f4f6f8;
            color: #333;
        }

        .container {
            width: 900px;
            margin: 40px auto;
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
            color: #222;
        }

        /* =========================
           FORM
           ========================= */

        .todo-form {
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 3px 10px rgba(0,0,0,0.1);
            margin-bottom: 30px;
        }

        .todo-form h2 {
            margin-bottom: 20px;
        }

        input[type="text"],
        textarea {

            width: 100%;

            padding: 12px;

            margin-bottom: 15px;

            border: 1px solid #ccc;

            border-radius: 6px;

            font-size: 15px;
        }

        textarea {
            height: 100px;
            resize: none;
        }

        button {

            border: none;

            padding: 10px 16px;

            border-radius: 6px;

            cursor: pointer;

            font-size: 14px;
        }

        .add-btn {

            background: #2563eb;

            color: white;

            width: 100%;
        }

        .add-btn:hover {
            background: #1d4ed8;
        }

        .update-btn {

            background: #f59e0b;

            color: white;

            width: 100%;
        }

        .update-btn:hover {
            background: #d97706;
        }

        .cancel-btn {

            display: block;

            text-align: center;

            margin-top: 10px;

            padding: 10px;

            border-radius: 6px;

            background: #6b7280;

            color: white;

            text-decoration: none;
        }

        .cancel-btn:hover {
            background: #4b5563;
        }

        /* =========================
           TODO LIST
           ========================= */

        .todo-list {

            display: flex;

            flex-direction: column;

            gap: 15px;
        }

        .todo-card {

            background: white;

            padding: 20px;

            border-radius: 10px;

            box-shadow: 0 3px 10px rgba(0,0,0,0.08);

            display: flex;

            justify-content: space-between;

            align-items: center;
        }

        .todo-info {
            flex: 1;
        }

        .todo-title {

            font-size: 20px;

            font-weight: bold;

            margin-bottom: 8px;
        }

        .todo-description {

            color: #666;

            margin-bottom: 8px;
        }

        .completed {

            text-decoration: line-through;

            color: #999;
        }

        .status {

            font-size: 13px;

            font-weight: bold;
        }

        .completed-status {
            color: green;
        }

        .pending-status {
            color: orange;
        }

        /* =========================
           BUTTONS
           ========================= */

        .actions {

            display: flex;

            gap: 8px;
        }

        .complete-btn {

            background: #16a34a;

            color: white;
        }

        .complete-btn:hover {
            background: #15803d;
        }

        .delete-btn {

            background: #dc2626;

            color: white;
        }

        .delete-btn:hover {
            background: #b91c1c;
        }

        .edit-btn {

            background: #f59e0b;

            color: white;
        }

        .edit-btn:hover {
            background: #d97706;
        }

        /* =========================
           EMPTY
           ========================= */

        .empty {

            text-align: center;

            background: white;

            padding: 30px;

            border-radius: 10px;

            color: #777;
        }

    </style>

</head>


<body>

<div class="container">

    <h1>My Todo List</h1>


    <!-- ==================================
         ADD / UPDATE FORM
         ================================== -->

    <%

        Todo editTodo = (Todo) request.getAttribute("editTodo");

        if (editTodo != null) {

    %>

        <!-- ==============================
             EDIT TODO
             ============================== -->

        <div class="todo-form">

            <h2>Edit Task</h2>

            <form
                action="<%= request.getContextPath() %>/todo"
                method="post">

                <!-- Tell servlet this is UPDATE -->
                <input
                    type="hidden"
                    name="action"
                    value="update">


                <!-- Existing Todo ID -->
                <input
                    type="hidden"
                    name="id"
                    value="<%= editTodo.getId() %>">


                <!-- Existing Title -->
                <input
                    type="text"
                    name="title"
                    value="<%= editTodo.getTitle() %>"
                    placeholder="Enter task title"
                    required>


                <!-- Existing Description -->
                <textarea
                    name="description"
                    placeholder="Enter task description"
                    required><%= editTodo.getDescription() %></textarea>


                <button
                    type="submit"
                    class="update-btn">

                    Update Task

                </button>

            </form>


            <!-- Cancel Edit -->

            <a
                href="<%= request.getContextPath() %>/todo"
                class="cancel-btn">

                Cancel

            </a>

        </div>


    <%

        } else {

    %>


        <!-- ==============================
             ADD TODO
             ============================== -->

        <div class="todo-form">

            <h2>Add New Task</h2>

            <form
                action="<%= request.getContextPath() %>/todo"
                method="post">

                <input
                    type="hidden"
                    name="action"
                    value="create">


                <input
                    type="text"
                    name="title"
                    placeholder="Enter task title"
                    required>


                <textarea
                    name="description"
                    placeholder="Enter task description"
                    required></textarea>


                <button
                    type="submit"
                    class="add-btn">

                    Add Task

                </button>

            </form>

        </div>


    <%

        }

    %>


    <!-- ==================================
         TODO LIST
         ================================== -->

    <div class="todo-list">

        <%

            List<Todo> todoList =
                (List<Todo>) request.getAttribute("todoList");


            if (todoList == null || todoList.isEmpty()) {

        %>


            <div class="empty">

                <h3>No tasks found</h3>

                <p>Add your first task above.</p>

            </div>


        <%

            } else {

                for (Todo todo : todoList) {

        %>


        <!-- ==================================
             TODO CARD
             ================================== -->

        <div class="todo-card">


            <!-- TODO INFORMATION -->

            <div class="todo-info">


                <div class="todo-title <%= todo.isCompleted() ? "completed" : "" %>">

                    <%= todo.getTitle() %>

                </div>


                <div class="todo-description">

                    <%= todo.getDescription() %>

                </div>


                <% if (todo.isCompleted()) { %>

                    <span class="status completed-status">

                        ✓ Completed

                    </span>

                <% } else { %>

                    <span class="status pending-status">

                        ● Pending

                    </span>

                <% } %>


            </div>


            <!-- ==================================
                 ACTION BUTTONS
                 ================================== -->

            <div class="actions">


                <!-- ==============================
                     COMPLETE
                     ============================== -->

                <% if (!todo.isCompleted()) { %>

                    <form
                        action="<%= request.getContextPath() %>/todo"
                        method="post">

                        <input
                            type="hidden"
                            name="action"
                            value="complete">


                        <input
                            type="hidden"
                            name="id"
                            value="<%= todo.getId() %>">


                        <button
                            type="submit"
                            class="complete-btn">

                            Complete

                        </button>

                    </form>

                <% } %>


                <!-- ==============================
                     EDIT
                     ============================== -->

                <form
                    action="<%= request.getContextPath() %>/todo"
                    method="get">

                    <input
                        type="hidden"
                        name="action"
                        value="edit">


                    <input
                        type="hidden"
                        name="id"
                        value="<%= todo.getId() %>">


                    <button
                        type="submit"
                        class="edit-btn">

                        Edit

                    </button>

                </form>


                <!-- ==============================
                     DELETE
                     ============================== -->

                <form
                    action="<%= request.getContextPath() %>/todo"
                    method="post">

                    <input
                        type="hidden"
                        name="action"
                        value="delete">


                    <input
                        type="hidden"
                        name="id"
                        value="<%= todo.getId() %>">


                    <button
                        type="submit"
                        class="delete-btn"
                        onclick="return confirm('Are you sure you want to delete this task?');">

                        Delete

                    </button>

                </form>


            </div>

        </div>


        <%

                }

            }

        %>

    </div>

</div>

</body>

</html>