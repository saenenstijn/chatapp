package domain;

public enum Role {

	//enum van role


	BIB("bibliothekaris"), LID("lid");

	private String description;

	Role(String description) {
		this.description = description;
	}
	
	Role() {
		
	}

	public String getDescription() {
		return description;
	}
}
