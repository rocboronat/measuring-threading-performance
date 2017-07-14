package net.rocboronat.performance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import net.rocboronat.performance.test.ASyncTaskTest;
import net.rocboronat.performance.test.SimpleThreadTest;

public class MainActivity extends AppCompatActivity {

  public static final int REPETITIONS = 100;

  public int threadIteration = 0;
  public long threadTestTime = 0;
  public int asyncTaskIteration = 0;
  public long asyncTaskTestTime = 0;

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
        results.setText("");
        threadIteration = 0;
        threadTestTime = 0;
        asyncTaskIteration = 0;
        asyncTaskTestTime = 0;
        runTests();
      }
    });
  }

  private void runTests() {
    printTestResults();
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
    }
  }

  private void printTestResults() {
    StringBuilder sb = new StringBuilder();
    sb.append("Simple thread: " + threadTestTime + "ms\n");
    sb.append("ASyncTask: " + asyncTaskTestTime + "ms\n");

    results.setText(sb.toString());
  }

  private long timeTook() {
    return System.currentTimeMillis() - testStartTime;
  }
}
