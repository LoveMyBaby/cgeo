package cgeo.geocaching.maps;

import cgeo.geocaching.R;
import cgeo.geocaching.maps.interfaces.MapActivityImpl;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

/**
 * Base class for the map activity. Delegates base class calls to the
 * provider-specific implementation.
 */
public abstract class AbstractMap implements AppCompatCallback {

    MapActivityImpl mapActivity;
    private AppCompatDelegate mDelegate;

    protected AbstractMap(final MapActivityImpl activity) {
        mapActivity = activity;
    }

    public Resources getResources() {
        return mapActivity.getResources();
    }

    public Activity getActivity() {
        return mapActivity.getActivity();
    }

    public void onCreate(final Bundle savedInstanceState) {

        getDelegate().installViewFactory();
        mapActivity.superOnCreate(savedInstanceState);
        getDelegate().onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
//            mapActivity.getActivity().requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
//        }
    }

    public void onPostCreate(Bundle savedInstanceState) {
        mapActivity.superOnPostCreate(savedInstanceState);
        mDelegate.onPostCreate(savedInstanceState);
    }

    public void onResume() {
        mapActivity.superOnResume();
    }

    public void onPostResume() {
        mapActivity.superOnPostResume();
        getDelegate().onPostResume();
    }

    public void onStop() {
        mapActivity.superOnStop();
        getDelegate().onStop();
    }

    public void onPause() {
        mapActivity.superOnPause();
    }

    public void onDestroy() {
        mapActivity.superOnDestroy();
        getDelegate().onDestroy();
    }

    public boolean onCreateOptionsMenu(final Menu menu) {
        //final boolean result = getDelegate().onCreateOptionsMenu(menu);
        getDelegate().getMenuInflater().inflate(R.menu.map_activity, menu);
        return true;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        mapActivity.superOnConfigurationChanged(newConfig);

    }

    public boolean onPrepareOptionsMenu(final Menu menu) {
        return mapActivity.superOnPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(final MenuItem item) {
        return mapActivity.superOnOptionsItemSelected(item);
    }

    public abstract void onSaveInstanceState(final Bundle outState);

    /**
     * @return The {@link AppCompatDelegate} being used by this Activity.
     */
    public AppCompatDelegate getDelegate() {
        if (mDelegate == null) {
            mDelegate = AppCompatDelegate.create(getActivity(), this);
        }
        return mDelegate;
    }


    // TODO: Intercept also setTitle


}
