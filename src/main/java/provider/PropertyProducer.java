package provider;


import static java.lang.annotation.ElementType.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import static java.lang.ClassLoader.getSystemClassLoader;

import static java.util.regex.Pattern.quote;

import static java.net.URLDecoder.decode;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.util.Nonbinding;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import java.io.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * Created by alexandremasanes on 25/04/2017.
 */

@Singleton
public class PropertyProducer {

    private Properties properties;

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD, PARAMETER})
    public @interface Property {

        @Nonbinding String value() default "";

        @Nonbinding char delimiter() default ',';

        @Nonbinding boolean required() default true;
    }

    @Property
    @Produces
    public String produceString(final InjectionPoint ip) {
        return properties.getProperty(getKey(ip));

    }

    @Property
    @Produces
    public String[] produceStringArray(final InjectionPoint ip) {
        return properties.getProperty(getKey(ip)).split(quote(getDelimiter(ip)));
    }

    @Property
    @Produces
    public int produceInt(final InjectionPoint ip) {
        return Integer.valueOf(properties.getProperty(getKey(ip)));
    }

    @Property
    @Produces
    public boolean produceBoolean(final InjectionPoint ip) {

        return Boolean.valueOf(properties.getProperty(getKey(ip)));

    }

    private String getKey(final InjectionPoint ip) {
        return (ip.getAnnotated()
                  .isAnnotationPresent(Property.class)
                &&
                !ip.getAnnotated()
                   .getAnnotation(Property.class)
                   .value().isEmpty())
                ? ip.getAnnotated()
                    .getAnnotation(Property.class)
                    .value()

                : ip.getMember()
                    .getName();

    }

    private String getDelimiter(final InjectionPoint ip) {
        return (Character.toString(ip.getAnnotated()
                .isAnnotationPresent(Property.class)
                ? ip.getAnnotated()
                .getAnnotation(Property.class)
                .delimiter()
                : 0));
    }

    @PostConstruct
    public void init() throws UnsupportedEncodingException {
        ArrayList<File> propertiesFiles;
        FileInputStream stream;
        this.properties = new Properties();

        String path = getSystemClassLoader().getResource("").getPath();
        path = decode(path, "UTF-8");
        propertiesFiles = new ArrayList<File>();
        seekProperties(path, propertiesFiles);

        if(propertiesFiles.isEmpty())
            throw new RuntimeException("No properties found under CLASSPATH");

        for(File file : propertiesFiles)
            try {

            properties.load(stream = new FileInputStream(file));
            stream.close();

            } catch (final IOException e) {
                throw new RuntimeException(file.getName() + " could not be loaded!");
            }
    }

    private void seekProperties(String dirName, List<File> files) {
        File inputFile;
        File[] filesArray;

        inputFile = new File(dirName);

        if((filesArray = inputFile.listFiles()) != null && filesArray.length != 0)
            for (File file : filesArray)
                if (file.isFile() && file.getName().endsWith(".properties"))
                    files.add(file);
                else if (file.isDirectory())
                    seekProperties(file.getAbsolutePath(), files);
    }
 }
