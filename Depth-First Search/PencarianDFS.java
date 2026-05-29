import java.util.*;

public class PencarianDFS {
    private int jumlahNode;
    private LinkedList<Integer>[] adjasiList;

    // Konstruktor
    @SuppressWarnings("unchecked")
    public PencarianDFS(int v) {
        jumlahNode = v;
        adjasiList = (LinkedList<Integer>[]) new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adjasiList[i] = new LinkedList<>();
        }
    }

    // Fungsi menambah edge/hubungan antar node
    public void tambahEdge(int v, int w) {
        adjasiList[v].add(w);
    }

    // Fungsi utilitas DFS Rekursif
    private boolean fungsiDFS(int nodeSkrg, int target, boolean[] dikunjungi) {
        // Tandai node saat ini sebagai dikunjungi dan cetak
        dikunjungi[nodeSkrg] = true;
        System.out.print("a" + nodeSkrg + " ");

        // Jika target ditemukan
        if (nodeSkrg == target) {
            return true;
        }

        // Telusuri semua node tetangga yang belum dikunjungi
        for (int tetangga : adjasiList[nodeSkrg]) {
            if (!dikunjungi[tetangga]) {
                if (fungsiDFS(tetangga, target, dikunjungi)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Fungsi utama pencarian DFS
    public void cariDFS(int nodeAwal, int target) {
        boolean[] dikunjungi = new boolean[jumlahNode];
        System.out.println("Memulai pencarian DFS dari node a" + nodeAwal + " mencari a" + target + ":");
        
        long waktuMulai = System.nanoTime();
        boolean ditemukan = fungsiDFS(nodeAwal, target, dikunjungi);
        long waktuSelesai = System.nanoTime();

        System.out.println();
        if (ditemukan) {
            System.out.println("Hasil: Node a" + target + " BERHASIL ditemukan!");
        } else {
            System.out.println("Hasil: Node a" + target + " TIDAK ditemukan.");
        }
        System.out.println("Waktu eksekusi: " + (waktuSelesai - waktuMulai) + " ns");
    }

    public static void main(String[] args) {
        PencarianDFS graf = new PencarianDFS(10);

        // Representasi Hubungan Node (Graf)
        graf.tambahEdge(0, 1); graf.tambahEdge(0, 2);
        graf.tambahEdge(1, 0); graf.tambahEdge(1, 3); graf.tambahEdge(1, 4);
        graf.tambahEdge(2, 0); graf.tambahEdge(2, 5); graf.tambahEdge(2, 6);
        graf.tambahEdge(3, 1); graf.tambahEdge(3, 7);
        graf.tambahEdge(4, 1); graf.tambahEdge(4, 7);
        graf.tambahEdge(5, 2); graf.tambahEdge(5, 8);
        graf.tambahEdge(6, 2); graf.tambahEdge(6, 9);
        graf.tambahEdge(7, 3); graf.tambahEdge(7, 4);
        graf.tambahEdge(8, 5);
        graf.tambahEdge(9, 6);

        // Cari node a6 (angka 6) dimulai dari a0
        graf.cariDFS(0, 6);
    }
}