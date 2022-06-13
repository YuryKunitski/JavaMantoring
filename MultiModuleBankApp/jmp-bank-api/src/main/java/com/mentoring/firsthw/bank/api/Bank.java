package com.mentoring.firsthw.bank.api;

import com.mentoring.firsthw.dto.BankCard;
import com.mentoring.firsthw.dto.BankCardType;
import com.mentoring.firsthw.dto.User;
import java.util.function.BiFunction;

public interface Bank {

  BankCard createBankCard(User user, BankCardType bankCardType);

  BankCard createBankCard(String cardNumber, User user, BiFunction<String, User, BankCard> biFunction);
}
