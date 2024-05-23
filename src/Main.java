import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        int[] quickSortArray = {5, 3, 8, 4, 2, 7, 1, 10, 6, 9};  // Example data
        int[] mergeSortArray = {5, 3, 8, 4, 2, 7, 1, 10, 6, 9};  // Example data

        // QuickSort Example
        ForkJoinPool pool = new ForkJoinPool();
        QuickSortTask quickSortTask = new QuickSortTask(quickSortArray, 0, quickSortArray.length - 1);
        pool.invoke(quickSortTask);

        // MergeSort Example
        MergeSortTask mergeSortTask = new MergeSortTask(mergeSortArray, 0, mergeSortArray.length - 1);
        pool.invoke(mergeSortTask);

        // Print the sorted arrays
        System.out.println("QuickSorted array: " + Arrays.toString(quickSortArray));
        System.out.println("MergeSorted array: " + Arrays.toString(mergeSortArray));
    }
}
