package game.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	public static BufferedImage grassU1,grassU2,grassL,grassR,grassD1,grassD2,grassC,
	grassCornerOutUR,grassCornerOutUL,grassCornerOutDR,grassCornerOutDL,
	grassCornerInUR,grassCornerInUL,grassCornerInDR,grassCornerInDL,backGround,empty,filter,health,pauseScreen,
	exit,select,explosion;
	public static BufferedImage[] guyIdleL, guyIdleR, guyLeft, guyRight,
	guyAttackL,guyAttackR,guyDashAttackL,guyDashAttackR,guyDashAttackUL,guyDashAttackUR,bat,test;
	public static int picW =16;
	public static int picH = 16;
	public static int frameW=32, frameH=32;
	
	public static void init() {
		BufferedImage sheet =  ImageLoader.load("/textures/pictures.png");
		guyIdleL = new BufferedImage[4];
		guyIdleR = new BufferedImage[4];
		guyLeft=new BufferedImage[3];
		guyRight=new BufferedImage[3];
		guyAttackL=new BufferedImage[3];
		guyAttackR=new BufferedImage[3];
		guyDashAttackL=new BufferedImage[3];
		guyDashAttackR=new BufferedImage[3];
		guyDashAttackUL=new BufferedImage[3];
		guyDashAttackUR=new BufferedImage[3];
		bat=new BufferedImage[5];
		test = new BufferedImage[1];
		health = sheet.getSubimage(0, frameW, 11,9);
		
		sheet =  ImageLoader.load("/textures/grass tileset.png");
		grassU1=sheet.getSubimage(0, 0, picW, picW);
		grassU2=sheet.getSubimage(picW, 0, picW, picW);
		grassD1=sheet.getSubimage(picW*2, 0, picW, picW);
		grassD2=sheet.getSubimage(picW*3,0, picW, picW);
		grassR=sheet.getSubimage(picW*2, picW, picW, picW);
		grassL=sheet.getSubimage(picW*3, picW, picW, picW);
		grassCornerOutUR=sheet.getSubimage(0, picW*2, picW, picW);
		grassCornerOutDR=sheet.getSubimage(picW, picW*2, picW, picW);
		grassCornerOutDL=sheet.getSubimage(picW*2, picW*2, picW, picW);
		grassCornerOutUL=sheet.getSubimage(picW*3, picW*2, picW, picW);
		grassCornerInUR=sheet.getSubimage(picW*2, picW*4, picW, picW);
		grassCornerInDR=sheet.getSubimage(picW*3, picW*4, picW, picW);
		grassCornerInDL=sheet.getSubimage(0, picW*5, picW, picW);
		grassCornerInUL=sheet.getSubimage(picW, picW*5, picW, picW);
		grassC=sheet.getSubimage(picW*3, picW*6, picW, picW);
		empty=sheet.getSubimage(0, picW, picW, picW);
		sheet=ImageLoader.load("/textures/guy attacks.png");
		picW=28;
		picH=26;
		guyAttackL[0]=sheet.getSubimage(0, 0, 22, 21);
		guyAttackL[1]=sheet.getSubimage(picW, 0, 22, 21);
		guyAttackL[2]=sheet.getSubimage(picW*2, 0, 22, 21);
		
		guyAttackR[0]=sheet.getSubimage(0, picH, 22, 21);
		guyAttackR[1]=sheet.getSubimage(picW, picH, 22, 21);
		guyAttackR[2]=sheet.getSubimage(picW*2, picH, 22, 21);
		
		guyDashAttackL[0]=sheet.getSubimage(0, picH*2, picW, 21);
		guyDashAttackL[1]=sheet.getSubimage(picW, picH*2, picW, 21);
		guyDashAttackL[2]=sheet.getSubimage(picW*2, picH*2, picW, 21);
		
		guyDashAttackR[0]=sheet.getSubimage(0, picH*3, picW, 21);
		guyDashAttackR[1]=sheet.getSubimage(picW, picH*3, picW, 21);
		guyDashAttackR[2]=sheet.getSubimage(picW*2, picH*3, picW, 21);
		
		guyDashAttackUL[0]=sheet.getSubimage(0, picH*4, 17, picH);
		guyDashAttackUL[1]=sheet.getSubimage(picW, picH*4, 17, picH);
		guyDashAttackUL[2]=sheet.getSubimage(picW*2, picH*4, 17, picH);
		
		guyDashAttackUR[0]=sheet.getSubimage(0, picH*5, 17, picH);
		guyDashAttackUR[1]=sheet.getSubimage(picW, picH*5, 17, picH);
		guyDashAttackUR[2]=sheet.getSubimage(picW*2, picH*5, 17, picH);
		
		sheet =  ImageLoader.load("/textures/guy sprite sheet.png");
		frameW=19; 
		frameH=21;
		picW=19;
		picH=21;
		guyLeft[0]= sheet.getSubimage(0, 0, picW,picH);
		guyLeft[1]= sheet.getSubimage(frameW, 0, picW,picH);
		guyLeft[2]= sheet.getSubimage(2*frameW, 0, picW,picH);
		
		guyRight[0]= sheet.getSubimage(0,frameH, picW,picH);
		guyRight[1]= sheet.getSubimage(frameW,frameH, picW,picH);		
		guyRight[2]= sheet.getSubimage(frameW*2,frameH, picW,picH);	
		picW=15;
		
		guyIdleL[0] = sheet.getSubimage(0, frameH*2, picW, picH);
		guyIdleL[1] = sheet.getSubimage(frameW,frameH*2, picW, picH);
		guyIdleL[2] = sheet.getSubimage(frameW*2,frameH*2, picW, picH);
		guyIdleL[3] = sheet.getSubimage(frameW*3, frameH*2, picW, picH);
		
		guyIdleR[0] = sheet.getSubimage(0, frameH*3, picW, picH);
		guyIdleR[1] = sheet.getSubimage(frameW,frameH*3, picW, picH);
		guyIdleR[2] = sheet.getSubimage(frameW*2,frameH*3, picW, picH);
		guyIdleR[3] = sheet.getSubimage(frameW*3, frameH*3, picW, picH);
		
		sheet=ImageLoader.load("/textures/Bat.png");
		picW=16;
		picH=11;
		bat[0]=sheet.getSubimage(0, 0, picW,picH);
		bat[1]=sheet.getSubimage(picW, 0, picW,picH);
		bat[2]=sheet.getSubimage(0, picH, picW,picH);
		bat[3]=sheet.getSubimage(picW, picH, picW,picH);
		bat[4]=sheet.getSubimage(0, picH*2, picW,picH);
		backGround=ImageLoader.load("/textures/bg2.png");
		pauseScreen=ImageLoader.load("/textures/pause screen.png");
		select=ImageLoader.load("/textures/select.png");
		filter=ImageLoader.load("/textures/filter.png");
		exit=ImageLoader.load("/textures/test door.png");
		test[0]=grassC;
	}

}
