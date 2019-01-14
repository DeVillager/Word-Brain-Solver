package wordbrainsolver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordBrainSolver {

    private static ArrayList<String> sanat = new ArrayList<>();
    private static int leveys = 3;
    private static int korkeus = 3;
    private static int sanojenPituus = 4;
    private static int k = 2;

    public static void main(String[] args) throws IOException {

        Scanner lukija = new Scanner(System.in);
//        System.out.println("Kuinka leveä pelialue?");
//        leveys = lukija.nextInt();
//        System.out.println("Kuinka korkea pelialue?");
//        korkeus = lukija.nextInt();
//        System.out.println("Minkä pituisia sanoja etsitään?");
//        sanojenPituus = lukija.nextInt();
        System.out.println("leveys:" + leveys + ", korkeus:" + korkeus + ", sanojen pituus:" + sanojenPituus);
        String[][] pohja = new String[leveys][korkeus];
        StringBuilder peli = new StringBuilder();
        System.out.println("Syötä kirjaimet");
        for (int i = 0; i < leveys; i++) {
//            System.out.println("Rivin " + (i + 1) + " kirjaimet:");
            for (int j = 0; j < korkeus; j++) {
                pohja[i][j] = lukija.nextLine();
            }
        }
        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < korkeus; j++) {
                System.out.print(pohja[i][j]);
            }
            System.out.println("");
        }
//        for (int k = 2; k < 10; k++) {
//            System.out.println(k + "-pituiset sanat:");
            findWord(pohja);
//            k++;
//        }
        
        System.out.println("Sanoja löytyi: " + sanat.size());
        checkWords(sanat);
    }

    private static void findWord(String[][] pohja) {
        Scanner lukija = new Scanner(System.in);
        for (int a = 0; a < leveys; a++) {
            for (int b = 0; b < korkeus; b++) {
                boolean[][] kayty = new boolean[leveys][korkeus];
                kayty[a][b] = true;
                makeWord(pohja, kayty, a, b, pohja[a][b]);
            }
        }
    }

    private static void makeWord(String[][] pohja, boolean[][] kayty, int a, int b, String word) {
        int i = a;
        int j = b;

        if (word.length() == sanojenPituus) {
            sanat.add(word);
        }
        while (word.length() < sanojenPituus) {
//                System.out.println(word + " (" + i + "," + j + ")");
            if (i - 1 >= 0 && !kayty[i - 1][j] && !sanat.contains(word + pohja[i - 1][j])) {
                kayty[i - 1][j] = true;
                makeWord(pohja, kayty, i - 1, j, word + pohja[i - 1][j]);
                kayty[i - 1][j] = false;
            }
            if (i - 1 >= 0 && j + 1 <= korkeus - 1 && !kayty[i - 1][j + 1] && !sanat.contains(word + pohja[i - 1][j + 1])) {
                kayty[i - 1][j + 1] = true;
                makeWord(pohja, kayty, i - 1, j + 1, word + pohja[i - 1][j + 1]);
                kayty[i - 1][j + 1] = false;
            }
            if (j + 1 <= korkeus - 1 && !kayty[i][j + 1] && !sanat.contains(word + pohja[i][j + 1])) {
                kayty[i][j + 1] = true;
                makeWord(pohja, kayty, i, j + 1, word + pohja[i][j + 1]);
                kayty[i][j + 1] = false;
            }
            if (i + 1 <= leveys - 1 && j + 1 <= korkeus - 1 && !kayty[i + 1][j + 1] && !sanat.contains(word + pohja[i + 1][j + 1])) {
                kayty[i + 1][j + 1] = true;
                makeWord(pohja, kayty, i + 1, j + 1, word + pohja[i + 1][j + 1]);
                kayty[i + 1][j + 1] = false;
            }
            if (i + 1 <= leveys - 1 && !kayty[i + 1][j] && !sanat.contains(word + pohja[i + 1][j])) {
                kayty[i + 1][j] = true;
                makeWord(pohja, kayty, i + 1, j, word + pohja[i + 1][j]);
                kayty[i + 1][j] = false;
            }
            if (i + 1 <= leveys - 1 && j - 1 >= 0 && !kayty[i + 1][j - 1] && !sanat.contains(word + pohja[i + 1][j - 1])) {
                kayty[i + 1][j - 1] = true;
                makeWord(pohja, kayty, i + 1, j - 1, word + pohja[i + 1][j - 1]);
                kayty[i + 1][j - 1] = false;
            }
            if (j - 1 >= 0 && !kayty[i][j - 1] && !sanat.contains(word + pohja[i][j - 1])) {
                kayty[i][j - 1] = true;
                makeWord(pohja, kayty, i, j - 1, word + pohja[i][j - 1]);
                kayty[i][j - 1] = false;
            }
            if (i - 1 >= 0 && j - 1 >= 0 && !kayty[i - 1][j - 1] && !sanat.contains(word + pohja[i - 1][j - 1])) {
                kayty[i - 1][j - 1] = true;
                makeWord(pohja, kayty, i - 1, j - 1, word + pohja[i - 1][j - 1]);
                kayty[i - 1][j - 1] = false;
                break;
            } else {
                break;
            }
        }
    }

    private static void checkWords(ArrayList<String> sanat) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("engwords.txt"));
        ArrayList<String> kirjasto = new ArrayList<>();
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                kirjasto.add(line);
                line = br.readLine();
            }

            for (String sana : sanat) {
                if (kirjasto.contains(sana)) {
                    System.out.println(sana);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(WordBrainSolver.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            br.close();
        }
    }
}
