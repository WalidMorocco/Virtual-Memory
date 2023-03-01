
public class Driver {
    public static void main(String[] args) {
    	
    	// Ask the user the number of page frames
    	PageFrame pageFrame = new PageFrame();
        int numPageFrames = pageFrame.getNumPageFrames();
        
        // Run the address translation
		AddressTranslation addressTranslation = new AddressTranslation(numPageFrames);
		int[] pageReferences = addressTranslation.generatePageReferenceStream();
		
		// Apply the FIFO algorithm
        FIFOAlgorithm fifoAlgorithm = new FIFOAlgorithm(numPageFrames);
        fifoAlgorithm.run(pageReferences);
    }
}
 