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

            UtilWSDL.generate(Util.loadconfiguration());

            if (UtilXSDs.load() > 1) {
            } else if (UtilXSDs.load() == 0) {
            } else {
                UtilXSDs.parseinputparameter();
                UtilXSDs.parseoutputparameter();
            }

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
