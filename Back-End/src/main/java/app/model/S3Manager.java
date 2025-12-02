package app.model;
import app.entities.FileSystemEntity;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

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

    public List<FileSystemEntity> getAllItensInPath(String Path){
        // Aqui vem a requisição ...

        // Tratamento de Requisição
        List<FileSystemEntity> itensList = new ArrayList<>();
        // Quando tivermos sistema de árvores, não pode haver esse "new". Deve haver ponteiro
        // parentDirectory: 0 => Ficticio por hora.

        itensList.add(new Folder("Renan", 256));
        itensList.add(new Folder("Naju", 10));
        itensList.add(new Folder("Igor", 10));

        return itensList;
    }
    


}
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
