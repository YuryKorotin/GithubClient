package android.hipster.githubclient.activities;

import android.hipster.githubclient.R;
import android.hipster.githubclient.components.ComponentsBuilder;
import android.hipster.githubclient.net.models.RepoData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;

import javax.inject.Inject;

@EActivity(R.layout.activity_repo_detail)
public class RepoDetailActivity extends AppCompatActivity {

    //TODO: Add loading from realm database of cached value

    private static final String REPO_DATA = "repo_data";

    @Extra(REPO_DATA)
    RepoData mRepoData;

    @Inject
    ImageLoader mImageLoader;

    @Inject
    DisplayImageOptions mDisplayImageOptions;

    @AfterViews
    void syncViews() {
        getSupportActionBar().setTitle(mRepoData.getFullName());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @OptionsItem(android.R.id.home)
    void onBack() {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ComponentsBuilder.getApplicationComponent().inject(this);
    }
}
