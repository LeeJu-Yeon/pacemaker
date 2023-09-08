package leejuyeon.pacemaker.redissonLock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class RedissonLockAop {

  private final RedissonLockService redissonLockService;

  // 해당 어노테이션이 붙은 메서드 전후로, 아래의 어드바이스를 적용하라는 의미
  @Around("@annotation(PartyLock) && args(partyId)")
  public Object partyLock(ProceedingJoinPoint pjp, Long partyId) throws Throwable {

    // lock 취득 시도
    redissonLockService.lock(partyId);

    try {
      // 메서드 실행
      return pjp.proceed();

    } finally {
      // lock 해제
      redissonLockService.unlock(partyId);
    }
  }

}
