package com.mentoring.firsthw.bank.impl;

import com.mentoring.firsthw.bank.api.Bank;
import com.mentoring.firsthw.dto.BankCard;
import com.mentoring.firsthw.dto.BankCardType;
import com.mentoring.firsthw.dto.CreditBankCard;
import com.mentoring.firsthw.dto.DebitBankCard;
import com.mentoring.firsthw.dto.User;
import java.util.UUID;
import java.util.function.BiFunction;

public class BankImpl implements Bank {

  @Override
  public BankCard createBankCard(User user, BankCardType bankCardType) {
    if (bankCardType.equals(BankCardType.CREDIT)) {
      return new CreditBankCard(UUID.randomUUID().toString(), user);
    } else if (bankCardType.equals(BankCardType.DEBIT)) {
      return new DebitBankCard(UUID.randomUUID().toString(), user);
    } else {
      throw new IllegalArgumentException(
          "Cannot create bank card by bankCardType = " + bankCardType);
    }
  }

  @Override
  public BankCard createBankCard(String cardNumber, User user,
      BiFunction<String, User, BankCard> biFunction) {
    return biFunction.apply(cardNumber, user);
  }
}
