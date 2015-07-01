package ec.soaint.xsdparser;

import com.sun.xml.xsom.XSSchema;
import com.sun.xml.xsom.XSSchemaSet;
import com.sun.xml.xsom.parser.XSOMParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ambruster on 6/10/2015.
 */
public class UtilXSDs {

    public static ArrayList<UtilValues> replacementslist;
    public static String XSD_NAME = "";
    public static String APP_PATH = "./";
    public static HashMap<Integer, String> map = null;
    public static final String XSD_TEMPLATE_PATH = "./templates/templateReq.xsd";

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

        replacementslist = new ArrayList<>();
        replacementslist.add(new UtilValues("TAG_24", map.get(1013)));
        replacementslist.add(new UtilValues("TAG_03", map.get(1009) + "/" + map.get(1010) + "/" + Util.capitalize(map.get(1011).toLowerCase()) + "/" + map.get(1001).toLowerCase() + "/" + map.get(1002).toLowerCase() + "/" + map.get(1003).toLowerCase() + "/Req-" + map.get(1006)));
        replacementslist.add(new UtilValues("TAG_REQ", map.get(1001).toLowerCase() + "" + map.get(1002) + "" + map.get(1003) + "ProdReq"));
        replace(replacementslist,name);

        XSSchemaSet schemaSet;
        XSSchema xsSchema;
        try {
            XSOMParser parser = new XSOMParser();
            //parser.parse(new File(XSD_NAME));
            //schemaSet = parser.getResult();
            //xsSchema = schemaSet.getSchema(1);

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


        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        }


    }

    public static void replace(ArrayList<UtilValues> list, String filename) throws IOException {

        File log = new File(XSD_TEMPLATE_PATH);
        File folder = new File("./Generated/Schemas");

        if(!folder.exists())
            folder.mkdir();

        File log_01 = new File("./Generated/Schemas/" + filename);
        Util.duplicate(log, log_01);

        try {
            FileReader fr = new FileReader(log_01);
            String s;
            String totalStr = "";
            try (BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    totalStr += "\n" + s;
                }

                for (int i = 0; i < list.size(); i++) {
                    totalStr = totalStr.replaceAll(list.get(i).getOldvalue(), list.get(i).getNewvalue());
                }

                FileWriter fw = new FileWriter(log_01);
                fw.write(totalStr);
                fw.close();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


}
