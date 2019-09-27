package com.pratamatechnocraft.silaporanpenjualan.Model;

public class BaseUrlApiModel {
    //private String baseURL="http://192.168.1.7/proyek/Si_Laporan_Penjualan/index.php/";
//    private static final String baseURL="http://192.168.1.8:86/api/";
    private static final String baseURL="http://192.168.43.110:86/api/";
//    private String baseURL="http://192.168.43.110:86/api/";
//    private static final String baseURL="http://172.16.17.51:86/api/";

    public static String getBaseURL() {
        return baseURL;
    }

}