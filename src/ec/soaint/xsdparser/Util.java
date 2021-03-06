package ec.soaint.xsdparser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by Ambruster on 6/10/2015.
 */
public class Util {

    public static String capitalize(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') {
                found = false;
            }
        }
        return String.valueOf(chars);
    }

    public static void duplicate(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    public static HashMap<Integer, String> loadconfiguration() throws ParserConfigurationException, IOException, SAXException {

        Hashtable<Integer, String> source = new Hashtable<>();
        HashMap<Integer, String> config_map = new HashMap(source);

        File xml = new File("./configuration.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xml);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("property");

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                config_map.put(Integer.parseInt(eElement.getAttribute("id")), eElement.getElementsByTagName("value").item(0).getTextContent());
            }
        }

        return config_map;
    }

    public static void ceate(ArrayList<UtilValues> list, File log, File log_01) throws IOException {


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

    public static void createpath() {
        File foldergen = new File("./Generated");

        if (!foldergen.exists())
            foldergen.mkdir();

        File folderwsdl = new File("./Generated/WSDL");

        if (!folderwsdl.exists())
            folderwsdl.mkdir();

        File folderxsd = new File("./Generated/Schemas");

        if(!folderxsd.exists())
            folderxsd.mkdir();
    }
}
