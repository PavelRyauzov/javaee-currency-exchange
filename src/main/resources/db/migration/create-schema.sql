create table if not exists Currencies (
    ID integer primary key autoincrement not null,
    Code text unique not null,
    FullName text not null,
    Sign text not null
);

create unique index idx_unique_currencies_code
on Currencies(code);

create table if not exists ExchangeRates (
    ID integer primary key autoincrement not null,
    BaseCurrencyId integer not null,
    TargetCurrencyId integer not null,
    Rate real not null,
    foreign key (BaseCurrencyId) references Currencies(ID),
    foreign key (TargetCurrencyId) references Currencies(ID)
);

create unique index idx_unique_exchangerates_basecurrencyid_targetcurrencyid
on ExchangeRates(BaseCurrencyId, TargetCurrencyId);