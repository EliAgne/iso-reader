package com.palantir.isofilereader.isofilereader.read;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

/**
 * {@link IsoDataReader} for a {@link SeekableByteChannel}.
 */
public class SeekableByteChannelReader implements IsoDataReader {
    /**
     * The backing {@link SeekableByteChannel}.
     */
    private final SeekableByteChannel seekableByteChannel;

    /**
     * Constructor.
     * @param seekableByteChannel The backing {@link SeekableByteChannel}.
     */
    public SeekableByteChannelReader(SeekableByteChannel seekableByteChannel) {
        this.seekableByteChannel = seekableByteChannel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void seek(long pos) throws IOException {
        seekableByteChannel.position(pos);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int read(byte[] bytes, int off, int len) throws IOException {
        return seekableByteChannel.read(ByteBuffer.wrap(bytes, off, len));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long length() throws IOException {
        return seekableByteChannel.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws IOException {
        if (seekableByteChannel.isOpen()) {
            seekableByteChannel.close();
        }
    }
}
