package it.unipv.ingsw.lasout.model.vault.paymentmethod;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class PaymentMethodFactory {


    private Properties properties;	
	
	public PaymentMethod get(String tipo){

        if(properties == null){
            properties = new Properties();
            try {
                properties.load(PaymentMethodFactory.class.getResourceAsStream("/methodpaymentfactories.properties"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String clazzName = properties.getProperty(tipo);
        try {
            return (PaymentMethod) Class.forName(clazzName).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
	
}
