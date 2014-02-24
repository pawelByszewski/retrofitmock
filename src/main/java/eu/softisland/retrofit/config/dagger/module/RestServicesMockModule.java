package eu.softisland.retrofit.config.dagger.module;

import dagger.Module;
import dagger.Provides;
import eu.softisland.retrofit.rest.echo.EchoService;
import eu.softisland.retrofit.rest.mock.RetrofitClientMock;
import eu.softisland.retrofit.ui.main.MainActivity;
import retrofit.RestAdapter;
import retrofit.client.Client;

import javax.inject.Named;

@Module(
    injects = MainActivity.class,
    library = true,
    complete = false
)
public class RestServicesMockModule {

    @Provides
    @Named("mockService")
    EchoService provideLogoutService(Client client) {
        return new RestAdapter.Builder()
                    .setServer("http://echo.jsontest.com")
                    .setClient(client)
                    .build()
                    .create(EchoService.class);
    }

    @Provides
    Client provideMockClient() {
        return new RetrofitClientMock();
    }
}