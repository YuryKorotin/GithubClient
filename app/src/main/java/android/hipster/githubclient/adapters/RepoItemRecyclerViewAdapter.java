package android.hipster.githubclient.adapters;

import android.hipster.githubclient.R;
import android.hipster.githubclient.fragments.RepoDataFragment;
import android.hipster.githubclient.listeners.OnBadgeChangeListener;
import android.hipster.githubclient.net.models.RepoData;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RepoItemRecyclerViewAdapter extends RecyclerView.Adapter<RepoItemRecyclerViewAdapter.ViewHolder> {

    private final List<RepoData> mValues;
    private final RepoDataFragment.OnListFragmentInteractionListener mListener;
    private final OnBadgeChangeListener mOnBadgeChangeListener;

    public RepoItemRecyclerViewAdapter(List<RepoData> items, RepoDataFragment.OnListFragmentInteractionListener listener, OnBadgeChangeListener onBadgeChangeListener) {
        mValues = items;
        mListener = listener;
        mOnBadgeChangeListener = onBadgeChangeListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_repodata, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //holder.mIdView.setText(String.valueOf(mValues.get(position).getId()));
        holder.mContentView.setText(mValues.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mOnBadgeChangeListener != null && mValues.size() > 0) {
            mOnBadgeChangeListener.onBadgeCountChange(0, mValues.size());
        }
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        //public final TextView mIdView;
        public final TextView mContentView;
        public RepoData mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
