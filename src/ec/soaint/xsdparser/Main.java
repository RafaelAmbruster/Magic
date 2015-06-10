package ec.soaint.xsdparser;

import com.sun.xml.xsom.XSSchema;
import com.sun.xml.xsom.XSSchemaSet;
import com.sun.xml.xsom.parser.XSOMParser;
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
 * Created by Ambruster on 6/9/2015.
 */
public class Main {

    public static Hashtable<Integer, String> source;
    public static HashMap<Integer, String> map;
    public static final String CONFIGURATION_PATH = "./configuration.xml";
    public static final String WSDL_TEMPLATE_PATH = "./template.wsdl";
    public static String XSD_NAME = "";
    public static int XSD_FILE_NUMBER = 0;
    public static ArrayList<replacements> replacementslist;

    public static void main(String[] args) {

        source = new Hashtable<>();
        map = new HashMap(source);

        try {

            readConfigurationXml();
            generateWSDL();

            System.out.println("Powered by Ambruster@SOAint 2015");

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

    private static void readConfigurationXml() throws ParserConfigurationException, IOException, SAXException {

        File xml = new File(CONFIGURATION_PATH);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xml);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("property");

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                map.put(Integer.parseInt(eElement.getAttribute("id")), eElement.getElementsByTagName("value").item(0).getTextContent());
            }
        }
    }

    public static void replace(ArrayList<replacements> list) throws IOException {

        File log = new File(WSDL_TEMPLATE_PATH);
        File log_01 = new File("./" + "OSB_" + map.get(1002) + "_" + map.get(1001) + "_" + map.get(1003) + ".wsdl");
        duplicate(log, log_01);

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

                System.out.println("OSB_" + map.get(1002) + "_" + map.get(1001) + "_" + map.get(1003) + ".wsdl generado correctamente!  ");

                System.out.println();


            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void duplicate(File source, File dest) throws IOException {
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

    private static void generateWSDL() throws IOException {

        replacementslist = new ArrayList<>();
        replacementslist.add(new replacements("TAG_01", map.get(1002) + map.get(1001) + "ServicePRD"));
        replacementslist.add(new replacements("TAG_02", map.get(1009) + "/" + map.get(1010) + "/" + map.get(1002).toLowerCase() + "/" + map.get(1001).toLowerCase() + "/" + map.get(1003).toLowerCase() + "-" + map.get(1004))); // BCO/CO/empresa/empleo/obtener-v1.1
        replacementslist.add(new replacements("TAG_03", map.get(1009) + "/" + map.get(1010) + "/" + map.get(1002).toLowerCase() + "/" + map.get(1001).toLowerCase() + "/" + map.get(1003).toLowerCase() + "/Req-" + map.get(1006))); // BCO/CO/empresa/empleo/obtener/Req-v2015.06
        replacementslist.add(new replacements("TAG_04", map.get(1009) + "/" + map.get(1010) + "/" + map.get(1002).toLowerCase() + "/" + map.get(1001).toLowerCase() + "/" + map.get(1003).toLowerCase() + "/Resp-" + map.get(1007))); // BCO/CO/empresa/empleo/obtener/Resp-v2015.06
        replacementslist.add(new replacements("TAG_05", "WLS_" + map.get(1002) + "_" + map.get(1001) + "_" + map.get(1003) + "Req.xsd")); // OSB_Empresa_Empleo_ObtenerReq.xsd
        replacementslist.add(new replacements("TAG_06", "WLS_" + map.get(1002) + "_" + map.get(1001) + "_" + map.get(1003) + "Resp.xsd")); // OSB_Empresa_Empleo_ObtenerResp.xsd
        replacementslist.add(new replacements("TAG_07", map.get(1002) + "" + map.get(1001) + "MsgReq")); //EmpresaEmpleoMsgReq
        replacementslist.add(new replacements("TAG_08", map.get(1002) + "" + map.get(1001) + "ReqParam")); //EmpresaEmpleoReqParam
        replacementslist.add(new replacements("TAG_09", map.get(1003) + "" + map.get(1001) + "" + map.get(1002) + "Req")); //ObtenerEmpleoEmpresaReq
        replacementslist.add(new replacements("TAG_10", map.get(1002) + "" + map.get(1001) + "HeaderParam")); //EmpresaEmpleoHeaderParam
        replacementslist.add(new replacements("TAG_11", map.get(1002) + "" + map.get(1001) + "MsgResp")); //EmpresaEmpleoMsgResp
        replacementslist.add(new replacements("TAG_12", map.get(1002) + "" + map.get(1001) + "RespParam")); //EmpresaEmpleoRespParam
        replacementslist.add(new replacements("TAG_13", map.get(1003) + "" + map.get(1001) + "" + map.get(1002) + "Resp")); //ObtenerEmpleoEmpresaResp
        replacementslist.add(new replacements("TAG_14", map.get(1002) + "" + map.get(1001) + "FaultMsg")); //EmpresaEmpleoFaultMsg
        replacementslist.add(new replacements("TAG_15", map.get(1002) + "" + map.get(1001) + "FaultParam")); //EmpresaEmpleoFaultParam
        replacementslist.add(new replacements("TAG_16", map.get(1002) + "" + map.get(1001) + "Pt")); //EmpresaEmpleoPt
        replacementslist.add(new replacements("TAG_17", map.get(1002) + "" + map.get(1001) + "Op"));  //EmpresaEmpleoOp
        replacementslist.add(new replacements("TAG_18", map.get(1002) + "" + map.get(1001) + "Binding")); //EmpresaEmpleoBinding
        replacementslist.add(new replacements("TAG_19", map.get(1009) + "/" + map.get(1010) + "/" + map.get(1002).toLowerCase() + "/" + map.get(1001).toLowerCase() + "/" + map.get(1003).toLowerCase() + "-" + map.get(1004) + "/Op")); // BCO/CO/empresa/empleo/obtener-v1.1/Op
        replacementslist.add(new replacements("TAG_20", map.get(1002) + "" + map.get(1001) + "PortPRD")); //EmpresaEmpleoPort
        replacementslist.add(new replacements("TAG_21", map.get(1009) + "/" + map.get(1010) + "/" + map.get(1002).toLowerCase() + "/V1_0/" + map.get(1001).toLowerCase() + map.get(1003)));  // BCO/CO/empresa/v1_0/empleoObtener
        replacementslist.add(new replacements("TAG_22", map.get(1002) + map.get(1001) + "OpFault")); //EmpresaEmpleoOpFault

        replace(replacementslist);
    }

    public static void getXSDFile(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
            } else {
                if (fileEntry.getName().toLowerCase().endsWith(".xsd")) {
                    XSD_NAME = fileEntry.getName();
                    System.out.println(fileEntry.getName() + " encontrado!");
                    XSD_FILE_NUMBER++;
                }
            }
        }
    }

    private static void readResourceXSD() throws ParserConfigurationException, IOException, SAXException {

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

    private static void generateXSD(String text, int key) {

        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("./" + map.get(key) + ".xsd"), "utf-8"));
            writer.write(text);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void parse(File file) {

    }
}
