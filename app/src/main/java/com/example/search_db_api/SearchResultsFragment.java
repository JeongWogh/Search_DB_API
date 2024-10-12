package com.example.search_db_api;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsFragment extends Fragment {

    private static final String ARG_PILL_LIST = "pill_list";
    private List<Pill> pillList;

    public static SearchResultsFragment newInstance(List<Pill> pills) {
        SearchResultsFragment fragment = new SearchResultsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PILL_LIST, new ArrayList<>(pills));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pillList = getArguments().getParcelableArrayList(ARG_PILL_LIST);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_results, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PillAdapter adapter = new PillAdapter(pillList);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
