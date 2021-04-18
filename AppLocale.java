
import java.util.*;

public class AppLocale {
	
	private static final String baseName = "lab06_2.LangBase";
	private static Locale loc = Locale.getDefault();
	private static ResourceBundle res = ResourceBundle.getBundle(baseName, loc);
	
	static Locale get() {return loc;}
	static void set(Locale l) {
		loc = l;
		res = ResourceBundle.getBundle(baseName, loc);
	}
	static ResourceBundle getBundle() {return res; }
	static String getString (String key) {return res.getString(key);}
	
	public static final String FromTown = "From";
	public static final String ToTown = "To";
	public static final String Distance = "Distance";
	public static final String Time = "Time";
	public static final String Cost = "Cost";
	public static final String Type = "Type";
	public static final String Date = "Date";
	public static final String Cargo_Weight = "Weight";
	public static final String Kol_Pas = "Number_of_passengers";
	
}
