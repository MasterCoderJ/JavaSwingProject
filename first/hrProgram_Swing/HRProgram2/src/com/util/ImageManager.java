package com.util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageManager {

	// 이미지에 대한 경로를 전달하면 이미지 객체를 반환하는 메서드 정의		
	public Image[] createImages(String[] imgName) {
			
		Class myClass= this.getClass();
		Image[] images = new Image[imgName.length];
		
		for(int i=0; i<imgName.length;i++) {
			//자원의 위치를 의미, 패키지 경로도 처리가능...
			URL url = myClass.getClassLoader().getResource(imgName[i]);
			
			try {
				//생성된 이미지를 배열에 담기
				images[i] = ImageIO.read(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

		return images;
	}
	
	/*----------------------------------------------------------------
	 * path: 클래스 패스내의 이미지경로 
	 * width, height : 크기 조정하고 싶은 값
	 ----------------------------------------------------------------*/
	public ImageIcon getIcon(String path, int width, int height) {
		Class myClass = this.getClass();
		ClassLoader loader = myClass.getClassLoader();
		URL url = loader.getResource(path);
		Image scaledImg = null;
		try {
			Image img = ImageIO.read(url);
			scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ImageIcon icon = new ImageIcon(scaledImg);

		return icon;
	}
	
	//이미지 삭제
	public static boolean deleteFile(String path) {
		File file = new File(path);
		boolean result = file.delete();
		
		return result;
	}


}
