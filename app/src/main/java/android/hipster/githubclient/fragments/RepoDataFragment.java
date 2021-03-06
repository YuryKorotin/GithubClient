package android.hipster.githubclient.fragments;

import android.content.Context;
import android.hipster.githubclient.AuthManager;
import android.hipster.githubclient.activities.MainActivity;
import android.hipster.githubclient.adapters.RepoItemRecyclerViewAdapter;
import android.hipster.githubclient.components.ComponentsBuilder;
import android.hipster.githubclient.listeners.OnBadgeChangeListener;
import android.hipster.githubclient.net.models.RepoData;
import android.hipster.githubclient.net.models.ReposList;
import android.hipster.githubclient.net.requests.AuthTokenRequest;
import android.hipster.githubclient.net.requests.ReposRequest;
import android.hipster.githubclient.util.Consts;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;
import android.hipster.githubclient.R;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.roughike.bottombar.BottomBarBadge;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import javax.inject.Inject;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
@EFragment(R.layout.fragment_repodata_list)
public class RepoDataFragment extends Fragment implements RequestListener<ReposList>, OnBadgeChangeListener  {

    private static final String ARG_COLUMN_COUNT = "column-count";

    private OnListFragmentInteractionListener mListener;

    @Inject
    SpiceManager mSpiceManager;

    @Inject
    ReposRequest mReposRequest;

    @Inject
    AuthManager mAuthManager;

    @FragmentArg(ARG_COLUMN_COUNT)
    int mColumnCount = 2;

    @ViewById(R.id.list)
    ViewGroup mRootView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RepoDataFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        ComponentsBuilder.getReposDataFragmentComponent().inject(this);
    }

    @AfterViews
    void syncViews() {
        if(mAuthManager.isAppAuthorized()) {
            mSpiceManager.execute(mReposRequest, Consts.REPOS_CACHE_KEY, DurationInMillis.ALWAYS_RETURNED, this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(mAuthManager.isAppAuthorized()) {
            mSpiceManager.execute(mReposRequest, Consts.REPOS_CACHE_KEY, DurationInMillis.ALWAYS_RETURNED, this);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        Snackbar.make(mRootView, spiceException.getLocalizedMessage(), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.ok), null).show();
    }

    @Override
    public void onRequestSuccess(ReposList reposList) {
        if (mRootView instanceof RecyclerView) {
            Context context = mRootView.getContext();
            RecyclerView recyclerView = (RecyclerView) mRootView;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new RepoItemRecyclerViewAdapter(reposList, mListener, this));
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(RepoData item);
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
            mSpiceManager.start(getActivity());
        }
        super.onStart();
    }

    @Override
    public void onBadgeCountChange(int badgePosition, int value) {
        MainActivity activity = (MainActivity) getActivity();
        if(activity != null) {
            activity.showBadge(badgePosition, value);
        }
    }
}
