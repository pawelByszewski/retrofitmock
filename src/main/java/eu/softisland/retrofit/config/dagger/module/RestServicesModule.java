package eu.softisland.retrofit.config.dagger.module;

import dagger.Module;
import dagger.Provides;
import eu.softisland.retrofit.rest.echo.EchoService;
import eu.softisland.retrofit.ui.main.MainActivity;
import retrofit.RestAdapter;

import javax.inject.Named;

@Module(
        injects = MainActivity.class,
        library = true,
        complete = false
)
public class RestServicesModule {

    @Provides
    @Named("realService")
    EchoService provideLogoutService() {
        return new RestAdapter.Builder().setServer("http://echo.jsontest.com").build().create(EchoService.class);
    }
}
