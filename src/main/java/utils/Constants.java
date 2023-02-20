package utils;


import helpers.PropertiesHelper;

public class Constants {

    /** Conf */
    public static final int LONG_WAIT = PropertiesHelper.getInt("conf.long.time.wait");
    public static final int MEDIUM_WAIT = PropertiesHelper.getInt("conf.medium.time.wait");

    public static final int SHORT_WAIT = PropertiesHelper.getInt("conf.short.time.wait");
    public static final String URL = PropertiesHelper.getPropValue("conf.website.url");
    public static final int APPIUM_PORT_NUMBER = PropertiesHelper.getInt("conf.server.port");

    /** Path */

    /** Default Value */

    /** Test data */

}
