package com.aad_team_42.travelmanticsrebranded.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.aad_team_42.travelmanticsrebranded.R;
import com.aad_team_42.travelmanticsrebranded.model.Explore;
import com.aad_team_42.travelmanticsrebranded.views.activities.DetailActivity;
import com.aad_team_42.travelmanticsrebranded.views.activities.MainActivity;
import com.aad_team_42.travelmanticsrebranded.views.fragments.ExploreFragment;
import com.aad_team_42.travelmanticsrebranded.views.fragments.FavoriteFragment;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {
    private List<Explore> mExploreList = new ArrayList<>();
    private Context mContext;
    private ItemSelectedListener mSelectedListener;

    public void setExplore(List<Explore> exploreList) {
        mExploreList.addAll(exploreList);
        notifyDataSetChanged();
    }

    public void setItemSelectedListener(ItemSelectedListener selectedListener) {
        mSelectedListener = selectedListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_explore, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Explore explore = mExploreList.get(position);
        holder.bind(explore);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext.getApplicationContext(), DetailActivity.class);
                intent.putExtra("destination", explore.getDestination());
                intent.putExtra("image", explore.getImageUrl());
                intent.putExtra("price",explore.getPrice());
                intent.putExtra("about", explore.getAbout());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mExploreList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, detail, price;
        ImageView imageUrl, like;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.destination);
            detail = itemView.findViewById(R.id.tour_detail);
            imageUrl = itemView.findViewById(R.id.image_tour);
            price = itemView.findViewById(R.id.price);
            like = itemView.findViewById(R.id.like);


        }

        public void bind(final Explore explore) {
            title.setText(explore.getDestination());
            detail.setText(explore.getAbout());
            price.setText(explore.getPrice());
            Glide.with(mContext).load(explore.getImageUrl()).centerCrop().into(imageUrl);
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (MainActivity.pager.getCurrentItem() == 0) {
                        like.setImageResource(R.drawable.favorite_red);
                        mSelectedListener.selectedItem(explore);
                    }


                }
            });
        }


    }

    public interface ItemSelectedListener {
        void selectedItem(Explore explore);
    }

}
