/**
 * Book.java
 * Kelas yang merepresentasikan sebuah buku dalam sistem peminjaman buku.
 * 
 * bookId: ID unik untuk buku
 * title: Judul buku
 * stock: Jumlah stok buku yang tersedia
 */
public class Book {
    /**
     * ID unik untuk buku.
     */
    private String bookId;
    /**
     * Judul buku.
     */
    private String title;
    /**
     * Jumlah stok buku yang tersedia.
     */
    private int stock;

    /**
     * Konstruktor untuk membuat buku baru.
     *
     * @param bookId ID unik untuk buku
     * @param title  Judul buku
     * @param stock  Jumlah stok buku yang tersedia
     */
    public Book(String bookId, String title, int stock) {
        this.bookId = bookId;
        this.title = title;
        this.stock = stock;
    }

    /**
     * Mendapatkan ID unik buku.
     *
     * @return ID unik buku
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * Mendapatkan judul buku.
     *
     * @return Judul buku
     */
    public String getTitle() {
        return title;
    }

    /**
     * Mendapatkan jumlah stok buku yang tersedia.
     *
     * @return Jumlah stok buku
     */
    public int getStock() {
        return stock;
    }

    /**
     * Mengurangi stok buku sebanyak 1 jika stok tersedia.
     *
     * @return true jika pengurangan stok berhasil, false jika stok habis
     */
    public boolean decreaseStock() {
        if (getStock() > 0) {
            this.stock--;
            return true;
        }

        return false;
    }

    /**
     * Menambahkan stok buku sebanyak 1.
     *
     * @return Jumlah stok buku setelah ditambahkan
     */
    public int increaseStock() {
        return this.stock++;
    }

    /**
     * Mengembalikan representasi string dari objek Book.
     *
     * @return Representasi string dari objek Book
     */
    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", stock=" + stock +
                '}';
    }
}