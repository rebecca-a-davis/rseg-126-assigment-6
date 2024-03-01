package edu.rdavis.rseg126.assignment4.main;

import java.util.ArrayList;
import java.util.Arrays;

public class Sieve {
  private boolean[] numbers;

  public Sieve(int maxPrime) {
    // We're "wasting" 2 (0, 1) spaces in the array this way, but that's
    // okay because it's not a lot of space and makes the logic far
    // easier to follow.
    this.numbers = new boolean[maxPrime + 1];
    // initialize the array to true
    Arrays.fill(this.numbers, true);
  }

  public ArrayList<Integer> getPrimes() {
    // the max prime number is 1 less than the length of the array
    // because we made the array "too big" for easier logic
    int max_prime = this.numbers.length - 1;

    for (int i = 2; i <= Math.sqrt(max_prime); i++) {
      if (this.numbers[i]) {
        for (int j = i * i; j <= max_prime; j += i) {
          this.numbers[j] = false;
        }
      }
    }

    // gather the list of numbers we've marked as prime
    ArrayList<Integer> primes = new ArrayList<Integer>();
    // start at 2 because that's the smallest prime number (skipping
    // the first 2 indices in the array)
    for (int i = 2; i < this.numbers.length; i++) {
      if (this.numbers[i]) {
        primes.add(i);
      }
    }

    return primes;
  }
}
