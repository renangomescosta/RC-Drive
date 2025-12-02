package app.service;
import app.model.S3Manager;
import app.model.Profile;
import app.model.File;
import app.model.Folder;
import app.entities.FileSystemEntity;

import java.util.ArrayList;
import java.util.List;

public class DriveService {
    private String currentlyPath;
    private Profile currentlyProfile;
    private List<Profile> allProfiles;
    private final S3Manager s3Manager = new S3Manager();

    public static void main(String[] args) {
        DriveService teste = new DriveService();
        System.out.println(teste.getcurrentlyProfile().getName());
        System.out.println(teste.getcurrentlyPath());
        System.out.println(teste.getcurrentlyPath());

    }

    public DriveService(){
        loadAllProfiles();
        loadDirectoryProfile(0);


    }
    public void loadAllProfiles(){
        List<Profile> allProfiles = new ArrayList<>();
        List<FileSystemEntity> allItens = s3Manager.getAllItensInPath("/");
        for(FileSystemEntity item: allItens)
            if(item instanceof Folder)
                allProfiles.add(new Profile(item.getName(),"-" ) );

        this.allProfiles = allProfiles;

    }
    public void loadDirectoryProfile(int profileId){ // Vê se vai continuar como profileId
        Profile currentlyProfile = this.allProfiles.get(profileId);
        setcurrentlyProfile(currentlyProfile);
        setcurrentlyPath(String.format("/%s/", currentlyProfile.getName()));
    }

    public String getcurrentlyPath(){return this.currentlyPath;}
    public Profile getcurrentlyProfile(){return this.currentlyProfile;}

    public void setcurrentlyProfile(Profile currentlyProfile){this.currentlyProfile = currentlyProfile;}
    public void setcurrentlyPath(String currentlyPath){this.currentlyPath = currentlyPath;}

}



// const files = [
//     { id: 'p1', name: 'Código_Fonte', isDir: true, modDate: new Date(), childrenCount: 15 },
//     { id: 'p2', name: 'Assets', isDir: true, modDate: new Date(), childrenCount: 8 },
//     { id: 'p3', name: 'arquitetura.png', modDate: new Date(), size: 1024 * 1024 },
//     { id: 'p5', name: 'main.py', modDate: new Date(), size: 4 * 1024 },
// ];

// const folderChain = [
//     { id: 'root', name: 'Meu Drive', isDir: true },
//     { id: 'docs', name: 'Documentos', isDir: true },
//     { id: '1', name: 'Projetos', isDir: true },
// ];