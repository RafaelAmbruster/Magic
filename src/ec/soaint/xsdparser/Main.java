package ec.soaint.xsdparser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


/**
 * Created by Ambruster on 6/9/2015.
 */
public class Main {

    public static void main(String[] args) {
        try {

            Util.createpath();
            UtilWSDL.init();
            UtilXSDs.init();

        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (SAXException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }


}
