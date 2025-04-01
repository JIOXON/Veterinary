package app.domain.models;

public class PetOwner extends Person{
	private long OwnerId;
	
	public long getOwnerId() {
		return OwnerId;
	}

	public void setOwnerId(long OwnerId) {
		this.OwnerId = OwnerId;
	}

	public PetOwner() {
		
	}

	@Override
	public String toString() {
		return "PetOwner [OwnerId=" + OwnerId + super.toString() +"]";
	}
	
	
}
