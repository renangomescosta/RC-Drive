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

import app.service.DriveService;



public class Server {

    private static final int PORT = 8001;
    private static String PASSWORD;

    public static void main(String[] args) {
        try {
            loadEnv(); // Carrega variáveis
            startServer(); // Agora o método existe
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadEnv() throws Exception {
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

    private static void startServer() throws Exception {
        SSLContext sslContext = createSSLContext();
        HttpsServer server = HttpsServer.create(new InetSocketAddress(PORT), 0);
        server.setHttpsConfigurator(new HttpsConfigurator(sslContext));

        // Inicializa Drive
        DriveService drive = new DriveService();

        server.createContext("/", exchange -> sendResponse(exchange, 200, "Hello HTTPS!"));
        server.createContext("/api/profile", exchange -> sendResponse(exchange, 200, "{\"status\": \"ok\"}"));

        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("HTTPS Server started on port " + PORT);
    }

    private static void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) { os.write(bytes); }
    }

    private static SSLContext createSSLContext() throws Exception {
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