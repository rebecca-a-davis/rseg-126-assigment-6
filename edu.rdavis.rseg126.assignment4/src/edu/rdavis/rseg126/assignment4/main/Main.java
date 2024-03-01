package edu.rdavis.rseg126.assignment4.main;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner reader = new Scanner(System.in);

    // initial prompt for user input
    System.out.print("Enter a number for the Sieve of Eratosthenes (n < 2 to quit): ");

    // get an integer value from the user; ask again if they don't give a valid
    // number
    int sieve_input = 0;
    while (!reader.hasNextInt()) {
      System.out.println("'" + reader.next() + "'" + " is not an integer");
      System.out.print("Enter a number for the Sieve of Eratosthenes (n < 2 to quit): ");
    }
    sieve_input = reader.nextInt();

    // quit immediately if we're given a value less than 2
    if (sieve_input < 2) {
      reader.close();
      System.exit(0);
    }

    // we have a valid integer to pass to the sieve, so get prime numbers
    Sieve sieve = new Sieve(sieve_input);
    System.out.println("Primes less than or equal to " + sieve_input + ": " + sieve.getPrimes());

    // clean up once we're done
    reader.close();
    System.exit(0);
  }
}
