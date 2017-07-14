package net.rocboronat.performance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import net.rocboronat.performance.asynctask.ASyncTaskTest;
import net.rocboronat.performance.intentservice.IntentServiceFinishedEvent;
import net.rocboronat.performance.intentservice.IntentServiceTest;
import net.rocboronat.performance.thread.SimpleThreadTest;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

  public static final int REPETITIONS = 1000;

  public int threadIteration;
  public long threadTestTime;
  public int asyncTaskIteration;
  public long asyncTaskTestTime;
  private int intentServiceIteration;
  public long intentServiceTestTime;

  public long testStartTime;
  private TextView results;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    results = (TextView) findViewById(R.id.results);

    findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        results.setText("Running tests...");
        threadIteration = 0;
        threadTestTime = 0;
        asyncTaskIteration = 0;
        asyncTaskTestTime = 0;
        intentServiceIteration = 0;
        intentServiceTestTime = 0;
        runTests();
      }
    });
  }

  @Override
  public void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
  }

  @Override
  public void onStop() {
    super.onStop();
    EventBus.getDefault().unregister(this);
  }

  private void runTests() {
    testStartTime = System.currentTimeMillis();
    if (threadIteration < REPETITIONS) {
      new SimpleThreadTest(new TestCallback() {
        @Override
        public void onEnd() {
          runOnUiThread(new Runnable() {
            @Override
            public void run() {
              threadIteration++;
              threadTestTime += timeTook();
              runTests();
            }
          });
        }
      }).run();
    } else if (asyncTaskIteration < REPETITIONS) {
      new ASyncTaskTest(new TestCallback() {
        @Override
        public void onEnd() {
          asyncTaskIteration++;
          asyncTaskTestTime += timeTook();
          runTests();
        }
      }).run();
    } else if (intentServiceIteration < REPETITIONS) {
      new IntentServiceTest().run(this);
    } else {
      printTestResults();
    }
  }

  private void printTestResults() {
    StringBuilder sb = new StringBuilder();
    sb.append("Simple thread: " + threadTestTime + "ms\n");
    sb.append("ASyncTask: " + asyncTaskTestTime + "ms\n");
    sb.append("Intent Service: " + intentServiceTestTime + "ms\n");

    results.setText(sb.toString());
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onMessageEvent(IntentServiceFinishedEvent event) {
    intentServiceIteration++;
    intentServiceTestTime += timeTook();
    runTests();
  }

  ;

  private long timeTook() {
    return System.currentTimeMillis() - testStartTime;
  }
}
