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

    // Merge Sort with divisible by k first
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
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, t = 0;
        
        // First place elements divisible by k
        while (i <= mid && j <= right) {
            boolean iDiv = arr[i] % k == 0;
            boolean jDiv = arr[j] % k == 0;
            
            if (iDiv && jDiv) {
                temp[t++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
            } else if (iDiv) {
                temp[t++] = arr[i++];
            } else if (jDiv) {
                temp[t++] = arr[j++];
            } else {
                break;
            }
        }
        
        // Remaining left divisible elements
        while (i <= mid && arr[i] % k == 0) temp[t++] = arr[i++];
        
        // Remaining right divisible elements
        while (j <= right && arr[j] % k == 0) temp[t++] = arr[j++];
        
        // Then place non-divisible elements in order
        i = left;
        j = mid + 1;
        
        // Skip already processed divisible elements
        while (i <= mid && arr[i] % k == 0) i++;
        while (j <= right && arr[j] % k == 0) j++;
        
        while (i <= mid && j <= right) {
            temp[t++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        
        // Remaining elements
        while (i <= mid) temp[t++] = arr[i++];
        while (j <= right) temp[t++] = arr[j++];
        
        // Copy back to original array
        System.arraycopy(temp, 0, arr, left, temp.length);
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
