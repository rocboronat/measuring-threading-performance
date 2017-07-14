package net.rocboronat.performance.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import net.rocboronat.performance.BackgroundTask;
import org.greenrobot.eventbus.EventBus;

public class IntentServiceExample extends IntentService {

  public IntentServiceExample() {
    super("IntentServiceExample");
  }

  @Override
  protected void onHandleIntent(@Nullable Intent intent) {
    BackgroundTask.run("Intent Service");
    EventBus.getDefault().post(new IntentServiceFinishedEvent());
  }
}
