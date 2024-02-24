
<?php

$id_transaksi = $_GET['id_transaksi'];

require_once('koneksi.php');

$sql = "SELECT * FROM transaksi WHERE id_transaksi='$id_transaksi'";

$r = mysqli_query($con,$sql);


$result = array();
$row = mysqli_fetch_array($r);
array_push($result,array(
"id_transaksi"=>$row['id_transaksi'],
"id_keb"=>$row['id_keb'],
"id_siswa"=>$row['id_siswa'],
"id_operator"=>$row['id_operator'],
"tanggal_waktu"=>$row['tanggal_waktu'],
"tempat"=>$row['tempat'],
"harga"=>$row['harga'],
"uang_bayar"=>$row['uang_bayar'],
"sisa_bayar"=>$row['sisa_bayar']
));


echo json_encode(array('result'=>$result));

mysqli_close($con);
?>