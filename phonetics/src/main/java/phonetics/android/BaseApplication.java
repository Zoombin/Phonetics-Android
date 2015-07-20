package phonetics.android;

import android.app.Application;

/**
 * Created by LSD on 2015/7/20.
 */
public class BaseApplication extends Application {
    private static Application app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static Application instance() {
        return app;
    }
}
