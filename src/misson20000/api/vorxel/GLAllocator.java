package misson20000.api.vorxel;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class GLAllocator {

    /**
     * Creates and returns a direct byte buffer with the specified capacity. Applies native ordering to speed up access.
     */
    public static synchronized ByteBuffer createDirectByteBuffer(int par0)
    {
        ByteBuffer bytebuffer = ByteBuffer.allocateDirect(par0).order(ByteOrder.nativeOrder());
        return bytebuffer;
    }

    /**
     * Creates and returns a direct int buffer with the specified capacity. Applies native ordering to speed up access.
     */
    public static IntBuffer createDirectIntBuffer(int par0)
    {
        return createDirectByteBuffer(par0 << 2).asIntBuffer();
    }

    /**
     * Creates and returns a direct float buffer with the specified capacity. Applies native ordering to speed up
     * access.
     */
    public static FloatBuffer createDirectFloatBuffer(int par0)
    {
        return createDirectByteBuffer(par0 << 2).asFloatBuffer();
    }
	
}
