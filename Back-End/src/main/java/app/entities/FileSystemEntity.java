package app.entities;

public interface FileSystemEntity {
    String getName();
    double getSizeInMegabytes();
    int getType(); // 1: Directory | 2: File

    void setName(String name);
    void setSizeInMegabytes(double sizeInMegabytes);

}
