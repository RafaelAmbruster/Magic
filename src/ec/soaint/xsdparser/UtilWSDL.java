package ec.soaint.xsdparser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ambruster on 6/10/2015.
 */
public class UtilWSDL {

    public static ArrayList<UtilValues> replacementslist;
    public static final String WSDL_TEMPLATE_PATH = "./templates/template.wsdl";
    public static HashMap<Integer, String> map = null;

    public static void init() throws IOException, ParserConfigurationException, SAXException {

        map = Util.loadconfiguration();

        replacementslist = new ArrayList<>();
        replacementslist.add(new UtilValues("TAG_01", map.get(1001) + map.get(1002) + map.get(1003) + "ServicePRD"));
        replacementslist.add(new UtilValues("TAG_02", map.get(1009) + "/" + map.get(1010) + "/" + Util.capitalize(map.get(1011).toLowerCase()) + "/" + map.get(1001).toLowerCase() + "/" + map.get(1002).toLowerCase() + "/" + map.get(1003).toLowerCase() + "-" + map.get(1004)));
        replacementslist.add(new UtilValues("TAG_03", map.get(1009) + "/" + map.get(1010) + "/" + Util.capitalize(map.get(1011).toLowerCase()) + "/" + map.get(1001).toLowerCase() + "/" + map.get(1002).toLowerCase() + "/" + map.get(1003).toLowerCase() + "/Req-" + map.get(1006)));
        replacementslist.add(new UtilValues("TAG_04", map.get(1009) + "/" + map.get(1010) + "/" + Util.capitalize(map.get(1011).toLowerCase()) + "/" + map.get(1001).toLowerCase() + "/" + map.get(1002).toLowerCase() + "/" + map.get(1003).toLowerCase() + "/Resp-" + map.get(1007)));
        replacementslist.add(new UtilValues("TAG_05", "WLS_" + map.get(1001) + "_" + map.get(1002) + "_" + map.get(1003) + "Req.xsd"));
        replacementslist.add(new UtilValues("TAG_06", "WLS_" + map.get(1001) + "_" + map.get(1002) + "_" + map.get(1003) + "Resp.xsd"));
        replacementslist.add(new UtilValues("TAG_07", map.get(1001) + "" + map.get(1002) + "" + map.get(1003) + "MsgReq"));
        replacementslist.add(new UtilValues("TAG_08", map.get(1001) + "" + map.get(1002) + "" + map.get(1003) + "ReqParam"));
        replacementslist.add(new UtilValues("TAG_09", map.get(1001) + "" + map.get(1002) + "" + map.get(1003) + "ProdReq"));
        replacementslist.add(new UtilValues("TAG_10", map.get(1001) + "" + map.get(1002) + "" + map.get(1003) + "HeaderParam"));
        replacementslist.add(new UtilValues("TAG_11", map.get(1001) + "" + map.get(1002) + "" + map.get(1003) + "MsgResp"));
        replacementslist.add(new UtilValues("TAG_12", map.get(1001) + "" + map.get(1002) + "" + map.get(1003) + "RespParam"));
        replacementslist.add(new UtilValues("TAG_13", map.get(1001) + "" + map.get(1002) + "" + map.get(1003) + "ProdResp"));
        replacementslist.add(new UtilValues("TAG_14", map.get(1001) + "" + map.get(1002) + "" + map.get(1003) + "FaultMsg"));
        replacementslist.add(new UtilValues("TAG_15", map.get(1001) + "" + map.get(1002) + "" + map.get(1003) + "FaultParam"));
        replacementslist.add(new UtilValues("TAG_16", map.get(1001) + "" + map.get(1002) + "" + map.get(1003) + "Pt"));
        replacementslist.add(new UtilValues("TAG_17", map.get(1001) + "" + map.get(1002) + "" + map.get(1003) + "Op"));
        replacementslist.add(new UtilValues("TAG_18", map.get(1001) + "" + map.get(1002) + "" + map.get(1003) + "Binding"));
        replacementslist.add(new UtilValues("TAG_19", map.get(1009) + "/" + map.get(1010) + "/" + Util.capitalize(map.get(1011).toLowerCase()) + "/" + map.get(1001).toLowerCase() + "/" + map.get(1002).toLowerCase() + "/" + map.get(1003).toLowerCase() + "-" + map.get(1004) + "/Op"));
        replacementslist.add(new UtilValues("TAG_20", map.get(1001) + "" + map.get(1002) + "" + map.get(1003) + "PortPRD"));
        replacementslist.add(new UtilValues("TAG_21", map.get(1009) + "/" + map.get(1010) + "/" + Util.capitalize(map.get(1011).toLowerCase()) + "/" + map.get(1001).toLowerCase() + "/V1_0/" + map.get(1002).toLowerCase() + map.get(1003)));
        replacementslist.add(new UtilValues("TAG_22", map.get(1001) + map.get(1002) + map.get(1003) + "OpFault"));
        replacementslist.add(new UtilValues("TAG_23", map.get(1012)));
        replacementslist.add(new UtilValues("TAG_24", map.get(1013)));

        File log_01 = new File("./Generated/WSDL/" + "WLS_" + map.get(1001) + "_" + map.get(1002) + "_" + map.get(1003) + ".wsdl");
        File log = new File(WSDL_TEMPLATE_PATH);
        Util.ceate(replacementslist, log, log_01);
    }


}
