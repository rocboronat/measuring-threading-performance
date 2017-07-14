package net.rocboronat.performance.test;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import net.rocboronat.performance.BackgroundTask;
import org.greenrobot.eventbus.EventBus;

public class IntentServiceTest {
  public void run(Context context) {
    context.startService(new Intent(context, IntentServiceExample.class));
  }
}