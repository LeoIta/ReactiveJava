package com.reactiveJavaProject.sec03FluxCreateGenerate.assignment;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class FileReaderService {

    private static final Path PATH = Paths.get("src/main/resources/assignment/sec03FluxCreateGenerate");

    public void readLines(String fileName, int lines) {
        read(fileName)
                .take(lines)
                .subscribe(Util.subscriber());
    }

    private Flux<String> read(String fileName) {
        return Flux.generate(
                openReader(fileName),
                read(),
                closeReader()
        );
    }

    private Callable<BufferedReader> openReader(String fileName) {
        return () -> Files.newBufferedReader(PATH.resolve(fileName));
    }

    private BiFunction<BufferedReader, SynchronousSink<String>, BufferedReader> read() {
        return (bufferedReader, sink) -> {
            try {
                String line = bufferedReader.readLine();
                if (Objects.isNull(line))
                    sink.complete();
                else
                    sink.next(line);
            } catch (IOException e) {
                sink.error(e);
            }
            return bufferedReader;
        };
    }

    private Consumer<BufferedReader> closeReader() {
        return br -> {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

}
