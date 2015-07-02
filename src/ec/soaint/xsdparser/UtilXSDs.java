package ec.soaint.xsdparser;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ambruster on 6/10/2015.
 */
public class UtilXSDs {

    public static ArrayList<UtilValues> replacementslist;
    public static HashMap<Integer, String> map;
    public static final String XSDREQ_TEMPLATE_PATH = "./templates/templateReq.xsd";

    public static void init() throws IOException, SAXException, ParserConfigurationException {
        map = Util.loadconfiguration();
        parseinputparameter();
        parseoutputparameter();
    }

    public static void parseinputparameter() throws ParserConfigurationException, IOException, SAXException {
        String name = "WLS_" + Util.capitalize(map.get(1001)) + "_" + Util.capitalize(map.get(1002)) + "_" + Util.capitalize(map.get(1003)) + "Req.xsd";

        replacementslist = new ArrayList<>();
        replacementslist.add(new UtilValues("TAG_24", map.get(1013)));
        replacementslist.add(new UtilValues("TAG_03", map.get(1009) + "/" + map.get(1010) + "/" + Util.capitalize(map.get(1011).toLowerCase()) + "/" + map.get(1001).toLowerCase() + "/" + map.get(1002).toLowerCase() + "/" + map.get(1003).toLowerCase() + "/Req-" + map.get(1006)));
        replacementslist.add(new UtilValues("TAG_REQ", Util.capitalize(map.get(1001)) + "" + map.get(1002) + "" + map.get(1003) + "ProdReq"));

        File log = new File(XSDREQ_TEMPLATE_PATH);
        File log_01 = new File("./Generated/Schemas/" + name);
        Util.ceate(replacementslist, log, log_01);

        try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("./" + map.get(1014)));


            NodeList nodeinput = doc.getElementsByTagName("InputParameters");
            Node in = nodeinput.item(0);

            NodeList nodesIN = in.getChildNodes().item(0).getChildNodes();


            for (int i = 0; i < nodesIN.getLength(); i++) {
                if(nodesIN.item(i).getAttributes().getLength() > 1) {
                    System.out.println("===================================================================");
                    System.out.println(nodesIN.item(i).getAttributes().getNamedItem("name").getNodeValue());
                    System.out.println(nodesIN.item(i).getAttributes().getNamedItem("type").getNodeValue());
                    System.out.println(nodesIN.item(i).getAttributes().getNamedItem("db:index").getNodeValue());
                    System.out.println(nodesIN.item(i).getAttributes().getNamedItem("db:type").getNodeValue());
                    System.out.println(nodesIN.item(i).getAttributes().getNamedItem("minOccurs").getNodeValue());
                    System.out.println("===================================================================");
                }
            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException ed) {
            ed.printStackTrace();
        }
    }


    public static void parseoutputparameter() throws ParserConfigurationException, IOException, SAXException {
        String name = "WLS_" + Util.capitalize(map.get(1001)) + "_" + Util.capitalize(map.get(1002)) + "_" + Util.capitalize(map.get(1003)) + "Resp.xsd";
    }


}
