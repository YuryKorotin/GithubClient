package android.hipster.githubclient.activities;

import android.hipster.githubclient.AuthManager;
import android.hipster.githubclient.R;
import android.hipster.githubclient.components.ComponentsBuilder;
import android.hipster.githubclient.fragments.RepoDataFragment;
import android.hipster.githubclient.fragments.RepoDataFragment_;
import android.hipster.githubclient.net.models.RepoData;
import android.hipster.githubclient.util.Preferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

@OptionsMenu(R.menu.main)
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RepoDataFragment.OnListFragmentInteractionListener {

    private static final int LOGIN_REQUEST_CODE = 100;
    private static final String CURRENT_FRAGMENT_TAG = "current_fragment_tag";
    private static final int REPOS_FRAGMENT_NUMBER = 0;

    @Inject
    AuthManager mAuthManager;

    @Inject
    Preferences mPreferences;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @ViewById(R.id.nav_view)
    NavigationView mNavigationView;

    @ViewById(R.id.main_content)
    ViewGroup mMainContentView;

    Fragment currentFragment;

    @InstanceState
    int mFragmentNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ComponentsBuilder.getApplicationComponent().inject(this);

        mFragmentNumber = mPreferences.getCurrentFragment();
    }

    @Override
    public void onStart() {
        super.onStart();

        if(!mAuthManager.isAppAuthorized()) {
            LoginActivity_.intent(this).startForResult(LOGIN_REQUEST_CODE);
        }
    }

    @AfterViews
    void syncViews() {
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        if(mFragmentNumber == REPOS_FRAGMENT_NUMBER) {
            currentFragment = RepoDataFragment_.instantiate(this, RepoDataFragment_.class.getName());
        }

        changeFrament(currentFragment);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @OptionsItem(R.id.action_settings)
    void openSettings() {
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment mainFragment = null;

        if (id == R.id.nav_repos) {
            mFragmentNumber = REPOS_FRAGMENT_NUMBER;

            mainFragment = RepoDataFragment_.instantiate(this, RepoDataFragment_.class.getName());

            mPreferences.setCurrentFragment(mFragmentNumber);
        }

        mDrawer.closeDrawer(GravityCompat.START);

        changeFrament(mainFragment);

        return true;
    }

    private void changeFrament(Fragment mainFragment) {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        //ft.setCustomAnimations(R.transition.slide_and_change_bounds, R.anim.window_exit, 0, R.anim.window_exit);
        if (currentFragment != null) {
            ft.remove(currentFragment);
        }
        ft.add(R.id.main_content, mainFragment, CURRENT_FRAGMENT_TAG);

        ft.addToBackStack(null);

        ft.commit();
    }

    @Override
    public void onListFragmentInteraction(RepoData item) {
        RepoDetailActivity_.intent(this).mRepoData(item).start();
    }
}
