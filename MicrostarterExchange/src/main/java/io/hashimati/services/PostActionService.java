package io.hashimati.services;

import io.micronaut.scheduling.annotation.Async;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

@Singleton
public class PostActionService {
    @Inject
    private SocketService socketService;

//    @Async
//    public Path writeTextToShareLocation(String sharedLocation, String filePrefix, String fileExtension, String content)
//    {
//        String filename = filePrefix + "_" +  Instant.now()+  "." + fileExtension;
//        try {
//           return  Files.writeString(new File(sharedLocation, filename).toPath(), content);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @Async
//    public Path writeBinaryToShareLocation(String sharedLocation, String filePrefix, String fileExtension, byte[] content)
//    {
//        String filename = filePrefix + "_" +  Instant.now()+  "." + fileExtension;
//        try {
//            return  Files.write(new File(sharedLocation, filename).toPath(), content);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @Async
//    public void writeStringToSocket(String host, int port, String content)
//    {
//        socketService.sendString(host, port, content);
//    }
//    @Async
//    public void writeBinaryToSocket(String host, int port, byte[] content)
//    {
//        socketService.sendBinary(host, port, content);
//    }


}
