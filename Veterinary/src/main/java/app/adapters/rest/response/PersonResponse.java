package app.adapters.rest.response;

public class PersonResponse {
    private long document;
    private String name;

    public PersonResponse(long document, String name) {
        this.document = document;
        this.name = name;
    }

    public long getDocument() { return document; }
    public String getName() { return name; }

	public void setDocument(long document) {
		this.document = document;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
    
    
}
