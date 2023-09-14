package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CreateCurrencyDto;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CurrencyService;

import java.io.IOException;

@WebServlet(value = "/currencies")
public class CurrenciesServlet extends HttpServlet {
    private CurrencyService currencyService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        currencyService = (CurrencyService) config.getServletContext().getAttribute("currencyService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new ObjectMapper().writeValue(
                resp.getWriter(),
                currencyService.findAll()
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new ObjectMapper().writeValue(
                resp.getWriter(),
                currencyService.save(
                        new CreateCurrencyDto(
                                req.getParameter("name"),
                                req.getParameter("code"),
                                req.getParameter("sign")
                        )
                )
        );
    }
}
