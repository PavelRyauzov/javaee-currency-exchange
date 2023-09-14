package listener;

import dao.impl.CurrencyDaoImpl;
import dao.impl.ExchangeRateDaoImpl;
import db.impl.SqliteDataSource;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.validation.Validation;
import service.impl.CurrencyServiceImpl;
import service.impl.ExchangeRateServiceImpl;

@WebListener
public class ApplicationServletContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent contextEvent) {
        ServletContext context = contextEvent.getServletContext();
        context.setAttribute("currencyService", new CurrencyServiceImpl(
                new CurrencyDaoImpl(
                        new SqliteDataSource()
                ),
                Validation.buildDefaultValidatorFactory().getValidator()
        ));
        context.setAttribute("exchangeRateService", new ExchangeRateServiceImpl(
                new ExchangeRateDaoImpl(
                        new SqliteDataSource()
                )
        ));
    }
}
