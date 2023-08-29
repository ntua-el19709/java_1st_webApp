package lab4.db.model;


public class Role {
	final Integer Id;
	final String name;
	
	public Role(Integer Id, String name) {
		super();
		this.Id = Id;
		this.name=name;
	}

	public Integer getId() {
		return Id;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
