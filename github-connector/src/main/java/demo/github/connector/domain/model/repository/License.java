package demo.github.connector.domain.model.repository;

import java.io.Serializable;

/**
 * Represents license of GitHub Repository.
 *
 * @author Aram Mkrtchyan.
 */
public class License implements Serializable {

    private String key;

    private String name;

    /**
     * Returns license key.
     * e.g {@code afl-3.0}
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns license name.
     * e.g {@code Academic Free License v3.0}
     */
    public String getName() {
        return name;
    }
}
