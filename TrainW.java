
import java.io.Serializable;

public class TrainW extends Cargo_Carrier implements Serializable{
	private static final long serialVersionUID = 1L;	
	
	public TrainW(String From, String To,double w, double Dist) {
		super(From, To, w, Dist, Cargo_Carrier.Type.Train);
	}
	
	public TrainW() {}
}