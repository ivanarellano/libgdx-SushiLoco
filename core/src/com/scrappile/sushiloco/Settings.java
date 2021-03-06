package com.scrappile.sushiloco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.badlogic.gdx.Gdx;

// Noninstantiable utility class
public class Settings {
    private static final String LOG = Settings.class.getSimpleName();

	public static boolean soundEnabled = false;
	public static boolean musicEnabled = false;
	public static boolean debugEnabled = false;
	public final static String file = ".sushiloco";
	
	// Suppress default constructor for noninstantiability
	private Settings() {
		throw new AssertionError();
	}
	
	public static void load() {
		BufferedReader in = null;
		
		try {
			in = new BufferedReader(new InputStreamReader(Gdx.files.external(file).read()));
			soundEnabled = Boolean.parseBoolean(in.readLine());
			musicEnabled = Boolean.parseBoolean(in.readLine());
			debugEnabled = Boolean.parseBoolean(in.readLine());
		} catch (Throwable e) {
			Gdx.app.error(LOG, e.getMessage());
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
                Gdx.app.error(LOG, e.getMessage());
			}
		}
	}
	
	public static void write() {
		BufferedWriter out = null;
		
		try {
			out = new BufferedWriter(new OutputStreamWriter(Gdx.files.external(file).write(false)));
			out.write(Boolean.toString(soundEnabled));
			out.write(Boolean.toString(musicEnabled));
			out.write(Boolean.toString(debugEnabled));
		} catch (Throwable e) {
            Gdx.app.error(LOG, e.getMessage());
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
                Gdx.app.error(LOG, e.getMessage());
			}
		}
	}
}
