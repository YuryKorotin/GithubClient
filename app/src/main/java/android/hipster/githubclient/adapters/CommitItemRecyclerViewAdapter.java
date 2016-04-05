package android.hipster.githubclient.adapters;

import android.hipster.githubclient.R;
import android.hipster.githubclient.fragments.RepoDataFragment;
import android.hipster.githubclient.net.models.CommitAuthor;
import android.hipster.githubclient.net.models.CommitData;
import android.hipster.githubclient.net.models.RepoData;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class CommitItemRecyclerViewAdapter extends RecyclerView.Adapter<CommitItemRecyclerViewAdapter.ViewHolder> {

    private final List<CommitData> mValues;
    private final DateFormat mDateFormat;

    public CommitItemRecyclerViewAdapter(List<CommitData> items) {
        mValues = items;
        mDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_commit_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        CommitAuthor author = holder.mItem.getCommit().getAuthor();

        holder.mShortMessageView.setText(holder.mItem.getCommit().getShortMessage());
        holder.mShaMessageView.setText(holder.mItem.getSha());
        holder.mAuthorNameView.setText(author.getName());
        holder.mDateView.setText(mDateFormat.format(author.getDate()));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        //public final TextView mIdView;
        public final TextView mShortMessageView;
        public final TextView mShaMessageView;
        public final TextView mAuthorNameView;
        public final TextView mDateView;
        public CommitData mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mIdView = (TextView) view.findViewById(R.id.id);
            mShortMessageView = (TextView) view.findViewById(R.id.short_message_textview);
            mShaMessageView = (TextView) view.findViewById(R.id.sha_textview);
            mAuthorNameView = (TextView) view.findViewById(R.id.author_name_textview);
            mDateView = (TextView) view.findViewById(R.id.date_textview);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mShaMessageView.getText() + "'";
        }
    }
}
