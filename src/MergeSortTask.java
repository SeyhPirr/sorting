import java.util.concurrent.RecursiveAction;

public class MergeSortTask extends RecursiveAction {
    private static final int THRESHOLD = 5;
    private int[] array;
    private int left;
    private int right;

    public MergeSortTask(int[] array, int left, int right) {
        this.array = array;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {
        if (right - left < THRESHOLD) {
            insertionSort(array, left, right);
        } else {
            int mid = (left + right) / 2;
            MergeSortTask leftTask = new MergeSortTask(array, left, mid);
            MergeSortTask rightTask = new MergeSortTask(array, mid + 1, right);
            invokeAll(leftTask, rightTask);
            merge(array, left, mid, right);
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = array[i++];
        }

        while (j <= right) {
            temp[k++] = array[j++];
        }

        System.arraycopy(temp, 0, array, left, temp.length);
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
