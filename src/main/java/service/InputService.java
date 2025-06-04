package service;

public interface InputService {
    boolean getBoolean(String prompt);
    double getDouble(String prompt);
    int getInt(String prompt);
    String getString(String prompt);
}
