package com.pratamatechnocraft.silaporanpenjualan.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.pratamatechnocraft.silaporanpenjualan.Drawable.BadgeDrawable;
import com.pratamatechnocraft.silaporanpenjualan.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

@SuppressLint("ValidFragment")
public class TabLayoutFragment extends Fragment {

    Integer jenisTransaksi;
    View view;
    Adapter adapter;
    NavigationView navigationView;

    public TabLayoutFragment(Integer jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new Adapter(getChildFragmentManager());
        if (jenisTransaksi==0){
            adapter.addFragment(new TransaksiFragment(0), "Penjualan");
//            adapter.addFragment(new TransaksiFragment(1,jenisTransaksi), "Piutang");
        }else {
            adapter.addFragment(new TransaksiFragment(0), "Pembelian");
//            adapter.addFragment(new TransaksiFragment(1,jenisTransaksi), "Hutang");
        }
        viewPager.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_layout_transaksi, container, false);
        navigationView = getActivity().findViewById( R.id.nav_view );
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this, view);
        final ViewPager viewPager = ButterKnife.findById(view, R.id.viewpager);
        setupViewPager(viewPager);
        TabLayout tabLayout = ButterKnife.findById(view, R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        if (navigationView.getMenu().findItem( R.id.nav_transaksi_penjualan ).isChecked()) {
            getActivity().setTitle("Transaksi Penjualan");
        }else {
            getActivity().setTitle("Transaksi Pembelian");
        }

        setHasOptionsMenu(true);

    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
