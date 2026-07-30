package com.palantir.isofilereader.isofilereader.read;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * {@link IsoDataReader} for a {@link RandomAccessFile}.
 */
public class RandomAccessFileReader implements IsoDataReader {
    /**
     * The backing {@link RandomAccessFile}.
     */
    private final RandomAccessFile randomAccessFile;

    /**
     * Constructor.
     * @param randomAccessFile The backing {@link RandomAccessFile}.
     */
    public RandomAccessFileReader(RandomAccessFile randomAccessFile) {
        this.randomAccessFile = randomAccessFile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void seek(long pos) throws IOException {
        randomAccessFile.seek(pos);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int read(byte[] bytes, int off, int len) throws IOException {
        return randomAccessFile.read(bytes, off, len);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int read(byte[] bytes) throws IOException {
        return randomAccessFile.read(bytes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int read() throws IOException {
        return randomAccessFile.read();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long length() throws IOException {
        return randomAccessFile.length();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getFilePointer() throws IOException {
        return randomAccessFile.getFilePointer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOpen() {
        return randomAccessFile.getChannel().isOpen();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws IOException {
        randomAccessFile.close();
    }
}
