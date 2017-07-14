package net.rocboronat.performance.intentservice;

import android.content.Context;
import android.content.Intent;

public class IntentServiceTest {
  public void run(Context context) {
    context.startService(new Intent(context, IntentServiceExample.class));
  }
}