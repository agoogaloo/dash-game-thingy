package game.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import game.Launcher;

public class Utils {

	public static String fileToString(String path){
		InputStream stream = Launcher.class.getResourceAsStream(path);
		String result = new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining("\n"));
		return result;
	}
}
