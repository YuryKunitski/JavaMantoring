import com.mentoring.firsthw.bank.api.Bank;
import com.mentoring.firsthw.bank.impl.BankImpl;

module jmp.cloud.bank.impl {
  requires transitive jmp.bank.api;
  requires jmp.dto;

  exports com.mentoring.firsthw.bank.impl;
  provides Bank with BankImpl;
}