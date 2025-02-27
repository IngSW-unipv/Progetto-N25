package it.unipv.ingsw.lasout.util;

import it.unipv.ingsw.lasout.facade.LaVaultFacade;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TextUtil {

    public enum Text{
        FRIENDREQUEST;
    }

    private static final TextUtil INSTANCE = new TextUtil();
    public static TextUtil getInstance() {
        return INSTANCE;
    }

    private final Map<Text, String> text = new HashMap<>();

    private TextUtil() {
        Properties properties  = new Properties();
        try {
            properties.load(LaVaultFacade.class.getResourceAsStream("/text.properties"));
            properties.forEach((key,value)->{
                text.put(Text.valueOf(key.toString().toUpperCase().replaceAll("_", "")), value.toString());
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String format(Text key, Object... params) {

        String txt = this.text.getOrDefault(key, "");

        for(int i  = 0; i  < (params  != null ? params.length : 0); i++){

            String val  = params[i].toString();
            txt = txt.replaceAll("\\{"+i+"\\}", (val != null  ? val : ""));

        }
        return txt;


    }
}
