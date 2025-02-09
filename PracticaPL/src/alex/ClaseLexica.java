package alex;

public enum ClaseLexica {

	EOF("EOF");
	
	private String image;
	
	public String getImage() {
		return image;
	}
	
	private ClaseLexica() {
		image = toString();
	}
	
	private ClaseLexica(String image) {
		this.image = image;  
	}
}
