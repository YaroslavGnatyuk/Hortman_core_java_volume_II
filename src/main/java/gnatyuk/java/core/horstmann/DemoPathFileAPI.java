package gnatyuk.java.core.horstmann;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DemoPathFileAPI {
    public static void main(String[] args) throws IOException {
        /*Path tempFolder = Paths.get("./Test");
        System.out.println(" The file name is: " + tempFolder.getFileName());

        System.out.println(" It's URI is: " + tempFolder.toUri());

        System.out.println(" The file absolute path is: " + tempFolder.toAbsolutePath());

        System.out.println(" The file normalize path is: " + tempFolder.normalize());

        System.out.println(" The file normalize absolute path is: " + tempFolder.normalize().toAbsolutePath().toUri());

        System.out.println(" The file normalize path is: " + tempFolder.normalize().toRealPath(LinkOption.NOFOLLOW_LINKS));*/

        Path library = Paths.get("/win_media/Library");
        try(Stream<Path> entries = Files.walk(library,100)){
            List<Path> pathes = entries.parallel().collect(Collectors.toList());
            Optional<Path> result = pathes
                    .parallelStream()
                    .filter(file->file.getFileName().normalize().toString().equals("Закат.fb2"))
                    .findFirst();

            if (result.isPresent()){
                System.out.println(Files.size(result.get()));
            }
        }
    }
}
