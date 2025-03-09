package methods;

public enum EndPoint {
    PET("/pet"),
    PETBYID("/pet/{id}"),
    PETBYSTATUS("/pet/findByStatus"),
    STORE("/store");
    private final String path;


    EndPoint(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
