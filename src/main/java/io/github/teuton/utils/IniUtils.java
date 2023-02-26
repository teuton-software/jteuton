package io.github.teuton.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import org.ini4j.Ini;

public class IniUtils {
	
	public static Map<String, Map<String, String>> parseIniFile(File fileToParse) throws IOException {
	    Ini ini = new Ini(fileToParse);
	    return ini.entrySet()
	    		.stream()
	    		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

}
