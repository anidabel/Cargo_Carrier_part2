
import java.text.DateFormat;
import java.util.Date;
import java.io.Serializable;

public class Cargo_Carrier implements Serializable {
	
	private static final long serailVersionUID = 1L;
	
	private final Date date = new Date();
	public String getCreationDate() {
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT,DateFormat.DEFAULT, AppLocale.get());
		String strDate = df.format(date);
		return strDate;
	}
	
	double Cargo_Weight;
	public double getWeight() {
		return Cargo_Weight;
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
	
	public Cargo_Carrier () {}
	
	protected Cargo_Carrier (String From, String To, double w, double Dist, Type t) {
		assert(validTownsName(From, To)) : "� ������ ������ ��� � ����� ������������ ��������";
		assert(validTowns(From, To)) : "��������� �� ����� ���� ������������ � ��� �� �����";
		assert(validDistance(Dist)) : "���������� ����� �������� �� ����� ���� ������������� ��� ������ ����";
		assert(validWeight(w, t)) : "������ ��� ���������� �� ��������� ���� ������ ����"; 
		this.Distance = Dist;
		this.FromTown = From;
		this.ToTown = To;
		this.type = t;
		this.Cargo_Weight = w;
		switch(t) {
			case Airplane: {
				assert(validDistanceAir(Dist)) : "�� ������ ���������� ������� �� ������";
				break;
			}
			case Train:{
				assert(validDistanceTrain(Dist)) : "�� ����� ��������� ������ �� �����";
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
		return (Dist > 6 && Dist < 4500);
	}
	public boolean validDistanceTrain(double Dist) {
		return (Dist > 2 && Dist < 2500);
	}
	public boolean validDistanceCar(double Dist) {
		return (Dist > 0 && Dist < 20000);
	}
	public boolean validWeight(double w, Type t) {
		switch(t) {
		case Airplane: if (w > 0 && w < 2000) return true; break;
		case Train: if (w > 0 && w < 10000) return true; break;
		case Car: if (w > 0 && w < 500) return true; break;
		}
		return false;
	}
	
	public String toString() {
		return AppLocale.getString(AppLocale.FromTown) + ": " + FromTown + "  |  " +
				AppLocale.getString(AppLocale.ToTown) + ": " + ToTown + "  |  " +
				AppLocale.getString(AppLocale.Distance) + ": " + Distance + "  |  " +
				AppLocale.getString(AppLocale.Cargo_Weight) + ": " + Cargo_Weight + AppLocale.getString("kg") + "  |  " +
				AppLocale.getString(AppLocale.Type) + ": " + AppLocale.getString(type.toString()) + "  |  " +
				AppLocale.getString(AppLocale.Date) + ": " + getCreationDate() + "  |  " +
				AppLocale.getString(AppLocale.Time) + ": " + Get_Time()  + "  |  " +
				AppLocale.getString(AppLocale.Cost) + ": " + GetCost();
	}
	
	public double GetCost() {
		double Price = 0;
		switch(type) {
		case Airplane:{
			Price = 30;
			if (Cargo_Weight > 5 && Cargo_Weight < 10)
				Price += 1;
			if (Cargo_Weight > 10 && Cargo_Weight < 100)
				Price += 2;
			if (Cargo_Weight > 100)
				Price += 5;
			break;
		}
		case Train: {
			Price = 7; 
			if (Cargo_Weight > 5 && Cargo_Weight < 10)
				Price += 2.5;
			if (Cargo_Weight > 10 && Cargo_Weight < 100)
				Price += 10;
			if (Cargo_Weight > 100)
				Price += 25;
			break;
		}
		case Car:{
			Price = 3; 
			if (Cargo_Weight > 5 && Cargo_Weight < 10)
				Price += 1.5;
			if (Cargo_Weight > 10 && Cargo_Weight < 100)
				Price += 2.5;
			if (Cargo_Weight > 100)
				Price += 10;
			break;
		}
		}
		return Distance*Price;
	}
	
	public String Get_Time() {
		long h = 0;
		long min = 0;
		switch(type) {
		case Airplane: h = (long)(Distance / 825); min = Math.round(Distance / 13.75) - h*60; break;
		case Train: h = (long)(Distance / 90); min = Math.round(Distance / 1.5) - h*60; break;
		case Car: h = (long)(Distance / 100); min = Math.round(Distance / 5 * 3) - h*60; break;
		}
		return new String (" " + h + ':' + min + ":00");
	}
}


