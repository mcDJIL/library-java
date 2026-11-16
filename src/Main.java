import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Main.java
 * Kelas utama untuk menjalankan demo sistem peminjaman buku.
 */
public class Main {

    /**
     * Membuat transaksi peminjaman dan menambahkannya ke riwayat.
     *
     * @param history Riwayat transaksi
     * @param id      ID unik transaksi
     * @param member  Anggota yang melakukan transaksi
     * @param book    Buku yang dipinjam
     * @param date    Tanggal peminjaman
     * @return Objek Transaction yang dibuat
     */
    private static Transaction borrow(History history, String id, Member member, Book book, LocalDate date) {
        Transaction t = new Transaction(id, member, book);
        t.processBorrow(date);
        history.addRecord(t);
        return t;
    }

    /**
     * Mengubah jumlah uang menjadi format rupiah.
     *
     * @param amount Jumlah uang
     * @return String dalam format rupiah
     */
    private static String rupiah(int amount) {
        return "Rp" + String.format(Locale.US, "%,d", amount).replace(',', '.');
    }

    /**
     * Metode utama untuk menjalankan demo sistem peminjaman buku.
     *
     * @param args Argumen baris perintah (tidak digunakan)
     */
    public static void main(String[] args) {
        System.out.println("=== SISTEM TRANSAKSI PERPUSTAKAAN ===\n");

        // Objek subclass disimpan dalam variabel/koleksi bertipe parent
        List<Book> books = new ArrayList<>();
        books.add(new Novel("B001", "Laskar Pelangi", 1, "Andrea Hirata"));
        books.add(new Textbook("B002", "Struktur Data & Algoritma", 3, "Informatika"));
        books.add(new Magazine("B003", "Tempo", 2, "Edisi 42"));
        books.add(new Textbook("B004", "Basis Data Lanjut", 0, "Informatika"));
        books.add(new Novel("B005", "Bumi Manusia", 5, "Pramoedya Ananta Toer"));

        Member dio = new Member("M001", "Dio");
        Member areal = new Student("M002", "Areal", "3124600001");
        Member lulu = new Lecturer("M003", "Dosen Lulu", "0012345601");
        List<Member> members = new ArrayList<>();
        members.add(dio);
        members.add(areal);
        members.add(lulu);

        Book laskarPelangi = books.get(0);
        Book strukturData = books.get(1);
        Book tempo = books.get(2);
        Book bumiManusia = books.get(4);

        System.out.println("--- Data Awal (toString() tiap subclass berbeda) ---");
        for (Book b : books) {
            System.out.println(b);
        }
        for (Member m : members) {
            System.out.println(m);
        }

        System.out.println("\n--- Demo Polimorfisme: pemanggilan method yang sama, hasil berbeda ---");
        for (Book b : books) {
            System.out.println(String.format("%-40s | lama pinjam %2d hari | boleh dipinjam: %-5s | denda telat 3 hari: %s",
                    b.getTitle() + " [" + b.getCategory() + "]", b.getLoanDays(),
                    b.isBorrowable() ? "ya" : "tidak", rupiah(b.calculateLateFee(3))));
        }
        for (Member m : members) {
            System.out.println(String.format("%-8s | jenis: %-9s | batas pinjam: %d buku",
                    m.getName(), m.getType(), m.getMaxLimit()));
        }

        History history = new History();
        LocalDate day = LocalDate.of(2026, 11, 2);

        System.out.println("\n--- Skenario Peminjaman (2 November 2026) ---");
        Transaction t1 = borrow(history, "T001", areal, laskarPelangi, day);
        borrow(history, "T002", dio, laskarPelangi, day);          // stok habis
        borrow(history, "T003", dio, tempo, day);                  // majalah tidak boleh dipinjam
        Transaction t4 = borrow(history, "T004", dio, strukturData, day);
        Transaction t5 = borrow(history, "T005", dio, bumiManusia, day);
        borrow(history, "T006", dio, strukturData, day);           // kuota anggota umum (2/2) penuh
        borrow(history, "T007", areal, strukturData, day);
        borrow(history, "T008", areal, bumiManusia, day);
        borrow(history, "T009", areal, strukturData, day);          // kuota mahasiswa (3/3) penuh
        borrow(history, "T010", lulu, strukturData, day);           // dosen: batas 5, masih longgar

        System.out.println("\n--- Skenario Pengembalian ---");
        t1.processReturn(LocalDate.of(2026, 11, 12));               // telat 3 hari (novel, 7 hari pinjam)
        t4.processReturn(LocalDate.of(2026, 11, 20));               // telat 4 hari (buku ajar, 14 hari pinjam)
        t5.processReturn(LocalDate.of(2026, 11, 8));                // tepat waktu
        t1.processReturn(LocalDate.of(2026, 11, 13));               // pengembalian ganda
        history.getLog().get(1).processReturn(LocalDate.of(2026, 11, 13)); // T002 yang ditolak, tidak bisa dikembalikan

        System.out.println("\n--- Status Akhir ---");
        for (Book b : books) {
            System.out.println(b);
        }
        for (Member m : members) {
            System.out.println(m);
        }

        history.printHistory();
    }
}
