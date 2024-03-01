package edu.rdavis.rseg126.assignment4.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import edu.rdavis.rseg126.assignment4.main.Sieve;

@RunWith(Theories.class)
public class SieveTest {
  // list of prime numbers <= 100,000 for validation
  private static ArrayList<Integer> goldenPrimes = new ArrayList<Integer>();
  // values to run the test with
  public static @DataPoints int[] candidates;

  @BeforeClass
  public static void setup() {
    // populate the data points for running the test repeatedly
    candidates = new int[100000 + 1];
    for (int i = 0; i <= 100000; i++) {
      candidates[i] = i;
    }

    try {
      // read in list of prime numbers <= 100k
      BufferedReader buf = new BufferedReader(new FileReader("resources/primes.txt"));

      // populate "golden" list of expected primes <= 100k
      String prime_str = buf.readLine();
      while (prime_str != null) {
        goldenPrimes.add(Integer.parseInt(prime_str));
        prime_str = buf.readLine();
      }

      buf.close();

    } catch (FileNotFoundException e) {
      System.out.println("Missing expected primes.txt file");
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Theory
  @Test
  public void PrimeTest(int candidate) {
    // single test run of the prime function for the given candidate value
    Sieve sieve = new Sieve(candidate);
    ArrayList<Integer> sieve_primes = sieve.getPrimes();

    // we shouldn't have more primes than possible
    Assert.assertTrue(sieve_primes.size() <= goldenPrimes.size());

    // since both lists are sorted, check each corresponding index against the
    // "golden" list
    for (int i = 0; i < sieve_primes.size(); i++) {
      Assert.assertEquals(goldenPrimes.get(i), sieve_primes.get(i));
    }
  }
}
