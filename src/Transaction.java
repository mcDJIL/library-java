import java.time.LocalDate;

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
     * Status transaksi (berhasil/gagal).
     */
    private boolean status;
    private LocalDate borrowedDate;
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
     * Memproses peminjaman buku.
     * Mengecek apakah anggota dapat meminjam buku dan apakah stok buku tersedia.
     * Jika berhasil, mengurangi stok buku dan menambahkan jumlah buku yang dipinjam oleh anggota.
     *
     * @return true jika peminjaman berhasil, false jika gagal
     */
    public boolean processBorrow() {
        if (!member.canBorrow()) {
            System.out.println("GAGAL [" + transactionId + "]: " + member.getName()
                    + " sudah mencapai batas maksimal peminjaman ("
                    + member.getBorrowedCount() + "/" + member.getMaxLimit() + ").");
            this.status = false;
            return false;
        }

        boolean stockReduced = book.decreaseStock();
        if (!stockReduced) {
            System.out.println("GAGAL [" + transactionId + "]: Stok buku \""
                    + book.getTitle() + "\" habis.");
            this.status = false;
            return false;
        }

        member.incrementBorrowed();
        isReturned = false;
        System.out.println("SUKSES [" + transactionId + "]: " + member.getName()
                + " berhasil meminjam \"" + book.getTitle() + "\". Sisa stok: "
                + book.getStock() + ", Total dipinjam anggota: "
                + member.getBorrowedCount() + "/" + member.getMaxLimit());
        this.status = true;
        return true;
    }

    /**
     * Memproses pengembalian buku.
     * Mengecek apakah buku sudah dikembalikan sebelumnya.
     * Jika berhasil, menambahkan stok buku dan mengurangi jumlah buku yang dipinjam oleh anggota.
     *
     * @return true jika pengembalian berhasil, false jika gagal
     */
    public boolean processReturn() {
        if (isReturned) {
            System.out.println("GAGAL [" + transactionId + "]: Buku \""
                    + book.getTitle() + "\" sudah pernah dikembalikan sebelumnya.");
            return false;
        }

        book.increaseStock();
        member.decrementBorrowed();
        isReturned = true;

        System.out.println("SUKSES [" + transactionId + "]: " + member.getName()
                + " berhasil mengembalikan \"" + book.getTitle() + "\". Stok sekarang: "
                + book.getStock() + ", Total dipinjam anggota: "
                + member.getBorrowedCount() + "/" + member.getMaxLimit());
        return true;
    }

    /**
     * Mengembalikan representasi string dari objek Transaction.
     *
     * @return Representasi string dari objek Transaction
     */
    @Override
    public String toString() {
        return "Transaksi " + transactionId + " | Anggota: " + member.getName()
                + " | Buku: " + book.getTitle()
                + " | Status: " + (status ? "Berhasil" : "Gagal");
    }
}