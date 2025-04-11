/******************************************************************
 *
 *   Zaki Khan / 272 001
 *
 *   This java file contains the problem solutions for the methods selectionSort,
 *   mergeSortDivisibleByKFirst, asteroidsDestroyed, and numRescueCanoes methods.
 *
 ********************************************************************/

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class ProblemSolutions {

    // ========== SELECTION SORT IMPLEMENTATION ==========
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

    // ========== MERGE SORT WITH DIVISIBLE BY K FIRST ==========
    public void mergeSortDivisibleByKFirst(int[] values, int k) {
        if (values == null || values.length <= 1 || k == 0) return;
        
        // Separate divisible and non-divisible elements
        List<Integer> divisible = new ArrayList<>();
        List<Integer> nonDivisible = new ArrayList<>();
        
        for (int num : values) {
            if (num % k == 0) {
                divisible.add(num);
            } else {
                nonDivisible.add(num);
            }
        }
        
        // Convert Lists to arrays and sort them
        int[] divArray = divisible.stream().mapToInt(i -> i).toArray();
        int[] nonDivArray = nonDivisible.stream().mapToInt(i -> i).toArray();
        
        Arrays.sort(divArray);
        Arrays.sort(nonDivArray);
        
        // Combine back into original array
        System.arraycopy(divArray, 0, values, 0, divArray.length);
        System.arraycopy(nonDivArray, 0, values, divArray.length, nonDivArray.length);
    }

    // ========== ASTEROIDS DESTROYED PROBLEM ==========
    public static boolean asteroidsDestroyed(int mass, int[] asteroids) {
        if (asteroids == null || asteroids.length == 0) return true;
        
        Arrays.sort(asteroids);
        long currentMass = mass; // Use long to prevent integer overflow
        
        for (int asteroid : asteroids) {
            if (currentMass < asteroid) {
                return false;
            }
            currentMass += asteroid;
        }
        return true;
    }

    // ========== NUMBER OF RESCUE SLEDS PROBLEM ==========
    public static int numRescueSleds(int[] people, int limit) {
        if (people == null || people.length == 0) return 0;
        
        Arrays.sort(people);
        int sleds = 0;
        int left = 0;
        int right = people.length - 1;
        
        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            sleds++;
        }
        return sleds;
    }
}
