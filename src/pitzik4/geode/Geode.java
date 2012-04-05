package pitzik4.geode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import org.lwjgl.util.vector.Vector3f;

import misson20000.api.vorxel.VorxelSettings;
import misson20000.api.vorxel.Vorxel;

public class Geode implements Tickable, Runnable {
	private static File gamedir;
	private static final String nativedownload = "http://dl.dropbox.com/u/9077764/lwjgl/native/";
	
	public boolean closing = false;
	private int ticks = 0;
	public long lifeTime = 0;
	private int frames = 0;
	public Logger log = Logger.getLogger("Geode");
	private PlayerController pcont = new PlayerController(this);
	public static final double TICKS_PER_MILLI = 20.0 / 1000.0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		gamedir = new File("./");
		File nativesf = new File("native/");
		if(!nativesf.exists()) {
			nativesf.mkdir();
			String[] nativeslinux = {"liblwjgl.so", "libjinput-linux.so", "libjinput-linux64.so", "liblwjgl64.so", "libopenal.so", "libopenal64.so"};
			String[] nativeswindows = {"jinput-dx8.dll", "jinput-dx8_64.dll", "jinput-raw.dll", "jinput-raw_64.dll", "lwjgl.dll", "lwjgl64.dll"};
			String[] nativesmac = {"libjinput-osx.jnilib", "liblwjgl.jnilib", "openal.dylib"};
			String[] nativesolaris = {"liblwjgl.so", "liblwjgl64.so", "libopenal.so", "libopenal64.so"};

			String os = System.getProperty("os.name").toLowerCase();
			String[] natives;
			if(os.startsWith("win")) {
				natives = nativeswindows;
				os = "windows";
			} else if(os.startsWith("mac")) {
				natives = nativesmac;
				os = "macosx";
			} else if(os.startsWith("sol")) {
				natives = nativesolaris;
				os = "solaris";
			} else if(os.startsWith("lin")) {
				natives = nativeslinux;
				os = "linux";
			} else {
				natives = null;
				System.out.println("Your OS is not supported: " + os);
				System.exit(1);
			}
			for(int i = 0; i < natives.length; i++) {
				System.out.println("Downloading required native: " + natives[i]);
				File nativef = new File("native/" + natives[i]);
				try {
					nativef.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				URL url;
		        HttpURLConnection conn;
				try {
					url = new URL(nativedownload + os + "/" + natives[i]);
					conn = (HttpURLConnection) url.openConnection();
			        conn.setRequestMethod("GET");
			        conn.connect();
			        
			        InputStream in = conn.getInputStream();
					OutputStream out = new FileOutputStream(nativef);
			        byte[] buffer = new byte[65536];
			        int bufferSize;
			        while ((bufferSize = in.read(buffer, 0, buffer.length)) != -1)
			        {
			        	out.write(buffer, 0, bufferSize);
			        }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.setProperty("org.lwjgl.librarypath", nativesf.getAbsolutePath());
		Geode game = new Geode();
		new Thread(game).start();
	}
	@Override
	public void tick() {
		pcont.tick();
		Vorxel.tick();
		ticks++;
		lifeTime++;
	}
	public void close() {
		closing = true;
	}
	public void render() {
		frames++;
	}
	@Override
	public void run() {
		Vorxel.init(new VorxelSettings(), this);
		pcont.goTo(new Vector3f(0, -5, 0), 0, 0);
		long time = System.currentTimeMillis();
		long lastTime = time;
		long slowness = 0;
		long lastSecond = time;
		double catchup = 0.0;
		while(!closing) {
			time = System.currentTimeMillis();
			slowness = time - lastTime;
			lastTime = time;
			Vorxel.render();
			catchup += ((double) slowness) * TICKS_PER_MILLI;
			while(catchup >= 1.0) {
				tick();
				catchup--;
			}
			if(time >= lastSecond + 1000) {
				lastSecond += 1000;
				log.info("FPS: " + frames + ", ticks: " + ticks);
				frames = ticks = 0;
			}
			slowness++;
			try {
				Thread.sleep(Math.round(50.0 / ((double) slowness)));
			} catch (InterruptedException e) {}
		}
		System.exit(0);
	}
	public PlayerController getPlayerController() {
		return pcont;
	}
	
}
