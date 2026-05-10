import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class ValidasiProfilOjol {
    public static String hitungMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : array) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====================================================");
        System.out.println("  SISTEM VALIDASI DATA PROFIL DRIVER/CUSTOMER OJOL  ");
        System.out.println("====================================================");

        // STAGE 1: Input Data Awal
        System.out.println("\n[1] INPUT DATA PROFIL BARU");
        System.out.print("Masukkan Nama     : ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Email    : ");
        String email = scanner.nextLine();
        System.out.print("Masukkan Nomor HP : ");
        String noHP = scanner.nextLine();

        // Menggabungkan data untuk membuat Checksum
        String dataGabunganAwal = nama + "|" + email + "|" + noHP;
        String hashAwal = hitungMD5(dataGabunganAwal);

        System.out.println("\n> Status: Data berhasil disimpan.");
        System.out.println("> Hash MD5 Awal: " + hashAwal);

        System.out.println("\n----------------------------------------------------");

        // STAGE 2: Input Data Baru (Simulasi Perubahan)
        System.out.println("[2] VERIFIKASI / MODIFIKASI DATA");
        System.out.println("(Masukkan data lagi untuk mengecek apakah ada perubahan)");
        System.out.print("Masukkan Nama     : ");
        String namaBaru = scanner.nextLine();
        System.out.print("Masukkan Email    : ");
        String emailBaru = scanner.nextLine();
        System.out.print("Masukkan Nomor HP : ");
        String noHPBaru = scanner.nextLine();

        String dataGabunganBaru = namaBaru + "|" + emailBaru + "|" + noHPBaru;
        String hashBaru = hitungMD5(dataGabunganBaru);

        // STAGE 3: Perbandingan Hash & Status
        System.out.println("\n[3] HASIL PERBANDINGAN");
        System.out.println("Hash Lama : " + hashAwal);
        System.out.println("Hash Baru : " + hashBaru);

        System.out.println("\n====================================================");
        if (hashAwal.equals(hashBaru)) {
            System.out.println("STATUS: DATA AMAN (INTEGRITAS TERJAMIN)");
            System.out.println("Keterangan: Tidak ada perubahan pada profil pengguna.");
        } else {
            System.out.println("STATUS: DATA BERUBAH / DIMODIFIKASI!");
            System.out.println("Keterangan: Sistem mendeteksi adanya perbedaan data.");
        }
        System.out.println("====================================================");

        scanner.close();
    }
}