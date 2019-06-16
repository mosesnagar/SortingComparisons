
import Plotter.Plotter;

import java.util.Random;


public class SortingComparisons {

    final static int INSERTION_VS_QUICK_LENGTH = 12;
    final static int MERGE_VS_QUICK_LENGTH = 15;
    final static int INSERTION_VS_QUICK_SORTED_LENGTH = 12;
    final static int ARBITRARY_VS_RANDOM_LENGTH = 16;
    final static int COUNTING_VS_QUICK_LENGTH = 15;
    final static double T = 600.0;


    /**
     * Choose the rightmost element of the subarray to be the pivot.
     * Place the elements which are smaller than the pivot at the left of it,
     * and the elements which are bigger than the pivot at the right of it.
     *
     * @param arr   - the array to be sorted
     * @param start - the first index
     * @param end   - the last index
     * @return - return the position of the pivot element
     */
    private static int partitionWithArbitraryPivot(double[] arr, int start, int end) {
        double pivot = arr[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                double temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        arr[end] = arr[i + 1];
        arr[i + 1] = pivot;
        return i + 1;
    }

    /**
     * Sorts a given range of array, between start and end, using the quick sort algorithm.
     * At each stage the pivot is chosen to be the rightmost element of the subarray.
     *
     * @param arr   - the array to be sorted
     * @param start - the first index
     * @param end   - the last index
     */
    private static void quickSortArbitraryPivot(double[] arr, int start, int end) {
        if (start < end) {
            int q = partitionWithArbitraryPivot(arr, start, end);
            quickSortArbitraryPivot(arr, start, q - 1);
            quickSortArbitraryPivot(arr, q + 1, end);
        }
    }


    /**
     * Sorts a given array using the quick sort algorithm.
     * At each stage the pivot is chosen to be the rightmost element of the subarray.
     * <p>
     * Should run in average complexity of O(nlog(n)), and worst case complexity of O(n^2)
     *
     * @param arr - the array to be sorted
     */
    public static void quickSortArbitraryPivot(double[] arr) {
        quickSortArbitraryPivot(arr, 0, arr.length - 1);
    }


    /**
     * Sorts a given array using the quick sort algorithm.
     * At each stage the pivot is chosen in the following way:
     * Choose a random index from the range, the element at this index is the pivot.
     * <p>
     * Should run in average complexity of O(nlog(n)), and worst case complexity of O(n^2)
     *
     * @param arr - the array to be sorted
     */
    public static void quickSortRandomPivot(double[] arr) {
        quickSortRandomPivot(arr, 0, arr.length - 1);
    }


    /**
     * Sorts a given range of array, between start and end, using the quick sort algorithm.
     * At each stage the pivot is chosen in the following way:
     * Choose a random index from the range, the element at this index is the pivot.
     *
     * @param arr   - the array to be sorted
     * @param start - the first index
     * @param end   - the last index
     */
    private static void quickSortRandomPivot(double[] arr, int start, int end) {
        if (start < end) {
            int q = partitionWithRandomPivot(arr, start, end);
            quickSortRandomPivot(arr, start, q - 1);
            quickSortRandomPivot(arr, q + 1, end);
        }
    }

    /**
     * Choose a random element of the subarray to be the pivot, using Math.random.
     * Place the elements which are smaller than the pivot at the left of it,
     * and the elements which are bigger than the pivot at the right of it.
     *
     * @param arr   - the array to be sorted
     * @param start - the first index
     * @param end   - the last index
     * @return - return the position of the pivot element.
     */
    private static int partitionWithRandomPivot(double[] arr, int start, int end) {
        int random = (int) (start + (Math.random() * (end - start + 1)));
        double temp = arr[end];
        arr[end] = arr[random];
        arr[random] = temp;
        return partitionWithArbitraryPivot(arr, start, end);
    }

    /**
     * Sorts a given array using the merge sort algorithm.
     * <p>
     * Should run in complexity O(nlog(n)) in the worst case.
     *
     * @param arr - the array to be sorted
     */
    public static void mergeSort(double[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * Sorts a given array, between start and end, using the merge sort algorithm.
     *
     * @param arr   - the array to be sorted
     * @param start - the first index
     * @param end   - the last index
     */
    private static void mergeSort(double[] arr, int start, int end) {
        if (start < end) {
            int q = (start + end) / 2;
            mergeSort(arr, start, q);
            mergeSort(arr, q + 1, end);
            merge(arr, start, q, end);
        }
    }

    /**
     * Divide the array to 2 sorted sub-arrays - from the start to q, and from q to end.
     * Merge the two sorted sub-arrays to produce the sorted array.
     *
     * @param arr   - the array to be sorted
     * @param start - the first index
     * @param q     - the middle index
     * @param end   - the last index
     */
    private static void merge(double[] arr, int start, int q, int end) {
        int n1 = q - start + 1;
        int n2 = end - q;
        double[] left = new double[n1 + 1];
        double[] right = new double[n2 + 1];
        for (int i = 0; i < n1; i++) {
            left[i] = arr[start + i];
        }
        for (int i = 0; i < n2; i++) {
            right[i] = arr[q + i + 1];
        }
        left[n1] = 1.0 / 0;
        right[n2] = 1.0 / 0;
        int i = 0, j = 0;
        for (int k = start; k <= end; k++) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
        }
    }

    /**
     * Sorts a given array, using the counting sort algorithm.
     * You may assume that all elements in the array are between 0 and k (not including k).
     * <p>
     * Should run in complexity O(n + k) in the worst case.
     *
     * @param arr
     * @param k   - an upper bound for all elements in the array.
     */
    public static void countingSort(int[] arr, int k) {
        int[] helper = new int[k];
        for (int i = 0; i < arr.length; i++) {
            helper[arr[i]]++;
        }
        int index = 0;
        for (int i = 0; i < helper.length; i++) {
            while (helper[i] > 0) {
                arr[index++] = i;
                helper[i]--;
            }
        }
    }

    /**
     * Sorts a given array using insertion sort.
     * <p>
     * The algorithm should run in complexity O(n^2) in the worst case.
     *
     * @param arr - the array to be sorted
     */
    public static void insertionSort(double[] arr) {
        int i = 1;
        while (i < arr.length) {
            int j = i;
            while (j > 0 && arr[j - 1] > arr[j]) {
                double temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                j--;
            }
            i++;
        }
    }

    public static void main(String[] args) {
        insertionVsQuick();
        mergeVsQuick();
        insertionVsQuickOnSortedArray();
        countingVsQuick();
        arbitraryPivotVsRandomPivot();
    }

    private static void countingVsQuick() {
        double[] quickTimes = new double[COUNTING_VS_QUICK_LENGTH];
        double[] countingTimes = new double[COUNTING_VS_QUICK_LENGTH];
        long startTime, endTime;
        Random r = new Random();
        for (int i = 0; i < COUNTING_VS_QUICK_LENGTH; i++) {
            long sumQuick = 0;
            long sumCounting = 0;
            for (int k = 0; k < T; k++) {
                int size = (int) Math.pow(2, i);
                double[] a = new double[size];
                int[] b = new int[size];
                for (int j = 0; j < a.length; j++) {
                    b[j] = r.nextInt(size);
                    a[j] = b[j];
                }
                startTime = System.currentTimeMillis();
                quickSortArbitraryPivot(a);
                endTime = System.currentTimeMillis();
                sumQuick += endTime - startTime;
                startTime = System.currentTimeMillis();
                countingSort(b, size);
                endTime = System.currentTimeMillis();
                sumCounting += endTime - startTime;
            }
            quickTimes[i] = sumQuick / T;
            countingTimes[i] = sumCounting / T;
        }
        Plotter.plot("Counting sort on arrays with elements < n", countingTimes, "Quick sort on arrays with elements < n", quickTimes);

    }

    /**
     * Compares the selection sort algorithm against quick sort on random arrays
     */
    public static void insertionVsQuick() {
        double[] quickTimes = new double[INSERTION_VS_QUICK_LENGTH];
        double[] insertionTimes = new double[INSERTION_VS_QUICK_LENGTH];
        long startTime, endTime;
        Random r = new Random();
        for (int i = 0; i < INSERTION_VS_QUICK_LENGTH; i++) {
            long sumQuick = 0;
            long sumInsertion = 0;
            for (int k = 0; k < T; k++) {
                int size = (int) Math.pow(2, i);
                double[] a = new double[size];
                double[] b = new double[size];
                for (int j = 0; j < a.length; j++) {
                    a[j] = r.nextGaussian() * 5000;
                    b[j] = a[j];
                }
                startTime = System.currentTimeMillis();
                quickSortArbitraryPivot(a);
                endTime = System.currentTimeMillis();
                sumQuick += endTime - startTime;
                startTime = System.currentTimeMillis();
                insertionSort(b);
                endTime = System.currentTimeMillis();
                sumInsertion += endTime - startTime;
            }
            quickTimes[i] = sumQuick / T;
            insertionTimes[i] = sumInsertion / T;
        }
        Plotter.plot("quick sort on random array", quickTimes, "insertion sort on random array", insertionTimes);
    }

    /**
     * Compares the merge sort algorithm against quick sort on random arrays
     */
    public static void mergeVsQuick() {
        double[] quickTimes = new double[MERGE_VS_QUICK_LENGTH];
        double[] mergeTimes = new double[MERGE_VS_QUICK_LENGTH];
        long startTime, endTime;
        Random r = new Random();
        for (int i = 0; i < MERGE_VS_QUICK_LENGTH; i++) {
            long sumQuick = 0;
            long sumMerge = 0;
            for (int k = 0; k < T; k++) {
                int size = (int) Math.pow(2, i);
                double[] a = new double[size];
                double[] b = new double[size];
                for (int j = 0; j < a.length; j++) {
                    a[j] = r.nextGaussian() * 5000;
                    b[j] = a[j];
                }
                startTime = System.currentTimeMillis();
                quickSortArbitraryPivot(a);
                endTime = System.currentTimeMillis();
                sumQuick += endTime - startTime;
                startTime = System.currentTimeMillis();
                mergeSort(b);
                endTime = System.currentTimeMillis();
                sumMerge += endTime - startTime;
            }
            quickTimes[i] = sumQuick / T;
            mergeTimes[i] = sumMerge / T;
        }
        Plotter.plot("quick sort on random array", quickTimes, "merge sort on random array", mergeTimes);
    }

    /**
     * Compares the merge sort algorithm against quick sort on pre-sorted arrays
     */
    public static void insertionVsQuickOnSortedArray() {
        double[] quickTimes = new double[INSERTION_VS_QUICK_SORTED_LENGTH];
        double[] insertionTimes = new double[INSERTION_VS_QUICK_SORTED_LENGTH];
        long startTime, endTime;
        for (int i = 0; i < INSERTION_VS_QUICK_SORTED_LENGTH; i++) {
            long sumQuick = 0;
            long sumInsertion = 0;
            for (int k = 0; k < T; k++) {
                int size = (int) Math.pow(2, i);
                double[] a = new double[size];
                double[] b = new double[size];
                for (int j = 0; j < a.length; j++) {
                    a[j] = j;
                    b[j] = j;
                }
                startTime = System.currentTimeMillis();
                quickSortArbitraryPivot(a);
                endTime = System.currentTimeMillis();
                sumQuick += endTime - startTime;
                startTime = System.currentTimeMillis();
                insertionSort(b);
                endTime = System.currentTimeMillis();
                sumInsertion += endTime - startTime;
            }
            quickTimes[i] = sumQuick / T;
            insertionTimes[i] = sumInsertion / T;
        }
        Plotter.plot("quick sort on sorted array", quickTimes, "insertion sort on sorted array", insertionTimes);
    }

    /**
     * Compares the quick sort algorithm once with a choice of an arbitrary pivot and once with a choice of a random pivot
     */
    public static void arbitraryPivotVsRandomPivot() {
        double[] arbitraryTimes = new double[ARBITRARY_VS_RANDOM_LENGTH];
        double[] randomTimes = new double[ARBITRARY_VS_RANDOM_LENGTH];
        long startTime, endTime;
        Random r = new Random();
        for (int i = 0; i < ARBITRARY_VS_RANDOM_LENGTH; i++) {
            long sumArbitrary = 0;
            long sumRandom = 0;
            for (int k = 0; k < T; k++) {
                int size = (int) Math.pow(2, i);
                double[] a = new double[size];
                double[] b = new double[size];
                for (int j = 0; j < a.length; j++) {
                    a[j] = r.nextGaussian() * 5000;
                    b[j] = a[j];
                }
                startTime = System.currentTimeMillis();
                quickSortArbitraryPivot(a);
                endTime = System.currentTimeMillis();
                sumArbitrary += endTime - startTime;
                startTime = System.currentTimeMillis();
                quickSortRandomPivot(b);
                endTime = System.currentTimeMillis();
                sumRandom += endTime - startTime;
            }
            arbitraryTimes[i] = sumArbitrary / T;
            randomTimes[i] = sumRandom / T;
        }
        Plotter.plot("quick sort with an arbitrary pivot", arbitraryTimes, "quick sort with a random pivot", randomTimes);
    }


}
