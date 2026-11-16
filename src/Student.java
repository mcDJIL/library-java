/**
 * Student.java
 * Kelas yang merepresentasikan seorang mahasiswa dalam sistem peminjaman buku.
 * 
 * memberId: ID unik untuk anggota
 * name: Nama anggota
 * nrp: Nomor Registrasi Mahasiswa
 */
public class Student extends Member {
    /**
     * Batas maksimum buku yang dapat dipinjam oleh mahasiswa.
     */
    private static final int STUDENT_LIMIT = 3;
    
    /**
     * Nomor Registrasi Mahasiswa.
     */
    private final String nrp;

    /**
     * Konstruktor untuk membuat mahasiswa baru.
     *
     * @param memberId ID unik anggota
     * @param name     Nama anggota
     * @param nrp      Nomor Registrasi Mahasiswa
     */
    public Student(String memberId, String name, String nrp) {
        super(memberId, name);
        this.nrp = nrp;
    }

    /**
     * Mendapatkan Nomor Registrasi Mahasiswa.
     *
     * @return Nomor Registrasi Mahasiswa
     */
    public String getNrp() {
        return nrp;
    }

    /**
     * Mendapatkan batas maksimum buku yang dapat dipinjam oleh mahasiswa.
     *
     * @return Batas maksimum buku yang dapat dipinjam
     */
    @Override
    public int getMaxLimit() {
        return STUDENT_LIMIT;
    }

    /**
     * Mendapatkan tipe anggota.
     *
     * @return Tipe anggota
     */
    @Override
    public String getType() {
        return "Mahasiswa";
    }

    /**
     * Mengembalikan representasi string dari mahasiswa.
     *
     * @return Representasi string dari mahasiswa
     */
    @Override
    public String toString() {
        return super.toString() + " - NRP: " + nrp;
    }
}
