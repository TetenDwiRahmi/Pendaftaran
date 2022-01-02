<?php
require_once('koneksi.php');
class emp{}

$cek = mysqli_query($conn, "SELECT * FROM maba");

$response = array();
if ($z = mysqli_num_rows($cek) > 0) {
		while ($x = mysqli_fetch_array($cek)) {
			$h['id'] = $x['id'];
			$h['nama'] = $x['nama'];
			$h['alamat'] = $x['alamat'];
			$h['hp'] = $x['hp'];
			$h['jenis_kelamin'] = $x['jenis_kelamin'];
			$h['lokasi_pendaftaran'] = $x['lokasi_pendaftaran'];
			$h['foto'] = $x['foto'];
				
			array_push($response, $h);
	}
		 echo strip_tags(json_encode($response));
	
	}else{
			$res = new emp();
			$res->kode = 0;
			$res->pesan = "Gagal";
			die(json_encode($res));
	}
	
?>