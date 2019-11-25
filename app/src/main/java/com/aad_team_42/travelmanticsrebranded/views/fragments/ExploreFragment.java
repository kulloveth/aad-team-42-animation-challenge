package com.aad_team_42.travelmanticsrebranded.views.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aad_team_42.travelmanticsrebranded.ExploreViewModel;
import com.aad_team_42.travelmanticsrebranded.R;
import com.aad_team_42.travelmanticsrebranded.adapters.ExploreAdapter;
import com.aad_team_42.travelmanticsrebranded.model.Explore;
import com.aad_team_42.travelmanticsrebranded.utils.FirebaseUtils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ExploreAdapter mAdapter;
    private ChildEventListener mChildEventLisener;
    private ProgressBar progressBar;
    TextView tvError;

    public ExploreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);


        progressBar = view.findViewById(R.id.progressBar);
        tvError = view.findViewById(R.id.network_error);
        mAdapter = new ExploreAdapter();

        mRecyclerView = view.findViewById(R.id.recylerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        final ExploreViewModel exploreViewModel = ViewModelProviders.of(this).get(ExploreViewModel.class);
        mAdapter.setItemSelectedListener(new ExploreAdapter.ItemSelectedListener() {
            @Override
            public void selectedItem(Explore explore) {
                exploreViewModel.addExplore(explore);
            }
        });
        getData();


        return view;
    }

    private void getData() {
        mChildEventLisener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                List<Explore> exploreList = new ArrayList<>();
                Explore explore = dataSnapshot.getValue(Explore.class);
                exploreList.add(explore);
                // progressBar.setVisibility(View.GONE);
                mAdapter.setExplore(exploreList);
                if (mRecyclerView == null) {
                    progressBar.setVisibility(View.VISIBLE);
                    tvError.setVisibility(View.VISIBLE);

                } else {
                    progressBar.setVisibility(View.GONE);
                    tvError.setVisibility(View.INVISIBLE);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        FirebaseUtils.mRef.child("explore").addChildEventListener(mChildEventLisener);
    }
}
