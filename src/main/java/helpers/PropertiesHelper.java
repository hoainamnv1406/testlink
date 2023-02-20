package helpers;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper {

    private static Properties _conf, _profile, _data;

    private static Properties _propsForName(String propFileName) {
        InputStream inputStream = null;
        try {
            LogHelper.info("Loading properties : " + propFileName);
            inputStream = PropertiesHelper.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                Properties prop = new Properties();
                prop.load(inputStream);
                return prop;
            } else {
                LogHelper.info(propFileName + " not found !");
                return null;
            }
        } catch (Exception e) {
            LogHelper.info("Exception: " + e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

    private static void _initProps() {
        if (_conf == null) {
            _conf = _propsForName("profiles/configs.properties");
        }

        if (_profile == null) {
            _profile = _propsForName("profiles/profile.properties");
        }

        if (_data == null) {
            _data = _propsForName("profiles/data.properties");
        }
    }

    public static String getPropValue(String key) {
        return getPropValue(key, null);
    }

    public static int getInt(String key) {
        String value = getPropValue(key, null);
        return Integer.parseInt(value);
    }

    public static double getDouble(String key) {
        String value = getPropValue(key, null);
        return Double.parseDouble(value);
    }

    public static boolean getBoolean(String key) {
        String value = getPropValue(key, null);
        return Boolean.parseBoolean(value);
    }

    public static String getPropValue(String key, String defaultValue) {
        _initProps();
        if (System.getProperty(key) != null) return System.getProperty(key);

        if (_profile != null && _profile.containsKey(key)) return _profile.getProperty(key);

        if (_conf != null && _conf.containsKey(key)) return _conf.getProperty(key);

        if (_data != null && _data.containsKey(key)) return _data.getProperty(key);

        return defaultValue;
    }
}
