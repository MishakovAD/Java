package DocumentPathTemplate;

import java.util.ArrayList;
import java.util.HashMap;

public class DocumentPathTemplate {
	static ArrayList<Integer> template1 = new ArrayList<>();
	static HashMap<String, ArrayList<Integer>> mapTemplates = new HashMap<>(); //карта с шаблонами, в которой названию шаблона соответсвует сам шаблон.
	
	//Пробная версия, сделать нормальные методы!
	public static ArrayList<Integer> addTemplate() {
		template1.add(2);
		template1.add(1);
		template1.add(3);
		mapTemplates.put("template1", template1);
		return template1;
	}
	
	public static ArrayList<Integer> getTemplate(String name) {
		addTemplate(); 
		return mapTemplates.get(name);
	}

	
}
