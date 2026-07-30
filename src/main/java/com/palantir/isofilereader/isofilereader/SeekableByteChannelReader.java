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
