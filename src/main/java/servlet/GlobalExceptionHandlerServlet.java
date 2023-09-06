package servlet;

import exception.currency.CurrencyNotFoundException;
import exception.common.DatabaseException;
import exception.common.InvalidPathVariableException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/error-handler")
public class GlobalExceptionHandlerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable) req.getAttribute("jakarta.servlet.error.exception");

        if (throwable instanceof CurrencyNotFoundException) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, throwable.getMessage());
            return;
        }

        if (throwable instanceof InvalidPathVariableException) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, throwable.getMessage());
            return;
        }

        if (throwable instanceof DatabaseException) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, throwable.getMessage());
            return;
        }

        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
