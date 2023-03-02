
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
    
    // We are using a hashmap with the let as the page number and the value as a counter to verify how much a page ahs been accessed

    public void run(int[] pageReferences) {
    	
    	// Loop through the page references
        for (int i = 0; i < pageReferences.length; i++) {
            int page = pageReferences[i];
            boolean pageFault = true; // Set the page fault as true until a page is found
            boolean pageFound = false; // Indicator for us to avoid using a break statement because it's bad
            
            // Loop through all page frames to see if the page is in the memory
            for (int j = 0; j < numPageFrames && !pageFound; j++) {
            	
            	// If page is found, update its count in the pageCounts map and set page fault to false
                if (pageFrames[j] == page) {
                    pageCounts.put(page, pageCounts.get(page) + 1); 
                    pageFault = false; 
                    pageFound = true; // Exit the for loop 
                }
            }
            
            // If page is not found in memory, find the least frequently used page to replace
            if (pageFault) {
                int minCount = Integer.MAX_VALUE; // Max value of an int
                int minIndex = -1; // Minimum value is 0 but we do not want to reach it
                boolean pageReplaced = false; // Same as page found
                
                for (int j = 0; j < numPageFrames && !pageReplaced; j++) {
                    int pageFrame = pageFrames[j];
                    
                    // If page frame has not been accessed before use it as the replacement
                    if (!pageCounts.containsKey(pageFrame)) {
                        minIndex = j;
                        pageReplaced = true; // Exit the for loop
                    } 
                    
                    // If it has been accessed, find the page frame with the lowest count
                    else {
                    	int count = pageCounts.get(pageFrame);
                        if (count < minCount) {
                            minCount = count;
                            minIndex = j;
                        }
                    }
                    
                }
                // Replace the page in the selected page frame with the new page
                pageFrames[minIndex] = page;
                
                // Add the new page to the pageCounts map with a count of 1
                pageCounts.put(page, 1);
                numPageFaults++;
            }
            
            System.out.println("Page Reference: " + page + ", Page Frames: " + Arrays.toString(pageFrames));
        }
        
        System.out.println("Number of Page Faults: " + numPageFaults);
    }
}

