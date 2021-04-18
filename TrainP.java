
import java.io.Serializable;

public class TrainP extends Passenger_Carrier implements Serializable{
	private static final long serialVersionUID = 1L;	
	
	public TrainP(String From, String To, int kol, double Dist) {
		super(From, To, kol, Dist, Passenger_Carrier.Type.Train);
	}
	
	public TrainP() {}
}
