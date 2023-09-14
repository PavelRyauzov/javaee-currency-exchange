package dao;

import model.ExchangeRate;

import java.util.List;

public interface ExchangeRateDao {
    List<ExchangeRate> findAll();
}
