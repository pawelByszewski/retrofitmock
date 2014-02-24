package eu.softisland.retrofit.ui.main;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import eu.softisland.retrofit.R;
import eu.softisland.retrofit.config.dagger.Injector;
import eu.softisland.retrofit.model.login.EchoResponse;
import eu.softisland.retrofit.rest.echo.EchoService;

import javax.inject.Inject;
import javax.inject.Named;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getName();

    @Inject
    @Named("mockService")
    EchoService mockedEchoService;

    @Inject
    @Named("realService")
    EchoService realEchoService;

    @InjectView(R.id.responseTextView)
    protected TextView responseTextView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Injector.inject(this);
    }

    @OnClick(R.id.button_mock_service)
    void onClickDevConfig() {
        QueryTask queryTask = new QueryTask(mockedEchoService);
        queryTask.execute();
    }

    @OnClick(R.id.button_real_service)
    void onClickDevConfigf() {
        QueryTask queryTask = new QueryTask(realEchoService);
        queryTask.execute();
    }

    private class QueryTask extends AsyncTask<Void, Void, EchoResponse> {

        private static final String MESSAGE = "example";
        private static final int QUANTITY = 32;

        private EchoService echoService;

        private QueryTask(EchoService echoService) {
            this.echoService = echoService;
        }

        @Override
        protected EchoResponse doInBackground(Void... params) {
            try {
                return echoService.getMessageAndQuantity(MESSAGE, QUANTITY);
            } catch (Exception e) {
                Log.e(TAG, "Some error " + e.getMessage(), e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(EchoResponse echoResponse) {
            String responseText = echoResponse != null ? echoResponse.toString() : "Fail to get response, check logs.";
            responseTextView.setText(responseText);
        }
    }
}
