package app.model;
import app.entities.FileSystemEntity;

public class File implements FileSystemEntity{
    private String name;
    private double sizeInMegabytes;

    public File(String name, double sizeInMegabytes){
        setName(name);
        setSizeInMegabytes(sizeInMegabytes);
    }
    
    // Gets
    public String getName(){ return this.name;}
    public double getSizeInMegabytes(){ return this.sizeInMegabytes;}
    //// Get Constante
    public int getType(){ return 2;}


    // Sets
    public void setName(String name){this.name = name;}
    public void setSizeInMegabytes(double sizeInMegabytes){this.sizeInMegabytes = sizeInMegabytes;}




}