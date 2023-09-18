package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import exception.common.InvalidPathVariableException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ExchangeRateService;

import java.io.IOException;

@WebServlet(value = "/exchangeRate/*")
public class ExchangeRateServlet extends HttpServlet {
    private ExchangeRateService exchangeRateService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        exchangeRateService = (ExchangeRateService) config.getServletContext().getAttribute("exchangeRateService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            throw new InvalidPathVariableException("Exchange rate code pair is missing in url path");
        }

        String codePair = req.getPathInfo().replaceFirst("/", "").toUpperCase();

        if (codePair.length() != 6) {
            throw new InvalidPathVariableException("Incorrect exchange rate code pair in url path");
        }

        new ObjectMapper().writeValue(
                resp.getWriter(),
                exchangeRateService.findByCodePair(codePair.substring(0, 3), codePair.substring(3, 6))
        );
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getMethod().equals("PATCH")) {
            super.service(req, resp);
        }

        this.doPatch(req, resp);
    }

    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) {

    }
}
