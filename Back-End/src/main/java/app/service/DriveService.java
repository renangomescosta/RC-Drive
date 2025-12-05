package app.service;
import app.model.S3Manager;
import app.model.Profile;
import app.model.Folder;
import app.entities.FileSystemEntity;

import java.util.ArrayList;
import java.util.List;

public class DriveService {
    private String currentlyPath;
    private Profile currentlyProfile;
    private List<Profile> allProfiles;
    private final S3Manager s3Manager = new S3Manager();

    public DriveService(){
        loadAllProfiles();
        loadDirectoryProfile(0);
    }
    private void loadAllProfiles(){
        List<Profile> allProfiles = new ArrayList<>();
        List<FileSystemEntity> allItens = s3Manager.getAllItensInPath("/");
        for(FileSystemEntity item: allItens)
            if(item instanceof Folder)
                allProfiles.add(new Profile(item.getName(),"-" ) );

        this.allProfiles = allProfiles;

    }
    private void loadDirectoryProfile(int profileId){ // Vê se vai continuar como profileId
        Profile currentlyProfile = this.allProfiles.get(profileId);
        setcurrentlyProfile(currentlyProfile);
        setcurrentlyPath(String.format("/%s/", currentlyProfile.getName()));
    }

    // Código com baixa coesão: é necessário criar uma nova classe --> ProfileManager
    public String getcurrentlyPath(){return this.currentlyPath;}
    public Profile getcurrentlyProfile(){return this.currentlyProfile;}
    public List<Profile> getAllProfiles(){return this.allProfiles;}

    public void setcurrentlyProfile(Profile currentlyProfile){this.currentlyProfile = currentlyProfile;}
    public void setcurrentlyPath(String currentlyPath){this.currentlyPath = currentlyPath;}

    public String buildCurrentlyProfile(){return ProfileToJSON(getcurrentlyProfile());}
    public String buildAllProfiles(){
        String s= "";
        for(Profile profile: allProfiles){
            s += ProfileToJSON(profile) + ",";
        }
        return s.substring(0, s.length() - 1);
    }
    private String ProfileToJSON(Profile profile){
        int id = getAllProfiles().indexOf(profile);
        return String.format("{\"id\": \"%d\", \"Profile\": \"%s\"}", id, profile.getName());
    }
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