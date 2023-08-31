package midtermproject.visualmidterm;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static String mesaj;
    static String letters;
    static char[] displayedLetters;
    static ArrayList<String> validWords;
    static ArrayList<String> wordsUsed;
    static ArrayList<String> randomGames;
    static ArrayList<String> possibleGames;

    static ArrayList<String> pangrams = new ArrayList<>();
    static Scanner sc;
    static int points;
    static String[] praiseNames = new String[]{"GÜZEL!!", "BAŞARILI!!", "HARİKA!!", "MÜTHİŞ!!", "İNANILMAZ!!", "KUSURSUZ!!", "BÜYÜLEYİCİ!!"};
    static String tebrik;

    public Game(String l) throws IOException {
        letters = l;
        sc = new Scanner(new FileReader("src/main/java/midtermproject/visualmidterm/sozluk.txt"));
        validWords = new ArrayList<>();
        wordsUsed = new ArrayList<>();
        pangrams = new ArrayList<>();
        displayedLetters = new char[7];
        for (int i = 0; i < letters.length(); i++) {
            displayedLetters[i] = letters.charAt(i);
        }
    }

    public static void allPlayableWords() throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/main/java/midtermproject/visualmidterm/sozluk.txt"));
        randomGames=new ArrayList<>();
        possibleGames=new ArrayList<>();
        do
        {
            if(s.toString().length()>=7)
            {
                randomGames.add(s.next());
            }
        }
        while (s.hasNext());
        for (String randomGame : randomGames) {

            differentChars(randomGame);
            if (differentChars(randomGame).length() == 7) {
                possibleGames.add(differentChars(randomGame));

            }
        }
    }

    public static void addValidWords() {
        while (sc.hasNext()) {
            String word = sc.next();
            boolean isValid = true;
            char coreLetter = letters.charAt(0);
            if (word.indexOf(coreLetter) < 0) {
                isValid = false;
            } else {
                for (int i = 0; i < word.length(); i++) {
                    if (letters.indexOf(word.charAt(i)) < 0) {
                        isValid = false;
                        break;
                    }
                }
            }
            if (isValid && word.length()>3) {
                validWords.add(word);
                if (checkIfPangram(word)) {
                    pangrams.add(word);
                }
            }
        }
    }

    public static boolean checkIfPangram(String w)
    {
        int counter = 0;
        for(int i = 0; i < letters.length(); i++)
        {
            if(w.contains("" + letters.charAt(i)))
            {
                counter++;
            }
        }
        return counter == letters.length();
    }

    public static void shuffle() {
        String nonCoreLetters = letters.substring(1);
        for (int i = 1; i <= 6; i++) {
            int index = (int) (Math.random() * nonCoreLetters.length());
            displayedLetters[i] = nonCoreLetters.charAt(index);
            nonCoreLetters = nonCoreLetters.substring(0, index) + nonCoreLetters.substring(index + 1);
        }
    }
    public static void updatePoints(String l) {
        if (l.length() == 4) {
            points += 4;
            tebrik = praiseNames[0];
        }
        if (l.length() > 4) {
            points += l.length();
            if (l.length() > 10) {
                tebrik = praiseNames[6];
            } else {
                tebrik = praiseNames[l.length() - 4];
            }
        }
        if (checkIfPangram(l)) {
            points += 7;
            tebrik = "PANGRAM BULUNDU!!";
        }
    }

    private static String differentChars(String str)
    {
        String charList =new String();
        String alf = "abcçdefgğhıijklmnoöprsştuüvyz";
        int MAX_CHARS =1500;
        boolean[] present = new boolean[MAX_CHARS];
        for(int i = 0; i < str.length(); i++) {
            if(alf.contains(String.valueOf(str.charAt(i))))
            {
                present[str.charAt(i)]=true;
            }
        }
        for(int i = 0; i < MAX_CHARS; i++)
        {
            if (present[i]==true) {
                charList+=(char)(i);
            }
        }
        return charList;
    }

}



