package com.palantir.isofilereader.isofilereader.read;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

/**
 * {@link IsoDataReader} for a {@link SeekableByteChannel}.
 */
public class SeekableByteChannelReader implements IsoDataReader {
    /**
     * Whether or not this channel is owned, meaning that the owner is required to close the stream.
     */
    private final boolean ownChannel;

    /**
     * The backing {@link SeekableByteChannel}.
     */
    private final SeekableByteChannel seekableByteChannel;

    /**
     * Reusable single-byte buffer to avoid small allocations on frequent read() calls.
     */
    private final ByteBuffer singleByteBuffer = ByteBuffer.allocate(1);

    /**
     * Constructor.
     * @param seekableByteChannel The backing {@link SeekableByteChannel}.
     */
    public SeekableByteChannelReader(SeekableByteChannel seekableByteChannel) {
        this.seekableByteChannel = seekableByteChannel;
        this.ownChannel = false;
    }

    /**
     * Constructor.
     * @param seekableByteChannel The backing {@link SeekableByteChannel}.
     * @param ownChannel Whether or not this channel is owned, meaning that the owner is required to close the stream.
     */
    public SeekableByteChannelReader(SeekableByteChannel seekableByteChannel, boolean ownChannel) {
        this.seekableByteChannel = seekableByteChannel;
        this.ownChannel = ownChannel;
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
    public int read(byte[] bytes) throws IOException {
        return seekableByteChannel.read(ByteBuffer.wrap(bytes));
    }

    /**
     * Reads a single byte of data from the underlying channel.
     * <p>
     * This method reads the byte at the channel's current position and advances the
     * position pointer by one. To adhere to the contract of {@link java.io.RandomAccessFile#read()},
     * the retrieved byte is zero-extended from a signed Java {@code byte} ($-128$ to $127$)
     * to an unsigned {@code int} ($0$ to $255$) using bitwise masking ({@code & 0xFF}).
     *
     * @return the next byte of data as an unsigned integer in the range {@code 0} to {@code 255},
     *         or {@code -1} if the end of the channel (EOF) has been reached.
     * @throws IOException if an I/O error occurs, or if the underlying channel is closed.
     */
    @Override
    public int read() throws IOException {
        singleByteBuffer.clear();
        int bytesRead = seekableByteChannel.read(singleByteBuffer);
        if (bytesRead <= 0) {
            return -1;
        }
        singleByteBuffer.flip();
        return singleByteBuffer.get() & 0xFF;
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
    public long getFilePointer() throws IOException {
        return seekableByteChannel.position();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOpen() {
        return seekableByteChannel.isOpen();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws IOException {
        if (ownChannel && seekableByteChannel.isOpen()) {
            seekableByteChannel.close();
        }
    }
}
