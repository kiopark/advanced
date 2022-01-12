package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

  private FieldService fieldService = new FieldService();

  @Test
  void filed() {
    log.info("main start");
    Runnable userA = () -> {
      fieldService.logic("userA");
    };
    log.info("main start");
    Runnable userB = () -> {
      fieldService.logic("userB");
    };

    Thread threadA = new Thread(userA);
    threadA.setName("threadA");
    Thread threadB = new Thread(userB);
    threadB.setName("threadB");

    threadA.start();
//    sleep(2000); // 동시성 문제 발생 x
    sleep(100); // 동시성 문제 발생 o
    threadB.start();

    sleep(3000); // 동시성 문제 발생 x
    log.info("main exit");
  }

  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
