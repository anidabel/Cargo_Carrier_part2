
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class test_2 {
	public static Cargo_Carrier[] CreateGroupCargo() {
		Cargo_Carrier[] A = new Cargo_Carrier[5];
		A[0] = new CarW(AppLocale.getString("Moscow"), AppLocale.getString("Minsk"), 11, 716);
		A[1] = new AirplaneW(AppLocale.getString("Minsk"), AppLocale.getString("Berlin"), 10.5, 970);
		A[2] = new TrainW(AppLocale.getString("Peter"), AppLocale.getString("Berlin"), 60, 1400);
		A[3] = new AirplaneW(AppLocale.getString("Minsk"), AppLocale.getString("Peter"), 120, 690);
		A[4] = new TrainW(AppLocale.getString("Minsk"), AppLocale.getString("Baranovichi"), 8, 140);

		return A;
	}
	public static Passenger_Carrier[] CreateGroupPass() {
		Passenger_Carrier[] A = new Passenger_Carrier[5];
		A[0] = new CarP(AppLocale.getString("Moscow"), AppLocale.getString("Minsk"), 8, 716);
		A[1] = new AirplaneP(AppLocale.getString("Minsk"), AppLocale.getString("Berlin"), 10, 970);
		A[2] = new TrainP(AppLocale.getString("Peter"), AppLocale.getString("Berlin"), 60, 1400);
		A[3] = new AirplaneP(AppLocale.getString("Minsk"), AppLocale.getString("Peter"), 120, 690);
		A[4] = new TrainP(AppLocale.getString("Minsk"), AppLocale.getString("Baranovichi"), 8, 140);

		return A;
	}

	static void setupConsole (String[] args) {
		if ( args.length >= 2 )
			if ( args[0].compareTo("-encoding") == 0 )
				try {
					System.setOut(new PrintStream(System.out, true, args[1]));
				}
				catch (UnsupportedEncodingException e) {
					System.err.println("Unsupported encoding: " + args[1]);
					e.printStackTrace();
					System.exit(1);
				}
	}
 	
 	static Locale createLocale (String[] args) {
		if (args.length == 2)
			return new Locale(args[0], args[1]);
		if ( args.length == 4 )
			return new Locale(args[2], args[3]);
		throw new IllegalArgumentException("Invalid arguments for java.util.Locale.Locale(String language, String country).");
	}
 	
	public static void main(String[] args) {
		try {
			setupConsole(args);
			Locale loc = createLocale(args);
			AppLocale.set(loc);
			Connector connector = new Connector("groupC.dat");
			
			connector.write(CreateGroupCargo());
			Cargo_Carrier[] groupCargo = connector.readC();
			System.out.println(AppLocale.getString("The_group_of_Cargo_Carriers") + ":");
			for (Cargo_Carrier l: groupCargo)
				System.out.println(l.toString());
			System.out.println(AppLocale.getString("Create_object_with_exception") + ":");
			Cargo_Carrier c = new CarW(AppLocale.getString("Minsk"), AppLocale.getString("Vitebsk"), 999, -1);
			System.out.println(c);
			System.out.println();
			////////////////////////////////////////////////////////////////////
			Connector con = new Connector("groupP.dat");
			con.write(CreateGroupPass());
			Passenger_Carrier[] groupPas = con.readP();
			System.out.println(AppLocale.getString("The_group_of_Passenger_Carriers") + ":");
			for (Passenger_Carrier l: groupPas)
				System.out.println(l.toString());
			/*System.out.println("Create_object_with_exception:");
			Passenger_Carrier p = new CarP(AppLocale.getString("Minsk"), AppLocale.getString("Vitebsk"), 70, 180);
			System.out.println(p);*/
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
