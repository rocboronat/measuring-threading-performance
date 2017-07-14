package net.rocboronat.performance;

import android.util.Log;

public class BackgroundTask {

  public static void run(String caller) {
    Log.d("THREAD", caller);
  }
}
