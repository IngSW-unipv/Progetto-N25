package it.unipv.ingsw.lasout.model.vault;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;


public class PaymentMethodDAO implements IPaymentMethodDAO{
	
	private static final Logger LOGGER = Logger.getLogger(PaymentMethodDAO.class.getName());
	
	private static final PaymentMethodDAO INSTANCE = new PaymentMethodDAO();
	public static PaymentMethodDAO getInstance() {
		return INSTANCE;
	}
	
	private final Map<String, Class<?>> classes = new HashMap<>();
	
	private PaymentMethodDAO() {
		populateMap();
	}
	
//	private static final String 

	private void populateMap() {
		Properties prop = new Properties();
		
		try {
			prop.load(PaymentMethodDAO.class.getResourceAsStream("/methodpaymentfactories.properties"));
		} catch(IOException e) {
			LOGGER.severe("Could not load the file");
		}
		
		prop.forEach((key, value) ->{
			try {
				classes.put((String) key, Class.forName(value.toString()));
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		});	
	}
	
	private Class<?> getClass(String key){
		return classes.getOrDefault(key, PaymentMethodFactory.class);
	}

	@Override
	public void load(PaymentMethod paymentmethod) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PaymentMethod paymentmethod) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(PaymentMethod paymentmethod) throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}
