import java.util.Arrays;

public class FIFOAlgorithm {
    private int numPageFrames;
    private int[] pageFrames;
    private int numPageFaults;

    public FIFOAlgorithm(int numPageFrames) {
        this.numPageFrames = numPageFrames;
        this.pageFrames = new int[numPageFrames];
        this.numPageFaults = 0;
    }

    public void run(int[] pageReferences) {
        int pointer = 0;
        for (int i = 0; i < pageReferences.length; i++) {
            int page = pageReferences[i];
            boolean pageFault = true;
            for (int j = 0; j < numPageFrames; j++) {
                if (pageFrames[j] == page) {
                    pageFault = false;
                    break;
                }
            }
            if (pageFault) {
                pageFrames[pointer] = page;
                pointer = (pointer + 1) % numPageFrames;
                numPageFaults++;
            }
            System.out.println("Page Reference: " + page + ", Page Frames: " + Arrays.toString(pageFrames));
        }
        System.out.println("Number of Page Faults: " + numPageFaults);
    }
}

