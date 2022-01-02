-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 02 Jan 2022 pada 08.45
-- Versi server: 10.4.14-MariaDB
-- Versi PHP: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pendaftaran`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `maba`
--

CREATE TABLE `maba` (
  `id` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `alamat` text NOT NULL,
  `hp` varchar(15) NOT NULL,
  `jenis_kelamin` varchar(10) NOT NULL,
  `lokasi_pendaftaran` text NOT NULL,
  `foto` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `maba`
--

INSERT INTO `maba` (`id`, `nama`, `alamat`, `hp`, `jenis_kelamin`, `lokasi_pendaftaran`, `foto`) VALUES
(13, 'Teten', 'Padang', '0812345678', 'Wanita', 'Jl. Kalumpang No.8, Bandar Buat, Kec. Lubuk Kilangan, Kota Padang, Sumatera Barat 25157, Indonesia', 'a4civxe0ucxnjnysenbz.jpg'),
(17, 'Nanda', 'Jakarta Timur ', '081234566', 'Pria', 'Jl. Raya Bandar Buat No.42, RT.03/RW.03, Bandar Buat, Kec. Lubuk Kilangan, Kota Padang, Sumatera Barat 25231, Indonesia', 'mdjjaibb3xr3har8p3wh.jpg'),
(18, 'Putri', 'Bandung', '0812345678', 'Wanita', 'Jl. Raya Bandar Buat No.42, RT.03/RW.03, Bandar Buat, Kec. Lubuk Kilangan, Kota Padang, Sumatera Barat 25231, Indonesia', 't74krx95m812cgc25z73.jpg'),
(19, 'Lala', 'Padang', '0828328382', 'Pria', 'Padang', '5jnscdvtjrxidasg99mk.jpg');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `maba`
--
ALTER TABLE `maba`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `maba`
--
ALTER TABLE `maba`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
