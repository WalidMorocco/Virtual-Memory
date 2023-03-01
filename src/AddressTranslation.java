import java.util.Random;

public class AddressTranslation {

	private int numPageFrames;

	public AddressTranslation(int numPageFrames) {
		this.numPageFrames = numPageFrames;
	}
	
	public int getPageNumber(int address) {
        return address / 256; 
    }

    public int getOffset(int address) {
        return address % 256;
    }

    public int[] generatePageReferenceStream() {
        int[] pageReferences = new int[100];
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int address = random.nextInt(65536); // 16-bit address space
            int page = getPageNumber(address);
            pageReferences[i] = page;
            int offset = getOffset(address);
            System.out.println(address + ", " + page + ", " + offset);
        }
        return pageReferences;
    }
    
}
