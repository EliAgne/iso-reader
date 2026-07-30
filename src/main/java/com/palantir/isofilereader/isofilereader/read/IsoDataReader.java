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
     * Reads a sequence of bytes from this channel into the given buffer.
     * @param bytes the buffer into which the data is read.
     * @return the total number of bytes read into the buffer, or -1 if there is no more data because the end of this
     * file has been reached.
     * @throws IOException If some I/O error occurs.
     */
    int read(byte[] bytes) throws IOException;

    /**
     * Reads a byte of data from this file. The byte is returned as an
     * integer in the range 0 to 255 ({@code 0x00-0x0ff}). This
     * method blocks if no input is yet available.
     *
     * @return     the next byte of data, or {@code -1} if the end of the
     *             file has been reached.
     * @exception  IOException  if an I/O error occurs.
     */
    int read() throws IOException;

    /**
     * The length of the reader's backing data.
     * @return The length of the reader's backing data
     * @throws IOException if some I/O error occurs.
     */
    long length() throws IOException;

    long getFilePointer() throws IOException;

    /**
     * Returns true if this resource is currently open. False otherwise.
     * @return true if this resource is currently open. False otherwise
     */
    boolean isOpen();
}
