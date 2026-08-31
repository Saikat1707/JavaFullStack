<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.student.model.Student" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Students</title>

    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: Arial, sans-serif;
            background: #f4f6f8;
            padding: 40px;
        }

        .container {
            max-width: 1000px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 25px;
        }

        h1 {
            color: #333;
        }

        .register-btn {
            text-decoration: none;
            background: #4a90e2;
            color: white;
            padding: 10px 16px;
            border-radius: 5px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th,
        td {
            padding: 14px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }

        th {
            background: #4a90e2;
            color: white;
        }

        tr:hover {
            background: #f5f5f5;
        }
    </style>

</head>

<body>

    <div class="container">

        <div class="header">

            <h1>Student List</h1>

            <a
                href="<%= request.getContextPath() %>/register"
                class="register-btn">
                + Register Student
            </a>

        </div>

        <table>

            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Age</th>
                <th>Email</th>
                <th>Course</th>
                <th>Action</th>
            </tr>

            <%
                List<Student> studentList =
                    (List<Student>) request.getAttribute("studentList");

                if (studentList != null && !studentList.isEmpty()) {

                    int count = 1;

                    for (Student student : studentList) {
            %>

                        <tr>
                            <td><%= count++ %></td>
                            <td><%= student.getName() %></td>
                            <td><%= student.getAge() %></td>
                            <td><%= student.getEmail() %></td>
                            <td><%= student.getCourse() %></td>
                            <td>
							    <form method="post">
							        <input type="hidden" name="email" value="<%= student.getEmail() %>">
							        <button type="submit">Delete</button>
							    </form>
							</td>
                        </tr>

            <%
                    }

                } else {
            %>

                    <tr>
                        <td colspan="5">
                            No students registered yet.
                        </td>
                    </tr>

            <%
                }
            %>

        </table>

    </div>

</body>

</html>