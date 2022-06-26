package com.mentoring.task5;

import com.mentoring.task5.model.Currency;
import com.mentoring.task5.model.ExchangeRate;
import com.mentoring.task5.model.UserAccount;
import com.mentoring.task5.service.AccountService;
import com.mentoring.task5.service.BankService;
import com.mentoring.task5.util.OwnTransfer;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

  private static AccountService accountService = new AccountService();

  public static void main(String[] args) {

    UserAccount userAccount = accountService.createUserAccount("userAccount1");
    setUpUserWallet(userAccount);

    ExchangeRate exchangeRateEURToUSD = new ExchangeRate(Currency.EUR, Currency.USD, 1.2);
    ExchangeRate exchangeRateUSDToPLN = new ExchangeRate(Currency.USD, Currency.PLN, 4.0);
    ExchangeRate exchangeRatePLNToEUR = new ExchangeRate(Currency.PLN, Currency.EUR, 0.2);

    BankService bankService = new BankService();

    OwnTransfer ownTransferPLNToEUR = new OwnTransfer(userAccount, BigDecimal.valueOf(1000),
        bankService, exchangeRatePLNToEUR);

    OwnTransfer ownTransferEURToUSD = new OwnTransfer(userAccount,
        userAccount.getWallet().get(exchangeRatePLNToEUR.getTo()), bankService,
        exchangeRateEURToUSD);

    OwnTransfer ownTransferUSDToPLN = new OwnTransfer(userAccount,
        userAccount.getWallet().get(exchangeRateEURToUSD.getTo()), bankService,
        exchangeRateUSDToPLN);

    List<OwnTransfer> transferThreads = List.of(ownTransferPLNToEUR, ownTransferEURToUSD,
        ownTransferUSDToPLN);

    ExecutorService executor = Executors.newFixedThreadPool(3);
    transferThreads.forEach(executor::execute);
    executor.shutdown();
    accountService.saveToFile(userAccount);
  }

  private static void setUpUserWallet(UserAccount userAccount) {
    userAccount.getWallet().put(Currency.PLN, BigDecimal.valueOf(1000));
    userAccount.getWallet().put(Currency.EUR, BigDecimal.valueOf(100));
    userAccount.getWallet().put(Currency.USD, BigDecimal.valueOf(500));
  }

}
