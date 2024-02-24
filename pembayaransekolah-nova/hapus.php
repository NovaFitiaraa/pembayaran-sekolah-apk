<?php

$id_transaksi = $_GET['id_transaksi'];

require_once('koneksi.php');

$sql = "DELETE FROM transaksi WHERE id_transaksi=$id_transaksi;";

if(mysqli_query($con,$sql)){
    echo 'Berhasil Menghapus data';
}else{
    echo 'Gagal Menghapus data';
}

mysqli_close($con);

?>