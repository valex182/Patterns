package ru.netology;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationByCardInfo {
    private String city;
    private String name;
    private String phone;
}