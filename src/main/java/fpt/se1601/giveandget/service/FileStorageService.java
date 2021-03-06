package fpt.se1601.giveandget.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.stream.Stream;

@Service
public class FileStorageService {
    private static final Path root = Paths.get(System.getProperty("user.dir") + "/webdocu-client/public");

    public static void init() {
        try {
            File file = new File(String.valueOf(root));
            if (!file.exists())
            {
                System.out.println(root.toString());
                Files.createDirectory(root);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public void deleteFileStartWith(String filename) {
        try {
            File directory = new File(String.valueOf(root));
            for (File f : directory.listFiles()) {
                if (f.getName().startsWith(filename)) {
                    f.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String save(MultipartFile files[], String fileName) {
        try {
            deleteFileStartWith(fileName);
            String path;
            path = fileName + '0' + '.' + FilenameUtils.getExtension(files[0].getOriginalFilename());
            for (int index = 0; index < files.length; index++) {
                File dest = new File(root.toString() + '/' + fileName + index + '.' + FilenameUtils.getExtension(files[index].getOriginalFilename()));
                Files.copy(files[index].getInputStream(), dest.toPath());
                if (index > 0)
                    path += '&' + fileName + index + '.' + FilenameUtils.getExtension(files[index].getOriginalFilename());
            }
            return path;
        } catch (Exception e) {
            return "Could not store the file";
        }
    }

    public String save(MultipartFile file, String fileName) {
        try {
            deleteFileStartWith(fileName);
            Files.copy(file.getInputStream(), root.resolve(fileName));
            return fileName;
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(root, 1).filter(path -> !path.equals(root)).map(root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
