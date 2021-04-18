
import java.io.Serializable;

public class CarW extends Cargo_Carrier implements Serializable{
		private static final long serialVersionUID = 1L;	
		
		public CarW(String From, String To, double w, double Dist) {
			super(From, To, w, Dist, Cargo_Carrier.Type.Car);
		}
		
		public CarW() {}
}