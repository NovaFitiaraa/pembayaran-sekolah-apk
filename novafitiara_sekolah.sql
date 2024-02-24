-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 24, 2023 at 10:32 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `novafitiara_sekolah`
--

-- --------------------------------------------------------

--
-- Table structure for table `logging_siswa`
--

CREATE TABLE `logging_siswa` (
  `id_logsis` int(12) NOT NULL,
  `status` varchar(50) NOT NULL,
  `tanggal-waktu` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `nama` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `logging_transaksi`
--

CREATE TABLE `logging_transaksi` (
  `id_logtrans` int(12) NOT NULL,
  `status` varchar(20) NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `id_transaksi` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `logging_transaksi`
--

INSERT INTO `logging_transaksi` (`id_logtrans`, `status`, `waktu`, `id_transaksi`) VALUES
(4, 'Update Data', '2023-11-24 01:27:23', 5),
(5, 'Tambah Data', '2023-11-24 01:38:22', 7),
(6, 'Tambah Data', '2023-11-24 08:10:59', 8),
(7, 'Hapus Data', '2023-11-24 08:11:50', 8),
(8, 'Tambah Data', '2023-11-24 08:14:44', 9),
(9, 'Update Data', '2023-11-24 08:14:52', 9),
(10, 'Tambah Data', '2023-11-24 09:07:44', 10),
(11, 'Update Data', '2023-11-24 09:10:58', 5),
(12, 'Hapus Data', '2023-11-24 09:11:04', 5);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id` int(12) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `level` enum('operator','admin','kepala_sekolah') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `id_siswa` int(12) NOT NULL,
  `nis_siswa` int(12) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `kelas` varchar(255) NOT NULL,
  `jenis_kelamin` enum('perempuan','laki-laki') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(12) NOT NULL,
  `id_keb` int(12) NOT NULL,
  `id_siswa` int(12) NOT NULL,
  `id_operator` int(12) NOT NULL,
  `tanggal_waktu` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `tempat` varchar(255) NOT NULL,
  `harga` varchar(255) NOT NULL,
  `uang_bayar` varchar(200) NOT NULL,
  `sisa_bayar` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_keb`, `id_siswa`, `id_operator`, `tanggal_waktu`, `tempat`, `harga`, `uang_bayar`, `sisa_bayar`) VALUES
(7, 2, 2, 2, '2023-11-24 02:38:22', 'Depok', '30000', '10000', '20000'),
(9, 2, 2, 9, '2023-11-24 09:14:44', 'Depok', '2000000', '1500000', '500000'),
(10, 3, 9, 0, '2023-11-24 10:07:44', 'Depok', '10000', '9000', '1000');

--
-- Triggers `transaksi`
--
DELIMITER $$
CREATE TRIGGER `add-tra` AFTER INSERT ON `transaksi` FOR EACH ROW INSERT INTO logging_transaksi (STATUS,id_transaksi) VALUES ('Tambah Data',new.id_transaksi)
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `del-tra` BEFORE DELETE ON `transaksi` FOR EACH ROW INSERT INTO logging_transaksi (STATUS,id_transaksi) VALUES ('Hapus Data',old.id_transaksi)
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `upd-tra` AFTER UPDATE ON `transaksi` FOR EACH ROW INSERT INTO logging_transaksi (STATUS,id_transaksi) VALUES ('Update Data',new.id_transaksi)
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `logging_siswa`
--
ALTER TABLE `logging_siswa`
  ADD PRIMARY KEY (`id_logsis`);

--
-- Indexes for table `logging_transaksi`
--
ALTER TABLE `logging_transaksi`
  ADD PRIMARY KEY (`id_logtrans`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`id_siswa`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `logging_siswa`
--
ALTER TABLE `logging_siswa`
  MODIFY `id_logsis` int(12) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `logging_transaksi`
--
ALTER TABLE `logging_transaksi`
  MODIFY `id_logtrans` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `siswa`
--
ALTER TABLE `siswa`
  MODIFY `id_siswa` int(12) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
