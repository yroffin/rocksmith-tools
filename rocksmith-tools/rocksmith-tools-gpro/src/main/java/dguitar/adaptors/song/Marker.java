package dguitar.adaptors.song;

public class Marker {

	String name;

	public Marker(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Marker [name=" + name + "]";
	}

}
