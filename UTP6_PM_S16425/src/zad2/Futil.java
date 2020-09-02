package zad2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Futil extends SimpleFileVisitor <Path>{
	Charset charSetIn = Charset.forName("Cp1250");
	Charset charSetOut = Charset.forName("UTF-8");
	List<Path> list = new ArrayList<Path>();
	
	
	public static void processDir(String dirName, String resultFileName) {
		// TODO Auto-generated method stub
		Path outpath = Paths.get(resultFileName);
		Path directory = Paths.get(dirName);
		Futil futil = new Futil();
		
		futil.metodaW(outpath);
		try {
			futil.postVisitDirectory(directory, null);
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void metodaW(Path out) {
		List<ByteBuffer> blist = new ArrayList<ByteBuffer>();
		for(Path p : list) {
			try(FileChannel in = FileChannel.open(p, StandardOpenOption.READ)){
				ByteBuffer bb = ByteBuffer.allocate((int)in.size());
				int a = in.read(bb);
				if(a > 0) {
					bb.flip();
					CharBuffer cb = charSetIn.decode(bb);
					bb = charSetOut.encode(cb);
					blist.add(bb);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try(FileChannel out2 = FileChannel.open(out, StandardOpenOption.CREATE, StandardOpenOption.WRITE)){
			out2.write(blist.toArray(new ByteBuffer[] {}));
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		// TODO Auto-generated method stub
		if(file.toString().endsWith(".txt")) {
			System.out.println(file.getFileName());
			list.add(file);
		}
		return FileVisitResult.CONTINUE;
	}
	
	

}
