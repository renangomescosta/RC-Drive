package app.model;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import app.entities.FileSystemEntity;

public class S3Manager {
    public S3Manager(){
    }

    public HashMap<Integer, String> writeFile(String path){
        HashMap<Integer, String> statusExit = new HashMap<>();
        statusExit.put(400, "Saída Bem Sucedida");
        return statusExit;
    }
    public HashMap<Integer, String> deleteFile(String path){
        HashMap<Integer, String> statusExit = new HashMap<>();
        statusExit.put(400, "Saída Bem Sucedida");
        return statusExit;
    }

    // public HashMap<Integer, String> openFile(String path){
    //     HashMap<Integer, String> statusExit = new HashMap<>();
    //     statusExit.put(400, "Saída Bem Sucedida");
    //     return statusExit;
    //     // Pensar nessa função em como retornar duas coisas.
    // }

    // public HashMap<Integer, String> downloadFile(String path){
    //     HashMap<Integer, String> statusExit = new HashMap<>();
    //     statusExit.put(400, "Saída Bem Sucedida");
    //     return statusExit;
    //     // Pensar nessa função em como retornar duas coisas.
    // }

    public List<FileSystemEntity> getAllItensInDirectoryId(int directoryId){
        // Aqui vem a requisição ...

        // Tratamento de Requisição
        List<FileSystemEntity> itensList = new ArrayList<>();
        itensList.add(new File(directoryId, "Arquivo.txt", 256));
        itensList.add(new Folder(directoryId, "PastaExemplo", 10));

        return itensList;
    }


    


}
