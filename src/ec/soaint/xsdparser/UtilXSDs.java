package ec.soaint.xsdparser;


import com.sun.xml.xsom.*;
import com.sun.xml.xsom.parser.SchemaDocument;
import com.sun.xml.xsom.parser.XSOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Ambruster on 6/10/2015.
 */
public class UtilXSDs {

    public static ArrayList<UtilValues> replacementslist;
    public static HashMap<Integer, String> map;
    public static final String XSDREQ_TEMPLATE_PATH = "./templates/templateReq.xsd";
    public static final String XSDRESP_TEMPLATE_PATH = "./templates/templateResp.xsd";


    public static void init() throws IOException, SAXException, ParserConfigurationException {
        map = Util.loadconfiguration();
        parseinputparameter();
        parseoutputparameter();
    }

    public static void parseinputparameter() throws ParserConfigurationException, IOException, SAXException {
        String name = "WLS_" + map.get(1001) + "_" + map.get(1002) + "_" + map.get(1003) + "Req.xsd";

        replacementslist = new ArrayList<>();
        replacementslist.add(new UtilValues("TAG_24", map.get(1013)));
        replacementslist.add(new UtilValues("TAG_03", map.get(1009) + "/" + map.get(1010) + "/" + Util.capitalize(map.get(1011).toLowerCase()) + "/" + map.get(1001).toLowerCase() + "/" + map.get(1002).toLowerCase() + "/" + map.get(1003).toLowerCase() + "/Req-" + map.get(1006)));
        replacementslist.add(new UtilValues("TAG_REQ", Util.capitalize(map.get(1001)) + "" + map.get(1002) + "" + map.get(1003) + "ProdReq"));

        File log = new File(XSDREQ_TEMPLATE_PATH);
        File log_01 = new File("./Generated/Schemas/" + name);
        Util.ceate(replacementslist, log, log_01);

        /*try {
            // parse the document
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("./" + map.get(1014)));
            NodeList list = doc.getElementsByTagName("element");

            //loop to print data
            for (int i = 0; i < list.getLength(); i++) {
                Element first = (Element) list.item(i);
                if (first.hasAttributes()) {
                    String nm = first.getAttribute("name");
                    System.out.println(nm);
                    String nm1 = first.getAttribute("type");
                    System.out.println(nm1);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException ed) {
            ed.printStackTrace();
        }*/




        try {

            InputStream is;
            if (new File("./" + map.get(1014)).exists()) {
                is = new FileInputStream("./" + map.get(1014));
                XSOMParser parser = new XSOMParser();
                parser.parse(is);
                XSSchemaSet result = parser.getResult();


                Iterator itr = result.iterateSchema();
                while (itr.hasNext()) {
                    XSSchema s = (XSSchema) itr.next();

                    System.out.println("Target namespace: " + s.getTargetNamespace());

                    printElements(s);

                    Iterator jtr = s.iterateElementDecls();
                    while (jtr.hasNext()) {
                        XSElementDecl e = (XSElementDecl) jtr.next();
                        System.out.print(e.getName());
                        System.out.println();

                    }
                    System.out.println("---esquema---");
                }



            }


        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        }

        /*

        XSOMParser reader = new XSOMParser();
        // set an error handler so that you can receive error messages
        reader.setErrorHandler(new ErrorReporter(System.out));
        // DomAnnotationParserFactory is a convenient default to use
        reader.setAnnotationParser(new DomAnnotationParserFactory());

        try {
            // the parse method can by called many times

                reader.parse(new File("./" + map.get(1014)));

            XSSchemaSet xss = reader.getResult();
            if(xss==null)
                System.out.println("error");
            else
                new SchemaWriter(new OutputStreamWriter(System.out)).visit(xss);

            dump(reader.getDocuments());
        } catch( SAXException e ) {
            if(e.getException()!=null)
                e.getException().printStackTrace();
            else
                e.printStackTrace();
            throw e;
        }*/
    }

    public static void printElements(XSSchema xsSchema) {
        XSComplexType xsComplexType = xsSchema.getComplexTypes().get(0);
        XSContentType xsContentType = xsComplexType.getContentType();
        XSParticle particle = xsContentType.asParticle();
        if (particle != null) {
            XSTerm term = particle.getTerm();
            if (term.isModelGroup()) {
                XSModelGroup xsModelGroup = term.asModelGroup();
                XSParticle[] particles = xsModelGroup.getChildren();
                for (XSParticle p : particles) {
                    XSTerm pterm = p.getTerm();
                    if (pterm.isElementDecl()) {
                        System.out.println(pterm);
                    }
                }
            }
        }
    }


    private static void dump(Set<SchemaDocument> documents) {
        for (SchemaDocument doc : documents) {
            System.out.println("Schema document: " + doc.getSystemId());
            System.out.println("  target namespace: " + doc.getTargetNamespace());
            for (SchemaDocument ref : doc.getReferencedDocuments()) {
                System.out.print("    -> " + ref.getSystemId());
                if (doc.includes(ref))
                    System.out.print(" (include)");
                System.out.println();
            }
        }

    }


    public static void parseoutputparameter() throws ParserConfigurationException, IOException, SAXException {
        String name = "WLS_" + map.get(1001) + "_" + map.get(1002) + "_" + map.get(1003) + "Resp.xsd";
    }

}
