package android.hipster.githubclient.activities;

import android.hipster.githubclient.R;
import android.hipster.githubclient.components.ComponentsBuilder;
import android.hipster.githubclient.net.models.RepoData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.akashandroid90.imageletter.MaterialLetterIcon;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

@EActivity(R.layout.activity_repo_detail)
public class RepoDetailActivity extends AppCompatActivity {

    //TODO: Add loading from realm database of cached value

    private static final String REPO_DATA = "repo_data";

    @Extra(REPO_DATA)
    RepoData mRepoData;

    @ViewById(R.id.avatar)
    ImageView mAvatarImageView;

    @ViewById(R.id.name)
    TextView mNameTextView;

    @ViewById(R.id.user_name)
    TextView mUserNameTextView;

    @ViewById(R.id.description)
    TextView mDescriptionTextView;

    @ViewById(R.id.forks_icon)
    MaterialLetterIcon mForksIcon;

    @ViewById(R.id.watches_icon)
    MaterialLetterIcon mWatchesIcon;

    @Inject
    ImageLoader mImageLoader;

    @Inject
    DisplayImageOptions mDisplayImageOptions;

    @AfterViews
    void syncViews() {
        getSupportActionBar().setTitle(mRepoData.getFullName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mForksIcon.setLetter(String.valueOf(mRepoData.getForksCount()));
        mWatchesIcon.setLetter(String.valueOf(mRepoData.getWatchersCount()));

        mDescriptionTextView.setText(mRepoData.getDescription());
        mNameTextView.setText(mRepoData.getName());

        mUserNameTextView.setText(mRepoData.getOwner().getLogin());

        //TODO: Add progress bar
        mImageLoader.displayImage(mRepoData.getOwner().getAvatarUrl(), mAvatarImageView);
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
