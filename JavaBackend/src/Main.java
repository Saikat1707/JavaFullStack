import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main{
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000),0);

        //InetSocketAddress represents the address of our server (Ip address + port number)
        //since we only give the port number it runs our server on localhost that is 127.0.0.1:8000
        //it throws IllegalArgumentException when a port number is given outside the range


        //HttpServer.create(address,0);
        //what is this 0? it is called Backlog
        //Backlog = How many incoming client connections can wait in line before the server accepts them.
        //The backlog controls the maximum queue size.
        //since we give 0 that means we let the use default queue size



        server.createContext("/",new HomeHandler());
        server.createContext("/about",new AboutHandler());
        server.createContext("/me", new ProfileHandler());

        server.start();
        System.out.println("Server started");
    }
}
