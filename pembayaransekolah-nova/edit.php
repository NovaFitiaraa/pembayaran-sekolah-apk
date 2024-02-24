<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
    $id_transaksi = $_POST['id_transaksi'];
    $id_keb = $_POST['id_keb'];
    $id_siswa = $_POST['id_siswa'];
    $id_operator = $_POST['id_operator'];
    $tempat = $_POST['tempat'];
    $harga = $_POST['harga'];
    $uang_bayar = $_POST['uang_bayar'];
    $sisa_bayar = $harga-$uang_bayar;

require_once('koneksi.php');

$sql = "UPDATE transaksi SET id_keb = '$id_keb', id_siswa = '$id_siswa',id_operator = '$id_operator', tempat = '$tempat', harga = '$harga',uang_bayar = '$uang_bayar', sisa_bayar = '$sisa_bayar' WHERE id_transaksi = $id_transaksi;";

if(mysqli_query($con,$sql)){
    echo 'Berhasil Update Data';
}else{
    echo 'Gagal Update Data';
}

mysqli_close($con);
}


?>