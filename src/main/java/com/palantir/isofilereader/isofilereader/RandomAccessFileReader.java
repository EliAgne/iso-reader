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
    public long length() throws IOException {
        return randomAccessFile.length();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws IOException {
        randomAccessFile.close();
    }
}
