import java.util.LinkedList;

public class FIFOAlgorithm {
    private int numPageFrames;
    private int numPageFaults;

    // Constructor to get variables from driver and set the number of page faults to 0
    public FIFOAlgorithm(int numPageFrames) {
        this.numPageFrames = numPageFrames;
        this.numPageFaults = 0;
    }

    public void run(int[] pageReferences) {
    	// Initialize a LinkedList to represent the page frames in memory
        LinkedList<Integer> pageFrames = new LinkedList<>();

        // Iterate over each page reference in the input array
        for (int i = 0; i < pageReferences.length; i++) {

            // Get the current page reference
            int page = pageReferences[i];

            // If the page is not already in memory, add it to the end of the page frames list
            if (!pageFrames.contains(page)) {

                // If the number of page frames in memory is equal to the maximum number of page frames, remove the first page in the list
                if (pageFrames.size() == numPageFrames) {
                    pageFrames.removeFirst();
                }

                // Add the current page to the end of the page frames list
                pageFrames.addLast(page);
                
                // Increment the number of page faults
                numPageFaults++;
            }
            // Print out the current state of the page frames list after each page reference
            System.out.println("Page Reference: " + page + ", Page Frames: " + pageFrames.toString());
        }
        // Print out the total number of page faults at the end
        System.out.println("Number of Page Faults: " + numPageFaults);
    }
}

