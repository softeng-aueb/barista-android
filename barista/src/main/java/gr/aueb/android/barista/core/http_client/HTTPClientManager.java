package gr.aueb.android.barista.core.http_client;

import gr.aueb.android.barista.core.utilities.DefaultBaristaConfigurationReader;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class HTTPClientManager {

    private static BaristaClient httpClient = null;

    public static void initialize(String endpoint){
        if(httpClient == null){
            httpClient = new DefaultBaristaRetrofitClient(endpoint,
                    DefaultBaristaConfigurationReader.getBaristaServerPort(),
                    JacksonConverterFactory.create());
        }
    }

    public static BaristaClient getInstance(){
        return httpClient;
    }
}
