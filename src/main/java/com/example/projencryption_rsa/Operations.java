package com.example.projencryption_rsa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Operations {

    public static String readFileForEncrypt(File file) throws Exception {


        Scanner scanner = new Scanner(file);


        String result = "";

        RSA rsa = new RSA('e');

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            line = line.toLowerCase(); // pre-processing on the data
            for (int i = 0; i < line.length(); i++) {
                result += rsa.encrypt(line.charAt(i)) + " ";

            }
        }
        String header = rsa.getD() + "\n" + rsa.getN() + "\n"; // saving d, n => for the decryption process!
        result = header + result;


        // making the file in the same directory (folder) of the plain text msg
        File fileHasEncryptedMsg = new File(file.getParent() + "\\" + "fileHasEncryptedMsg.txt");

        writeToFile(result, fileHasEncryptedMsg);
        return result; // return the saved result of the Encryption process=> to show it on the GUI
    }

    // writing to msg to a file => either decrypted or encrypted : work for both
    public static void writeToFile(String msg, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(msg);
        fileWriter.close();
    }

    public static String readFileForDecrypt(File file) throws IOException {

        Scanner scanner = new Scanner(file);


        String result = "";

        int d = Integer.parseInt(scanner.nextLine().trim());
        int n = Integer.parseInt(scanner.nextLine().trim());

        RSA rsa = new RSA('d');
        rsa.setD(d);
        rsa.setN(n);

        while (scanner.hasNextLine()) {
            String[] arrOfEachChar = scanner.nextLine().trim().split(" ");

            for (int i = 0; i < arrOfEachChar.length; i++) {

                result += rsa.decrypt(Integer.parseInt(arrOfEachChar[i] + "")) + "";

            }
        }

        // making the file in the same directory (folder) of the encrypted text msg

        File fileHasDecryptedMsg = new File(file.getParent() + "\\" + "fileHasDecryptedMsg.txt");

        writeToFile(result, fileHasDecryptedMsg); // writing to the file: the decrypted msg (the original plain text)
        return result; // return the saved result of the decryption process=> to show it on the GUI
    }
}
