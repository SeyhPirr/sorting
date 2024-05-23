import java.util.concurrent.RecursiveAction;

public class QuickSortTask extends RecursiveAction {
    private static final int THRESHOLD = 100;
    private Comparable[] array;
    private int left;
    private int right;

    public QuickSortTask(Comparable[] array, int left, int right) {
        this.array = array;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {
        if (right - left < THRESHOLD) {
            insertionSort(array, left, right);
        } else {
            int pivotIndex = partition(array, left, right);
            QuickSortTask leftTask = new QuickSortTask(array, left, pivotIndex - 1);
            QuickSortTask rightTask = new QuickSortTask(array, pivotIndex + 1, right);
            invokeAll(leftTask, rightTask);
        }
    }

    private int partition(Comparable[] array, int left, int right) {
        Comparable pivot = array[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (array[j].compareTo(pivot) < 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, right);
        return i + 1;
    }

    private void swap(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void insertionSort(Comparable[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            Comparable key = array[i];
            int j = i - 1;
            while (j >= left && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
}
