
public class Driver {
    public static void main(String[] args) {
    	
    	// Ask the user the number of page frames
    	PageFrame pageFrame = new PageFrame();
        int numPageFrames = pageFrame.getNumPageFrames();
        
        // Run the address translation
        System.out.println("\nAddress Translation:\n");
		AddressTranslation addressTranslation = new AddressTranslation();
		int[] pageReferences = addressTranslation.generatePageReferenceStream();
		
		System.out.println("\n--------------------------------------------");
		
		// Apply the FIFO algorithm
		System.out.println("\nFifo Algorithm:\n");
        FIFOAlgorithm fifoAlgorithm = new FIFOAlgorithm(numPageFrames);
        fifoAlgorithm.run(pageReferences);
        
        System.out.println("\n--------------------------------------------");
        
        // Apply the LFU algorithm
        System.out.println("\nLFU Algorithm:\n");
        LFUAlgorithm lfuAlgorithm = new LFUAlgorithm(numPageFrames);
        lfuAlgorithm.run(pageReferences);
        
        System.out.println("\n--------------------------------------------");
        
        // Apply the Optimal algorithm
        System.out.println("\nOptimal Replacement Algorithm:\n");
        OptimalAlgorithm optimalAlgorithm = new OptimalAlgorithm(numPageFrames);
        optimalAlgorithm.run(pageReferences);
    }
}
 