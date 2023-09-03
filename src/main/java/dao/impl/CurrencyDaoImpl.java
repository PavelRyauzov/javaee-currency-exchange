package dao.impl;

import dao.CurrencyDao;
import db.DataSource;
import lombok.extern.slf4j.Slf4j;
import model.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class CurrencyDaoImpl implements CurrencyDao {
    @Override
    public List<Currency> findAll() {
        List<Currency> currencies = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStmt = connection.prepareStatement("SELECT * FROM Currencies;");
             ResultSet resultSet = preparedStmt.executeQuery();
        ) {
            while (resultSet.next()) {
                currencies.add(new Currency(
                        resultSet.getInt("ID"),
                        resultSet.getString("Code"),
                        resultSet.getString("FullName"),
                        resultSet.getString("Sign")
                ));
            }
        } catch (SQLException e) {
            log.error("Error while getting currencies from db.", e);
        }

        return currencies;
    }

    @Override
    public Optional<Currency> findByCode(String code) {
        Currency currency = null;

        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStmt = connection.prepareStatement("SELECT * FROM Currencies WHERE Code = ?");
             ResultSet resultSet = preparedStmt.executeQuery();
        ) {
            preparedStmt.setString(1, code);
            if (resultSet.next()) {
                currency = new Currency(
                        resultSet.getInt("ID"),
                        resultSet.getString("Code"),
                        resultSet.getString("FullName"),
                        resultSet.getString("Sign")
                );
            }
        } catch (SQLException e) {
            log.error("Error while getting currency by code", e);
        }

        return Optional.ofNullable(currency);
    }
}
