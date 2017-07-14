package net.rocboronat.performance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import net.rocboronat.performance.test.SimpleThreadTest;

public class MainActivity extends AppCompatActivity {

  public static final int REPETITIONS = 1000;

  public int threadIteration = 0;
  public long threadTestTime = 0;

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
        runTests();
      }
    });
  }

  private void runTests() {
    if (threadIteration < REPETITIONS) {
      testStartTime = System.currentTimeMillis();
      new SimpleThreadTest(new TestCallback() {
        @Override
        public void onEnd() {
          runOnUiThread(new Runnable() {
            @Override
            public void run() {
              threadIteration++;
              threadTestTime += System.currentTimeMillis() - testStartTime;
              runTests();
            }
          });
        }
      }).run();
    } else {
      printTestResults();
    }
  }

  private void printTestResults() {
    StringBuilder sb = new StringBuilder();
    sb.append("Simple threads: " + threadTestTime + "ms");

    results.setText(sb.toString());
  }
}
