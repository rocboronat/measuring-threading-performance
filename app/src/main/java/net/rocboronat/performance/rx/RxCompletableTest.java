package net.rocboronat.performance.rx;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Callable;
import net.rocboronat.performance.BackgroundTask;
import net.rocboronat.performance.TestCallback;

public class RxCompletableTest {
  private TestCallback testCallback;

  public RxCompletableTest(TestCallback testCallback) {
    this.testCallback = testCallback;
  }

  public void run() {
    Completable.fromCallable(new Callable<Object>() {
      @Override
      public Object call() throws Exception {
        BackgroundTask.run("RX Completable");
        return null;
      }
    })
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action() {
          @Override
          public void run() throws Exception {
            testCallback.onEnd();
          }
        });
  }
}
