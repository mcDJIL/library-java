# Sistem Transaksi Perpustakaan (Library System)

Studi kasus Pemrograman Berorientasi Objek (Java) — sistem sederhana untuk
mengelola data buku, data anggota, serta mencatat transaksi peminjaman dan
pengembalian di sebuah perpustakaan.

## Deskripsi Sistem

Perpustakaan membutuhkan sistem untuk mengelola data buku, data anggota,
serta mencatat setiap transaksi peminjaman dan pengembalian. Sistem harus
memastikan stok buku dikelola dengan aman, setiap anggota terdata dengan
jelas, dan status setiap transaksi dapat dipantau.

**Aturan bisnis (enkapsulasi & interaksi):**

- **Buku** — stok tidak boleh bernilai negatif, dan hanya berkurang jika
  stok tersedia (≥ 1).
- **Anggota** — setiap anggota punya batas maksimal peminjaman (maks 2 buku).
- **Transaksi** — satu transaksi menghubungkan tepat satu Anggota dan satu
  Buku; ditolak jika stok buku habis atau kuota peminjaman anggota penuh.

## Class yang Digunakan

| Class | Fungsi |
|---|---|
| `Book` | Model data buku — menyimpan `bookId`, `title`, `stock`; mengatur perubahan stok secara aman lewat `decreaseStock()` / `increaseStock()`. |
| `Member` | Model data anggota — menyimpan identitas dan jumlah buku yang sedang dipinjam, dibatasi konstanta `MAX_LIMIT`. |
| `Transaction` | Menghubungkan satu `Member` dan satu `Book`; memproses logika peminjaman (`processBorrow()`) & pengembalian (`processReturn()`), termasuk validasi stok dan kuota. |
| `History` | Mencatat seluruh `Transaction` yang pernah dibuat sebagai riwayat yang bisa ditampilkan (`printHistory()`). |
| `LibrarySystem` (Main) | Kelas *runner* — berisi `main()` yang membuat objek, menjalankan skenario simulasi, dan mencetak hasilnya ke konsol. |

## Cara Menjalankan

Butuh **JDK 8+** terpasang.

```bash
javac LibrarySystem.java
java LibrarySystem
```

## Contoh Output

```
=== SISTEM TRANSAKSI PERPUSTAKAAN ===

--- Skenario Peminjaman ---
SUKSES [T001]: Areal berhasil meminjam "Pemrograman Java Dasar". Sisa stok: 0
GAGAL  [T002]: Stok buku "Pemrograman Java Dasar" habis.
GAGAL  [T003]: Stok buku "Basis Data Lanjut" habis.
SUKSES [T004]: Areal berhasil meminjam "Struktur Data & Algoritma". Sisa stok: 2
GAGAL  [T005]: Areal sudah mencapai batas maksimal peminjaman (2/2).

--- Skenario Pengembalian ---
SUKSES [T001]: Areal berhasil mengembalikan "Pemrograman Java Dasar".
SUKSES [T006]: Budi berhasil meminjam "Pemrograman Java Dasar". Sisa stok: 0
GAGAL  [T001]: Buku "Pemrograman Java Dasar" sudah pernah dikembalikan sebelumnya.

=== RIWAYAT TRANSAKSI ===
T001 - Areal - Pemrograman Java Dasar      - Dikembalikan
T002 - Budi  - Pemrograman Java Dasar      - Sedang Dipinjam
T004 - Areal - Struktur Data & Algoritma   - Sedang Dipinjam
T006 - Budi  - Pemrograman Java Dasar      - Sedang Dipinjam
```

*(Angka stok & urutan transaksi bisa berbeda tergantung skenario yang
dijalankan di `main()`.)*

## Struktur Program

```
LibrarySystem.java   # berisi seluruh class: Book, Member, Transaction,
                      # History, dan LibrarySystem (Main / runner)
```

## Kelompok

- Kelompok: 2
- Anggota: Moch Djauharil Ilmi (3125600063)
- Anggota: Lulu'atul Mahfudoh (3125600075)
- Anggota: Erlandio Bahy Atmajaya (3125600085)
- Mata kuliah: Pemrograman Berorientasi Objek — D4 Teknik Informatika, PENS