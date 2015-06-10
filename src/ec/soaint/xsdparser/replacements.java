package ec.soaint.xsdparser;

/**
 * Created by Ambruster on 6/10/2015.
 */
public class replacements {
    String oldvalue;
    String newvalue;

    public replacements(String oldvalue, String newvalue) {
        this.oldvalue = oldvalue;
        this.newvalue = newvalue;
    }

    public replacements() {
    }

    public String getOldvalue() {
        return oldvalue;
    }

    public void setOldvalue(String oldvalue) {
        this.oldvalue = oldvalue;
    }

    public String getNewvalue() {
        return newvalue;
    }

    public void setNewvalue(String newvalue) {
        this.newvalue = newvalue;
    }
}
