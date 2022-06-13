module application {
  uses com.mentoring.firsthw.service.api.Service;

  requires jmp.bank.api;
  requires jmp.service.api;
  requires jmp.cloud.bank.impl;
  requires jmp.cloud.service.impl;
  requires jmp.dto;
}