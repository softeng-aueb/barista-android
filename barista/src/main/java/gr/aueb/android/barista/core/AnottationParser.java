package gr.aueb.android.barista.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gr.aueb.android.barista.core.annotations.SaySomething;
import timber.log.Timber;

import static java.util.Objects.requireNonNull;


public class AnottationParser
{
    private Object o;

    public AnottationParser(Object o){
        this.o = o;

    }

    public List<Method> getFrameworkMethods(){
        Class<?> objectClass  = requireNonNull(o).getClass();
        Method[] methods = objectClass.getDeclaredMethods();
        List<Method> frameworkMethods = new ArrayList<>();
        for(Method m : methods){
            m.setAccessible(true);
            if(m.isAnnotationPresent(SaySomething.class)){

                frameworkMethods.add(m);
            }

        }
        return frameworkMethods;
    }

    public List<Method> getAllMethods(){
        Class<?> objectClass  = requireNonNull(o).getClass();
        Method[] methods = objectClass.getDeclaredMethods();
        List<Method> frameworkMethods = new ArrayList<>();
        for(Method m : methods){
                frameworkMethods.add(m);
        }
        return frameworkMethods;
    }

    public HashMap<String,String> printValue(Object o){
        Class<?> objectClass  = requireNonNull(o).getClass();
        Field[] fields = objectClass.getDeclaredFields();
        Method[] methods = objectClass.getDeclaredMethods();

        System.out.println(o.getClass().toString());
        HashMap<String,String> nameOfFields = new HashMap<>();
        for(Field f : fields){

            System.out.println("    "+f.toString());
            Annotation[] annotationList = f.getAnnotations();
            System.out.println(annotationList.length);
            f.setAccessible(true);
            if(f.isAnnotationPresent(SaySomething.class)){
                Timber.d("FIELD TO STRING: %s",f.toString());
                Timber.d("Field Name: %s",f.getName());
                Timber.d("FILD CLASS: %s",f.getType());
                Timber.d("ANNOTAION TO STRING: %s", f.getAnnotation(SaySomething.class).toString());

                nameOfFields.put(f.getAnnotation(SaySomething.class).param1(),"Field");
                nameOfFields.put(f.getAnnotation(SaySomething.class).param2(),"Field");
                nameOfFields.put(f.getAnnotation(SaySomething.class).param3(),"Field");
                nameOfFields.put(f.getAnnotation(SaySomething.class).param4(),"Field");

            }

        }

        for(Method m : methods){
            m.setAccessible(true);
            if(m.isAnnotationPresent(SaySomething.class)){
                nameOfFields.put(m.getName(),"Method");
            }

        }

        return nameOfFields;
    }
}
