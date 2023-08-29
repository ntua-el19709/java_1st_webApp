package lab4.db.model;

public class Status {

	private final Integer id;
	private final String typeName;
	
	public Status(Integer id, String typeName) {
		this.id = id;
		this.typeName = typeName;
	}
	
	public Integer getId() {
		return this.id;
	}
	public String getName() {
		return this.typeName;
	}
}
