package gr.aueb.android.barista.core.http_client;

import gr.aueb.android.barista.core.utilities.DefaultBaristaConfigurationReader;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class HTTPClientManager {

    private static BaristaClient httpClient = null;
    // The ip of the host machine (where the Barista server Runs)
    private static final String BASE_URL = "http://10.0.2.2";


    public static void initialize(){
        if(httpClient == null){
            httpClient = new DefaultBaristaRetrofitClient(BASE_URL,
                    DefaultBaristaConfigurationReader.getBaristaServerPort(),
                    JacksonConverterFactory.create());
        }
    }

    public static BaristaClient getInstance(){
        return httpClient;
    }
}
