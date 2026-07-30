/*
 * (c) Copyright 2026 Palantir Technologies Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.palantir.isofilereader.isofilereader;

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
