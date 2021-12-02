package p3;

import static org.junit.Assert.*;
//import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.ZipFileSet;
import org.junit.Test;
import org.apache.tools.ant.FileScanner;

public class JarTest {

	 

	@Test
	public void testGrabManifests() {
		
	}

	@Test
	public void testGrabNonFileSetResources() {
		//fail("Not yet implemented");
		Jar j = new Jar();

		File file = new File("hello.txt");
		File file2 =  new File("helloThere.txt");

		ResourceCollection[] rcs = {(ResourceCollection) file, (ResourceCollection) file2};
        Resource[][] result = new Resource[2][];

		assertEquals(result, j.grabNonFileSetResources(rcs));
		
	}

	@Test
	public void testGrabResources() throws IOException {
		

		
		/*Project project = new Project();
		project.setDescription("Helllo");
		Jar j = new Jar();
		ZipFileSet z = new ZipFileSet();
		z.setFullpath("C:\\Users\\miran\\OneDrive\\Desktop\\581 Final");
		File file = new File("C:\\Users\\miran\\OneDrive\\Desktop\\581 Final\\581Final\\dir");
		file.createNewFile();
		file.mkdirs();
		z.setDir(file);
		z.setupDirectoryScanner(null);
		FileSet[] filesets = {z};
		FileScanner scanner = z.getDirectoryScanner(project);
		z.setupDirectoryScanner(scanner);
        Resource[][] result = new Resource[filesets.length][];
        assertEquals(result, j.grabResources(filesets));*/
	}

}
