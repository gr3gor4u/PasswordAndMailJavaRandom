import java.awt.*;
import java.io.*;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class RandomMailAndPassword {
    public static void main(String[] args) throws IOException {
        Scanner scancl = new Scanner(System.in);
        System.out.println("Enter name your file: ");
        String nameOfTxt = scancl.nextLine();
        System.out.println("Enter count your personal: ");
        int countOFSotr = scancl.nextInt();
        Scanner scantxt = new Scanner(nameOfTxt + ".txt");
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(nameOfTxt + ".txt"));
        String[] currentLine = new String[countOFSotr];
        for (int i = 0; i < countOFSotr; i++) {
            currentLine[i] = br.readLine();
        }
        for (int i = 0; i < countOFSotr; i++) {
            System.out.println(currentLine[i]);
        }
        System.out.println();
        MailAndPasswordCreate(countOFSotr, currentLine);
        // char str=currentLine[1].charAt(0);
        // str=Character.toUpperCase(str);
    }

    private static void MailAndPasswordCreate(int countOFSotr, String[] currentLine) throws IOException {
        String randomCharMass = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789#%&@!$*(_)&^";
        Random random = new Random();
        String[] randomPassword = new String[countOFSotr];
        for (int j = 0; j < countOFSotr; j++) {
            randomPassword[j] = "";
            for (int i = 0; i < 8; i++) {
                if (randomPassword[j].length() < 8) {
                    randomPassword[j] += randomCharMass.charAt(random.nextInt(randomCharMass.length()));
                } else {
                    break;
                }
            }
            System.out.println(randomPassword[j]);
        }
        char[] abcCur = { ' ', 'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с',
                'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'э', 'ю', 'я', 'ы', 'ь', 'ъ', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж',
                'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Э', 'Ю',
                'Я', 'Ы', 'Ь', 'Ъ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', '/', '-' };
        String[] abcTrans = { "_", "a", "b", "v", "g", "d", "e", "zh", "z", "i", "iy", "k", "l", "m", "n", "o", "p",
                "r", "s", "t", "y", "f", "h", "c", "ch", "sh", "sch", "ai", "yu", "ya", "bI", "'", "^", "A", "B", "V",
                "G", "D", "E", "Zh", "Z", "I", "IY", "K", "L", "M", "N", "O", "P", "R", "S", "T", "Y", "F", "H", "C",
                "CH", "SH", "SCH", "AI", "YU", "YA", "bI", "'", "^", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E",
                "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "/", "-" };
        String[] TransletedWords = new String[countOFSotr];
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < countOFSotr; j++) {
            for (int i = 0; i < currentLine[j].length(); i++) {
                for (int x = 0; x < abcCur.length; x++) {
                    if (currentLine[j].charAt(i) == abcCur[x]) {
                        builder.append(abcTrans[x]);

                    } else {
                        TransletedWords[j] = builder.toString();
                    }
                }
            }
            builder.setLength(0);
        }
        MakeFileWithResult(TransletedWords, randomPassword, countOFSotr);
    }

    public static void MakeFileWithResult(String[] TransletedWords, String[] randeomPassword, int count)
            throws IOException {
        System.out.println("ВВедите название куда будет сохраняться результат: ");
        Scanner scan = new Scanner(System.in);
        String nameOfFile = scan.nextLine() + ".txt";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(nameOfFile));
            for (int i = 0; i < count; i++) {
                writer.write(
                        (i + 1) + ": " + TransletedWords[i] + "@gmail.com" + " пароль:" + randeomPassword[i] + "\n");
            }
        } catch (IOException e) {

        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
            }
        }
        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File(nameOfFile));
    }
}