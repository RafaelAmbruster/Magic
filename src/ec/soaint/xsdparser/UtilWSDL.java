package ec.soaint.xsdparser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.beans.Introspector;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ambruster on 6/10/2015.
 */
public class UtilWSDL {

    public static ArrayList<UtilValues> replacementslist;
    public static final String WSDL_WLS_TEMPLATE_PATH = "./templates/templateWLS.wsdl";
    public static final String WSDL_OSB_TEMPLATE_PATH = "./templates/templateOSB.wsdl";
    public static HashMap<Integer, String> map = null;

    public static void init() throws IOException, ParserConfigurationException, SAXException {

        map = Util.loadconfiguration();
        WLS();
        OSB();
    }

    public static void WLS() throws IOException, ParserConfigurationException, SAXException {
        replacementslist = new ArrayList<>();
        replacementslist.add(new UtilValues("TAG_01", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "ServicePRD"));
        replacementslist.add(new UtilValues("TAG_02", map.get(1009) + "/" + map.get(1010) + "/" + Util.capitalize(map.get(1011).toLowerCase()) + "/" + map.get(1001).toLowerCase() + "/" + map.get(1002).toLowerCase() + "/" + map.get(1003).toLowerCase() + "-" + map.get(1004)));
        replacementslist.add(new UtilValues("TAG_03", map.get(1009) + "/" + map.get(1010) + "/" + Util.capitalize(map.get(1011).toLowerCase()) + "/" + map.get(1001).toLowerCase() + "/" + map.get(1002).toLowerCase() + "/" + map.get(1003).toLowerCase() + "/Req-" + map.get(1006)));
        replacementslist.add(new UtilValues("TAG_04", map.get(1009) + "/" + map.get(1010) + "/" + Util.capitalize(map.get(1011).toLowerCase()) + "/" + map.get(1001).toLowerCase() + "/" + map.get(1002).toLowerCase() + "/" + map.get(1003).toLowerCase() + "/Resp-" + map.get(1007)));
        replacementslist.add(new UtilValues("TAG_05", "WLS_" + Util.capitalize(map.get(1001).toLowerCase()) + "_" + Util.capitalize(map.get(1002).toLowerCase()) + "_" + Util.capitalize(map.get(1003).toLowerCase()) + "Req.xsd"));
        replacementslist.add(new UtilValues("TAG_06", "WLS_" + Util.capitalize(map.get(1001).toLowerCase()) + "_" + Util.capitalize(map.get(1002).toLowerCase()) + "_" + Util.capitalize(map.get(1003).toLowerCase()) + "Resp.xsd"));
        replacementslist.add(new UtilValues("TAG_07", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "MsgReq"));
        replacementslist.add(new UtilValues("TAG_08", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "ReqParam"));
        replacementslist.add(new UtilValues("TAG_09", Introspector.decapitalize(map.get(1001)) + "" + map.get(1002) + "" + map.get(1003) + "ProdReq"));
        replacementslist.add(new UtilValues("TAG_10", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "HeaderParam"));
        replacementslist.add(new UtilValues("TAG_11", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "MsgResp"));
        replacementslist.add(new UtilValues("TAG_12", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "RespParam"));
        replacementslist.add(new UtilValues("TAG_13", Introspector.decapitalize(map.get(1001)) + "" + map.get(1002) + "" + map.get(1003) + "ProdResp"));
        replacementslist.add(new UtilValues("TAG_14", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "FaultMsg"));
        replacementslist.add(new UtilValues("TAG_15", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "FaultParam"));
        replacementslist.add(new UtilValues("TAG_16", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "Pt"));
        replacementslist.add(new UtilValues("TAG_17", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "Op"));
        replacementslist.add(new UtilValues("TAG_18", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "Binding"));
        replacementslist.add(new UtilValues("TAG_19", map.get(1009) + "/" + map.get(1010) + "/" + Util.capitalize(map.get(1011).toLowerCase()) + "/" + map.get(1001).toLowerCase() + "/" + map.get(1002).toLowerCase() + "/" + map.get(1003).toLowerCase() + "-" + map.get(1004) + "/Op"));
        replacementslist.add(new UtilValues("TAG_20", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "PortPRD"));
        replacementslist.add(new UtilValues("TAG_21", map.get(1009) + "/" + map.get(1010) + "/" + Util.capitalize(map.get(1011).toLowerCase()) + "/" + map.get(1001).toLowerCase() + "/V1_0/" + map.get(1002).toLowerCase() + map.get(1003)));
        replacementslist.add(new UtilValues("TAG_22", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase())+ "OpFault"));
        replacementslist.add(new UtilValues("TAG_23", map.get(1012)));
        replacementslist.add(new UtilValues("TAG_24", map.get(1013)));

        File log_01 = new File("./Generated/WSDL/" + "WLS_" + Util.capitalize(map.get(1001).toLowerCase()) + "_" + Util.capitalize(map.get(1002).toLowerCase())  + "_" + Util.capitalize(map.get(1003).toLowerCase()) + ".wsdl");
        File log = new File(WSDL_WLS_TEMPLATE_PATH);
        Util.ceate(replacementslist, log, log_01);
    }

    public static void OSB() throws IOException, ParserConfigurationException, SAXException {
        replacementslist = new ArrayList<>();
        replacementslist.add(new UtilValues("TAG_01", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "ServiceIMPL"));
        replacementslist.add(new UtilValues("TAG_02", map.get(1009) + "/" + map.get(1010) + "/" + Util.capitalize(map.get(1011).toLowerCase()) + "/" + map.get(1001).toLowerCase() + "/" + map.get(1002).toLowerCase() + "/" + map.get(1003).toLowerCase() + "-" + map.get(1004)));
        replacementslist.add(new UtilValues("TAG_03", map.get(1009) + "/" + map.get(1010) + "/" + Util.capitalize(map.get(1011).toLowerCase()) + "/" + map.get(1001).toLowerCase() + "/" + map.get(1002).toLowerCase() + "/" + map.get(1003).toLowerCase() + "/Req-" + map.get(1006)));
        replacementslist.add(new UtilValues("TAG_04", map.get(1009) + "/" + map.get(1010) + "/" + Util.capitalize(map.get(1011).toLowerCase()) + "/" + map.get(1001).toLowerCase() + "/" + map.get(1002).toLowerCase() + "/" + map.get(1003).toLowerCase() + "/Resp-" + map.get(1007)));
        replacementslist.add(new UtilValues("TAG_05", "OSB_" + Util.capitalize(map.get(1001).toLowerCase()) + "_" + Util.capitalize(map.get(1002).toLowerCase())  + "_" + Util.capitalize(map.get(1003).toLowerCase()) + "Req.xsd"));
        replacementslist.add(new UtilValues("TAG_06", "OSB_" + Util.capitalize(map.get(1001).toLowerCase()) + "_" + Util.capitalize(map.get(1002).toLowerCase())  + "_" + Util.capitalize(map.get(1003).toLowerCase()) + "Resp.xsd"));
        replacementslist.add(new UtilValues("TAG_07", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "MsgReq"));
        replacementslist.add(new UtilValues("TAG_08", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "ReqParam"));
        replacementslist.add(new UtilValues("TAG_09", Introspector.decapitalize(map.get(1001)) + "" + map.get(1002) + "" + map.get(1003) + "ImplReq"));
        replacementslist.add(new UtilValues("TAG_10", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "HeaderParam"));
        replacementslist.add(new UtilValues("TAG_11", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "MsgResp"));
        replacementslist.add(new UtilValues("TAG_12", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "RespParam"));
        replacementslist.add(new UtilValues("TAG_13", Introspector.decapitalize(map.get(1001)) + "" + map.get(1002) + "" + map.get(1003) + "ImplResp"));
        replacementslist.add(new UtilValues("TAG_14", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "FaultMsg"));
        replacementslist.add(new UtilValues("TAG_15", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "FaultParam"));
        replacementslist.add(new UtilValues("TAG_16", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "Pt"));
        replacementslist.add(new UtilValues("TAG_17", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "Op"));
        replacementslist.add(new UtilValues("TAG_18", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "Binding"));
        replacementslist.add(new UtilValues("TAG_19", map.get(1009) + "/" + map.get(1010) + "/" + Util.capitalize(map.get(1011).toLowerCase()) + "/" + map.get(1001).toLowerCase() + "/" + map.get(1002).toLowerCase() + "/" + map.get(1003).toLowerCase() + "-" + map.get(1004) + "/IMPL/Op"));
        replacementslist.add(new UtilValues("TAG_20", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "PortIMPL"));
        replacementslist.add(new UtilValues("TAG_21", map.get(1009) + "/" + map.get(1010) + "/" + Util.capitalize(map.get(1011).toLowerCase()) + "/" + map.get(1001).toLowerCase() + "/V1_0/" + map.get(1002).toLowerCase() + map.get(1003)));
        replacementslist.add(new UtilValues("TAG_22", Util.capitalize(map.get(1001).toLowerCase()) + "" + Util.capitalize(map.get(1002).toLowerCase()) + "" + Util.capitalize(map.get(1003).toLowerCase()) + "OpFault"));
        replacementslist.add(new UtilValues("TAG_23", map.get(1012)));
        replacementslist.add(new UtilValues("TAG_24", map.get(1013)));

        File log_01 = new File("./Generated/WSDL/" + "OSB_" + Util.capitalize(map.get(1001).toLowerCase()) + "_" + Util.capitalize(map.get(1002).toLowerCase())  + "_" + Util.capitalize(map.get(1003).toLowerCase()) + "_IMPL.wsdl");
        File log = new File(WSDL_OSB_TEMPLATE_PATH);
        Util.ceate(replacementslist, log, log_01);
    }

}
