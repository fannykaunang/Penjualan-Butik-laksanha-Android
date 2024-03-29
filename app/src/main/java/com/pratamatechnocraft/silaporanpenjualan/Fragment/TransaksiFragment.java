package com.pratamatechnocraft.silaporanpenjualan.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pratamatechnocraft.silaporanpenjualan.Adapter.AdapterRecycleViewDataTransaksi;
import com.pratamatechnocraft.silaporanpenjualan.Adapter.DBDataSourceKeranjang;
import com.pratamatechnocraft.silaporanpenjualan.Model.BaseUrlApiModel;
import com.pratamatechnocraft.silaporanpenjualan.Model.ListItemTransaksi;
import com.pratamatechnocraft.silaporanpenjualan.R;
import com.pratamatechnocraft.silaporanpenjualan.Service.SessionManager;
import com.pratamatechnocraft.silaporanpenjualan.TransaksiBaruActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

@SuppressLint("ValidFragment")
public class TransaksiFragment extends Fragment{

    Integer menuTab;
    private RecyclerView recyclerViewDataTransaksi;
    private AdapterRecycleViewDataTransaksi adapterDataTransaksi;
    LinearLayout noDataTransaksi, koneksiDataTransaksi;
    SwipeRefreshLayout refreshDataTransaksi;
    ProgressBar progressBarDataTransaksi;
    Button cobaLagiDataTransaksi;
    FloatingActionButton fabTransaksiBaru;
    NavigationView navigationView;
    private DBDataSourceKeranjang dbDataSourceKeranjang;

    private List<ListItemTransaksi> listItemTransaksis;

    BaseUrlApiModel baseUrlApiModel = new BaseUrlApiModel();
    private String baseUrl=baseUrlApiModel.getBaseURL();
    private static String API_URL ="";
    private Boolean statusFragment = false;

    public TransaksiFragment(Integer menuTab) {
        this.menuTab = menuTab;
    }

    @SuppressLint("RestrictedApi")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_transaksi, container, false);
        navigationView = getActivity().findViewById( R.id.nav_view );
        noDataTransaksi = view.findViewById( R.id.noDataTransaksi );
        refreshDataTransaksi = (SwipeRefreshLayout) view.findViewById(R.id.refreshDataTransaksi);
        cobaLagiDataTransaksi = view.findViewById( R.id.cobaLagiTransaksi );
        koneksiDataTransaksi = view.findViewById( R.id.koneksiDataTransaksi );
        fabTransaksiBaru = view.findViewById( R.id.fabTransaksiBaru );
        progressBarDataTransaksi = view.findViewById( R.id.progressBarDataTransaksi );

        recyclerViewDataTransaksi = (RecyclerView) view.findViewById(R.id.recycleViewDataTransaksi);

        if (menuTab==0){
            fabTransaksiBaru.setVisibility( View.VISIBLE );
        }else {
            fabTransaksiBaru.setVisibility( View.GONE );
        }

        fabTransaksiBaru.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                dbDataSourceKeranjang = new DBDataSourceKeranjang( getContext() );
//                dbDataSourceKeranjang.open();
//                dbDataSourceKeranjang.deleteAll();
//                Intent i = new Intent(getContext(), TransaksiBaruActivity.class );
//                if (jenisTransaksi==0) {
//                    i.putExtra( "type", "0" );
//                }else {
//                    i.putExtra( "type", "1" );
//                }
//                startActivity(i);
            }
        } );

        refreshDataTransaksi.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listItemTransaksis.clear();
                loadDataTransaksi();
//                if (jenisTransaksi==0){
//                    if (menuTab==0){
//                        loadDataTransaksi("penjualan");
//                    }else {
//                        loadDataTransaksi("piutang");
//                    }
//                }else{
//                    if (menuTab==0){
//                        loadDataTransaksi("pembelian");
//                    }else {
//                        loadDataTransaksi("utang");
//                    }
//                }
            }
        } );

        cobaLagiDataTransaksi.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                koneksiDataTransaksi.setVisibility( View.GONE );
                progressBarDataTransaksi.setVisibility( View.VISIBLE );
                loadDataTransaksi();
//                if (jenisTransaksi==0){
//                    if (menuTab==0){
//                        loadDataTransaksi("penjualan");
//                    }else {
//                        loadDataTransaksi("piutang");
//                    }
//                }else{
//                    if (menuTab==0){
//                        loadDataTransaksi("pembelian");
//                    }else {
//                        loadDataTransaksi("utang");
//                    }
//                }
            }
        } );

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        setHasOptionsMenu( true );
        getActivity().setTitle("Transaksi Penjualan");

        loadDataTransaksi();

//        if (jenisTransaksi==0) {
//            getActivity().setTitle("Transaksi Penjualan");
//            if (menuTab==0) {
//                loadDataTransaksi("penjualan");
//            } else {
//                loadDataTransaksi("piutang");
//            }
//        } else {
//            getActivity().setTitle("Trasaksi Pembelian");
//            if (menuTab==0){
//                loadDataTransaksi("pembelian");
//            }else {
//                loadDataTransaksi("utang");
//            }
//        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.ic_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapterDataTransaksi.getFilter().filter(s);
                return false;
            }
        } );
        searchView.setQueryHint("Cari: No Invoice, Tanggal, Catatan");
    }

//    private String getTanggal(String tgl) {
//        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
//        final TimeZone UTC;
//        UTC = TimeZone.getTimeZone("UTC");
//        sdf.setTimeZone(UTC);
//        return sdf.format(new Date(tgl));
//
//
//////        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss"); // your format
////        try {
//////            SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
//////                    Locale.ENGLISH);
////
////            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss",
////                    Locale.ENGLISH);
////            Date date = format.parse(tgl);
////            SimpleDateFormat print = new SimpleDateFormat("MMM d, yyyy HH:mm:ss");
////
//////            String formattedDate = format.format(date);
////            return print.format(date);
////        } catch (java.text.ParseException e) {
////            // TODO Auto-generated catch block
////            e.printStackTrace();
////            return null;
////        }
//    }

    private void loadDataTransaksi(){
        listItemTransaksis = new ArrayList<>();
//        if (jenis.equals( "penjualan" )) {
//            API_URL = "PenjualanTransaksi/GetAll";
//        } else if (jenis.equals( "piutang" )) {
//            API_URL = "PenjualanTransaksi/GetAll";
//        } else if (jenis.equals( "pembelian" )) {
//            API_URL = "api/transaksi?api=pembelian";
//        } else if (jenis.equals( "utang" )) {
//            API_URL = "api/transaksi?api=utang";
//        }

        StringRequest stringRequest = new StringRequest( Request.Method.GET, baseUrl+"PenjualanTransaksi",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
//                        JSONObject jsonObject = new JSONObject(response);
                        listItemTransaksis.clear();
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = (JSONObject) jsonArray.get(i);
                            if (jo.getInt( "JUMLAH_DATA" )==0){
                                noDataTransaksi.setVisibility( View.VISIBLE );
                            }else{
                                noDataTransaksi.setVisibility( View.GONE );
                                //                                JSONObject transaksiobject = jsonObject.getJSONObject( i );
//                                JSONObject jo = (JSONObject) jsonObject.get(i);
//                                String jenis;
//                                if(jo.getString( "jenis_transaksi" ).equals( "0" )){
//                                    jenis="PL";
//                                }else{
//                                    jenis="PB";
//                                }

                                ListItemTransaksi listItemTransaksi = new ListItemTransaksi(
                                        jo.getString( "INVOICE_PENJUALAN"),
                                        jo.getString( "NAMA_BARANG"),
                                        jo.getString( "TANGGAL_PENJUALAN" ) + " " + jo.getString( "JAM_PENJUALAN" ),
                                        jo.getString( "JAM_PENJUALAN" ),
                                        Float.valueOf(jo.getString("HARGA_JUAL")),
                                        jo.getInt( "STOK" ),
                                        "Rp. " + jo.getString( "TOTAL_BAYAR" ),
                                        jo.getString( "KETERANGAN" ),
                                        jo.getInt( "JUMLAH_BELI" )
                                );
                                listItemTransaksis.add( listItemTransaksi );


//                            JSONArray data = jsonObject.getJSONArray("data");
//                                for (int ii = 0; ii<jo.length(); ii++){
////                                JSONObject transaksiobject = jsonObject.getJSONObject( i );
////                                JSONObject jo = (JSONObject) jsonObject.get(i);
//                                    String jenis;
//                                    if(jo.getString( "jenis_transaksi" ).equals( "0" )){
//                                        jenis="PL";
//                                    }else{
//                                        jenis="PB";
//                                    }
//                                    ListItemTransaksi listItemTransaksi = new ListItemTransaksi(
//                                            "#"+jenis+jo.getString( "kd_transaksi"),
//                                            "Rp. "+jo.getString( "harga_total" ),
//                                            jo.getString( "tgl_transaksi" ),
//                                            jo.getString("catatan")
//                                    );
//
//                                    listItemTransaksis.add( listItemTransaksi );
//                                }
                            }
                        }



//                        if (jsonArray.getInt( "jml_data" )==0){
//                            noDataTransaksi.setVisibility( View.VISIBLE );
//                        }else{
//                            noDataTransaksi.setVisibility( View.GONE );
////                            JSONArray data = jsonObject.getJSONArray("data");
//                            for (int i = 0; i<jsonObject.length(); i++){
////                                JSONObject transaksiobject = jsonObject.getJSONObject( i );
////                                JSONObject jo = (JSONObject) jsonObject.get(i);
//                                String jenis;
//                                if(jsonObject.getString( "jenis_transaksi" ).equals( "0" )){
//                                    jenis="PL";
//                                }else{
//                                    jenis="PB";
//                                }
//                                ListItemTransaksi listItemTransaksi = new ListItemTransaksi(
//                                        "#"+jenis+jsonObject.getString( "kd_transaksi"),
//                                        "Rp. "+jsonObject.getString( "harga_total" ),
//                                        jsonObject.getString( "tgl_transaksi" ),
//                                        jsonObject.getString("catatan")
//                                );
//
//                                listItemTransaksis.add( listItemTransaksi );
//                            }
//                        }
                        refreshDataTransaksi.setRefreshing(false);
                        progressBarDataTransaksi.setVisibility( View.GONE );
                        koneksiDataTransaksi.setVisibility( View.GONE);
                        setUpRecycleView();
                    }catch (JSONException e){
                        e.printStackTrace();
                        refreshDataTransaksi.setRefreshing(false);
                        progressBarDataTransaksi.setVisibility( View.GONE );
                        noDataTransaksi.setVisibility( View.GONE );
                        setUpRecycleView();
                        listItemTransaksis.clear();
                        adapterDataTransaksi.notifyDataSetChanged();
                        koneksiDataTransaksi.setVisibility( View.VISIBLE );
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    refreshDataTransaksi.setRefreshing( false );
                    progressBarDataTransaksi.setVisibility( View.GONE );
                    noDataTransaksi.setVisibility( View.GONE );
                    setUpRecycleView();
                    listItemTransaksis.clear();
                    adapterDataTransaksi.notifyDataSetChanged();
                    koneksiDataTransaksi.setVisibility( View.VISIBLE );
                    Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                }
            }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("user", "user");
                params.put("pass", "pass");
                params.put("api", "login");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue( getContext() );
        requestQueue.add( stringRequest );
    }

    private void setUpRecycleView(){
        recyclerViewDataTransaksi.setHasFixedSize(true);
        recyclerViewDataTransaksi.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterDataTransaksi = new AdapterRecycleViewDataTransaksi( listItemTransaksis, getContext());
        recyclerViewDataTransaksi.setAdapter( adapterDataTransaksi );
        adapterDataTransaksi.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        statusFragment=true;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDataTransaksi();

//        if (jenisTransaksi==0) {
//            if (menuTab==0){
//                if (statusFragment){
//                    loadDataTransaksi("penjualan");
//                }
//            }else {
//                if (statusFragment){
//                    loadDataTransaksi("piutang");
//                }
//            }
//        }else {
//            if (menuTab==0){
//                if (statusFragment){
//                    loadDataTransaksi("pembelian");
//                }
//            }else {
//                if (statusFragment){
//                    loadDataTransaksi("utang");
//                }
//            }
//        }

    }
}
