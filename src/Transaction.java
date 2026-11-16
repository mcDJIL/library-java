import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * Transaction.java
 * Kelas yang merepresentasikan transaksi peminjaman dan pengembalian buku.
 * 
 * transactionId: ID unik transaksi
 * member: Anggota yang melakukan transaksi
 * book: Buku yang dipinjam atau dikembalikan
 * isReturned: Status apakah buku sudah dikembalikan
 * status: Status transaksi (berhasil/gagal)
 * borrowedDate: Tanggal peminjaman
 * returnedDate: Tanggal pengembalian
 */
public class Transaction {
    /**
     * Format tanggal untuk menampilkan tanggal peminjaman dan pengembalian.
     */
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * ID unik transaksi.
     */
    private String transactionId;
    
    /**
     * Anggota yang melakukan transaksi.
     */
    private Member member;
    
    /**
     * Buku yang dipinjam atau dikembalikan.
     */
    private Book book;
    
    /**
     * Status apakah buku sudah dikembalikan.
     */
    private boolean isReturned;

    /**
     * Tanggal peminjaman buku.
     */
    private LocalDate borrowedDate;
    
    /**
     * Tanggal pengembalian buku.
     */
    private LocalDate returnedDate;

    /**
     * Konstruktor untuk membuat transaksi baru.
     *
     * @param transactionId ID unik transaksi
     * @param member        Anggota yang melakukan transaksi
     * @param book          Buku yang dipinjam atau dikembalikan
     */
    public Transaction(String transactionId, Member member, Book book) {
        this.transactionId = transactionId;
        this.member = member;
        this.book = book;
        this.isReturned = false;
    }

    /**
     * Mendapatkan ID unik transaksi.
     *
     * @return ID unik transaksi
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Mendapatkan anggota yang melakukan transaksi.
     *
     * @return Anggota yang melakukan transaksi
     */
    public Member getMember() {
        return member;
    }

    /**
     * Mendapatkan buku yang dipinjam atau dikembalikan.
     *
     * @return Buku yang dipinjam atau dikembalikan
     */
    public Book getBook() {
        return book;
    }

    /**
     * Mengecek apakah buku sudah dikembalikan.
     *
     * @return true jika buku sudah dikembalikan, false jika belum
     */
    public boolean isReturned() {
        return isReturned;
    }

    /**
     * Mendapatkan status transaksi.
     *
     * @return true jika transaksi berhasil, false jika gagal
     */
    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    /**
     * Mendapatkan tanggal pengembalian buku.
     * 
     * @return Tanggal pengembalian buku
     */
    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    /**
     * Mendapatkan tanggal jatuh tempo pengembalian buku.
     *
     * @return Tanggal jatuh tempo pengembalian buku
     */
    public LocalDate getDueDate() {
        return borrowedDate == null ? null : borrowedDate.plusDays(book.getLoanDays());
    }

    /**
     * Mendapatkan status transaksi dalam bentuk string.
     *
     * @return Status transaksi dalam bentuk string
     */
    public String getStatus() {
        if (borrowedDate == null) {
            return "Ditolak";
        }
        return isReturned ? "Dikembalikan" : "Sedang Dipinjam";
    }

    /**
     * Memproses peminjaman buku.
     * Mengecek apakah anggota dapat meminjam buku dan apakah stok buku tersedia.
     * Jika berhasil, mengurangi stok buku dan menambahkan jumlah buku yang dipinjam oleh anggota.
     *
     * @return true jika peminjaman berhasil, false jika gagal
     */
    public boolean processBorrow() {
        return processBorrow(LocalDate.now());
    }

    /**
     * Memproses peminjaman buku dengan tanggal peminjaman tertentu.
     * Mengecek apakah anggota dapat meminjam buku dan apakah stok buku tersedia.
     * Jika berhasil, mengurangi stok buku dan menambahkan jumlah buku yang dipinjam oleh anggota.
     *
     * @param borrowDate Tanggal peminjaman
     * @return true jika peminjaman berhasil, false jika gagal
     */
    public boolean processBorrow(LocalDate borrowDate) {
        if (borrowedDate != null) {
            System.out.println("GAGAL [" + transactionId + "]: Transaksi ini sudah pernah diproses.");
            return false;
        }

        if (!book.isBorrowable()) {
            System.out.println("GAGAL [" + transactionId + "]: " + book.getCategory() + " \""
                    + book.getTitle() + "\" tidak dapat dipinjam (hanya dibaca di tempat).");
            return false;
        }

        if (!member.canBorrow()) {
            System.out.println("GAGAL [" + transactionId + "]: " + member.getName() + " ("
                    + member.getType() + ") sudah mencapai batas maksimal peminjaman ("
                    + member.getBorrowedCount() + "/" + member.getMaxLimit() + ").");
            return false;
        }

        if (!book.decreaseStock()) {
            System.out.println("GAGAL [" + transactionId + "]: Stok buku \""
                    + book.getTitle() + "\" habis.");
            return false;
        }

        member.incrementBorrowed();
        borrowedDate = borrowDate;
        isReturned = false;
        System.out.println("SUKSES [" + transactionId + "]: " + member.getName() + " ("
                + member.getType() + ") meminjam \"" + book.getTitle() + "\" [" + book.getCategory()
                + "]. Jatuh tempo: " + getDueDate().format(FORMAT) + ". Sisa stok: " + book.getStock()
                + ", dipinjam: " + member.getBorrowedCount() + "/" + member.getMaxLimit());
        return true;
    }

    /**
     * Memproses pengembalian buku dengan tanggal pengembalian saat ini.
     *
     * @return true jika pengembalian berhasil, false jika gagal
     */
    public boolean processReturn() {
        return processReturn(LocalDate.now());
    }

    /**
     * Memproses pengembalian buku dengan tanggal pengembalian tertentu.
     * Mengecek apakah buku sudah pernah dipinjam dan belum dikembalikan.
     * Jika berhasil, menambahkan stok buku dan mengurangi jumlah buku yang dipinjam oleh anggota.
     *
     * @param returnDate Tanggal pengembalian
     * @return true jika pengembalian berhasil, false jika gagal
     */
    public boolean processReturn(LocalDate returnDate) {
        if (borrowedDate == null) {
            System.out.println("GAGAL [" + transactionId
                    + "]: Transaksi ini tidak pernah berhasil dipinjam, tidak ada yang dikembalikan.");
            return false;
        }
        if (isReturned) {
            System.out.println("GAGAL [" + transactionId + "]: Buku \""
                    + book.getTitle() + "\" sudah pernah dikembalikan sebelumnya.");
            return false;
        }

        book.increaseStock();
        member.decrementBorrowed();
        isReturned = true;
        returnedDate = returnDate;

        long daysLate = ChronoUnit.DAYS.between(getDueDate(), returnDate);
        String feeInfo = daysLate > 0
                ? "Terlambat " + daysLate + " hari, denda " + rupiah(book.calculateLateFee((int) daysLate))
                : "Tepat waktu, tanpa denda";
        System.out.println("SUKSES [" + transactionId + "]: " + member.getName() + " mengembalikan \""
                + book.getTitle() + "\" pada " + returnDate.format(FORMAT) + ". " + feeInfo
                + ". Stok sekarang: " + book.getStock());
        return true;
    }

    private static String rupiah(int amount) {
        return "Rp" + String.format(Locale.US, "%,d", amount).replace(',', '.');
    }

    private static String format(LocalDate date) {
        return date == null ? "-" : date.format(FORMAT);
    }

    @Override
    public String toString() {
        return "Transaksi " + transactionId + " | " + member.getName() + " | " + book.getTitle()
                + " | " + getStatus() + " | Pinjam: " + format(borrowedDate)
                + " | Kembali: " + format(returnedDate);
    }
}