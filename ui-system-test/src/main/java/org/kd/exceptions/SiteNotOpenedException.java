package org.kd.exceptions;

public class SiteNotOpenedException extends RuntimeException {

    public SiteNotOpenedException(String url, int timeout) {
        super("Couldn't load site + " + url
                + "Timeout expired." + timeout);
    }
}
