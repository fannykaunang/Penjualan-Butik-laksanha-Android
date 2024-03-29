package com.pratamatechnocraft.silaporanpenjualan.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pratamatechnocraft.silaporanpenjualan.Service.SessionManager;
import com.pratamatechnocraft.silaporanpenjualan.R;

import java.util.HashMap;

public class DashboardFragment extends Fragment {

    private CardView kliktransaksijual, kliktransaksibeli, klikbarang, klikkategori, klikuser, kliklapharian, kliklapbulanan, kliklaptahunan, kliklaplabarugi, klikbiaya;
    NavigationView navigationView;
    SessionManager sessionManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_dashboard, container, false);
        kliktransaksijual = view.findViewById(R.id.cardhometransaksipenjualan);
//        kliktransaksibeli = view.findViewById(R.id.cardhometransaksipembelian);
        klikbarang = view.findViewById(R.id.cardhomebarang);
//        klikkategori = view.findViewById(R.id.cardhomekategori);
        klikuser = view.findViewById(R.id.cardhomeuser);
        kliklapharian = view.findViewById(R.id.cardhomelapharian);
        kliklapbulanan = view.findViewById(R.id.cardhomelapbulanan);
        kliklaptahunan = view.findViewById(R.id.cardhomelaptahunan);
//        kliklaplabarugi = view.findViewById(R.id.cardhomelaplabarugi);
//        klikbiaya = view.findViewById(R.id.cardhomebiaya);
        navigationView = getActivity().findViewById( R.id.nav_view );

        sessionManager = new SessionManager( getContext() );
        HashMap<String, String> user = sessionManager.getUserDetail();

        if (user.get(sessionManager.LEVEL_USER).equals("Administrator")) {
            kliktransaksijual.setVisibility(View.VISIBLE);
//            kliktransaksibeli.setVisibility(View.VISIBLE);
            klikbarang.setVisibility(View.VISIBLE);
//            klikkategori.setVisibility(View.VISIBLE);
            klikuser.setVisibility(View.VISIBLE);
            kliklapharian.setVisibility(View.VISIBLE);
            kliklapbulanan.setVisibility(View.VISIBLE);
            kliklaptahunan.setVisibility(View.VISIBLE);
//            kliklaplabarugi.setVisibility(View.VISIBLE);
//            klikbiaya.setVisibility(View.VISIBLE);
        }else{
            kliktransaksijual.setVisibility(View.VISIBLE);
//            kliktransaksibeli.setVisibility(View.GONE);
            klikbarang.setVisibility(View.GONE);
//            klikkategori.setVisibility(View.GONE);
            klikuser.setVisibility(View.GONE);
            kliklapharian.setVisibility(View.VISIBLE);
            kliklapbulanan.setVisibility(View.VISIBLE);
            kliklaptahunan.setVisibility(View.VISIBLE);
//            kliklaplabarugi.setVisibility(View.GONE);
//            klikbiaya.setVisibility(View.GONE);
        }

        kliktransaksijual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationView.setCheckedItem( R.id.nav_transaksi_penjualan );
//                TransaksiFragment fragment = new TransaksiFragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.cardhometransaksipenjualan, fragment);
//                fragmentTransaction.commit();

                TransaksiFragment fragment2 = new TransaksiFragment(0);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, fragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


//                TabLayoutFragment tabLayoutFragment = new TabLayoutFragment(0);
//
//                getFragmentManager().beginTransaction()
//                        .replace(R.id.screen_area, tabLayoutFragment )
//                        .addToBackStack(null)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .commit();
            }
        });

//        kliktransaksibeli.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                navigationView.setCheckedItem( R.id.nav_transaksi_pembelian );
//                TabLayoutFragment tabLayoutFragment = new TabLayoutFragment(1);
//
//                getFragmentManager().beginTransaction()
//                        .replace(R.id.screen_area, tabLayoutFragment )
//                        .addToBackStack(null)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .commit();
//            }
//        });

        klikbarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationView.setCheckedItem( R.id.nav_barang );
                DataBarangFragment dataBarangFragment = new DataBarangFragment();

                getFragmentManager().beginTransaction()
                        .replace(R.id.screen_area, dataBarangFragment )
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        });

        klikuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationView.setCheckedItem( R.id.nav_user );
                DataUserFragment dataUserFragment = new DataUserFragment();

                getFragmentManager().beginTransaction()
                        .replace(R.id.screen_area, dataUserFragment )
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        });

//        klikkategori.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                navigationView.setCheckedItem( R.id.nav_kategori_barang );
//                DataKategoriBarangFragment dataKategoriBarangFragment = new DataKategoriBarangFragment();
//
//                getFragmentManager().beginTransaction()
//                        .replace(R.id.screen_area, dataKategoriBarangFragment )
//                        .addToBackStack(null)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .commit();
//            }
//        });

//        klikbiaya.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navigationView.setCheckedItem( R.id.nav_biaya );
//                BiayaFragment biayaFragment = new BiayaFragment();
//
//                getFragmentManager().beginTransaction()
//                        .replace(R.id.screen_area, biayaFragment )
//                        .addToBackStack(null)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .commit();
//            }
//        } );

//        kliklaplabarugi.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navigationView.setCheckedItem( R.id.nav_laporan_labarugi );
//                LaporanLabaRugiFragment laporanLabaRugiFragment = new LaporanLabaRugiFragment();
//
//                getFragmentManager().beginTransaction()
//                        .replace(R.id.screen_area, laporanLabaRugiFragment )
//                        .addToBackStack(null)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .commit();
//            }
//        } );

        kliklapharian.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.setCheckedItem( R.id.nav_laporan_harian);
                LaporanFragment laporanFragment = new LaporanFragment(0);

                getFragmentManager().beginTransaction()
                        .replace(R.id.screen_area, laporanFragment)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        } );

        kliklapbulanan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.setCheckedItem( R.id.nav_laporan_bulanan);
                LaporanFragment laporanFragment = new LaporanFragment(1);

                getFragmentManager().beginTransaction()
                        .replace(R.id.screen_area, laporanFragment)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        } );

        kliklaptahunan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.setCheckedItem( R.id.nav_laporan_tahunan);
                LaporanFragment laporanFragment = new LaporanFragment(2);

                getFragmentManager().beginTransaction()
                        .replace(R.id.screen_area, laporanFragment)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        } );

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.app_name);

    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
