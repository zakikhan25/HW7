/******************************************************************
 *
 *   Zaki Khan / 272 001
 *
 *   This java file contains the problem solutions for the methods selectionSort,
 *   mergeSortDivisibleByKFirst, asteroidsDestroyed, and numRescueCanoes methods.
 *
 ********************************************************************/

import java.util.Arrays;

public class ProblemSolutions {

    // ========== SELECTION SORT (27 points) ==========
    public void selectionSort(int[] values) {
        selectionSort(values, true);
    }

    public static void selectionSort(int[] values, boolean ascending) {
        for (int i = 0; i < values.length - 1; i++) {
            int extremeIndex = i;
            for (int j = i + 1; j < values.length; j++) {
                if (ascending ? values[j] < values[extremeIndex] 
                             : values[j] > values[extremeIndex]) {
                    extremeIndex = j;
                }
            }
            // Swap elements
            int temp = values[i];
            values[i] = values[extremeIndex];
            values[extremeIndex] = temp;
        }
    }

    // ========== MERGE SORT DIVISIBLE BY K FIRST (19 points) ==========
    public void mergeSortDivisibleByKFirst(int[] values, int k) {
        if (values == null || values.length <= 1 || k == 0) return;
        
        // Create a copy to preserve original indices for stability
        Integer[] withIndices = new Integer[values.length];
        for (int i = 0; i < values.length; i++) {
            withIndices[i] = values[i];
        }
        
        // Custom sort that:
        // 1. Puts divisible by k first
        // 2. Maintains original order for ties
        Arrays.sort(withIndices, (a, b) -> {
            boolean aDiv = (a % k == 0);
            boolean bDiv = (b % k == 0);
            if (aDiv != bDiv) {
                return aDiv ? -1 : 1;
            }
            return 0; // Maintain original order
        });
        
        // Copy back to original array
        for (int i = 0; i < values.length; i++) {
            values[i] = withIndices[i];
        }
        
        // Now sort the divisible and non-divisible sections separately
        int divCount = 0;
        for (int num : values) {
            if (num % k == 0) divCount++;
        }
        
        Arrays.sort(values, 0, divCount); // Sort divisible portion
        Arrays.sort(values, divCount, values.length); // Sort non-divisible portion
    }

    // ========== DESTROYING ASTEROIDS (27 points) ==========
    public static boolean asteroidsDestroyed(int mass, int[] asteroids) {
        if (asteroids == null || asteroids.length == 0) return true;
        Arrays.sort(asteroids);
        long planetMass = mass;
        for (int asteroid : asteroids) {
            if (planetMass < asteroid) return false;
            planetMass += asteroid;
        }
        return true;
    }

    // ========== NUMBER OF RESCUE SLEDS (27 points) ==========
    public static int numRescueSleds(int[] people, int limit) {
        if (people == null || people.length == 0) return 0;
        Arrays.sort(people);
        int sleds = 0;
        int i = 0, j = people.length - 1;
        while (i <= j) {
            if (people[i] + people[j] <= limit) i++;
            j--;
            sleds++;
        }
        return sleds;
    }
}
