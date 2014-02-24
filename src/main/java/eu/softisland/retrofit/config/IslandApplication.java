package eu.softisland.retrofit.config;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import eu.softisland.retrofit.config.dagger.Injector;
import eu.softisland.retrofit.config.dagger.module.RestServicesMockModule;
import eu.softisland.retrofit.config.dagger.module.RestServicesModule;

public class IslandApplication extends Application {

    private static IslandApplication instance;

    public IslandApplication() {
    }

    public IslandApplication(final Context context) {
        super();
        attachBaseContext(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        Injector.init(new RestServicesModule(), new RestServicesMockModule());
    }

    private static void setInstance(IslandApplication allegroApplication) {
        instance = allegroApplication;
    }

    public IslandApplication(final Instrumentation instrumentation) {
        super();
        attachBaseContext(instrumentation.getTargetContext());
    }


}
