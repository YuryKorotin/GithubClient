package android.hipster.githubclient.activities;

import android.hipster.githubclient.AuthManager;
import android.hipster.githubclient.R;
import android.hipster.githubclient.components.ComponentsBuilder;
import android.hipster.githubclient.fragments.RepoDataFragment;
import android.hipster.githubclient.fragments.RepoDataFragment_;
import android.hipster.githubclient.net.models.RepoData;
import android.hipster.githubclient.util.Preferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity
        implements RepoDataFragment.OnListFragmentInteractionListener,
        OnMenuTabClickListener {

    private static final int LOGIN_REQUEST_CODE = 100;
    private static final String CURRENT_FRAGMENT_TAG = "current_fragment_tag";
    private static final int REPOS_FRAGMENT_NUMBER = 0;

    @Inject
    AuthManager mAuthManager;

    @Inject
    Preferences mPreferences;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.main_coordinator)
    CoordinatorLayout mMainCoordinator;

    //@ViewById(R.id.scrolling_content)
    //NestedScrollView mNestedScrollView;

    @ViewById(R.id.main_content)
    ViewGroup mMainContentView;

    Fragment currentFragment;

    private BottomBar mBottomBar;

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
    }

    //TODO: Add adapter for navigation and render of user data to navigation drawer
    //TODO: ADd material icon library https://github.com/code-mc/material-icon-lib

    @AfterViews
    void syncViews() {

        setSupportActionBar(mToolbar);

        if(mFragmentNumber == REPOS_FRAGMENT_NUMBER) {
            currentFragment = RepoDataFragment_.instantiate(this, RepoDataFragment_.class.getName());
        }

        changeFrament(currentFragment);

        //mBottomBar = BottomBar.attachShy(mMainCoordinator, mNestedScrollView, getIntent().getExtras());

        mBottomBar = BottomBar.attach(this, getIntent().getExtras());

        mBottomBar.setItemsFromMenu(R.menu.main_bottom_menu, this);
    }

    private void changeFrament(Fragment mainFragment) {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        //ft.setCustomAnimations(R.transition.slide_and_change_bounds, R.anim.window_exit, 0, R.anim.window_exit);
        if (currentFragment != null) {
            ft.remove(currentFragment);
        }
        ft.add(R.id.main_content, mainFragment, CURRENT_FRAGMENT_TAG);

        ft.commit();
    }

    @Override
    public void onListFragmentInteraction(RepoData item) {
        RepoDetailActivity_.intent(this).mRepoData(item).start();
    }

    @Override
    public void onMenuTabSelected(@IdRes int menuItemId) {
        Fragment mainFragment = null;
        if (menuItemId == R.id.bottom_bar_item_repos) {
            mFragmentNumber = REPOS_FRAGMENT_NUMBER;

            mainFragment = RepoDataFragment_.instantiate(this, RepoDataFragment_.class.getName());

            mPreferences.setCurrentFragment(mFragmentNumber);
        }
        changeFrament(mainFragment);
    }

    @Override
    public void onMenuTabReSelected(@IdRes int menuItemId) {
        Fragment mainFragment = null;
        if (menuItemId == R.id.bottom_bar_item_repos) {
                mFragmentNumber = REPOS_FRAGMENT_NUMBER;

                mainFragment = RepoDataFragment_.instantiate(this, RepoDataFragment_.class.getName());

                mPreferences.setCurrentFragment(mFragmentNumber);
        }
        changeFrament(mainFragment);
    }
}
