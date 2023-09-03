package listener;

import dao.impl.CurrencyDaoImpl;
import db.SqliteDataSource;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import service.impl.CurrencyServiceImpl;

@WebListener
public class ApplicationServletContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent contextEvent) {
        contextEvent.getServletContext()
                .setAttribute("currencyService", new CurrencyServiceImpl(
                        new CurrencyDaoImpl(
                                new SqliteDataSource()
                        )
                ));
    }
}
