package gr.aueb.android.barista.framework;

import android.app.Instrumentation;
import android.content.Context;
import android.util.Log;

import junit.framework.TestCase;
import junit.framework.TestListener;
import junit.framework.TestResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import gr.aueb.android.barista.annotation.Configuration;
import gr.aueb.android.barista.annotation.Configurations;
import gr.aueb.android.barista.annotation.Device;
import gr.aueb.android.barista.annotation.Devices;
import gr.aueb.android.barista.command.Command;
import gr.aueb.android.barista.command.CommandChooser;
import gr.aueb.android.barista.command.CommandDefaultSDK;
import gr.aueb.android.barista.helper.ConfigurationHelper;

/**
 * Created by Miltiadis Konsolakis on 23/11/15.
 */
public class AndroidTestRunner extends android.test.AndroidTestRunner {
    private TestResult mTestResult;
    private Context context;
    private Instrumentation instrumentation;

    public static Configuration currentConfiguration = null;

    @Override
    public void runTest(TestResult testResult) {
        Log.d("AUEB_DEBUG", "AndroidTestRunner<runTest> called!");

        mTestResult = testResult;

        List<TestListener> mTestListeners = null;
        Context mContext = null;
        Instrumentation mInstrumentation = null;
        Object mPerfWriter = null;

        Method methodSetContextIfAndroidTestCase  = null;
        Method methodSetInstrumentationIfInstrumentationTestCase = null;
        Method methodSetPerformanceWriterIfPerformanceCollectorTestCase = null;

        Class<?> c = this.getClass().getSuperclass();

        try {
            Field[] fields = c.getDeclaredFields();
            Method[] methods = c.getDeclaredMethods();

            for(Field field : fields) {
                field.setAccessible(true);

                //System.out.println("field :" + field.getName());
                //System.out.println("type :" + field.getType());
                //System.out.println("value :" + field.get(this));

                if(field.getName().equals("mTestListeners")) {
                    mTestListeners = (List<TestListener>) field.get(this);
                } if(field.getName().equals("mContext")) {
                    mContext = (Context) field.get(this);
                } else if(field.getName().equals("mInstrumentation")) {
                    mInstrumentation = (Instrumentation) field.get(this);
                } else if(field.getName().equals("mPerfWriter")) {
                    mPerfWriter = field.get(this);
                }
            }

            // Loop for get all the methods in class
            for(Method method : methods) {
                method.setAccessible(true);

                //System.out.println("method :" + method.getName());
                //System.out.println("parametes :"+ method.getReturnType());

                if(method.getName().equals("setContextIfAndroidTestCase")) {
                    methodSetContextIfAndroidTestCase = method;
                } else if(method.getName().equals("setInstrumentationIfInstrumentationTestCase")) {
                    methodSetInstrumentationIfInstrumentationTestCase = method;
                } else if(method.getName().equals("setPerformanceWriterIfPerformanceCollectorTestCase")) {
                    methodSetPerformanceWriterIfPerformanceCollectorTestCase = method;
                }
            }
        } catch(Exception ex) {
            Log.e("AUEB_DEBUG", ex.toString());
        }

        for (TestListener testListener : mTestListeners) {
            testResult.addListener(testListener);
        }

        Context testContext = mInstrumentation == null ? mContext : mInstrumentation.getContext();

        List<TestCase> testCases = getTestCases();
        for (int i=0; i<testCases.size(); i++) {
            Log.d("AUEB_DEBUG", "TestCase: " + testCases.get(i).getClass() + "\n" + testCases.get(i).getName() + "\n--\n");
        }

        for (TestCase testCase : getTestCases()) {
            try {
                methodSetContextIfAndroidTestCase.invoke(this, testCase, mContext, testContext);
            } catch(Exception e) {
                Log.e("AUEB_DEBUG", e.toString());
            }

            try {
                methodSetInstrumentationIfInstrumentationTestCase.invoke(this, testCase, mInstrumentation);
            } catch(Exception e) {
                Log.e("AUEB_DEBUG", e.toString());
            }

            try {
                methodSetPerformanceWriterIfPerformanceCollectorTestCase.invoke(this, testCase, mPerfWriter);
            } catch(Exception e) {
                Log.e("AUEB_DEBUG", e.toString());
            }

            //setContextIfAndroidTestCase(testCase, mContext, testContext);
            //setInstrumentationIfInstrumentationTestCase(testCase, mInstrumentation);
            //setPerformanceWriterIfPerformanceCollectorTestCase(testCase, mPerfWriter);

            context = mContext;
            instrumentation = mInstrumentation;

            if(BaristaInstrumentationTestRunner.wmEnabled) {
                try {
                    ArrayList<Configuration> configs = new ArrayList<Configuration>();

                    // Annotations TestCase Class
                    Class<? extends TestCase> obj = testCase.getClass();
                    if (obj.isAnnotationPresent(Configuration.class)) {
                        Annotation annotation = obj.getAnnotation(Configuration.class);
                        Configuration config = (Configuration) annotation;

                        //System.out.printf("dpi :%d, width :%d, height :%d, orientation :%s\n", config.dpi(), config.width(), config.height(), config.orientation());

                        configs.add(config);
                    } else if(obj.isAnnotationPresent(Configurations.class)) {
                        Annotation annotation = obj.getAnnotation(Configurations.class);
                        Configurations configurations = (Configurations) annotation;

                        for (Configuration config : configurations.configurations()) {
                            //System.out.printf("dpi :%d, width :%d, height :%d, orientation :%s\n", config.dpi(), config.width(), config.height(), config.orientation());
                            configs.add(config);
                        }
                    }

                    if(obj.isAnnotationPresent(Device.class)) {
                        Annotation annotation = obj.getAnnotation(Device.class);
                        final Device device = (Device) annotation;

                        Configuration config = new Configuration() {
                            @Override
                            public Class<? extends Annotation> annotationType() { return Configuration.class; }
                            @Override
                            public int dpi() { return device.model().getDpi(); }
                            @Override
                            public int width() { return device.model().getWidth(); }
                            @Override
                            public int height() { return device.model().getHeight(); }
                            @Override
                            public Orientation orientation() { return device.orientation(); }
                        };

                        //System.out.printf("dpi :%d, width :%d, height :%d, orientation :%s\n", config.dpi(), config.width(), config.height(), config.orientation());
                        configs.add(config);
                    } else if(obj.isAnnotationPresent(Devices.class)) {
                        Annotation annotation = obj.getAnnotation(Devices.class);
                        Devices devices = (Devices) annotation;

                        for (final Device device : devices.devices()) {
                            Configuration config = new Configuration() {
                                @Override
                                public Class<? extends Annotation> annotationType() { return Configuration.class; }
                                @Override
                                public int dpi() { return device.model().getDpi(); }
                                @Override
                                public int width() { return device.model().getWidth(); }
                                @Override
                                public int height() { return device.model().getHeight(); }
                                @Override
                                public Orientation orientation() { return device.orientation(); }
                            };

                            //System.out.printf("dpi :%d, width :%d, height :%d, orientation :%s\n", config.dpi(), config.width(), config.height(), config.orientation());
                            configs.add(config);
                        }
                    }

                    // Annotations Current Method
                    Method[] methods = testCase.getClass().getMethods();
                    for (Method m : methods) {
                        if (m.getName().equals(testCase.getName())) {
                            if (m.isAnnotationPresent(Configuration.class)) {
                                Annotation annotation = m.getAnnotation(Configuration.class);
                                Configuration config = (Configuration) annotation;

                                //System.out.printf("dpi :%d, width :%d, height :%d, orientation :%s\n", config.dpi(), config.width(), config.height(), config.orientation());
                                configs.add(config);
                            } else if (m.isAnnotationPresent(Configurations.class)) {
                                Annotation annotation = m.getAnnotation(Configurations.class);
                                Configurations configurations = (Configurations) annotation;

                                for (Configuration config : configurations.configurations()) {
                                    //System.out.printf("dpi :%d, width :%d, height :%d, orientation :%s\n", config.dpi(), config.width(), config.height(), config.orientation());
                                    configs.add(config);
                                }
                            }

                            if (m.isAnnotationPresent(Device.class)) {
                                Annotation annotation = m.getAnnotation(Device.class);
                                final Device device = (Device) annotation;

                                Configuration config = new Configuration() {
                                    @Override
                                    public Class<? extends Annotation> annotationType() {
                                        return Configuration.class;
                                    }

                                    @Override
                                    public int dpi() {
                                        return device.model().getDpi();
                                    }

                                    @Override
                                    public int width() {
                                        return device.model().getWidth();
                                    }

                                    @Override
                                    public int height() {
                                        return device.model().getHeight();
                                    }

                                    @Override
                                    public Orientation orientation() {
                                        return device.orientation();
                                    }
                                };

                                //System.out.printf("dpi :%d, width :%d, height :%d, orientation :%s\n", config.dpi(), config.width(), config.height(), config.orientation());

                                configs.add(config);
                            } else if (m.isAnnotationPresent(Devices.class)) {
                                Annotation annotation = m.getAnnotation(Devices.class);
                                Devices devices = (Devices) annotation;

                                for (final Device device : devices.devices()) {
                                    Configuration config = new Configuration() {
                                        @Override
                                        public Class<? extends Annotation> annotationType() {
                                            return Configuration.class;
                                        }

                                        @Override
                                        public int dpi() {
                                            return device.model().getDpi();
                                        }

                                        @Override
                                        public int width() {
                                            return device.model().getWidth();
                                        }

                                        @Override
                                        public int height() {
                                            return device.model().getHeight();
                                        }

                                        @Override
                                        public Orientation orientation() {
                                            return device.orientation();
                                        }
                                    };

                                    //System.out.printf("dpi :%d, width :%d, height :%d, orientation :%s\n", config.dpi(), config.width(), config.height(), config.orientation());
                                    configs.add(config);
                                }
                            }
                        }
                    }

                    int size = configs.size();
                    if(size > 0) {
                        System.out.println("FinalSize: " + size);
                        for (Configuration config : configs) {
                            setupDeviceWithConfiguration(config);
                            currentConfiguration = config;

                            Log.d("AUEB_DEBUG", "RunTest " + testCase.getName() + ConfigurationHelper.configToString(config));
                            testCase.run(mTestResult);

                            //reset device
                            Log.d("AUEB_DEBUG", "Reset device configuration");
                            setupDeviceWithConfiguration(ConfigurationHelper.getResetConfiguration());
                            Thread.sleep(200);
                        }

                        // Reset current config
                        currentConfiguration = null;

                        // Reset Device WM
                        Log.d("AUEB_DEBUG", "Reset Device WM now");
                        setupDeviceWithConfiguration(ConfigurationHelper.getResetConfiguration());

                        Thread.sleep(200);
                    } else {
                        testCase.run(mTestResult); //default test run
                    }
                } catch (Exception e) {
                    Log.d("AUEB_DEBUG", e.getMessage());
                }
            } else { //default test run
                testCase.run(mTestResult);
            }
        }
    }

    private boolean setupDeviceWithConfiguration(Configuration config) {
        boolean result = false;

        Command command = CommandChooser.GetCommandInstance();

        if (command != null) {
            //set context to command
            command.setContext(context);

            if(config != null) {
                result = command.setDensity(config.dpi()) && command.setSize(config.width(), config.height());

                if(config.orientation() == Configuration.Orientation.PORTRAIT) {
                    result = result && command.setOrientation(CommandDefaultSDK.Orientation.PORTRAIT);
                } else if(config.orientation() == Configuration.Orientation.LANDSCAPE) {
                    result = result && command.setOrientation(CommandDefaultSDK.Orientation.LANDSCAPE);
                }

                instrumentation.waitForIdleSync();
            }
        }

        return result;
    }

    @Override
    public TestResult getTestResult() {
        return mTestResult;
    }

    @Override
    public void testStarted(String testName) {
        super.testStarted(testName);
        Log.d("AUEB_DEBUG", "name: " + testName);
    }

}
