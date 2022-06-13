import com.mentoring.firsthw.service.api.Service;
import com.mentoring.firsthw.service.impl.ServiceImpl;

module jmp.cloud.service.impl {
  requires transitive jmp.service.api;
  requires jmp.dto;

  exports com.mentoring.firsthw.service.impl;
  provides Service with ServiceImpl;
}