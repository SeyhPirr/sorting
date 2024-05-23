import java.util.concurrent.RecursiveAction;

public class QuickSortTask extends RecursiveAction {
    private static final int THRESHOLD = 5;
    private int[] array;
    private int left;
    private int right;

    public QuickSortTask(int[] array, int left, int right) {
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

    private int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, right);
        return i + 1;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void insertionSort(int[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= left && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
}
