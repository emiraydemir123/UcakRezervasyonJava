package com.odev.ucak.rezervasyon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Islemler {
    private final List<Sefer> seferler;
    private final List<Rezervasyon> rezervasyonlar;

    public Islemler(List<Sefer> sefer_listesi) {
        seferler = sefer_listesi;
        rezervasyonlar = new ArrayList<>();
    }

    public List<Sefer> GetirSeferListesiKalkisVeVarisVeTariheGore(String kalkis_yeri, String varis_yeri, Date zaman) {
        List<Sefer> liste = new ArrayList<>();

        for (Sefer sefer : seferler) {
            if (sefer.Kalkis.Ad.equals(kalkis_yeri)
                && sefer.Varis.Ad.equals(varis_yeri)
                && sefer.Zaman.equals(zaman)) {
                liste.add(sefer);
            }
        }

        return liste;
    }

    public int GetirBosKoltukSayisiSefereGore(Sefer sefer) {
        int mevcut_koltuk_sayisi = sefer.Tasit.Kapasite;

        int dolu_koltuk_sayisi = 0;
        for (Rezervasyon r : rezervasyonlar) {
            if (r.Nereye.Id == sefer.Id) {
                dolu_koltuk_sayisi += 1;
            }
        }

        return mevcut_koltuk_sayisi - dolu_koltuk_sayisi;
    }

    public Sefer GetirSeferIdYeGore(int sefer_id) {
        Sefer d = null;

        for (Sefer sefer : seferler) {
            if (sefer.Id == sefer_id) {
                d = sefer;
            }
        }
        return d;
    }

    public void RezervasyonYap(Sefer sefer, String ad, String soyad, int koltuk_no) {
        Musteri m = new Musteri();
        m.Ad = ad;
        m.Soyad = soyad;

        Rezervasyon r = new Rezervasyon();
        r.KimIcin = m;
        r.Nereye = sefer;
        r.KoltukNo = koltuk_no;

        rezervasyonlar.add(r);
    }
}
