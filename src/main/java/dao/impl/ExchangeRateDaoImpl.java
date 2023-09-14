package dao.impl;

import dao.ExchangeRateDao;
import db.DataSource;
import exception.common.DatabaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Currency;
import model.ExchangeRate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ExchangeRateDaoImpl implements ExchangeRateDao {
    private final DataSource dataSource;

    @Override
    public List<ExchangeRate> findAll() {
        List<ExchangeRate> exchangeRates = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStmt = connection.prepareStatement(
                     "select ers.ID as ers_ID, ers.Rate as ers_Rate, " +
                             "bc.ID as bc_ID, bc.FullName as bc_FullName, bc.Code as bc_Code, bc.Sign as bc_Sign, " +
                             "tc.ID as tc_ID, tc.FullName as tc_FullName, tc.Code as tc_Code, tc.Sign as tc_Sign " +
                             "from ExchangeRates ers " +
                             "join Currencies bc on ers.BaseCurrencyId = bc.ID " +
                             "join Currencies tc on ers.TargetCurrencyId = tc.ID"
             );
             ResultSet resultSet = preparedStmt.executeQuery()
        ) {
            while (resultSet.next()) {
                exchangeRates.add(
                        createExchangeRateFromResultSet(resultSet)
                );
            }
        } catch (SQLException e) {
            log.error("Error while getting exchange rates from db", e);
            throw new DatabaseException("Error while getting exchange rates from db");
        }

        return exchangeRates;
    }

    private ExchangeRate createExchangeRateFromResultSet(ResultSet resultSet) throws SQLException {
        return new ExchangeRate(
                resultSet.getInt("ers_ID"),
                new Currency(
                        resultSet.getInt("bc_id"),
                        resultSet.getString("bc_FullName"),
                        resultSet.getString("bc_Code"),
                        resultSet.getString("bc_Sign")
                ),
                new Currency(
                        resultSet.getInt("tc_id"),
                        resultSet.getString("tc_FullName"),
                        resultSet.getString("tc_Code"),
                        resultSet.getString("tc_Sign")
                ),
                resultSet.getBigDecimal("ers_Rate")
        );
    }
}
