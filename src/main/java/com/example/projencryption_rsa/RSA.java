package com.example.projencryption_rsa;

import java.util.ArrayList;
import java.util.Random;

public class RSA {

    private final int MIN_VALUE = 7;

    private final int MAX_VALUE = 32; // 32; // used to pick a random p, q values => n= p*q => n: is the block size
    private final char[] ENGLISH_LETTERS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm'
            , 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ', ',', '?', '!', '.'};
    private int p;
    private int q;
    private int n;
    private int phy;
    private int e; // public "key"
    private int d; // private "key"

    public RSA(char operation) {
        if (operation == 'e') {
            getPrimes(); // get p, q , n, phy
            find_e(); // get: 'e' -> the public "key" ===> then get : 'd' -> the private "key"
            //find_d(); // get : 'd' the private "key"
        }
        // if decryption => no need to calculate anything => the d, n ==> are read from the header(from the encrypted file)
    }

    private int modulo(int ch, int power, int n) { // e.g. finding  3^200 mod 50 : (ch=3 = the char(plain text), b=200 = d or e, c=50 = n ) char
        long x = 1;
        long y = ch;
        while (power > 0) {
            if (power % 2 == 1) {
                x = (x * y) % n;
            }
            y = (y * y) % n; // squaring the base
            power /= 2;
        }
        return (int) x % n;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return "RSA{" +
                "p=" + p +
                ", q=" + q +
                ", n=" + n +
                ", phy=" + phy +
                ", e=" + e +
                ", d=" + d +
                '}';
    }

    public int encrypt(char ch) {


        return modulo(indexOfChar(ch), e, n); // c = p^2 mod n
    }


    public char decrypt(int encryptedChar) { // d: the 'private key'


        int ind = modulo(encryptedChar, d, n); // the encrypted msg was based on the index(0-25) "english letters"
        return charOfIndex(ind);// so we return the original char=> by getting its value from the array

    }

    private void getPrimes() {

        Random random = new Random();

        ArrayList<Integer> arrPrimes = getPrimesArray(MIN_VALUE, MAX_VALUE);

        int index_p = random.nextInt(arrPrimes.size());
        int index_q = random.nextInt(arrPrimes.size());
        p = arrPrimes.get(index_p);
        q = arrPrimes.get(index_q);
        n = p * q;
        phy = (p - 1) * (q - 1);


    }

    private void find_e() {
        Random random = new Random();



        ArrayList<Integer> arrPrimes = getPrimesArray(2, phy);

        int index_e = random.nextInt(arrPrimes.size());
        e = arrPrimes.get(index_e);
        find_d();


    }

    private void find_d() { // 'd': the private key


        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        ArrayList<Integer> d = new ArrayList<>();
        ArrayList<Integer> k = new ArrayList<>();

        // modInverse

        // adding the initial values to the arrays;
        // using extended euclidean (table method) algorithm to find the GCD

        a.add(1);
        a.add(0);

        b.add(0);
        b.add(1);

        d.add(phy);
        d.add(e);
        k.add(Integer.MAX_VALUE);
        k.add(d.get(0) / d.get(1));

        int i = 2;
        while (true) {

            a.add(a.get(i - 2) - (a.get(i - 1) * k.get(i - 1)));
            b.add(b.get(i - 2) - (b.get(i - 1) * k.get(i - 1)));
            d.add(d.get(i - 2) - (d.get(i - 1) * k.get(i - 1)));
            k.add(d.get(i - 1) / d.get(i));


            if (d.get(i) == 1)
                break;
            i++;
        }


        this.d = b.get(i);
        if (this.d < 0)
            this.d += phy;
        System.out.println("is it true=" + ((this.d * e) % phy == 1)); // just to make sure that my work is correct
    }

    private boolean isPrime(int num) {

        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    private ArrayList<Integer> getPrimesArray(int min, int max) {


        ArrayList<Integer> arrPrimes = new ArrayList<>();

        for (int i = min; i <= max; i++) {
            if (isPrime(i))
                arrPrimes.add(i);
        }
        return arrPrimes;


    }

    private int indexOfChar(char ch) {

        int index = 25; // if not presented in the array => use 'z' instead of it
        for (int i = 0; i < ENGLISH_LETTERS.length; i++) {
            if (ENGLISH_LETTERS[i] == ch) {
                index = i;
                break;
            }

        }
        return index;
    }

    private char charOfIndex(int index) {

        return ENGLISH_LETTERS[index];

    }
}
