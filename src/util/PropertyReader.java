package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: shakhawat.hossain
 * Date: 4/27/14
 * Time: 4:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class PropertyReader {


    public static Properties getProperty(String fileName) {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(fileName);
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return prop;
    }

}
