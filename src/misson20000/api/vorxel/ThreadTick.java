package misson20000.api.vorxel;

public class ThreadTick extends Thread {
	private boolean stopping;
	private double TICKS_PER_MILLI;

	public void run() {
		long time = System.currentTimeMillis();
		long lastTime = time;
		double catchup = 0.0;
		while(!stopping) {
			time = System.currentTimeMillis();
			long slowness = time - lastTime;
			lastTime = time;
			catchup += ((double) slowness) * TICKS_PER_MILLI;
			while(catchup >= 1.0) {
				tick();
				catchup--;
			}
			if(slowness > 0) {
				try {
				Thread.sleep(50 / slowness);
				} catch (InterruptedException e) {}
			}
		}
	}

	private void tick() {
		// TODO Auto-generated method stub
		Vorxel.tick();
	}
}
