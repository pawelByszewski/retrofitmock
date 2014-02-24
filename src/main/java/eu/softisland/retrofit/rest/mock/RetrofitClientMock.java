package eu.softisland.retrofit.rest.mock;

import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

import java.io.IOException;
import java.util.Collections;

public class RetrofitClientMock implements Client {

    private static final int HTTP_OK_STATUS = 200;

    private static final String LOGIN_VALID_RESP = "{\n"
            + "   \"message\": \"mock message\",\n"
            + "   \"quantity\": \"11\"\n"
            + "}";

    @Override
    public Response execute(Request request) throws IOException {
        return createResponseWithCodeAndJson(HTTP_OK_STATUS, LOGIN_VALID_RESP);
    }

    private Response createResponseWithCodeAndJson(int responseCode, String json) {
        return new Response(responseCode, "nothing", Collections.EMPTY_LIST,
                new TypedByteArray("application/json", json.getBytes()));
    }
}
