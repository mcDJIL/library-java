import java.util.ArrayList;
import java.util.List;

/**
 * History.java
 * Kelas yang merepresentasikan riwayat transaksi peminjaman dan pengembalian buku.
 * 
 * log: Daftar transaksi yang tercatat
 */
public class History {
    /**
     * Daftar transaksi yang tercatat.
     */
    private List<Transaction> log;

    /**
     * Konstruktor untuk membuat riwayat transaksi baru.
     */
    public History() {
        this.log = new ArrayList<>();
    }

    /**
     * Menambahkan transaksi ke dalam riwayat.
     *
     * @param transaction Transaksi yang akan ditambahkan
     */
    public void addRecord(Transaction transaction) {
        log.add(transaction);
    }

    /**
     * Mencetak riwayat transaksi.
     */
    public void printHistory() {
        System.out.println("\n=== RIWAYAT TRANSAKSI ===");
        if (log.isEmpty()) {
            System.out.println("Belum ada transaksi yang tercatat.");
            return;
        }
        for (Transaction t : log) {
            System.out.println(t);
        }
    }

    /**
     * Mendapatkan daftar transaksi yang tercatat.
     *
     * @return Daftar transaksi
     */
    public List<Transaction> getLog() {
        return log;
    }
}