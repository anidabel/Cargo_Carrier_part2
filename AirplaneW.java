
import java.io.Serializable;

public class AirplaneW extends Cargo_Carrier implements Serializable{
	private static final long serialVersionUID = 1L;	
	
	public AirplaneW(String From, String To, double w, double Dist) {
		super(From, To, w, Dist, Cargo_Carrier.Type.Airplane);
	}
	
	public AirplaneW() {}
}

