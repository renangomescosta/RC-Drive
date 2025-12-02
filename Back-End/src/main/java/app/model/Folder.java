package app.model;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import app.entities.FileSystemEntity;

public class Folder implements FileSystemEntity{
    private String name;
    private int directoryId;
    private double sizeInMegabytes;

    public Folder(int directoryId, String name, float sizeInMegabytes){
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

    // public Dictionary<String, List<File>> getAllItensOnFolder(){
    //     Dictionary <String, List<File> itens = new Hashtable<>();

    //     arquivos.put("Arquivos",["A", "B", "C"]);
    //     return arquivos
    // }

    //DADOS MOCADOS:
    //     export const MyFileBrowser = () => {
    // // Dados Mockados (No futuro virão do Backend via props ou useEffect)
    //     const files = [
    //         { id: 'p1', name: 'Código_Fonte', isDir: true, modDate: new Date(), childrenCount: 15 },
    //         { id: 'p2', name: 'Assets', isDir: true, modDate: new Date(), childrenCount: 8 },
    //         { id: 'p3', name: 'arquitetura.png', modDate: new Date(), size: 1024 * 1024 },
    //         { id: 'p5', name: 'main.py', modDate: new Date(), size: 4 * 1024 },
    //     ];

    //     const folderChain = [
    //         { id: 'root', name: 'Meu Drive', isDir: true },
    //         { id: 'docs', name: 'Documentos', isDir: true },
    //         { id: '1', name: 'Projetos', isDir: true },
    //     ];




}