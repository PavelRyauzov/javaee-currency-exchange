package model;

import lombok.Value;

@Value
public class Currency {
    int id;
    String code;
    String fullName;
    String sign;
}
