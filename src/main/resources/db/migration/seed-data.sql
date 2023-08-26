insert or ignore into Currencies (Code, FullName, Sign) values ('RUB', 'Russian Ruble', '₽');
insert or ignore into Currencies (Code, FullName, Sign) values ('USD', 'US Dollar', '$');
insert or ignore into Currencies (Code, FullName, Sign) values ('EUR', 'Euro', '€');
insert or ignore into Currencies (Code, FullName, Sign) values ('AMD', 'Armenian Dram', 'դր.');
insert or ignore into Currencies (Code, FullName, Sign) values ('AUD', 'Australian Dollar', 'AU$');
insert or ignore into Currencies (Code, FullName, Sign) values ('BYN', 'Belarussian Ruble', 'Br');
insert or ignore into Currencies (Code, FullName, Sign) values ('BRL', 'Brazilian Real', 'R$');
insert or ignore into Currencies (Code, FullName, Sign) values ('KZT', 'Tenge', '₸');


insert or ignore into ExchangeRates (BaseCurrencyId, TargetCurrencyId, Rate)
values (1, 2, 0.010558);
insert or ignore into ExchangeRates (BaseCurrencyId, TargetCurrencyId, Rate)
values (1, 3, 0.0098);
insert or ignore into ExchangeRates (BaseCurrencyId, TargetCurrencyId, Rate)
values (1, 4, 4.08);
insert or ignore into ExchangeRates (BaseCurrencyId, TargetCurrencyId, Rate)
values (1, 5, 0.016454);
insert or ignore into ExchangeRates (BaseCurrencyId, TargetCurrencyId, Rate)
values (1, 6, 0.010558);
insert or ignore into ExchangeRates (BaseCurrencyId, TargetCurrencyId, Rate)
values (1, 7, 0.033488);
insert or ignore into ExchangeRates (BaseCurrencyId, TargetCurrencyId, Rate)
values (1, 8, 0.051467);
insert or ignore into ExchangeRates (BaseCurrencyId, TargetCurrencyId, Rate)
values (1, 9, 4.88);