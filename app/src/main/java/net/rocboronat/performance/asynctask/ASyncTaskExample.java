package net.rocboronat.performance.asynctask;

import android.os.AsyncTask;
import net.rocboronat.performance.BackgroundTask;
import net.rocboronat.performance.TestCallback;

public class ASyncTaskExample extends AsyncTask<Void, Void, Boolean> {

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
