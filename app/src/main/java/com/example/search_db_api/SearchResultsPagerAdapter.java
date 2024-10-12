package com.example.search_db_api;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class SearchResultsPagerAdapter extends FragmentStateAdapter {

    private List<Pill> otcPills;
    private List<Pill> etcPills;

    public SearchResultsPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Pill> otcPills, List<Pill> etcPills) {
        super(fragmentActivity);
        this.otcPills = otcPills;
        this.etcPills = etcPills;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return SearchResultsFragment.newInstance(otcPills);  // 일반의약품 탭
        } else {
            return SearchResultsFragment.newInstance(etcPills);  // 전문의약품 탭
        }
    }

    @Override
    public int getItemCount() {
        return 2;  // 두 개의 탭 (일반의약품과 전문의약품)
    }
}
