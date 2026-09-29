public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEM TRANSAKSI PERPUSTAKAAN ===\n");

        Book bookA = new Book("B001", "Pemrograman Java Dasar", 1);
        Book bookB = new Book("B002", "Struktur Data & Algoritma", 3);
        Book bookC = new Book("B003", "Basis Data Lanjut", 0);

        Member memberX = new Member("M001", "Areal");
        Member memberY = new Member("M002", "Budi");

        History history = new History();

        System.out.println("--- Data Awal ---");
        System.out.println(bookA);
        System.out.println(bookB);
        System.out.println(bookC);
        System.out.println(memberX);
        System.out.println(memberY);

        System.out.println("\n--- Skenario Peminjaman ---");

        Transaction t1 = new Transaction("T001", memberX, bookA);
        t1.processBorrow();
        history.addRecord(t1);

        Transaction t2 = new Transaction("T002", memberY, bookA);
        t2.processBorrow();
        history.addRecord(t2);

        Transaction t3 = new Transaction("T003", memberY, bookC);
        t3.processBorrow();
        history.addRecord(t3);

        Transaction t4 = new Transaction("T004", memberX, bookB);
        t4.processBorrow();
        history.addRecord(t4);

        Book bookD = new Book("B004", "Jaringan Komputer", 5);
        Transaction t5 = new Transaction("T005", memberX, bookD);
        t5.processBorrow();
        history.addRecord(t5);

        System.out.println("\n--- Skenario Pengembalian ---");

        t1.processReturn();
        history.addRecord(t1);

        Transaction t6 = new Transaction("T006", memberY, bookA);
        t6.processBorrow();
        history.addRecord(t6);

        t1.processReturn();

        System.out.println("\n--- Status Akhir ---");
        System.out.println(bookA);
        System.out.println(bookB);
        System.out.println(bookC);
        System.out.println(bookD);
        System.out.println(memberX);
        System.out.println(memberY);

        history.printHistory();
    }
}