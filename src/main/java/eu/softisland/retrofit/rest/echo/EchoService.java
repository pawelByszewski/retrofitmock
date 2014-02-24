package eu.softisland.retrofit.rest.echo;

import eu.softisland.retrofit.model.login.EchoResponse;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

public interface EchoService {

    @Headers("Content-Type: form-urlencoded; charset=utf-8")
    @GET("/message/{message}/quantity/{quantity}")
    EchoResponse getMessageAndQuantity(@Path("message") String message, @Path("quantity") int quantity);

}
