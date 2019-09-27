package com.pratamatechnocraft.silaporanpenjualan.Model;

public class ListItemBarangTerjual {
    private String namaBarang,hargaJual,subTotalTerjual;
    private int jmlTerjual;

    public ListItemBarangTerjual(String namaBarang, String hargaJual, int jmlTerjual, String subTotalTerjual) {
        this.namaBarang = namaBarang;
        this.hargaJual = hargaJual;
        this.jmlTerjual = jmlTerjual;
        this.subTotalTerjual = subTotalTerjual;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public String getHargaJual() {
        return hargaJual;
    }

    public int getJmlTerjual() {
        return jmlTerjual;
    }

    public String getSubTotalTerjual() {
        return subTotalTerjual;
    }
}
