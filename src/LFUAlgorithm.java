
import java.util.*;

public class LFUAlgorithm {
    private int numPageFrames;
    private int[] pageFrames;
    private int numPageFaults;
    private Map<Integer, Integer> pageCounts;

    public LFUAlgorithm(int numPageFrames) {
        this.numPageFrames = numPageFrames;
        this.pageFrames = new int[numPageFrames];
        this.numPageFaults = 0;
        this.pageCounts = new HashMap<Integer, Integer>();
    }

    public void run(int[] pageReferences) {
        for (int i = 0; i < pageReferences.length; i++) {
            int page = pageReferences[i];
            boolean pageFault = true;
            for (int j = 0; j < numPageFrames; j++) {
                if (pageFrames[j] == page) {
                    pageFault = false;
                    pageCounts.put(page, pageCounts.get(page) + 1);
                    break;
                }
            }
            if (pageFault) {
                int minCount = Integer.MAX_VALUE;
                int minIndex = -1;
                for (int j = 0; j < numPageFrames; j++) {
                    int pageFrame = pageFrames[j];
                    if (!pageCounts.containsKey(pageFrame)) {
                        minIndex = j;
                        break;
                    }
                    int count = pageCounts.get(pageFrame);
                    if (count < minCount) {
                        minCount = count;
                        minIndex = j;
                    }
                }
                pageFrames[minIndex] = page;
                pageCounts.put(page, 1);
                numPageFaults++;
            }
            System.out.println("Page Reference: " + page + ", Page Frames: " + Arrays.toString(pageFrames));
        }
        System.out.println("Number of Page Faults: " + numPageFaults);
    }
}

