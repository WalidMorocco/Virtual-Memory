import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OptimalAlgorithm {
    private int numPageFrames;
    private int[] pageFrames;
    private int numPageFaults;
    private Queue<Integer> futureReferences;

    public OptimalAlgorithm(int numPageFrames) {
        // Initialize class variables
        this.numPageFrames = numPageFrames;
        this.pageFrames = new int[numPageFrames];
        this.numPageFaults = 0;
        this.futureReferences = new LinkedList<Integer>(); // Linked List to have a flexible structure, going to be transformed to a queue
    }

    public void run(int[] pageReferences) {
    	
        // Loop through each page reference in the input array
        for (int i = 0; i < pageReferences.length; i++) {
            int page = pageReferences[i]; 
            boolean pageFault = true; // Assume page fault until proven otherwise
            boolean found = false; // Boolean indicator for whether page is found in memory
            int j = 0; // Track current index in pageFrames
            
            // Search for page in memory
            while (!found && j < numPageFrames) {
                if (pageFrames[j] == page) {
                	
                    // If page is found in memory, mark as not a page fault
                    pageFault = false;
                    found = true;
                }
                j++;
            }
            
            // If page is not found in memory, replace a page with the least future references
            if (pageFault) {
            	
                // If futureReferences queue is empty, populate it with future page references
                if (futureReferences.isEmpty()) {
                    futureReferences.addAll(IntStream.of(Arrays.copyOfRange(pageReferences, i + 1, pageReferences.length)).boxed().collect(Collectors.toList()));
                }
                int maxIndex = -1; // Index of page frame to replace
                int maxFutureReference = -1; // Furthest future reference for pages not found in futureReferences
                // Search for the page in memory with the least future references
                for (j = 0; j < numPageFrames; j++) {
                    int pageFrame = pageFrames[j];
                    // If a page is not found in future references, choose it as the replacement
                    if (!futureReferences.contains(pageFrame)) {
                        maxIndex = j;
                        break;
                    }
                    int futureIndex = ((LinkedList<Integer>)futureReferences).indexOf(pageFrame);
                    // If a page is found in future references, choose it as the replacement if it has the furthest future reference
                    if (futureIndex > maxFutureReference) {
                        maxIndex = j;
                        maxFutureReference = futureIndex;
                    }
                }
                
                // Replace the chosen page frame with the new page and increment page fault count
                pageFrames[maxIndex] = page;
                numPageFaults++;
            }
            
            // Remove the current page from futureReferences queue and output current state of pageFrames array
            futureReferences.remove(page);
            System.out.println("Page Reference: " + page + ", Page Frames: " + Arrays.toString(pageFrames));
        }
        
        // Output total number of page faults
        System.out.println("Number of Page Faults: " + numPageFaults);
    }
}
