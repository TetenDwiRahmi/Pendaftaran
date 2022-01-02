<?php
require_once('koneksi.php');
class emp{}

$nama = $_POST['nama'];
$alamat = $_POST['alamat'];
$hp = $_POST['hp'];
$jk = $_POST['jenis_kelamin'];
$lokasi = $_POST['lokasi_pendaftaran'];
$foto = $_POST['foto'];

$random = random_word(20);	
$path = $random.".jpg";
$sql = "INSERT INTO maba (nama, alamat, hp, jenis_kelamin, lokasi_pendaftaran, foto) VALUES ('$nama', '$alamat', '$hp', '$jk', '$lokasi', '$path')";

if (mysqli_query($conn,$sql)) {
	file_put_contents("upload/".$path,base64_decode($foto));
	$res = new emp();	
	$res->response = "Mahasiswa sudah terdaftar";
	$res->kode = 1;
	die(json_encode($res));
}else{
	$res = new emp();	
	$res->response = "Mahasiswa belum terdaftar";
	$res->kode = 1;
	die(json_encode($res));
}

function random_word($id = 20){
	$pool = '1234567890abcdefghijkmnpqrstuvwxyz';
	$word = '';
	for ($i = 0; $i < $id; $i++){
		$word .= substr($pool, mt_rand(0, strlen($pool) -1), 1);
	}
	return $word; 
}
?>

	