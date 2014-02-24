package eu.softisland.retrofit;

import dagger.Module;
import dagger.Provides;
import eu.softisland.retrofit.rest.echo.EchoService;
import retrofit.RestAdapter;
import retrofit.client.Client;

import javax.inject.Singleton;

import static org.mockito.Mockito.mock;

@Module(
    injects = OfferDetailAdapterTest.class,
    overrides = true,
    library = true,
    complete = false

)
public class TestModule {

    @Provides
    EchoService provideLogoutService(Client client) {
        return new RestAdapter.Builder().setServer("http://echo.jsontest.com").setClient(client).build().create(EchoService.class);
    }

    @Provides
    @Singleton
    Client provideMockClient() {
        return mock(Client.class);
    }
}