package net.rocboronat.performance.test;

import android.os.AsyncTask;
import android.util.Log;
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

class ASyncTaskExample extends AsyncTask<Void, Void, Boolean> {

  private TestCallback testCallback;

  public ASyncTaskExample(TestCallback testCallback) {
    this.testCallback = testCallback;
  }

  @Override
  protected Boolean doInBackground(Void... params) {
    BackgroundTask.run("ASyncTask");
    return true;
  }

  @Override
  protected void onPostExecute(Boolean result) {
    testCallback.onEnd();
  }
}