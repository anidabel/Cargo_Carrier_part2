
import java.text.DateFormat;
import java.util.Date;
import java.io.Serializable;

public class Passenger_Carrier implements Serializable{
	
	private static final long serailVersionUID = 1L;

	private final Date date = new Date();
	public String getCreationDate() {
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT,DateFormat.DEFAULT, AppLocale.get());
		String strDate = df.format(date);
		return strDate;
	}
	
	int Kol_Pas;
	public int GetPas() {
		return Kol_Pas;
	}
	
	String FromTown;
	public String GetFrom() {
		return FromTown;
	}
	
	String ToTown;
	public String GetTo() {
		return ToTown;
	}
	
	double Distance;
	public double GetDistance() {
		return Distance;
	}
	
	public enum Type {Airplane, Train, Car};
	private Type type;
	public Type GetType() {
		return type;
	}
	
	public Passenger_Carrier () {}
	
	protected Passenger_Carrier (String From, String To, int kol, double Dist, Type t) {
		assert(validTownsName(From, To)) : "� ������ ������ ��� � ����� ������������ ��������";
		assert(validTowns(From, To)) : "��������� �� ����� ���� ������������ � ��� �� �����";
		assert(validDistance(Dist)) : "���������� ����� �������� �� ����� ���� ������������� ��� ������ ����";
		assert(kol >= 0) : "���������� ����� �� ����� ���� �������������";
		assert(validKolPeople(kol, t)) : "������ ��� ���������� �� ����� ��������� ����� ���������� �����";
		this.Distance = Dist;
		this.FromTown = From;
		this.ToTown = To;
		this.type = t;
		this.Kol_Pas = kol;
		switch(t) {
		case Airplane: {
			assert(validDistanceAir(Dist)) : "�� ������ ���������� ������� �� ������";
			break;
		}
		case Train:{
			assert(validDistanceTrain(Dist)) : "�� ����� ���������� ������ �� �����";
			break;
		}
		case Car:{
			assert(validDistanceCar(Dist)) : "�� ����� ���������� ������ �� �����";
			break;
		}
		}
	}
	
	public boolean validTownsName(String From, String To) {
		return (From != null && From.length() > 0 && To != null && To.length() > 0);
	}
	public boolean validTowns(String From, String To) {
		if (From.equals(To) == true)
			return false;
		return true;
	}
	public boolean validDistance(double Dist) {
		return (Dist > 0);
	}
	public boolean validDistanceAir(double Dist) {
		return (Dist > 4 && Dist < 10000);
	}
	public boolean validDistanceTrain(double Dist) {
		return (Dist > 2 && Dist < 6000);
	}
	public boolean validDistanceCar(double Dist) {
		return (Dist > 0 && Dist < 20000);
	}
	public boolean validKolPeople(int kol, Type t) {
		switch(t) {
		case Airplane: if (kol >= 0 && kol < 350) return true; break;
		case Train: if (kol >= 0 && kol < 500) return true; break;
		case Car: if (kol >= 0 && kol < 10) return true; break;
		}
		return false;
	}
	
	public String toString() {
		return AppLocale.getString(AppLocale.FromTown) + ": " + FromTown + "  |  " +
				AppLocale.getString(AppLocale.ToTown) + ": " + ToTown + "  |  " +
				AppLocale.getString(AppLocale.Distance) + ": " + Distance + "  |  " +
				AppLocale.getString(AppLocale.Kol_Pas) + ": " + Kol_Pas + "  |  " +
				AppLocale.getString(AppLocale.Type) + ": " + AppLocale.getString(type.toString()) + "  |  " +
				AppLocale.getString(AppLocale.Date) + ": " + getCreationDate() + "  |  " +
				AppLocale.getString(AppLocale.Time) + ": " + Get_Time()  + "  |  " +
				AppLocale.getString(AppLocale.Cost) + ": " + GetCost();
	}
	
	public double GetCost() {
		double Price = 0;
		switch(type) {
		case Airplane: Price = 50; break;
		case Train: Price = 10; break;
		case Car: Price = 5; break;
		}
		return Distance*Price;
	}
	
	public String Get_Time() {
		
		long h = 0;
		long min = 0;
		switch(type) {
		case Airplane: h = (long)(Distance / 900); min = Math.round(Distance / 15) - h*60; break;
		case Train: h = (long)(Distance / 50); min = Math.round(Distance / 5 *6) - h*60; break;
		case Car: h = (long)(Distance / 110); min = Math.round(Distance / 11 * 6) - h*60; break;
		}
		return new String(" " + h + ':' + min + ":00");
	}
}
