package com.palantir.isofilereader.isofilereader.read;

import java.io.Closeable;
import java.io.IOException;

/**
 * Interface for reading seekable ISO data.
 */
public interface IsoDataReader extends Closeable {
    /**
     * Sets this reader's position.
     * @param position the position to set the reader.
     * @throws IOException If some I/O error occurs.
     */
    void seek(long position) throws IOException;

    /**
     * Reads a sequence of bytes from this reader into the given {@code bytes} buffer.
     * @param bytes he buffer into which the data is read.
     * @param off the start offset in array {@code bytes} at which the data is written.
     * @param len the maximum number of bytes read.
     * @return the total number of bytes read into the buffer, or {@code -1} if there
     * is no more data because the end of the file has been reached.
     * @throws IOException if some I/O error occurs.
     */
    int read(byte[] bytes, int off, int len) throws IOException;

    /**
     * The length of the reader's backing data.
     * @return The length of the reader's backing data
     * @throws IOException if some I/O error occurs.
     */
    long length() throws IOException;
}
