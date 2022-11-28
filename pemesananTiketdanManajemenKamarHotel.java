import java.util.Scanner;

public class pemesananTiket&ManajemenKamarHotel {

    public static Scanner scan = new Scanner(System.in); // public scanner

    public static void cls() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void lanjut() { // method lanjut pencet dulu
        System.out.println("tekan enter untuk melanjutkan......");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // method untuk visualisasi denah ruangan, ketersediaan kamar dan data pemesan
    // kamar.
    public static void cekKetersediaanKamar(boolean nomor[], String nama[], String noTelp[]) {
        cls();
        System.out.println("Denah dan Status kamar (lantai 1-4)");
        System.out.println("O = terisi \t X = kosong");
        for (int i = 20; i > 0; --i) {
            if (i % 5 == 0) {
                System.out.println("\n");
            }
            if (nomor[i - 1] == true) {
                System.out.print("| kamar " + (i) + "(O) |\t");
            } else {
                System.out.print("| kamar " + (i) + "(X) |\t");
            }
        }
        System.out.println("\n\nData pemesan:");
        for (int i = 0; i < 20; i++) {
            if (nomor[i] == true) { // kalau booleannya true display naman dan nomor telepon
                System.out.println("kamar " + (i + 1) + " (" + nama[i] + " | telp: " + noTelp[i] + ")");
            }
        }
        lanjut();
        cls();
    }

    // method menu ganti harga
    public static void gantiHargaPaket(int harga[]) {
        cls();
        int gantiPaket;
        while (true) {
            System.out.println("pilih harga paket yang ingin diganti \n1. Byte: Rp" + harga[0] + "\n2. Short: Rp"
                    + harga[7] + "\n3. Integer: Rp" + harga[13] + "\n4. Long: Rp" + harga[18]);
            System.out.print("pilih paket: ");
            gantiPaket = scan.nextInt();
            if (gantiPaket >= 1 && gantiPaket <= 4) {
                break;
            }
        }
        System.out.print("Harga baru : Rp");
        int hargaBaru = scan.nextInt();
        if (gantiPaket == 1) {
            for (int i = 0; i < 5; i++) {
                harga[i] = hargaBaru;
            }
        } else if (gantiPaket == 2) {
            for (int i = 5; i < 10; i++) {
                harga[i] = hargaBaru;
            }

        } else if (gantiPaket == 3) {
            for (int i = 10; i < 15; i++) {
                harga[i] = hargaBaru;
            }
        } else if (gantiPaket == 4) {
            for (int i = 15; i < 20; i++) {
                harga[i] = hargaBaru;
            }
        }
        lanjut();
        cls();
    }

    // method function yang akan mereturn hari checkout
    static int hariCheckout(int h) {
        int tgl;
        if (h < 3) {
            tgl = h + 28;
        } else {
            tgl = h - 2;
        }
        return tgl;
    }

    // method function mereturn bulan checkout
    static int bulanCheckout(int h) {
        int bulan;
        if (h < 3) {
            bulan = 11;
        } else {
            bulan = 12;
        }
        return bulan;
    }

    // method menu hapus kamar
    public static void hapusDataKamar(boolean nomor[]) {
        cls();
        int no = 0;
        while (true) {
            System.out.print("masukkan nomor kamar yang ingin dikosongkan(1-20): ");
            no = scan.nextInt();
            if (no >= 1 && no <= 20) {
                break;
            } else {
                System.out.println("invalid input");
            }
        }
        nomor[no - 1] = false;
        System.out.println("data kamar telah dihapus, kamar " + no + " berstatus kosong.");
        lanjut();
        cls();
    }

    // method menu 1 pemesanan kamar (booking kamar)
    public static void bookingKamar(boolean nomor[], String nama[], int harga[], String paket[], String noTelp[]) {
        cls();
        int h; // variabel lama hari sewa kamar
        System.out.println("daftar paket, nomor dan harga kamar\n");
        System.out.println("paket Byte(kamar nomor 1-5)       harga RP" + harga[0] + "/malam");
        System.out.println("\npaket Short(kamar nomor 6-10)     harga RP" + harga[7] + "/malam");
        System.out.println("\npaket Integer(kamar nomor 11-15)  harga RP" + harga[13] + "/malam");
        System.out.println("\npaket Long(kamar nomor 16-20)     harga RP" + harga[18] + "/malam\n");
        int n = pilihNomor(nomor); // pilih nomor kamar
        System.out.print("\nNama pemesan: ");
        nama[n - 1] = scan.nextLine();// memasukkan inputan nama ke dalam array nama
        while (true) { // meminta inputan lama menyewa
            System.out.print("\nIngin menginap menginap berapa hari? (1-30): ");
            h = scan.nextInt();
            if (h >= 1 && h <= 30) { // looping untuk mengecek apakah inputan 1-30 malam
                break;
            } else {
                System.out.println("INPUTANNYA TOLONG DIPERHATIKAN");
            }
        }
        System.out.print("\nNomor telephone (untuk data resepsionis): ");
        scan.nextLine(); // agar tidak terskip di console
        noTelp[n - 1] = scan.nextLine();
        System.out.print("----Picik ENTER untuk menampilkan struk pemesanan kamar-----");
        scan.nextLine();
        cls();
        int tgl = hariCheckout(h);
        int bulan = bulanCheckout(h);
        System.out.println("\n===Struk Pemesanan Kamar Offline Hotel Compell===\n");
        System.out.println("nama              :\t" + nama[n - 1]);
        System.out.println("\nnomor kamar       :\t" + n);
        System.out.println("\nwaktu check in    :\t14:00 28/11/2022");
        System.out.println("\nwaktu check out   :\t12:00  " + tgl + "/" + bulan + "/2022");
        System.out.println("\npaket             :\t" + paket[n - 1] + " (Rp" + harga[n - 1] + "/malam)");
        System.out.println("\ntotal pembayaran  :\tRp" + (harga[n - 1] * h));
        System.out.println("\n===pastikan metode pembayaran cash===");
        System.out.println(
                "\n<<<Terima Kasih telah menginap di hotel compell>>>\n<<<Hubungi Resepsionis melalui (14022) untuk keperluan tambahan>>>");
        System.out.println("");
        lanjut();
        cls();
    }

    // method return untuk mengisi status(true/false) array nomor[], untuk data
    // kamar kosong/tidak
    public static int pilihNomor(boolean nomor[]) {
        int n;
        while (true) {
            // kalau sudah true tetep true/terisi kalau belum true jadikan true/terisi
            System.out.print("pilih nomor kamar: ");
            n = scan.nextInt();
            scan.nextLine();
            if (n >= 1 && n <= 20 && nomor[n - 1] != true) {

                nomor[n - 1] = true;

                break;
            } else {
                System.out.println("nomor kamar yang anda masukkan penuh/input invalid");
            }
        }
        return n;
    }

    // main method
    public static void main(String[] args) {
        boolean nomor[] = new boolean[20]; // array status ketersediaan kamar
        String nama[] = new String[20]; // array nama pembooking
        int harga[] = new int[20]; // array harga
        String noTelp[] = new String[20]; // array nomor telephone
        String paket[] = new String[20]; // array nama paket

        // mengisi array paket dan harga (default) menggunakan forloop
        for (int z = 0; z < 20; z++) {
            if (z >= 0 && z <= 4) {
                paket[z] = "byte";
                harga[z] = 80_000;
            } else if (z >= 5 && z <= 9) {
                paket[z] = "short";
                harga[z] = 150_000;
            } else if (z >= 10 && z <= 14) {
                paket[z] = "Integer";
                harga[z] = 225_000;
            } else if (z >= 15 && z < 20) {
                paket[z] = "long";
                harga[z] = 350_000;
            }
        }
        
        // looping program
        while (true) {
            int pilihan = 0;
            while (true) {
                try {
                    System.out.println("\tProgram Reservasi dan Manajemen Kamar Hotel Compell\t");
                    System.out.println("\n----------------RECEPTIONIST ONLY--------------\n");
                    System.out.println("1. Menu Pemesanan Kamar\n");
                    System.out.println("2. Menu pengaturan Kamar\n");
                    System.out.println("3. exit/close/stop\n");
                    System.out.print("pilih menu: ");
                    pilihan = Integer.parseInt(scan.nextLine());
                } catch (Exception e) {
                    System.out.println("tolong masukkan integer");
                }
                if (pilihan >= 1 && pilihan <= 3) {
                    cls();
                    break;
                }
            }
            // if menu exit/close program
            if (pilihan == 3) {
                System.out.println("==ended==");
                break;
            } else if (pilihan == 1) { // if menu pemesanan kamar
                int p = 0;

                // awal dari looping menu pemesanan kamar
                while (true) {
                    System.out.print(
                            "<<< Menu Pemesanan Kamar Hotel>>>\n\n1. Booking kamar\n\n2. cek denah dan data kamar\n\n3. Kembali ke main menu\n\npilih: ");
                    p = scan.nextInt();
                    scan.nextLine();
                    cls();
                    // if balik ke main menu
                    if (p == 3) {
                        break;
                    } else if (p == 1) {
                        bookingKamar(nomor, nama, harga, paket, noTelp);
                    } else if (p == 2) {
                        cekKetersediaanKamar(nomor, nama, noTelp);
                    }
                }
                // akhir menu pemesanan kamar

                // awal menu pengaturan kamar
            } else if (pilihan == 2) {
                while (true) {
                    System.out.print(
                            "<<<Menu Pengaturan Kamar Hotel>>>\n1. Delete data kamar\n2. Ganti Harga Packet kamar ke harga terbaru\n3. kembali ke Main Menu\n");
                    int menu = 0;
                    while (true) { // selama input salah maka akan terus meminta input
                        System.out.print("pilih menu: ");
                        menu = scan.nextInt();
                        scan.nextLine();
                        if (menu >= 1 && menu <= 3) {
                            break;
                        } else {
                            System.out.println("invalid input");
                        }
                    }
                    if (menu == 1) {
                        hapusDataKamar(nomor);
                    } else if (menu == 3) {
                        cls();
                        break;
                    } else if (menu == 2) {
                        gantiHargaPaket(harga);
                    }
                }
            }
        }
    }
}
