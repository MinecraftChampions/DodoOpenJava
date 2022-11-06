package io.github.mcchampions.DodoOpenJava.Api;

/**
 * Dodo开放平台的版本
 */
public enum Version {
    /**
     * V1版本
     */
    V1("v1"),
    /**
     * V2版本
     */
    V2("v2");

    Version(String version) {
        this.version = version;
    }

    public String version;

    public String getVersion() {
        return this.version;
    }
}
