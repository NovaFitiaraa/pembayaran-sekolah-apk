<?php


require 'koneksi.php';

$query = mysqli_query($con, "SELECT * FROM logging_transaksi");
while($r = mysqli_fetch_assoc($query)){
    $rows[] = $r;
}

$data['status'] = true;
$data['result'] = $rows;

echo json_encode($data);

?>