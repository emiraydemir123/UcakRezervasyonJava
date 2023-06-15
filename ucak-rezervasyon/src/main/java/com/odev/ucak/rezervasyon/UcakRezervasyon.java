package com.odev.ucak.rezervasyon;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UcakRezervasyon {
    public static void main(String[] args) {
        Ucak u1 = new Ucak();
        u1.Id = 1;
        u1.Kapasite = 50;
        u1.Marka = "BOEING";

        Ucak u2 = new Ucak();
        u2.Id = 2;
        u2.Kapasite = 55;
        u2.Marka = "AIRBUS";

        Lokasyon l1 = new Lokasyon();
        l1.Id = 1;
        l1.Ad = "ISTANBUL";
        l1.Ulke = "TURKIYE";

        Lokasyon l2 = new Lokasyon();
        l2.Id = 2;
        l2.Ad = "IZMIR";
        l2.Ulke = "TURKIYE";

        Lokasyon l3 = new Lokasyon();
        l3.Id = 3;
        l3.Ad = "ANKARA";
        l3.Ulke = "TURKIYE";

        Sefer s1 = new Sefer();
        s1.Id = 1;
        s1.Kalkis = l1;
        s1.Varis = l2;
        s1.Zaman = new Date(2023, 12, 1);
        s1.Tasit = u1;

        Sefer s2 = new Sefer();
        s2.Id = 2;
        s2.Kalkis = l1;
        s2.Varis = l3;
        s2.Zaman = new Date(2023, 12, 1);
        s2.Tasit = u2;

        List<Sefer> sefer_listesi = new ArrayList<>();
        sefer_listesi.add(s1);
        sefer_listesi.add(s2);
        Islemler i = new Islemler(sefer_listesi);

        System.out.println("KALKIS YERI (BUYUK HARFLERLE): ");
        Scanner scanner = new Scanner(System.in);
        String kalkis_yeri = scanner.nextLine();

        System.out.println("VARIS YERI (BUYUK HARFLERLE): ");
        String varis_yeri = scanner.nextLine();

        System.out.println("TARIH (GUN BOSLUK AY BOSLUK YIL OLARAK): ");
        String tarih_str = scanner.nextLine();
        String[] tarih_array = tarih_str.split(" ");
        int gun = Integer.parseInt(tarih_array[0]);
        int ay = Integer.parseInt(tarih_array[1]);
        int yil = Integer.parseInt(tarih_array[2]);
        Date tarih = new Date(yil, ay, gun);

        List<Sefer> seferler = i.GetirSeferListesiKalkisVeVarisVeTariheGore(kalkis_yeri, varis_yeri, tarih);
        if (seferler.isEmpty()) {
            System.out.println("BOYLE BIR SEFER YOK");
        } else {
            System.out.println("MEVCUT SEFERLER LISTELENMEKTEDIR");
            System.out.println("SEFER NO/KALKIS/VARIS/TARIH/KAPASITE/BOS KAPASITE");

            String pattern = "dd/MM/yyyy";
            DateFormat df = new SimpleDateFormat(pattern);

            for (Sefer s : seferler) {
                int bos_koltuk = i.GetirBosKoltukSayisiSefereGore(s);

                System.out.println(String.format("%d/%s/%s/%s/%d/%d",
                        s.Id,
                        s.Kalkis.Ad,
                        s.Varis.Ad,
                        df.format(s.Zaman),
                        s.Tasit.Kapasite,
                        bos_koltuk));
            }

            System.out.println("KAYIT (SEFER NO BOSLUK AD BOSLUK SOYAD BOSLUK KOLTUK NO OLARAK)");
            String kayit_str = scanner.nextLine();
            String[] kayit_array = kayit_str.split(" ");
            int sefer_no = Integer.parseInt(kayit_array[0]);
            String ad = kayit_array[1];
            String soyad = kayit_array[2];
            int koltuk_no = Integer.parseInt(kayit_array[3]);

            Sefer sefer = i.GetirSeferIdYeGore(sefer_no);
            i.RezervasyonYap(sefer, ad, soyad, koltuk_no);
            System.out.println("REZERVASYON TAMAMLANDI");

            System.out.println("SEFER NO/KALKIS/VARIS/TARIH/KAPASITE/BOS KAPASITE");

            int bos_koltuk_kayittan_sonra = i.GetirBosKoltukSayisiSefereGore(sefer);

            System.out.println(String.format("%d/%s/%s/%s/%d/%d",
                    sefer.Id,
                    sefer.Kalkis.Ad,
                    sefer.Varis.Ad,
                    df.format(sefer.Zaman),
                    sefer.Tasit.Kapasite,
                    bos_koltuk_kayittan_sonra));
        }
    }
}
