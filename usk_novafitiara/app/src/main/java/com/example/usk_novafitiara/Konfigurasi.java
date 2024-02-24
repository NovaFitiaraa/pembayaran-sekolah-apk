package com.example.usk_novafitiara;

public class Konfigurasi {
    public static final String TRANSAKSIS_ADD = "http://192.168.0.112/pembayaransekolah-nova/tambah.php";
    public static final String TRANSAKSIS_ALL = "http://192.168.0.112/pembayaransekolah-nova/tampil.php";
    public static final String HISTORY_ALL = "http://192.168.0.112/pembayaransekolah-nova/tampilhistor.php";
    public static final String TRANSAKSIS_UPDATE = "http://192.168.0.112/pembayaransekolah-nova/edit.php";
    public static final String TRANSAKSIS_HAPUS = "http://192.168.0.112/pembayaransekolah-nova/hapus.php?id_transaksi=";
    public static final String TRANSAKSIS_DETAILS = "http://192.168.0.112/pembayaransekolah-nova/detail.php?id_transaksi=";


    //PHP


    public static final String KEY_TRANSAKSI_IDTRANSAKSI = "id_transaksi";
    public static final String KEY_TRANSAKSI_IDKEB = "id_keb";
    public static final String KEY_TRANSAKSI_IDSISWA = "id_siswa";
    public static final String KEY_TRANSAKSI_IDOPERATOR = "id_operator";
    public static final String KEY_TRANSAKSI_TEMPAT = "tempat";
    public static final String KEY_TRANSAKSI_HARGA = "harga";
    public static final String KEY_TRANSAKSI_UANGBAYAR = "uang_bayar";


    //JSON Tags
    public static final String TAG_JSON_ARRAY = "result";

    public static final String TAG_IDTRANSAKSI = "id_transaksi";
    public static final String TAG_IDPKEB = "id_keb";
    public static final String TAG_IDSISWA = "id_siswa";
    public static final String TAG_IDOPERATOR = "id_operator";
    public static final String TAG_IDLOGTRANS = "id_logtrans";
    public static final String TAG_STATUS = "status";
    public static final String TAG_WAKTU = "waktu";
    public static final String TAG_IDTRANSLOG = "id_transaksi";
    public static final String TAG_TANGGALWAKTU = "tanggal_waktu";
    public static final String TAG_TEMPAT = "tempat";
    public static final String TAG_HARGA = "harga";
    public static final String TAG_UANGBAYAR = "uang_bayar";
    public static final String TAG_SISABAYAR = "sisa_bayar";

    public static final String EMP_ID = "id";
}
