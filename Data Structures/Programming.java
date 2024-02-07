import java.util.Arrays;

public class Programming {

    public static int count(int n, int k) {
        int count = 0;

        if (n == 0 && k == 0)
            return 1;

        while (n > 0) {
            if (n % 10 == k)
                count += 1;
            n /= 10;
        }
        return count;
    }

    public static class Sort {
        int index;
        int freq;
        int value;

        Sort(int index, int frequency, int value) {
            this.index = index;
            this.freq = frequency;
            this.value = value;
        }
    }

    public static void swap(Sort[] sortArray, int i, int j){
        Sort temp;
        temp = sortArray[i];
        sortArray[i] = sortArray[j];
        sortArray[j] = temp;
    }

    public static void sortArrayBasedOnOccurence(int[] arr, int k) {

        Sort[] sortArray = new Sort[arr.length];

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            count = count(arr[i], k);
            sortArray[i] = new Sort(i, count, arr[i]);
        }

        Arrays.sort(sortArray, (p1, p2) -> {
            if (p1.freq == p2.freq)
                return p1.value - p2.value;
            return p1.freq - p2.freq;
        });
        

        // for (int i = 0; i < sortArray.length - 1; i++) {
        //     for (int j = i + 1; j < sortArray.length; j++) {
        //         if (sortArray[i].freq == sortArray[j].freq && sortArray[i].value > sortArray[j].value) {
        //                 swap(sortArray, i, j);
        //         }

        //         else if (sortArray[i].freq > sortArray[j].freq) {
        //             swap(sortArray, i, j);
        //         }
        //     }
        // }

        for (Sort s : sortArray) {
            System.out.print(arr[s.index] + " ");
        }

        
    }
    
    public static void main(String[] args) {
        
        int[] arr = { 37, 100, 5502, 1505, 7455, 37555, 125, 12, 65 };
        int k = 5;
        
        System.out.println("Given array: " + Arrays.toString(arr));
        System.out.print("Sorted array: ");
        sortArrayBasedOnOccurence(arr, k);

    }
}