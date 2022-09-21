package ca.ulaval.glo4002.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResourcesImageLoader implements ImageLoader {
    @Override
    public byte[] load(String path) {
        try {
            return Files.readAllBytes(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
