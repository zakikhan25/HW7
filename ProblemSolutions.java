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

    // Selection Sort implementations
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
            // Swap
            int temp = values[i];
            values[i] = values[extremeIndex];
            values[extremeIndex] = temp;
        }
    }

    // Merge Sort with divisible by k first - FIXED VERSION
    public void mergeSortDivisibleByKFirst(int[] values, int k) {
        if (values == null || values.length <= 1 || k == 0) return;
        mergeSort(values, k, 0, values.length - 1);
    }

    private void mergeSort(int[] arr, int k, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, k, left, mid);
        mergeSort(arr, k, mid + 1, right);
        merge(arr, k, left, mid, right);
    }

    private void merge(int[] arr, int k, int left, int mid, int right) {
        // Create temporary arrays
        int[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);
        
        int i = 0, j = 0, pos = left;
        
        // First place elements divisible by k from both halves
        while (i < leftArr.length && j < rightArr.length) {
            boolean leftDiv = (leftArr[i] % k == 0);
            boolean rightDiv = (rightArr[j] % k == 0);
            
            if (leftDiv && rightDiv) {
                if (leftArr[i] <= rightArr[j]) {
                    arr[pos++] = leftArr[i++];
                } else {
                    arr[pos++] = rightArr[j++];
                }
            } else if (leftDiv) {
                arr[pos++] = leftArr[i++];
            } else if (rightDiv) {
                arr[pos++] = rightArr[j++];
            } else {
                break;
            }
        }
        
        // Remaining left divisible elements
        while (i < leftArr.length && leftArr[i] % k == 0) {
            arr[pos++] = leftArr[i++];
        }
        
        // Remaining right divisible elements
        while (j < rightArr.length && rightArr[j] % k == 0) {
            arr[pos++] = rightArr[j++];
        }
        
        // Now merge non-divisible elements from both halves
        i = 0;
        j = 0;
        
        // Skip already processed divisible elements
        while (i < leftArr.length && leftArr[i] % k == 0) i++;
        while (j < rightArr.length && rightArr[j] % k == 0) j++;
        
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[pos++] = leftArr[i++];
            } else {
                arr[pos++] = rightArr[j++];
            }
        }
        
        // Remaining left elements
        while (i < leftArr.length) {
            arr[pos++] = leftArr[i++];
        }
        
        // Remaining right elements
        while (j < rightArr.length) {
            arr[pos++] = rightArr[j++];
        }
    }

    // Asteroids Destroyed problem
    public static boolean asteroidsDestroyed(int mass, int[] asteroids) {
        if (asteroids == null || asteroids.length == 0) return true;
        Arrays.sort(asteroids);
        long planetMass = mass;
        
        for (int asteroid : asteroids) {
            if (planetMass < asteroid) {
                return false;
            }
            planetMass += asteroid;
        }
        return true;
    }

    // Number of Rescue Sleds problem
    public static int numRescueSleds(int[] people, int limit) {
        if (people == null || people.length == 0) return 0;
        Arrays.sort(people);
        int sleds = 0;
        int i = 0, j = people.length - 1;
        
        while (i <= j) {
            if (people[i] + people[j] <= limit) {
                i++;
            }
            j--;
            sleds++;
        }
        return sleds;
    }
}
