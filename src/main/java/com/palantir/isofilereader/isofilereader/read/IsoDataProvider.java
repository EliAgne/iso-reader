package com.palantir.isofilereader.isofilereader.read;

import java.io.IOException;

/**
 * Provider of data for the {@link IsoDataReader}.
 */
public interface IsoDataProvider {
    /**
     * Returns a {@link IsoDataReader}.
     * @return a {@link IsoDataReader}
     **/
    IsoDataReader provide() throws IOException;
}
