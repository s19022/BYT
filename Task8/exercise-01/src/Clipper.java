public class Clipper {
    public void clip(int[] arr, int clipLimit){
        // Clip "too-large" values
        for (int i = 0; i < arr.length; i++)
            if (arr[i] > clipLimit)
                arr[i] = clipLimit;
    }
}
