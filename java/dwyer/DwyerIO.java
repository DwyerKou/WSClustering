package dwyer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class DwyerIO {
	public void writeSet(String FileName, Set<String> s) throws IOException{
		FileWriter writer=new FileWriter(FileName);
		Iterator<String> it = s.iterator(); 
		while(it.hasNext()){
			String str = it.next();
			writer.write(str + "\n");
		}
		writer.close();
	}
}
