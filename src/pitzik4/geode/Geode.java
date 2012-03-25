package pitzik4.geode;

import java.util.logging.Logger;

import misson20000.api.vorxel.VorxelSettings;
import misson20000.api.vorxel.Vorxel;

public class Geode implements Tickable, Runnable {
	public boolean closing = false;
	private int ticks = 0;
	public long lifeTime = 0;
	private int frames = 0;
	public Logger log = Logger.getLogger("Geode");
	public static final double TICKS_PER_MILLI = 20.0 / 1000.0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Geode game = new Geode();
		new Thread(game).start();
	}
	@Override
	public void tick() {
		Vorxel.tick();
		ticks++;
		lifeTime++;
	}
	public void close() {
		closing = true;
	}
	@Override
	public void run() {
		Vorxel.init(new VorxelSettings(), this);
		long time = System.currentTimeMillis();
		long lastTime = time;
		long slowness = 0;
		long lastSecond = time;
		double catchup = 0.0;
		while(!closing) {
			time = System.currentTimeMillis();
			slowness = time - lastTime;
			lastTime = time;
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
	
}
