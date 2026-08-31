<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Student Portal</title>

    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: Arial, sans-serif;
            background: #f4f6f8;
            min-height: 100vh;

            display: flex;
            justify-content: center;
            align-items: center;
        }

        .container {
            width: 90%;
            max-width: 600px;
            text-align: center;
        }

        .card {
            background: white;
            padding: 50px 40px;
            border-radius: 15px;
            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.08);
        }

        h1 {
            color: #1f2937;
            margin-bottom: 35px;
        }

        .buttons {
            display: flex;
            justify-content: center;
            gap: 20px;
        }

        .button {
            display: inline-block;
            padding: 13px 25px;
            border-radius: 8px;
            text-decoration: none;
            font-weight: bold;
            color: white;
            background: #2563eb;
            transition: 0.2s;
        }

        .button:hover {
            background: #1d4ed8;
        }

        .button.register {
            background: #16a34a;
        }

        .button.register:hover {
            background: #15803d;
        }
    </style>

</head>

<body>

<div class="container">

    <div class="card">

        <h1>Welcome to Student Portal</h1>

        <div class="buttons">

            <a href="${pageContext.request.contextPath}/students"
               class="button">
                View All Students
            </a>

            <a href="${pageContext.request.contextPath}/register"
               class="button register">
                Register Student
            </a>

        </div>

    </div>

</div>

</body>

</html>