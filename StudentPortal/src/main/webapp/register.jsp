<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Register</title>

    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: Arial, sans-serif;
            background: #f4f6f8;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .register-container {
            width: 400px;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.15);
        }

        h1 {
            text-align: center;
            margin-bottom: 25px;
            color: #333;
        }

        .form-group {
            margin-bottom: 18px;
        }

        label {
            display: block;
            margin-bottom: 7px;
            font-weight: bold;
            color: #444;
        }

        input,
        select {
            width: 100%;
            padding: 11px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 15px;
        }

        input:focus,
        select:focus {
            outline: none;
            border-color: #4a90e2;
        }

        button {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 5px;
            background: #4a90e2;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background: #357abd;
        }

        .login-link {
            text-align: center;
            margin-top: 18px;
        }

        .login-link a {
            color: #4a90e2;
            text-decoration: none;
        }
    </style>
</head>

<body>

    <div class="register-container">

        <h1>Student Registration</h1>

        <form action="${pageContext.request.contextPath}/register" method="post">

            <div class="form-group">
                <label for="name">Name</label>
                <input
                    type="text"
                    id="name"
                    name="name"
                    placeholder="Enter your name"
                    required>
            </div>

            <div class="form-group">
                <label for="age">Age</label>
                <input
                    type="number"
                    id="age"
                    name="age"
                    placeholder="Enter your age"
                    min="1"
                    max="100"
                    required>
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input
                    type="email"
                    id="email"
                    name="email"
                    placeholder="Enter your email"
                    required>
            </div>

            <div class="form-group">
                <label for="course">Course</label>

                <select id="course" name="course" required>
                    <option value="">Select Course</option>
                    <option value="MCA">MCA</option>
                    <option value="BCA">BCA</option>
                    <option value="B.Tech">B.Tech</option>
                    <option value="M.Tech">M.Tech</option>
                    <option value="MBA">MBA</option>
                </select>
            </div>

            <button type="submit">Register</button>

        </form>

        <div class="login-link">
            <p>
                Already registered?
                <a href="${pageContext.request.contextPath}/students">
                    View Students
                </a>
            </p>
        </div>

    </div>

</body>

</html>