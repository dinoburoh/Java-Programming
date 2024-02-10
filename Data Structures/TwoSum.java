import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TwoSum {
    public static void main(String[] args) {
        int[] arr = { 34, 5, 9, 2, 1, 12, 7 };
        int target = 13;
        System.out.println("Index " + Arrays.toString(returnIndices(arr, target)));
        System.out.println("Values" + Arrays.toString(returnValues(arr, target)));
    }

    public static int[] returnIndices(int[] arr, int target) {
        Map<Integer, Integer> valueIndex = new HashMap<>();
        
        for (int i = 0; i < arr.length; i++) {
            if (valueIndex.containsKey(target - arr[i]))
                return new int[]{ i, valueIndex.get(target - arr[i]) };
            valueIndex.put(arr[i], i);
        }
        return null;
    }
    public static int[] returnValues(int[] arr, int target) {
        Set<Integer> values = new HashSet<>();
        
        for (int n : arr) {
            if (values.contains(target - n))
                return new int[]{ n, target - n };
            values.add(n);
        }
        return null;
    }
}
