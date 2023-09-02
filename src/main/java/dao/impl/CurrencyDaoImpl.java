package dao.impl;

import dao.CurrencyDao;
import model.Currency;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CurrencyDaoImpl implements CurrencyDao {
    private final Connection connection;

    public CurrencyDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Currency> findAll() {
        try(ResultSet resultSet = connection.createStatement().executeQuery(
                "SELECT * FROM Currencies;"
        )) {
            List<Currency> currencies = new ArrayList<>();
            while (resultSet.next()) {
                currencies.add(new Currency(
                        resultSet.getInt("ID"),
                        resultSet.getString("Code"),
                        resultSet.getString("FullName"),
                        resultSet.getString("Sign")
                ));
            }
            return currencies;
        } catch (SQLException e) {
            //todo add logging
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Currency> findByCode(String code) {
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Currencies WHERE Code = ?"
        )) {
           statement.setString(1, code);
           try(ResultSet resultSet = statement.executeQuery()) {
               Currency currency = null;
               if (resultSet.next()) {
                   currency = new Currency(
                           resultSet.getInt("ID"),
                           resultSet.getString("Code"),
                           resultSet.getString("FullName"),
                           resultSet.getString("Sign")
                   );
               }
               return Optional.ofNullable(currency);
           }
        } catch (SQLException e) {
            //todo add logging
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
