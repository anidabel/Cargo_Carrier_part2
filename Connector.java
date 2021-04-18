
import java.io.*;

public class Connector {
	private String filename;
	
	public Connector (String fn) {filename = fn;} 
	
	public void write(Cargo_Carrier[] group) throws IOException {
		FileOutputStream fos = new FileOutputStream(filename);
		try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeInt(group.length);
			for (int i = 0; i < group.length; ++i)
				oos.writeObject(group[i]);
			oos.flush();
		}
	}
	public Cargo_Carrier[] readC() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(filename);
		try (ObjectInputStream ois = new ObjectInputStream(fis)) {
			int length = ois.readInt();
			Cargo_Carrier[] group = new Cargo_Carrier[length];
			for (int i = 0; i < length; ++i)
				group[i] = (Cargo_Carrier)ois.readObject();
			return group;
		}
	}
	
	public void write(Passenger_Carrier[] group) throws IOException {
		FileOutputStream fos = new FileOutputStream(filename);
		try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeInt(group.length);
			for (int i = 0; i < group.length; ++i)
				oos.writeObject(group[i]);
			oos.flush();
		}
	}
	public Passenger_Carrier[] readP() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(filename);
		try (ObjectInputStream ois = new ObjectInputStream(fis)) {
			int length = ois.readInt();
			Passenger_Carrier[] group = new Passenger_Carrier[length];
			for (int i = 0; i < length; ++i)
				group[i] = (Passenger_Carrier)ois.readObject();
			return group;
		}
	}
	
}
