package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import exception.currency.InvalidPathVariableException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CurrencyService;

import java.io.IOException;

@WebServlet(value = "/currency/*")
public class CurrencyServlet extends HttpServlet {
    private CurrencyService currencyService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        currencyService = (CurrencyService) config.getServletContext().getAttribute("currencyService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            throw new InvalidPathVariableException("Incorrect currency code in url path");
        }

        String currencyCode = req.getPathInfo().replaceFirst("/", "").toUpperCase();
        new ObjectMapper().writeValue(
                resp.getWriter(),
                currencyService.findByCode(currencyCode)
        );
    }
}
