package app.model;
import app.entities.FileSystemEntity;

public class File implements FileSystemEntity{
    private String name;
    private int directoryId;
    private double sizeInMegabytes;

    public File(int directoryId, String name, double sizeInMegabytes){
        setName(name);
        setdirectoryId(directoryId);
        setSizeInMegabytes(sizeInMegabytes);
    }
    
    // Gets
    public String getName(){ return this.name;}
    public int getdirectoryId(){ return this.directoryId;}
    public double getSizeInMegabytes(){ return this.sizeInMegabytes;}
    //// Get Constante
    public int getType(){ return 2;}


    // Sets
    public void setName(String name){this.name = name;}
    public void setdirectoryId(int directoryId){this.directoryId = directoryId;}
    public void setSizeInMegabytes(double sizeInMegabytes){this.sizeInMegabytes = sizeInMegabytes;}




}