package ec.soaint.xsdparser;

/**
 * Created by Ambruster on 6/10/2015.
 */
public class UtilElement {

    String name;
    String type;
    String db_index;
    String db_type;
    String minOccurs;

    public UtilElement(String name, String type, String db_index, String db_type, String minOccurs) {
        this.name = name;
        this.type = type;
        this.db_index = db_index;
        this.db_type = db_type;
        this.minOccurs = minOccurs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDb_index() {
        return db_index;
    }

    public void setDb_index(String db_index) {
        this.db_index = db_index;
    }

    public String getDb_type() {
        return db_type;
    }

    public void setDb_type(String db_type) {
        this.db_type = db_type;
    }

    public String getMinOccurs() {
        return minOccurs;
    }

    public void setMinOccurs(String minOccurs) {
        this.minOccurs = minOccurs;
    }
}

