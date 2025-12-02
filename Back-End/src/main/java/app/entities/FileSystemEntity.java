package app.entities;

public interface FileSystemEntity {
    String getName();
    int getdirectoryId();
    double getSizeInMegabytes();
    int getType(); // 1: Directory | 2: File

    void setName(String name);
    void setdirectoryId(int directoryId);
    void setSizeInMegabytes(double sizeInMegabytes);

}
