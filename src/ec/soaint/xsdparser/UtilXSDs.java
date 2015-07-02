package ec.soaint.xsdparser;

import com.sun.xml.xsom.*;
import com.sun.xml.xsom.impl.scd.Iterators;
import com.sun.xml.xsom.parser.XSOMParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Ambruster on 6/10/2015.
 */
public class UtilXSDs {

    public static ArrayList<UtilValues> replacementslist;
    public static HashMap<Integer, String> map = null;
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

        XSSchemaSet schemaSet;
        XSSchema xsSchema;

        try {
            XSOMParser parser = new XSOMParser();
            parser.parse(new File("./"+map.get(14)));
            schemaSet = parser.getResult();
            xsSchema = schemaSet.getSchema(0);
            System.out.print( xsSchema.getTargetNamespace());

            Iterator jtr = xsSchema.iterateElementDecls();
            while (jtr.hasNext()) {
                XSElementDecl e = (XSElementDecl) jtr.next();
                System.out.print("got ElementDecls " + e.getName());

            }

            XSSchemaSet sset = parser.getResult();

            // =========================================================
            // types namepace
            XSSchema gtypesSchema = sset.getSchema("http://xmlns.oracle.com/pcbpel/adapter/db/sp/movimienTC");
            Iterator<XSComplexType> ctiter = gtypesSchema.iterateComplexTypes();
            while (ctiter.hasNext())
            {
                XSComplexType ct = (XSComplexType) ctiter.next();
                String typeName = ct.getName();
                // these are extensions so look at the base type to see what it is
                String baseTypeName = ct.getBaseType().getName();
                System.out.println(typeName + " is a " + baseTypeName);
            }

            // =========================================================
            // global namespace
            XSSchema globalSchema = sset.getSchema("");
            // local definitions of enums are in complex types
            ctiter = globalSchema.iterateComplexTypes();
            while (ctiter.hasNext())
            {
                XSComplexType ct = (XSComplexType) ctiter.next();
                String typeName = ct.getName();
                String baseTypeName = ct.getBaseType().getName();
                System.out.println(typeName + " is a " + baseTypeName);
            }

            // =========================================================
            // the main entity of this file is in the Elements
            // there should only be one!
            if (globalSchema.getElementDecls().size() != 1)
            {

            }

            XSElementDecl ed = globalSchema.getElementDecls().values()
                    .toArray(new XSElementDecl[0])[0];
            String entityType = ed.getName();
            XSContentType xsContentType = ed.getType().asComplexType().getContentType();
            XSParticle particle = xsContentType.asParticle();
            if (particle != null)
            {

                XSTerm term = particle.getTerm();
                if (term.isModelGroup())
                {
                    XSModelGroup xsModelGroup = term.asModelGroup();
                    term.asElementDecl();
                    XSParticle[] particles = xsModelGroup.getChildren();
                    String propertyName = null;
                    String propertyType = null;
                    XSParticle pp =particles[0];
                    for (XSParticle p : particles)
                    {
                        XSTerm pterm = p.getTerm();
                        if (pterm.isElementDecl())
                        {
                            propertyName = pterm.asElementDecl().getName();
                            if (pterm.asElementDecl().getType().getName() == null)
                            {
                                propertyType = pterm.asElementDecl().getType().getBaseType().getName();
                            }
                            else
                            {
                                propertyType = pterm.asElementDecl().getType().getName();
                            }
                            System.out.println(propertyName + " is a " + propertyType);
                        }
                    }
                }
            }




        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        }
    }



    public static void parseoutputparameter() throws ParserConfigurationException, IOException, SAXException {
        String name = "WLS_" + map.get(1001) + "_" + map.get(1002) + "_" + map.get(1003) + "Resp.xsd";
    }

}
