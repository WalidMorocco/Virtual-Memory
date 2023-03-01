
public class Driver {
    public static void main(String[] args) {
    	
    	// Ask the user the number of page frames
    	PageFrame pageFrame = new PageFrame();
        int numPageFrames = pageFrame.getNumPageFrames();
        
        // Run the address translation
		AddressTranslation addressTranslation = new AddressTranslation(numPageFrames);
		int[] pageReferences = addressTranslation.generatePageReferenceStream();
		
		for (int i = 0; i < pageReferences.length; i++) {
			System.out.println(pageReferences[i]);
		}
    }
}
 