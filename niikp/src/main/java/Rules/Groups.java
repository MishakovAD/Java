package Rules;

import java.util.ArrayList;

public class Groups {
	public static String SECRETARY = "Секретари";
	public static String TESTER = "Испытатели";
	
	public static ArrayList<String> groupList = new ArrayList<>();
	
	public Groups() {
		groupList.add("Секретари");
		groupList.add("Испытатели");
		groupList.add("Бухгалтера");
		groupList.add("IT отдел");
	}
	

}
