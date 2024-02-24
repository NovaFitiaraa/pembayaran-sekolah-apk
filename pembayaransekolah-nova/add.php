<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="tambah.php" method="post">
        <input type="number" name="id_keb">
        <input type="number" name="id_siswa">
        <input type="number" name="id_operator">
        <input type="text" name="tempat">
        <div>
                <label for="harga"> Nominal Pembayaran :</label>
                <input class="form-control" type="number" name="harga" id="harga" value="200000">
            </div>
            <br>
    <div>
                <label for="uang_bayar">Uang Bayar :</label>
                <input class="form-control" type="number" name="uang_bayar" id="uang_bayar">
            </div>
            <br>


            <button type="submit">sub</button>
    </form>
</body>
</html>