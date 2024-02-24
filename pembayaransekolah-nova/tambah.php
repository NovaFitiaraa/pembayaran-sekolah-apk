
<?php
require 'koneksi.php';

$id_keb = $_POST['id_keb'];
$id_siswa = $_POST['id_siswa'];
$id_operator = $_POST['id_operator'];
$tanggal_waktu = date("o:m:d H:i:s");
$tempat = $_POST['tempat'];
$harga = $_POST['harga'];
$uang_bayar = $_POST['uang_bayar'];
$sisa_bayar = $harga - $uang_bayar;

$query = mysqli_query($con,"INSERT INTO transaksi (id_keb,id_siswa,id_operator,tanggal_waktu,tempat,harga,uang_bayar,sisa_bayar) VALUES('$id_keb','$id_siswa','$id_operator','$tanggal_waktu','$tempat','$harga','$uang_bayar','$sisa_bayar')");

if($query){
    $data['status'] = true;
    $data['result'] = "Berhasil menambah data";
} else {
    $data['status'] = false;
    $data['result'] = "Gagal menambah data";
}

echo json_encode($data);
?>
