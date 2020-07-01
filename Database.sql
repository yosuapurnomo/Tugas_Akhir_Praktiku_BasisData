-- Create TableSpace
create tablespace sensus
datafile 'F:\backup data e\DATA Yosua\Semester 4\Pratikum\Basdat\Modul 1\Tugas_Pratikum\sensus.dbf'
size 50m;

-- Create User
create user yosua_pratikum1
identified by yosua123
default tablespace sensus
quota 50m on sensus;

-- Memberikan Hak Akses Sepenuhnya
grant dba to yosua_pratikum1;

-- Membuat Tabel kartu_keluarga
create table kartu_keluarga (
No_kk varchar2(20),
Kode_pos varchar2(5),
Provinsi varchar2(20),
Kota varchar2(20),
Alamat varchar2(20),
constraint PK_no_kk primary key (No_kk)
);

-- Membuat Tabel data_warga_06979
create table data_warga_06979 (
Nik number(20),
No_kk varchar2(20),
Nama varchar2(20),
Status_keluarga varchar2(20),
Tanggal_lahir date,
Jenis_kelamin varchar2(10),
Umur integer,
No_telp Number(12),
constraint PK_nik primary key (Nik),
constraint FK_no_kk FOREIGN KEY (No_kk) REFERENCES kartu_keluarga (No_kk)
);

-- Membuat Tabel Pekerjaan_warga
create table pekerjaan_warga (
Nik number(20),
Nama_tempat_bekerja varchar2(100),
Jabatan varchar2(20),
Gaji_perbulan varchar2(20),
Tanggal_bergabung date,
constraint FK_Nik FOREIGN KEY (Nik) REFERENCES data_warga_06979 (Nik)
);

-- Membuat View Jumlah_Warga
create view jumlah_warga
AS select No_kk, Kota, Alamat, (select count(Nik) from data_warga_06979
where kartu_keluarga.No_kk = data_warga_06979.No_kk) AS Jumlah_orang
from kartu_keluarga order by No_kk desc;

-- Untuk Menambahkan Data Pada Tabel Kartu_keluarga
insert into kartu_keluarga (No_kk, Kode_pos, Provinsi, Kota, Alamat)
values ('123456789', 6914, 'Jawa Timur', 'Surabaya', 'Jl.Gunungsari');

-- Untuk Menambahkan Data Pada Tabel Data_warga_06979
insert into data_warga_06979 (Nik, No_kk, Nama, Status_keluarga, Tanggal_lahir, Jenis_kelamin, Umur, No_telp)
values (1, '345678912', 'Yuyun', 'Ibu', to_date('02/02/1999', 'dd/mm/yyyy'), 'Perempuan', 20, 08145678910);

-- Untuk Menambahkan Data Pada Tabel Pekerjaan_warga
insert into pekerjaan_warga (Nama_tempat_bekerja, Jabatan, Tanggal_bergabung, Nik, Gaji)
values ('PT. Gloria', 'Admin', to_date('16/11/2018','dd/mm/yyyy'), 1, 3200000);

-- Menampilkan Data Jumlah Orang Dengan Menerapkan Klaus dan Join
SELECT JUMLAH_WARGA.JUMLAH_ORANG, DATA_WARGA_06979.* 
FROM JUMLAH_WARGA JOIN DATA_WARGA_06979 ON JUMLAH_WARGA.NO_KK = DATA_WARGA_06979.NO_KK 
WHERE JUMLAH_WARGA.NO_KK = '789123456' ORDER BY JUMLAH_WARGA.NO_KK DESC;