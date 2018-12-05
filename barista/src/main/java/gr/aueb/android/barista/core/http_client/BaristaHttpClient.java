package gr.aueb.android.barista.core.http_client;



import java.io.IOException;

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

    private Retrofit retrofit;
    private  String URI ;
    private int port = 8040;

    /**
     * Construct an HTTP client in order to perform REST CALLS to the Barista Server
     *
     */
    public BaristaHttpClient(){
        //todo Target Server UI  must be dynamicaly provided
        //this.URI = URI;

        //Get the port number provided by  the .gradle file


        if(BuildConfig.BARISTA_PORT != null ){
            Timber.d("Given port is "+ this.port);
            this.port = BuildConfig.BARISTA_PORT;

        }

        else{

            Timber.d("No port provided, using default port "+this.port+".");
            //this.port = 8040;
        }
        this.URI = "http://10.0.2.2:"+port+"/barista/";
        this.retrofit = getRequestClient();

    }

    public String getStatus(){
        StatusService service = getRequestClient().create(StatusService.class);
        Call<String> callSync = service.getStatusMessage();
        Timber.d("REST CALL URI: "+callSync.request().url().toString());

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

    public void resizeScreen(String width, String height){
        StatusService service = getRequestClient().create(StatusService.class);
        Call<String> callSync = service.resizeScreen(width,height);
        try {
            Timber.d("Calling '/setDimension' Service");
            callSync.execute();
        } catch (IOException e) {
            Timber.e("Error Occured when calling '/setSize' Service");
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
}
