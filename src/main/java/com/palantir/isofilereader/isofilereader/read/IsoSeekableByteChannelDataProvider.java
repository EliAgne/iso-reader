package com.palantir.isofilereader.isofilereader.read;

import java.nio.channels.SeekableByteChannel;

/**
 * Provides a {@link SeekableByteChannelReader} from the {@code byteChannel} member.
 */
public class IsoSeekableByteChannelDataProvider implements IsoDataProvider {
    /**
     * The byte channel to provide the data from.
     */
    private final SeekableByteChannel byteChannel;

    /**
     * Constructor.
     * @param byteChannel The byte channel to provide the data from.
     */
    public IsoSeekableByteChannelDataProvider(SeekableByteChannel byteChannel) {
        this.byteChannel = byteChannel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IsoDataReader provide() {
        return new SeekableByteChannelReader(byteChannel);
    }
}
