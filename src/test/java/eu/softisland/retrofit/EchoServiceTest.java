package eu.softisland.retrofit;

import eu.softisland.retrofit.config.dagger.Injector;
import eu.softisland.retrofit.config.dagger.module.RestServicesMockModule;
import eu.softisland.retrofit.config.dagger.module.RestServicesModule;
import eu.softisland.retrofit.model.login.EchoResponse;
import eu.softisland.retrofit.rest.echo.EchoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.robolectric.RobolectricTestRunner;
import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Collections;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class EchoServiceTest {

    @Inject
    protected EchoService echoService;

    @Inject
    protected Client client;

    @Before
    public void setUp() throws Exception {
        Injector.add(new RestServicesModule(), new RestServicesMockModule(), new TestModule());
        Injector.inject(this);
    }

    @Test
    public void shouldReturnOfferInAsyncMode() throws IOException {
        //given
        int expectedQuantity = 765;
        String responseContent = "{" +
                "   \"message\": \"mock message\"," +
                "   \"quantity\": \"" + expectedQuantity + "\"" +
                "}";
        mockResponseWithCodeAndContent(200, responseContent);

        //when
        EchoResponse echoResponse = echoService.getMessageAndQuantity("test", 2435);

        //then
        assertThat(echoResponse.getQuantity()).isEqualTo(expectedQuantity);
    }

    @Test
    public void shouldReturnOfferInAsyncModea() throws IOException {
        //given
        int expectedQuantity = 2;
        String responseContent = "{" +
                "   \"message\": \"mock message\"," +
                "   \"quantity\": \"" + expectedQuantity + "\"" +
                "}";
        mockResponseWithCodeAndContent(200, responseContent);

        //when
        EchoResponse echoResponse = echoService.getMessageAndQuantity("test", 123);

        //then
        assertThat(echoResponse.getQuantity()).isEqualTo(expectedQuantity);
    }


    protected void mockResponseWithCodeAndContent(int httpCode, String content) throws IOException {
        Response response = createResponseWithCodeAndJson(httpCode, content);
        when(client.execute(Matchers.<Request>anyObject())).thenReturn(response);
    }

    private Response createResponseWithCodeAndJson(int responseCode, String json) {
        return new Response(responseCode, "nothing", Collections.EMPTY_LIST, new TypedByteArray("application/json", json.getBytes()));
    }
}
