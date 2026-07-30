package com.palantir.isofilereader.isofilereader.read;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Provides a {@link RandomAccessFileReader} from the {@code file} member.
 */
public class IsoFileDataProvider implements IsoDataProvider {
    /**
     * The read-only value for {@link RandomAccessFile#RandomAccessFile(File, String)}.
     */
    private static final String READ_ONLY = "r";

    /**
     * The file to provide the data from.
     */
    private final File file;

    /**
     * Constructor.
     * @param file The file to provide the data from.
     */
    public IsoFileDataProvider(File file) {
        this.file = file;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IsoDataReader provide() throws IOException {
        return new RandomAccessFileReader(new RandomAccessFile(file, READ_ONLY));
    }
}
