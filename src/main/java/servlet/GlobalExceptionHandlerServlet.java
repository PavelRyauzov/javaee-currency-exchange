package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import exception.common.DatabaseException;
import exception.common.InvalidPathVariableException;
import exception.currency.CurrencyAlreadyExistException;
import exception.currency.CurrencyNotFoundException;
import exception.currency.CurrencyValidationException;
import exception.exchangerate.ExchangeRateNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/error-handler")
public class GlobalExceptionHandlerServlet extends HttpServlet {
    private final Map<Class<? extends Throwable>, Integer> certainExceptions =
            new HashMap<>(Map.of(
                    CurrencyValidationException.class, HttpServletResponse.SC_BAD_REQUEST,
                    CurrencyAlreadyExistException.class, HttpServletResponse.SC_CONFLICT,
                    CurrencyNotFoundException.class, HttpServletResponse.SC_NOT_FOUND,
                    ExchangeRateNotFoundException.class, HttpServletResponse.SC_NOT_FOUND,
                    InvalidPathVariableException.class, HttpServletResponse.SC_BAD_REQUEST,
                    DatabaseException.class, HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            ));

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable) req.getAttribute("jakarta.servlet.error.exception");

        Integer statusCode = certainExceptions.get(throwable.getClass());

        if (statusCode == null) {
           resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
           return;
        }

        resp.setStatus(statusCode);
        new ObjectMapper().writeValue(
                resp.getWriter(),
                Map.of("message", throwable.getMessage())
        );
    }
}
