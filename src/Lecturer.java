/**
 * Lecturer.java
 * Kelas yang merepresentasikan seorang dosen dalam sistem peminjaman buku.
 * 
 * memberId: ID unik untuk anggota
 * name: Nama anggota
 * nidn: Nomor Induk Dosen Nasional
 */
public class Lecturer extends Member {
    /**
     * Batas maksimum buku yang dapat dipinjam oleh dosen.
     */
    private static final int LECTURER_LIMIT = 5;
    /**
     * Nomor Induk Dosen Nasional.
     */
    private final String nidn;

    /**
     * Konstruktor untuk membuat dosen baru.
     *
     * @param memberId ID unik anggota
     * @param name     Nama anggota
     * @param nidn     Nomor Induk Dosen Nasional
     */
    public Lecturer(String memberId, String name, String nidn) {
        super(memberId, name);
        this.nidn = nidn;
    }

    /**
     * Mendapatkan Nomor Induk Dosen Nasional.
     *
     * @return Nomor Induk Dosen Nasional
     */
    public String getNidn() {
        return nidn;
    }

    /**
     * Mendapatkan batas maksimum buku yang dapat dipinjam oleh dosen.
     *
     * @return Batas maksimum buku yang dapat dipinjam
     */
    @Override
    public int getMaxLimit() {
        return LECTURER_LIMIT;
    }

    /**
     * Mendapatkan tipe anggota.
     *
     * @return Tipe anggota
     */
    @Override
    public String getType() {
        return "Dosen";
    }

    /**
     * Mengembalikan representasi string dari dosen.
     *
     * @return Representasi string dari dosen
     */
    @Override
    public String toString() {
        return super.toString() + " - NIDN: " + nidn;
    }
}
