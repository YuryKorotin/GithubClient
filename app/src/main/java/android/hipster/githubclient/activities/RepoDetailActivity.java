package android.hipster.githubclient.activities;

import android.content.Context;
import android.hipster.githubclient.AuthManager;
import android.hipster.githubclient.R;
import android.hipster.githubclient.adapters.CommitItemRecyclerViewAdapter;
import android.hipster.githubclient.adapters.RepoItemRecyclerViewAdapter;
import android.hipster.githubclient.components.ComponentsBuilder;
import android.hipster.githubclient.fragments.RepoDataFragment;
import android.hipster.githubclient.net.models.CommitData;
import android.hipster.githubclient.net.models.CommitsList;
import android.hipster.githubclient.net.models.RepoData;
import android.hipster.githubclient.net.models.ReposList;
import android.hipster.githubclient.net.requests.AuthTokenRequest;
import android.hipster.githubclient.net.requests.CommitsRequest;
import android.hipster.githubclient.util.Consts;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.akashandroid90.imageletter.MaterialLetterIcon;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

@EActivity(R.layout.activity_repo_detail)
public class RepoDetailActivity extends AppCompatActivity implements RequestListener<CommitsList> {

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

    @ViewById(R.id.commits_list)
    RecyclerView mCommitList;

    @Inject
    ImageLoader mImageLoader;

    @Inject
    DisplayImageOptions mDisplayImageOptions;

    @Inject
    SpiceManager mSpiceManager;

    @Inject
    CommitsRequest mCommitsRequest;

    int mColumnCount = 2;

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

        mCommitsRequest.setRepoData(mRepoData);

        mSpiceManager.execute(mCommitsRequest, mRepoData.getId(), DurationInMillis.ALWAYS_RETURNED, this);
    }

    @OptionsItem(android.R.id.home)
    void onBack() {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ComponentsBuilder.getRepoDetailActivityComponent().inject(this);
    }

    @Override
    public void onStop() {
        if(mSpiceManager.isStarted()) {
            mSpiceManager.shouldStop();
        }
        super.onStop();
    }

    @Override
    public void onStart() {
        if(!mSpiceManager.isStarted()) {
            mSpiceManager.start(this);
        }
        super.onStart();
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        Snackbar.make(mAvatarImageView, spiceException.getLocalizedMessage(), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.ok), null).show();
    }

    @Override
    public void onRequestSuccess(CommitsList commitsList) {
        Context context = mCommitList.getContext();
        if (mColumnCount <= 1) {
            mCommitList.setLayoutManager(new LinearLayoutManager(context));
        } else {
            mCommitList.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        mCommitList.setAdapter(new CommitItemRecyclerViewAdapter(commitsList));
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(CommitData item);
    }
}
