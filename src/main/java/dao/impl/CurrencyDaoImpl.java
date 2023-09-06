package dao.impl;

import dao.CurrencyDao;
import db.DataSource;
import exception.common.DatabaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class CurrencyDaoImpl implements CurrencyDao {
    private final DataSource dataSource;

    @Override
    public List<Currency> findAll() {
        List<Currency> currencies = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStmt = connection.prepareStatement("SELECT * FROM Currencies;");
             ResultSet resultSet = preparedStmt.executeQuery();
        ) {
            while (resultSet.next()) {
                currencies.add(
                        createCurrencyFromResultSet(resultSet)
                );
            }
        } catch (SQLException e) {
            log.error("Error while getting currencies from db", e);
            throw new DatabaseException("Error while getting currencies from db");
        }

        return currencies;
    }

    @Override
    public Optional<Currency> findByCode(String code) {
        Currency currency = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStmt = connection.prepareStatement("SELECT * FROM Currencies WHERE Code = ?;");
        ) {
            preparedStmt.setString(1, code);

            try (ResultSet resultSet = preparedStmt.executeQuery()) {
                if (resultSet.next()) {
                    currency = createCurrencyFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            log.error("Error while getting currency by code", e);
            throw new DatabaseException("Error while getting currency by code");
        }

        return Optional.ofNullable(currency);
    }

    private Currency createCurrencyFromResultSet(ResultSet resultSet) throws SQLException {
        return new Currency(
                resultSet.getInt("ID"),
                resultSet.getString("Code"),
                resultSet.getString("FullName"),
                resultSet.getString("Sign")
        );
    }
}
