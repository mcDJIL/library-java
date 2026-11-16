/**
 * Novel.java
 * Kelas yang merepresentasikan sebuah novel dalam sistem peminjaman buku.
 * 
 * bookId: ID unik untuk buku
 * title: Judul buku
 * stock: Jumlah stok buku yang tersedia
 * author: Nama penulis novel
 */
public class Novel extends Book {
    /**
     * Nama penulis novel.
     */
    private final String author;

    /**
     * Konstruktor untuk membuat novel baru.
     *
     * @param bookId ID unik untuk buku
     * @param title  Judul buku
     * @param stock  Jumlah stok buku yang tersedia
     * @param author Nama penulis novel
     */
    public Novel(String bookId, String title, int stock, String author) {
        super(bookId, title, stock);
        this.author = author;
    }

    /**
     * Mendapatkan nama penulis novel.
     *
     * @return Nama penulis novel
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Mendapatkan kategori buku.
     *
     * @return Kategori buku
     */
    @Override
    public String getCategory() {
        return "Novel";
    }

    /**
     * Mendapatkan jumlah hari peminjaman untuk novel.
     *
     * @return Jumlah hari peminjaman
     */
    @Override
    public int getLoanDays() {
        return 7;
    }

    /**
     * Mendapatkan denda keterlambatan per hari untuk novel.
     *
     * @return Denda keterlambatan per hari
     */
    @Override
    public int getLateFeePerDay() {
        return 1000;
    }

    /**
     * Mengembalikan representasi string dari novel.
     *
     * @return Representasi string dari novel
     */
    @Override
    public String toString() {
        return super.toString() + " - Penulis: " + author;
    }
}
