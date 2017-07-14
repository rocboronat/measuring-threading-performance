package net.rocboronat.performance.asynctask;

import android.os.AsyncTask;
import net.rocboronat.performance.BackgroundTask;
import net.rocboronat.performance.TestCallback;

public class ASyncTaskTest {
  private TestCallback testCallback;

  public ASyncTaskTest(TestCallback testCallback) {
    this.testCallback = testCallback;
  }

  public void run() {
    new ASyncTaskExample(testCallback).execute();
  }
}