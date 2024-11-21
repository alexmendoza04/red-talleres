package org.rimacseguros;

import java.util.List;

public class Response {
    private List<Deducible> payload;

    public List<Deducible> getPayload() {
        return payload;
    }

    public void setPayload(List<Deducible> payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "{" +
                "\"payload\":" + payload +
                '}';
    }
}
