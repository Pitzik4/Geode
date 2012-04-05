package misson20000.api.vorxel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

public class TextureHelper {
	private static final int TEX_WIDTH = 16;
	private static final int TEX_HEIGHT = 16;
	private static final String TEX_FILE_NAME = "textures.png";
	private static ByteBuffer imageData;

	public static void init() {
        imageData = GLAllocator.createDirectByteBuffer(0x1000000);
        try {
			BufferedImage img = ImageIO.read(new File(TEX_FILE_NAME));
			BufferedImage[][] tex = new BufferedImage[16][16];
			int i = 1;
			for(int y = 0; y < TEX_HEIGHT*16; y+=TEX_HEIGHT) {
				for(int x = 0; x < TEX_WIDTH*16; x+= TEX_WIDTH) {
					loadTexture(img.getSubimage(x, y,TEX_WIDTH, TEX_HEIGHT), i);
					i++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadTexture(BufferedImage buf, int id) {
		 GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
	     GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
	     GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
	     GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
	     GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
	     GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
	     GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);

	     int i = buf.getWidth();
	     int j = buf.getHeight();
	     int ai[] = new int[i * j];
	     byte abyte0[] = new byte[i * j * 4];
	     buf.getRGB(0, 0, i, j, ai, 0, i);
         for (int k = 0; k < ai.length; k++)
	     {
	         int i1 = ai[k] >> 24 & 0xff;
	         int k1 = ai[k] >> 16 & 0xff;
	         int i2 = ai[k] >> 8 & 0xff;
	         int k2 = ai[k] & 0xff;
             abyte0[k * 4 + 0] = (byte)k1;
	         abyte0[k * 4 + 1] = (byte)i2;
	         abyte0[k * 4 + 2] = (byte)k2;
	         abyte0[k * 4 + 3] = (byte)i1;
	     }
         imageData.clear();
         imageData.put(abyte0);
	     imageData.position(0).limit(abyte0.length);
	     GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, i, j, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, imageData);

	}
}
