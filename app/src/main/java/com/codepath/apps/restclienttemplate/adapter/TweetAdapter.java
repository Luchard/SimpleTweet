package com.codepath.apps.restclienttemplate.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import static java.security.AccessController.getContext;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by luchi on 2/23/2018.
 */

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {
    private List<Tweet> mTwests;
    Context context;
private TweetAdapterListener mListener;    
    public interface TweetAdapterListener{
        public void onItemSelected(View view, int position);
    }

    public TweetAdapter(List<Tweet> tweets){

        mTwests = tweets;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater =  LayoutInflater.from(context);
        View tweetView = inflater.inflate(R.layout.item_tweet, parent , false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return viewHolder;

    }
    public void clear() {

        mTwests.clear();

        notifyDataSetChanged();

    }



// Add a list of items -- change to type used

    public void addAll(List<Tweet> list) {

        mTwests.addAll(list);

        notifyDataSetChanged();

    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Tweet tweet = mTwests.get(position);
        holder.tvUsername.setText(tweet.user.name);
        holder.tvBody.setText(tweet.body);
        holder.tvCreated.setText(getRelativeTimeAgo(tweet.createdAt));

        Glide.with(context).load(tweet.user.profileImageUrl)
                .bitmapTransform(new RoundedCornersTransformation(getContext(), 30, 10))
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(holder.ivProfileImage);





/*        if (tweet.getTweet_media_type() != null && tweet.getTweet_media_url() != null) {

            if (tweet.getTweet_media_type().equals("video")) {

                holder.img_tweet.setVisibility(View.GONE);

                holder.video_tweet.setVisibility(View.VISIBLE);
                Uri vidUri = Uri.parse(tweet.getTweet_media_url());
                holder.video_tweet.setVideoURI(vidUri);
                holder.video_tweet.setBackgroundResource(R.drawable.tw__ic_tweet_photo_error_light);

                MediaController vidControl = new MediaController(getContext());
                vidControl.setAnchorView(holder.video_tweet);
                vidControl.setVisibility(View.GONE);
                holder.video_tweet.setMediaController(vidControl);
                holder.video_tweet.requestFocus();
                holder.video_tweet.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        holder.video_tweet.start();
                    }
                });

                holder.video_tweet.start();

            }else {

                holder.video_tweet.setVisibility(View.GONE);

                holder.img_tweet.setVisibility(View.VISIBLE);
                Glide.with(getContext())
                        .load(tweet.getTweet_media_url())
                        .into(holder.img_tweet);
            }

        }else {
            holder.img_tweet.setVisibility(View.GONE);
            holder.video_tweet.setVisibility(View.GONE);
        }*/
    }

    private Context getContext() {
        return context;
    }

    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
    @Override
    public int getItemCount() {
        return mTwests.size();
    }

    private OnItemClickListener listener;
    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivProfileImage;
        public TextView tvUsername;
        public TextView tvBody;
        public TextView tvCreated;
        public ImageView img_tweet;
        public VideoView video_tweet;

        public ViewHolder(final View itemView){
            super(itemView);
            ivProfileImage = (ImageView) itemView.findViewById(R.id.ivProfileImage);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUserName);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
            tvCreated = (TextView) itemView.findViewById(R.id.tvCreated);
            img_tweet = (ImageView) itemView.findViewById(R.id.tweet_adapter_img_tweet);
            video_tweet=(VideoView) itemView.findViewById(R.id.tweet_adapter_video_tweet);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                    // final Contact contact = (Contact)v.getTag();
                    //   if (contact != null) {

                    // Fire an intent when a contact is selected
                    // Pass contact object in the bundle and populate details activity.
                    // }
                }
            });
        }
    }
}
