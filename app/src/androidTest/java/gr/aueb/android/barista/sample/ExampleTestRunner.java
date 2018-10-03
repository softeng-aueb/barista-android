package gr.aueb.android.barista.sample;

import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnitRunner;

import com.linkedin.android.testbutler.TestButler;

import gr.aueb.android.barista.framework.BaristaInstrumentationTestRunner;

public class ExampleTestRunner extends AndroidJUnitRunner {

  @Override
  public void onStart() {
      TestButler.setup(InstrumentationRegistry.getTargetContext());
      super.onStart();

  }

  @Override
  public void finish(int resultCode, Bundle results) {
      TestButler.teardown(InstrumentationRegistry.getTargetContext());
      super.finish(resultCode, results);
  }
}