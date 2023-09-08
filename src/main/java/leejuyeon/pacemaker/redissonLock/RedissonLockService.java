package leejuyeon.pacemaker.redissonLock;

import java.util.concurrent.TimeUnit;
import leejuyeon.pacemaker.enums.Error;
import leejuyeon.pacemaker.exception.PartyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedissonLockService {

  private final RedissonClient redissonClient;

  private static String getLockKey(Long partyId) {
    return "PartyLock : " + partyId;
  }

  public void lock(Long partyId) {
    RLock lock = redissonClient.getLock(getLockKey(partyId));
    log.debug("PartyLock 획득 시도 : {}", partyId);

    try {
      if (!lock.tryLock(2, 3, TimeUnit.SECONDS)) {
        log.error("PartyLock 획득 실패");
        throw new PartyException(Error.PARTY_JOIN_LOCK);
      }
    } catch (PartyException e) {
      throw e;
    } catch (InterruptedException e) {
      log.error("PartyLock 획득 실패", e);
      throw new RuntimeException(e);
    }
  }

  public void unlock(Long partyId) {
    redissonClient.getLock(getLockKey(partyId)).unlock();
    log.debug("PartyLock 언락 partyId : {}", partyId);
  }

}
