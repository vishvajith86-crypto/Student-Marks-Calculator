package com.example.marks;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calculate")
public class MarksServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("studentName");

        try {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException(
                    "Please enter the student's name.");
            }

            int m1 = Integer.parseInt(request.getParameter("mark1"));
            int m2 = Integer.parseInt(request.getParameter("mark2"));
            int m3 = Integer.parseInt(request.getParameter("mark3"));

            if (m1 < 0 || m1 > 100 ||
                m2 < 0 || m2 > 100 ||
                m3 < 0 || m3 > 100) {

                throw new IllegalArgumentException(
                    "Marks must be between 0 and 100.");
            }

            int total = m1 + m2 + m3;
            double average = total / 3.0;

            String grade;

            if (average >= 90) {
                grade = "A";
            } else if (average >= 75) {
                grade = "B";
            } else if (average >= 60) {
                grade = "C";
            } else if (average >= 40) {
                grade = "D";
            } else {
                grade = "Fail";
            }

            request.setAttribute("studentName", name.trim());
            request.setAttribute("total", total);
            request.setAttribute("average",
                    String.format("%.2f", average));
            request.setAttribute("grade", grade);

            request.getRequestDispatcher("/result.jsp")
                   .forward(request, response);

        } catch (NumberFormatException e) {
            showError(request, response,
                    "Please enter valid whole-number marks.");
        } catch (IllegalArgumentException e) {
            showError(request, response, e.getMessage());
        }
    }

    private void showError(HttpServletRequest request,
                           HttpServletResponse response,
                           String message)
            throws ServletException, IOException {

        request.setAttribute("errorMessage", message);

        request.getRequestDispatcher("/index.jsp")
               .forward(request, response);
    }
}
