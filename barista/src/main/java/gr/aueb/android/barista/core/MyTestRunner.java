package gr.aueb.android.barista.core;

import android.util.Log;

import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import gr.aueb.android.barista.core.annotations.SaySomething;
import gr.aueb.android.barista.core.http_client.BaristaHttpClient;

/**
 *  Attempt to create custom TestRunner
 *  follwing: https://www.baeldung.com/junit-4-custom-runners tutorial
 *
 *  In order to use this way onw must replace the testInstrumentationRunner of the target project.
 */
public class MyTestRunner extends ParentRunner {

    private Class testClass;
    private AnottationParser parser;
    //todo delete this . Only for testing purposes
    private BaristaHttpClient client;

    /**
     * Constructs a new {@code ParentRunner} that will run {@code @TestClass}
     *
     * @param testClass
     */
    public MyTestRunner(Class testClass) throws InitializationError {

        super(testClass);
        client = new BaristaHttpClient();

        System.out.println("Default Constructor call");
        System.out.println("Class String: "+testClass.toString());
        parser = new AnottationParser(testClass);
        this.testClass = testClass;
    }

    /**
     * Return Methods wraped as FrameworkMethod containing @SaySomething annotation
     * @return
     */
    @Override
    protected List<FrameworkMethod> getChildren() {
        List<FrameworkMethod> methodList = new ArrayList<>();
        if(this.testClass != null){
            Method[] methods = testClass.getDeclaredMethods();
            for(Method m : methods){
                m.setAccessible(true);
                if(m.isAnnotationPresent(SaySomething.class)){
                    System.out.println("Name: "+m.getName());
                    System.out.println("Param1: "+  m.getAnnotation(SaySomething.class).param1());
                    System.out.println("Param2: "+  m.getAnnotation(SaySomething.class).param2());
                    System.out.println("Param3: "+  m.getAnnotation(SaySomething.class).param3());
                    System.out.println("Param4: "+  m.getAnnotation(SaySomething.class).param4());
                    System.out.println(client.getStatus());
                    methodList.add(new FrameworkMethod(m));
                }
            }
        }
       // client.killServer();
        return methodList;
    }

    @Override
    protected Description describeChild(Object child) {
        System.out.println("[describeChild] "+child.getClass().toString());
        return Description.createTestDescription(child.getClass(),child.toString());
    }

    @Override
    protected void runChild(Object child, RunNotifier notifier) {
        FrameworkMethod method = (FrameworkMethod) child;
        Log.d("AUEB_DEBUG", "Running Test"+method.getName());
    }

    private void sendRequestToServer(){

    }




    public Description getDescription(FrameworkMethod method) {
        System.out.println("Get Description Method: "+method.getName());
        return Description.createTestDescription(testClass,method.getName());
       //return Description.createSuiteDescription("MyTestRunner",SaySomething.class);

    }


}
