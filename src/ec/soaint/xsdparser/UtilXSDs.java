package ec.soaint.xsdparser;

import com.sun.xml.xsom.XSSchema;
import com.sun.xml.xsom.XSSchemaSet;
import com.sun.xml.xsom.parser.XSOMParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.HashMap;

/**
 * Created by Ambruster on 6/10/2015.
 */
public class UtilXSDs {

    public static String XSD_NAME = "";
    public static String APP_PATH = "./";
    public static HashMap<Integer, String> map = null;

    public static int load() throws IOException, SAXException, ParserConfigurationException {
        int xsdfilenumber = 0;
        for (final File fileEntry : new File(APP_PATH).listFiles()) {
            if (fileEntry.isDirectory()) {
            } else {
                if (fileEntry.getName().toLowerCase().endsWith(".xsd")) {
                    if (xsdfilenumber == 0) {
                        XSD_NAME = fileEntry.getName();
                        map = Util.loadconfiguration();
                    }
                    xsdfilenumber++;

                }
            }
        }

        return xsdfilenumber;
    }

    public static void parseinputparameter() throws ParserConfigurationException, IOException, SAXException {
        String name = "WLS_" + map.get(1001) + "_" + map.get(1002) + "_" + map.get(1003) + "Req.xsd";
        Util.loadconfiguration();
        XSSchemaSet schemaSet;
        XSSchema xsSchema;

        try {
            XSOMParser parser = new XSOMParser();
            //parser.parse(new File(XSD_NAME));
            //schemaSet = parser.getResult();
            //xsSchema = schemaSet.getSchema(1);

            generate("prueba", name);
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        }


    }

    public static void parseoutputparameter() throws ParserConfigurationException, IOException, SAXException {
        String name = "WLS_" + map.get(1001) + "_" + map.get(1002) + "_" + map.get(1003) + "Resp.xsd";
        XSSchemaSet schemaSet;
        XSSchema xsSchema;

        try {
            XSOMParser parser = new XSOMParser();
            //parser.parse(new File(XSD_NAME));
            //schemaSet = parser.getResult();
            //xsSchema = schemaSet.getSchema(1);

            generate("prueba", name);
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        }


    }

    private static void generate(String text, String name) throws Exception {

        File folder = new File("./Generated/Schemas");

        if (!folder.exists())
            folder.mkdir();


        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("./Generated/Schemas/" + name), "utf-8"));
            writer.write(text);
        } catch (IOException ex) {
            throw new IOException();
        } finally {
            writer.close();
        }
    }


}
