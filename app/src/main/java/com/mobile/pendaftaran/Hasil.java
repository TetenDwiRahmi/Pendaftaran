package com.mobile.pendaftaran;

public class Hasil {
    private int id;
    private String nama;
    private String alamat;
    private String hp;
    private String jenis_kelamin;
    private String lokasi_pendaftaran;
    private String foto;

    public Hasil(int id, String nama, String alamat, String hp, String jenis_kelamin, String lokasi_pendaftaran, String foto) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.hp = hp;
        this.jenis_kelamin = jenis_kelamin;
        this.lokasi_pendaftaran = lokasi_pendaftaran;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getHp() {
        return hp;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public String getLokasi_pendaftaran() {
        return lokasi_pendaftaran;
    }

    public String getFoto() {
        return foto;
    }
}
