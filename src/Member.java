/**
 * Member.java
 * Kelas yang merepresentasikan anggota perpustakaan.
 * 
 * memberId: ID unik anggota
 * name: Nama anggota
 * borrowedCount: Jumlah buku yang sedang dipinjam
 * MAX_LIMIT: Batas maksimum buku yang dapat dipinjam oleh anggota
 */
public class Member {
    /**
     * ID unik anggota.
     */
    private String memberId;
    /**
     * Nama anggota.
     */
    private String name;
    /**
     * Jumlah buku yang sedang dipinjam oleh anggota.
     */
    private int borrowedCount;
    /**
     * Batas maksimum buku yang dapat dipinjam oleh anggota.
     */
    private static final int MAX_LIMIT = 3;

    /**
     * Konstruktor untuk membuat anggota baru.
     *
     * @param memberId ID unik anggota
     * @param name     Nama anggota
     */
    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedCount = 0;
    }

    /**
     * Mendapatkan ID unik anggota.
     *
     * @return ID unik anggota
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * Mendapatkan nama anggota.
     *
     * @return Nama anggota
     */
    public String getName() {
        return name;
    }

    /**
     * Mendapatkan jumlah buku yang sedang dipinjam oleh anggota.
     *
     * @return Jumlah buku yang sedang dipinjam
     */
    public int getBorrowedCount() {
        return borrowedCount;
    }

    /**
     * Mendapatkan batas maksimum buku yang dapat dipinjam oleh anggota.
     *
     * @return Batas maksimum buku yang dapat dipinjam
     */
    public static int getMaxLimit() {
        return MAX_LIMIT;
    }

    /**
     * Mengecek apakah anggota dapat meminjam buku baru.
     *
     * @return true jika anggota dapat meminjam buku, false jika sudah mencapai batas maksimum
     */
    public boolean canBorrow() {
        return getBorrowedCount() < MAX_LIMIT;
    }

    /**
     * Menambahkan jumlah buku yang sedang dipinjam oleh anggota.
     *
     * @return true jika berhasil menambahkan, false jika sudah mencapai batas maksimum
     */
    public boolean incrementBorrowed() {
        if (this.canBorrow()) {
            this.borrowedCount++;
            return true;
        }

        return false;
    }

    /**
     * Mengurangi jumlah buku yang sedang dipinjam oleh anggota.
     * Pastikan borrowedCount tidak menjadi negatif.
     */
    public void decrementBorrowed() {
        if (getBorrowedCount() > 0) {
            this.borrowedCount--;
        }
    }

    /**
     * Mengembalikan representasi string dari objek Member.
     *
     * @return Representasi string dari objek Member
     */
    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                ", borrowedCount=" + borrowedCount +
                '}';
    }
}