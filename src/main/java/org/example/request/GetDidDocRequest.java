package org.example.request;

public class GetDidDocRequest {
    private String did;

    public GetDidDocRequest() {
    }

    public GetDidDocRequest(String did) {
        this.did = did;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    @Override
    public String toString() {
        return "DidExistRequest{" +
                "did='" + did + '\'' +
                '}';
    }
}
