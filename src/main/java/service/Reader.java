package service;

public class Reader {
    private final InputService service;

    public Reader(InputService service) {
        this.service = service;
    }

    public String getString(String prompt) {
        return service.getString(prompt);
    }

    public int getInt(String prompt) {
        return service.getInt(prompt);
    }

    public double getDouble(String prompt) {
        return service.getDouble(prompt);
    }

    public boolean getBoolean(String prompt) {
        return service.getBoolean(prompt);
    }
}