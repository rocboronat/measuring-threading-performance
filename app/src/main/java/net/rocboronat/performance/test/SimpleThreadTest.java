package net.rocboronat.performance.test;

import net.rocboronat.performance.BackgroundTask;
import net.rocboronat.performance.TestCallback;

public class SimpleThreadTest {
  private TestCallback testCallback;

  public SimpleThreadTest(TestCallback testCallback) {
    this.testCallback = testCallback;
  }

  public void run() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        BackgroundTask.run("Simple thread");
        testCallback.onEnd();
      }
    }).start();
  }
}
