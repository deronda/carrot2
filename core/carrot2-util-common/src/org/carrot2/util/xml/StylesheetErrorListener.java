package org.carrot2.util.xml;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;

/**
 * An {@link ErrorListener} that reacts to errors when parsing (compiling) the stylesheet.
 */
public final class StylesheetErrorListener implements ErrorListener
{
    private final static Logger logger = Logger.getLogger(StylesheetErrorListener.class);
    
    /*
     * 
     */
    public void warning(TransformerException e) throws TransformerException
    {
        logger.warn("Warning (recoverable): " + e.getMessage());
    }

    /*
     * 
     */
    public void error(TransformerException e) throws TransformerException
    {
        logger.warn("Error (recoverable): " + e.getMessage());
    }

    /**
     * Unrecoverable errors cause an exception to be rethrown.
     */
    public void fatalError(TransformerException e) throws TransformerException
    {
        logger.error("Fatal error: " + e.getMessage());
        throw e;
    }
}