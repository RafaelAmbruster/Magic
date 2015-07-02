package ec.soaint.xsdparser;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.MessageFormat;


public class ErrorReporter implements ErrorHandler {

    private final PrintStream out;

    public ErrorReporter(PrintStream o) {
        this.out = o;
    }

    public ErrorReporter(OutputStream o) {
        this(new PrintStream(o));
    }

    public void warning(SAXParseException e) throws SAXException {
        print("[Warning]", e);
    }

    public void error(SAXParseException e) throws SAXException {
        print("[Error  ]", e);
    }

    public void fatalError(SAXParseException e) throws SAXException {
        print("[Fatal  ]", e);
    }

    private void print(String header, SAXParseException e) {
        out.println(header + ' ' + e.getMessage());
        out.println(MessageFormat.format("   line {0} at {1}",
                new Object[]{
                        Integer.toString(e.getLineNumber()),
                        e.getSystemId()}));
    }
}

