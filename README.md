# 🏗️ PT XYZ Data Warehouse – README

> **Project:** Perancangan Data Warehouse Industri Pertambangan PT XYZ  
> **Tech Stack:** Docker, Airflow, SQL Server, Grafana, Superset, Metabase, Jupyter

---

## 📁 Struktur Folder Proyek

```
Perancangan-Data-Warehouse-Industri-Pertambangan-PT.XYZ/
│
├── dags/                     # Folder untuk DAG & skrip ETL
│   └── standalone_etl.py
│
├── data/
│   └── raw/
│       └── Dataset/          # Berisi file CSV sumber data
│           ├── dataset_production.csv
│           ├── dataset_employee.csv
│           └── dataset_maintenance.csv
│
├── init-scripts/             # Skrip inisialisasi database
│   ├── init-db.sh
│   └── create-schema.sql
│
├── tests/                    # Folder validasi otomatis
│   └── validate.sql
│
├── .env.example              # Template environment variables
├── docker-compose.yml        # Konfigurasi utama Docker
├── requirements.txt          # Daftar dependency Python
└── README.md                 # Dokumentasi utama (file ini)
```

---

## ⚙️ Persiapan Awal

### 1️⃣ Clone Repository

```bash
git clone https://github.com/rrdtlsh/Perancangan-Data-Warehouse-Industri-Pertambangan-PT.XYZ.git
cd Perancangan-Data-Warehouse-Industri-Pertambangan-PT.XYZ
```

### 2️⃣ Siapkan File Environment

Buat file `.env` dari template:

```bash
copy .env.example .env
```

---

## 🐳 Menjalankan Proyek (Docker)

### 1️⃣ Jalankan Semua Layanan

```bash
docker compose up -d
```

Tunggu hingga semua service aktif (`sqlserver`, `airflow-webserver`, `grafana`, dll).

### 2️⃣ Cek Status Layanan

```bash
docker compose ps
```

Jika semua status `running` atau `healthy`, berarti environment siap digunakan.

---

## 🧠 ETL (Extract – Transform – Load)

### 1️⃣ Pastikan Dependensi Python Terpasang

```bash
py -m pip install -r requirements.txt
```

### 2️⃣ Jalankan Skrip ETL

File ETL berada di folder `dags/`.

#### Jalankan di Host
```bash
python dags/standalone_etl.py
```

#### Jalankan di Dalam Kontainer Airflow
```bash
docker exec ptxyz_airflow_worker python /opt/airflow/dags/standalone_etl.py
```

---

## 💾 Menyalin Dataset ke Kontainer

Jika file CSV tidak terbaca di dalam container:

```bash
docker exec ptxyz_airflow_worker mkdir -p /opt/airflow/data/raw/Dataset
docker cp ./data/. ptxyz_airflow_worker:/opt/airflow/data/raw/Dataset/
```

---

## 🧩 Struktur Database

Database utama: **PTXYZ_DataWarehouse**

### Skema
- **dim.*** → tabel dimensi  
- **fact.*** → tabel fakta  
- **staging.*** → tabel sementara

### Contoh Tabel
| Schema | Table | Deskripsi |
|---------|--------|-----------|
| dim     | DimEmployee | Data karyawan |
| dim     | DimEquipment | Data peralatan |
| fact    | FactProduction | Data produksi |
| fact    | FactMaintenance | Data biaya & durasi perawatan |
| staging | Production | Data mentah hasil ekstraksi |

---

## 📊 Visualisasi BI

### Akses Layanan (Default Ports)

| Layanan | URL | Default Login |
|----------|------|----------------|
| **Airflow** | [http://localhost:8080](http://localhost:8080) | airflow / airflow |
| **Grafana** | [http://localhost:3000](http://localhost:3000) | admin / admin |
| **Superset** | [http://localhost:8088](http://localhost:8088) | admin / admin |
| **Metabase** | [http://localhost:3001](http://localhost:3001) | admin / admin |
| **Jupyter** | [http://localhost:8888](http://localhost:8888) | token otomatis |

---

### Koneksi SQL Server di Grafana
1. **Add new data source** → pilih **Microsoft SQL Server**  
2. Isi konfigurasi:
   - Host: `sqlserver:1433`
   - Database: `PTXYZ_DataWarehouse`
   - User: `sa`
   - Password: isi dari `.env`
   - Encryption: `disable`
3. Klik **Save & Test** → pastikan "Database connection OK".

---

## 🧪 Validasi Otomatis (Opsional)

### 1️⃣ Jalankan Tes Validasi

```bash
docker cp ./tests/validate.sql ptxyz_sqlserver:/tmp/validate.sql
docker exec ptxyz_sqlserver /opt/mssql-tools18/bin/sqlcmd \
  -S localhost -U sa -P 'PTXYZSecure123!' \
  -d PTXYZ_DataWarehouse -i /tmp/validate.sql
```

### 2️⃣ Output
Jika semua pengujian berhasil → muncul hasil `SUCCESS` di terminal.

---

## 📈 Contoh Dashboard BI

| Panel | Tujuan |
|-------|--------|
| **Total Produksi per Material** | Menampilkan total hasil produksi tiap jenis material |
| **Top 5 Downtime Equipment** | Mengidentifikasi alat dengan waktu rusak tertinggi |
| **Perbandingan Biaya vs Anggaran** | Monitoring efisiensi keuangan proyek |
| **Efisiensi Peralatan (KPI)** | KPI utama peralatan aktif |
| **Top 3 Biaya Maintenance** | Fokus pada alat dengan biaya perawatan tertinggi |

---

## ✅ Selesai

> Proyek siap dijalankan secara penuh menggunakan Docker Compose.  
> Pastikan semua layanan **running** sebelum melakukan proses **ETL dan visualisasi**.  
> Dokumentasi tambahan (screenshots, analisis, laporan akhir) dapat ditambahkan di `RUNBOOK.md` atau `docs/`.

---

## 👥 Tim Pengembang
- **Project Lead:** Raudatul Sholehah  
- **Collaborators:** Anggota Tim PT XYZ Data Warehouse
