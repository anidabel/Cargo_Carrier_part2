
import java.io.Serializable;

public class CarP extends Passenger_Carrier implements Serializable{
	private static final long serialVersionUID = 1L;	
	
	public CarP(String From, String To, int kol, double Dist) {
		super(From, To, kol, Dist, Passenger_Carrier.Type.Car);
	}
	
	public CarP() {}

}