package it.unipv.ingsw.lasout.model.vault;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class PaymentMethodFactory {

	private static Map<String, String> classes;
	
	public static PaymentMethod getInstance(String key) {
		
		String className = classes.get(key);
		
		Class<?> clazz = null;
		
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		PaymentMethod pm = null;
		
		try {
			pm = (PaymentMethod) clazz.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
		return pm;
		
	}
	
}
