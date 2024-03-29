package com.supervillainy.game.sound;

import java.nio.ByteBuffer;

/**
 * Data describing the sounds in a OGG file
 * 
 * @author Kevin Glass
 */
class OggData {
	/** The data that has been read from the OGG file */
	public ByteBuffer data;
	/** The sampling rate */
	public int rate;
	/** The number of channels in the sound file */
	public int channels;
}
