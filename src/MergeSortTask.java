import java.util.concurrent.RecursiveAction;

public class MergeSortTask extends RecursiveAction {
    private static final int THRESHOLD = 100;
    private Comparable[] array;
    private int left;
    private int right;

    public MergeSortTask(Comparable[] array, int left, int right) {
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

    private void merge(Comparable[] array, int left, int mid, int right) {
        Comparable[] temp = new Comparable[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (array[i].compareTo(array[j]) <= 0) {
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