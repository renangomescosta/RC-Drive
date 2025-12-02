package app.model;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import app.entities.FileSystemEntity;

public class Folder implements FileSystemEntity{
    private String name;
    private double sizeInMegabytes;

    public Folder(String name, float sizeInMegabytes){
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

    // public Dictionary<String, List<File>> getAllItensOnFolder(){
    //     Dictionary <String, List<File> itens = new Hashtable<>();

    //     arquivos.put("Arquivos",["A", "B", "C"]);
    //     return arquivos
    // }





}