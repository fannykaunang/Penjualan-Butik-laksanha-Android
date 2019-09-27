package com.pratamatechnocraft.silaporanpenjualan.Model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ListItemTransaksi {
    private int KODE_PENJUALAN;
    private String INVOICE_PENJUALAN;
    private int KODE_PENERIMAAN;
    private String INVOICE_PENERIMAAN;
    private int ID_BARANG;
    private String KODE_BARANG;
    private String NAMA_BARANG;
    private String KATEGORI_BARANG;
    private String BARCODE_FILEPATH;
    private String NAMA_SUPPLIER;
    private String NAMA_USER;
    private String TANGGAL_MASUK;
    private int STOK;
    private String HARGA_BELI;
    private String TOTAL_HARGA;
    private String NAMA_KONSUMEN;
    private String TANGGAL_PENJUALAN;
    private String JAM_PENJUALAN;
    private Float HARGA_JUAL;
    private int JUMLAH_BELI;
    private String TOTAL_BAYAR;
    private String PERIODE;
    private String KETERANGAN;


//    private String noInvoice;
//    private String totalHarga;
//    private String tanggalTransaksi;
//    private String catatan;

    public ListItemTransaksi(String INVOICE_PENJUALAN, String NAMA_BARANG, String TANGGAL_PENJUALAN, String JAM_PENJUALAN, Float HARGA_JUAL, int STOK, String TOTAL_BAYAR, String KETERANGAN, int JUMLAH_BELI) {
        this.INVOICE_PENJUALAN = INVOICE_PENJUALAN;
        this.NAMA_BARANG = NAMA_BARANG;
        this.TANGGAL_PENJUALAN = TANGGAL_PENJUALAN;
        this.JAM_PENJUALAN = JAM_PENJUALAN;
        this.HARGA_JUAL = HARGA_JUAL;
        this.STOK = STOK;
        this.TOTAL_BAYAR = TOTAL_BAYAR;
        this.KETERANGAN = KETERANGAN;
        this.JUMLAH_BELI = JUMLAH_BELI;
    }

    public String getINVOICE_PENJUALAN() {
        return INVOICE_PENJUALAN;
    }

    public String getNAMA_BARANG() {
        return NAMA_BARANG;
    }

    public String getTANGGAL_PENJUALAN() {
        return TANGGAL_PENJUALAN;
    }
    public String getJAM_PENJUALAN() {
        return JAM_PENJUALAN;
    }

    public Float getHARGA_JUAL() {
        return HARGA_JUAL;
    }
    public int getSTOK() {
        return STOK;
    }
    public String getTOTAL_BAYAR() {
        return TOTAL_BAYAR;
    }
    public String getKETERANGAN() {
        return KETERANGAN;
    }
    public int getJUMLAH_BELI() {
        return JUMLAH_BELI;
    }
}
