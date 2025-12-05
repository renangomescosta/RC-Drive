package app.model;

import com.sun.net.httpserver.*;
import javax.net.ssl.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.Properties; // Import necessário para o .env
import java.util.concurrent.Executors;
import java.util.List;

import app.service.DriveService;

public class Server extends ServerHTTPs{
    public DriveService driveService = new DriveService();
    public static void main(String[] args) {
        try{
            Server obj = new Server();
        }
        catch (Exception e){
            System.err.println(e);
        }
    }
    public Server()throws Exception{
        super();
        startServer();
    }

    private void startServer() throws Exception {
        SSLContext sslContext = createSSLContext();
        HttpsServer server = HttpsServer.create(new InetSocketAddress(PORT), 0);
        server.setHttpsConfigurator(new HttpsConfigurator(sslContext));


        server.createContext("/", exchange -> {handleHome(exchange);});
        server.createContext("/profile", exchange -> {handleProfile(exchange);});
        server.createContext("/directory", exchange -> {handleDirectory(exchange);});
        server.createContext("/files", exchange -> {handleFiles(exchange);});
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("HTTPS Server started on port " + PORT);
    }


    private void handleHome(HttpExchange exchange) throws IOException{
        if("POST".equalsIgnoreCase(exchange.getRequestMethod())){
            sendResponse(exchange, 200, "{\"status\": \"Home POST\"}");
        }
        else if("GET".equalsIgnoreCase(exchange.getRequestMethod())){
            sendResponse(exchange, 200, "{\"status\": \"Home GET\"}");
        }
        else sendResponse(exchange, 400, "{\"status\": \"HTTP method is not supported.\"}");
        
    }

    private void handleProfile(HttpExchange exchange) throws IOException{
        int statusCode;
        String response,statusString,profile;

        if("POST".equalsIgnoreCase(exchange.getRequestMethod())){
            statusCode = 200;
            profile = driveService.getcurrentlyPath();
        }
        else if("GET".equalsIgnoreCase(exchange.getRequestMethod())){
            String[] parametersForResponse = getMethodProfile(exchange);
            statusCode = Integer.parseInt(parametersForResponse[0]);
            profile = parametersForResponse[1];
        }
        else{
            statusCode = 404;
            profile="";
        }

        statusString = convertStatusCodeToStatusString(statusCode);
        response = String.format("{\"status\": \"%s\", \"Profile\": %s}", statusString, profile);
        sendResponse(exchange, statusCode, response);
    } 

    private String[] getMethodProfile(HttpExchange exchange) throws IOException{
        String query, profile;
        String[] parametersForResponse = new String[2];
        int statusCode;
        query = exchange.getRequestURI().getQuery();
        System.out.println("----");
        
        if (query == null){
            System.out.println(1);
            statusCode = 200;
            profile = driveService.buildCurrentlyProfile();
        }
        else if(query.split("=").length == 2){
            String[] querySplit = query.split("=");
            if(querySplit[0].equals("all")){
                if(querySplit[1].equals("True")){
                    statusCode=200;
                    profile=driveService.buildAllProfiles();
                }
                else if(querySplit[1].equals("False")){
                    statusCode=200;
                    profile=driveService.buildCurrentlyProfile();
                }
                else{
                    statusCode=400;
                    profile="";
                }
            }
            else{
                statusCode=400;
                profile="";
            }
        }
        else{
            System.out.println(3);

            statusCode=400;
            profile="";
        }

        System.out.println(4);

        parametersForResponse[0] = Integer.toString(statusCode);
        parametersForResponse[1] = profile;
        return parametersForResponse;

    }


    private void handleDirectory(HttpExchange exchange) throws IOException{
        if("POST".equalsIgnoreCase(exchange.getRequestMethod())){
            sendResponse(exchange, 200, "{\"status\": \"Home POST\"}");
        }
        else if("GET".equalsIgnoreCase(exchange.getRequestMethod())){
            sendResponse(exchange, 200, "{\"status\": \"Home GET\"}");
        }
        else sendResponse(exchange, 405, "{\"status\": \"HTTP method is not supported.\"}");
        
    }

    private void handleFiles(HttpExchange exchange) throws IOException{
        if("POST".equalsIgnoreCase(exchange.getRequestMethod())){
            sendResponse(exchange, 200, "{\"status\": \"Home POST\"}");
        }
        else if("GET".equalsIgnoreCase(exchange.getRequestMethod())){
            sendResponse(exchange, 200, "{\"status\": \"Home GET\"}");
        }
        else sendResponse(exchange, 405, "{\"status\": \"HTTP method is not supported.\"}");
        
    }






    private String convertStatusCodeToStatusString(int statusCode){
        if(statusCode == 200){return "Ok";}
        if(statusCode == 404){return "Error: Not Found";}
        if(statusCode == 400){return "Error: Bad Request";}
        //if(statusCode == 500){return "Error: Internal Server Error";}
        return "Error: Internal Server Error";
    }

}


class ServerHTTPs {
    protected static final int PORT = 8001;
    protected static String PASSWORD;

    public ServerHTTPs() throws Exception{
        loadEnv(); 
    }

    protected void loadEnv() throws Exception {
        var props = new Properties();
        try (var is = new FileInputStream("../resources/keystore_pass.env")) {
            props.load(is);
            props.forEach((k, v) -> System.setProperty((String)k, (String)v));
        } catch (java.io.FileNotFoundException e) {
            throw new RuntimeException("Arquivo .env não encontrado no caminho.");
        }

        PASSWORD = System.getProperty("KEYSTORE_PASS");
        if (PASSWORD == null) throw new RuntimeException("KEYSTORE_PASS não encontrada dentro do .env!");
    }



    protected void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) { os.write(bytes); }
    }

    protected SSLContext createSSLContext() throws Exception {
        KeyStore ks = KeyStore.getInstance("JKS");
        // Corrigido para Server.class (não SecureServer)
        try (var is = new java.io.FileInputStream("../resources/keystore.jks")) {
            ks.load(is, PASSWORD.toCharArray());
        } catch (java.io.FileNotFoundException e) {
            throw new RuntimeException("Arquivo não achado! Verifique se você está rodando o comando java da pasta correta.");
        }
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, PASSWORD.toCharArray());
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), null, null);
        return sslContext;
    }
}