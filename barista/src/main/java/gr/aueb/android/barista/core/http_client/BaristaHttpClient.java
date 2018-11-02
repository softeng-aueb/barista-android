package gr.aueb.android.barista.core.http_client;

import java.io.IOException;

import okhttp3.OkHttpClient;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;


public class BaristaHttpClient {

    private Retrofit retrofit;


    /**
     *     todo java.lang.IllegalArgumentException: baseUrl must end in /
     *     Error if URI does not end with /.
     *     Discuss with ZV if a handler must be present if URI is given by client
     */

    private static String URI = "http://10.0.2.2:8040/barista/";

    /**
     * Construct an HTTP client in order to perform REST CALLS to the Barista Server
     * @param URI
     */
    public BaristaHttpClient(String URI){
        //todo Target Server UI  must be dynamicaly provided
        //this.URI = URI;

        this.retrofit = getRequestClient();

    }

    public String getStatus(){
        StatusService service = getRequestClient().create(StatusService.class);
        Call<String> callSync = service.getStatusMessage();
        System.out.println("REST CALL URI: "+callSync.request().url().toString());

        try {
            Response<String> response = callSync.execute();
            printResponse(response);
            String returnedMessage = response.body();
            return returnedMessage;
        } catch (IOException e) {
            System.out.println("EXCEPTION OCCURED");
            e.printStackTrace();
        }
        return null;
    }

    public String getStatus2(){
        StatusService service = getRequestClient().create(StatusService.class);
        Call<String> callSync = service.getStatusMessage2();
        System.out.println("REST CALL URI: "+callSync.request().url().toString());

        try {
            Response<String> response = callSync.execute();
            printResponse(response);
            String returnedMessage = response.body();

            return returnedMessage;
        } catch (IOException e) {
            System.out.println("EXCEPTION OCCURED");
            e.printStackTrace();
        }
        return null;
    }

    public String killServer(){
        StatusService service = getRequestClient().create(StatusService.class);
        Call<String> callSync = service.killServer();
        System.out.println("REST CALL URI: "+callSync.request().url().toString());

        try {
            Response<String> response = callSync.execute();
            printResponse(response);
            String returnedMessage = response.body();

            return returnedMessage;
        } catch (IOException e) {
            System.out.println("EXCEPTION OCCURED");
            e.printStackTrace();
        }
        return null;
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
         * Currently active cconverter factories :
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
        System.out.println("REST CODE: "+response.code());
        System.out.println("REST MESSAGE: "+response.message());
        System.out.println("REST isSUCESFUL: "+response.isSuccessful());
        System.out.println("REST TO_STRING: "+response.toString());
    }
}
