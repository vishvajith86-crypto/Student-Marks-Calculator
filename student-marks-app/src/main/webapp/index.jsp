<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Marks Calculator</title>
</head>
<body>
    <h2>Student Marks Calculator</h2>

    <% if (request.getAttribute("errorMessage") != null) { %>
    <p style="color:red;">
        Invalid input: Please check the name and marks (0–100).
    </p>
<% } %>

    <form action="calculate" method="post">
        <label>Student Name:</label>
        <input type="text" name="studentName" required>
        <br><br>

        <label>Subject 1 Marks:</label>
        <input type="number" name="mark1" min="0" max="100"
               step="1" required>
        <br><br>

        <label>Subject 2 Marks:</label>
        <input type="number" name="mark2" min="0" max="100"
               step="1" required>
        <br><br>

        <label>Subject 3 Marks:</label>
        <input type="number" name="mark3" min="0" max="100"
               step="1" required>
        <br><br>

        <button type="submit">Calculate Result</button>
    </form>
</body>
</html>
