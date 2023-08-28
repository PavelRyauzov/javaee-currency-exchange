package dao.impl;

import dao.CurrencyDao;
import model.Currency;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurrencyDaoImpl implements CurrencyDao {
    private final Connection connection;

    public CurrencyDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Currency> findAll() {
        List<Currency> currencies = new ArrayList<>();

        try(ResultSet resultSet = connection.createStatement().executeQuery(
                "SELECT * FROM Currencies;"
        )) {
            while (resultSet.next()) {
                currencies.add(new Currency(
                        resultSet.getInt("ID"),
                        resultSet.getString("Code"),
                        resultSet.getString("FullName"),
                        resultSet.getString("Sign")
                ));
            }
        } catch (SQLException e) {
            //todo add logging
            e.printStackTrace();
        }

        return currencies;
    }

    @Override
    public Optional<Currency> findByCode(String code) {
        Currency currency = null;

        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Currencies WHERE Code = ?"
        )) {
           statement.setString(1, code);
           try(ResultSet resultSet = statement.executeQuery()) {
               if (resultSet.next()) {
                   currency = new Currency(
                           resultSet.getInt("ID"),
                           resultSet.getString("Code"),
                           resultSet.getString("FullName"),
                           resultSet.getString("Sign")
                   );
               }
           }
        } catch (SQLException e) {
            //todo add logging
            e.printStackTrace();
        }

        return Optional.ofNullable(currency);
    }
}
