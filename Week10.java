import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class data implements Serializable {
	String name;

	public data(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "data [name=" + name + "]";
	}
	
}
public class Week10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Person nick = new Person("Nick Beausoleil");
		
		writeToFile(nick);

	}
	
	public static void writeToFile(Person p) throws IOException{
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Week11.bin"));
		
		objectOutputStream.writeObject(p);
	}
	
	public static void readFile() {
		
	}

}
