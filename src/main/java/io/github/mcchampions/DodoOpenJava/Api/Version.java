package io.github.mcchampions.DodoOpenJava.Api;

/**
 * Dodo����ƽ̨�İ汾
 */
public enum Version {
    /**
     * V1�汾
     */
    V1("v1"),
    /**
     * V2�汾
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
