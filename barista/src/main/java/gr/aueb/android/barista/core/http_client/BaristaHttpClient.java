package gr.aueb.android.barista.core.http_client;



import android.os.Environment;
import android.support.test.InstrumentationRegistry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import gr.aueb.android.barista.BuildConfig;
import okhttp3.OkHttpClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import timber.log.Timber;


public class BaristaHttpClient {

    private static BaristaHttpClient INSTANCE = null;

    private final int DEFAULT_PORT = 8040;
    private Retrofit retrofit;
    private  String URI ;
    private int port;
    private String token;

    /**
     * Construct an HTTP client in order to perform REST CALLS to the Barista Server
     *
     */
    private BaristaHttpClient(){
        int portValue = decidePort();
        if(portValue != -1){
            Timber.d("Given port is "+ this.port);
            this.port = portValue;
        }
        else{
            Timber.d("No port provided, using default port "+this.port+".");
            //this.port = 8040;
        }
        this.URI = "http://10.0.2.2:"+port+"/barista/";

        this.token = getAccessToken();
        Timber.d("Access Token aquired: "+token);

        this.retrofit = getRequestClient();

    }

    public String getAccessToken(){

       File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"barista-token.txt");
       String accessToken = "";
       Timber.d("Searching for token at: "+file.getAbsolutePath());
       try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                accessToken = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accessToken;
    }

    public static BaristaHttpClient getInstance(){
        if(INSTANCE == null){
            INSTANCE = new BaristaHttpClient();
        }
        return INSTANCE;
    }

    public String getStatus(){

        StatusService service = getRequestClient().create(StatusService.class);
        Call<String> callSync = service.getStatusMessage();
        Timber.d("REST CALL URI: "+callSync.request().url().toString());

        try {
            Response<String> response = callSync.execute();

            //analyze response
            Set<String> names = response.headers().names();
            for (String name: names ) {
                Timber.d("Response From Server: ["+name+"]"+response.headers().get(name));
            }

            printResponse(response);
            String returnedMessage = response.body();
            return returnedMessage;
        } catch (IOException e) {
            Timber.d("EXCEPTION OCCURED");
            e.printStackTrace();
        }

        return null;
    }

    public String getStatus2(){

        StatusService service = getRequestClient().create(StatusService.class);
        Call<String> callSync = service.getStatusMessage2();
        Timber.d("REST CALL URI: %s",callSync.request().url().toString());

        try {
            Response<String> response = callSync.execute();
            printResponse(response);
            String returnedMessage = response.body();

            return returnedMessage;
        } catch (IOException e) {
            Timber.d("EXCEPTION OCCURED");
            e.printStackTrace();
        }

        return null;
    }

    public void echoMessage(String msg){

        StatusService service = getRequestClient().create(StatusService.class);
        Call<String> callSync = service.echoMessage(msg);
        callSync.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                printResponse(response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Timber.d("Task Failed! %s", t.getMessage());
            }
        });

    }

    //todo if server has stoped normally by  the gradle plugin. timeout exception will occur
    public String killServer(){

        StatusService service = getRequestClient().create(StatusService.class);
        Call<String> callSync = service.killServer();
        Timber.d("REST CALL URI: %s",callSync.request().url().toString());

        try {
            Response<String> response = callSync.execute();
            // debug only
            //printResponse(response);
            String returnedMessage = response.body();

            return returnedMessage;
        } catch (IOException e) {
            Timber.d("EXCEPTION OCCURED");
            e.printStackTrace();
        }

        return null;
    }

    public void resizeScreen( String width, String height) {
        StatusService service = getRequestClient().create(StatusService.class);

        Call<String> callSync = service.resizeScreen(this.token,width, height);

        try {
            Timber.d("Calling '/setDimension' Service");
            callSync.execute();
        } catch (IOException e) {
            Timber.e("Error Occured when calling '/setSize' Service");
            e.printStackTrace();
        }
    }

    public void setGeofix(double lat, double longt) {
        StatusService service = getRequestClient().create(StatusService.class);
        Call<String> callSync = service.setGeoFix(this.token,lat,longt);
        try {
            Timber.d("Calling '/geofix' Service");
            callSync.execute();
        } catch (IOException e) {
            Timber.e("Error Occured when calling '/geofix' Service");
            e.printStackTrace();
        }
    }



    public void resetScreen(){
        StatusService service = getRequestClient().create(StatusService.class);
        Call<String> callSync = service.resetSize(this.token);
        try {
            Timber.d("Calling '/reset' Service");
            callSync.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Retrofit getRequestClient(){
        if(retrofit == null){
            this.create();
        }

        return retrofit;
    }

    private void create(){
        /**
         * httClient is going to take care of connecting to the server and the sending and retrieval information.
         *
         * ConverterFactory should contain proper factories for each possible response type (xml, json, plain text etc.)
         * If a converterFactory isn't present for a specific response type an exception will be thrown
         *
         * for example: MalformedJsonException if the response is not JSON.
         * for info on how ConverterFactory works on retrofit here(https://proandroiddev.com/retrofit-advance-multi-converter-c675e9483801)
         *
         *
         * Currently active converter factories :
         *  Gson for JSON response
         *  Scalar for plain text
         *
         */
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        retrofit = new Retrofit.Builder()
                .baseUrl(URI)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    private void printResponse(Response response){
        Timber.d("REST CODE: "+response.code());
        Timber.d("REST MESSAGE: "+response.message());
        Timber.d("REST BODY: "+response.body());
        Timber.d("REST IS_SUCESFUL: "+response.isSuccessful());
        Timber.d("REST TO_STRING: "+response.toString());
    }

    /**
     * Decides for the listening port of the server. If not provided in the gradle configuration extension
     * default port will de used
     * @return
     */
    private int decidePort(){
        Integer portValue;

        String packageName = InstrumentationRegistry.getTargetContext().getPackageName();
        String buildConfigClass = packageName + ".BuildConfig";

        try {
            // TODO: get emulator port and ip address through reflection on BuildConfig
            Class clazz = Class.forName(buildConfigClass);
            Field portField = clazz.getField("BARISTA_PORT");

            if(portField == null){
                portValue = DEFAULT_PORT;
                Timber.d("Using default port: %s",portValue);
            }
            else{
                portField.setAccessible(true);
                portValue = (Integer) portField.get(clazz);
                Timber.d("Using given configuration port: %s", portValue);
            }

            return portValue;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
