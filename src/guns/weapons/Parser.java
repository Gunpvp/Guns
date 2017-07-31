package guns.weapons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import guns.weapons.data.GunData;

public class Parser {
	
	public static void saveToFile(File file, GunData data) {
		
		FileOutputStream fout;
		ObjectOutputStream oos;
		
		try {
			fout = new FileOutputStream(file);
			oos = new ObjectOutputStream(fout);
			
			oos.writeObject(data);
			oos.flush();
			
			fout.close();
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	
	}
	
	public static GunData loadFromFile(File file) {
		
		FileInputStream fin;
		ObjectInputStream ois;
		GunData data = null;
		
		try {
			fin = new FileInputStream(file);
			ois = new ObjectInputStream(fin);
			
			data = (GunData) ois.readObject();
			
			fin.close();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
}
