package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	//SCREEN SETTINGS
	final int originalTileSize = 16; //16 x16
	final int scale = 3;
	
	final int tileSize = originalTileSize * scale; //48x48 tile
	
	//Kích thước màn hình
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; //768 pixels
	final int screenHeight = tileSize * maxScreenRow; //576 pixels

	
	
	//FPS
	int FPS = 60;
	
	KeyHandler keyH = new KeyHandler();
	//giữ cho chương trình chạy cho đến khi bạn dừng nó
	Thread gameThread;
	
	
	//Set player's default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;

	public GamePanel() {
		//Dòng này đặt kích thước mong muốn cho thành phần.
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		//Dòng này kích hoạt đệm kép cho thành phần
		//(Đệm kép là kỹ thuật được sử dụng để cải thiện
        //độ mượt mà của các hình ảnh động và đồ họa trên màn hình.)
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		//khi dùng lệnh này hàm run sẽ được gọi
		gameThread.start();
	}

	
	//Trong phương thức này chúng ta sẽ tạo vòng lặp trò chơi
	@Override
//	public void run() {
//		
//		double drawInterval = 1000000000/FPS; //thời gian vẽ 0.01666 seconds
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		//miễn là gameThread còn tồn tại nó sẽ lặp lại các
//		//quy trình được viết bên trong
//		while(gameThread != null) {
//					
//			// UPDATE: update information such as character positions
//			update();
//			// DRAW: draw the screen with the updated information
//			repaint();
//			
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				//chuyển nano giây => mili giây để phù hợp với phương thức sleep
//				remainingTime = remainingTime/1000000;
//				
//				if(remainingTime < 0) {
//					remainingTime = 0;
//				}
//				
//				Thread.sleep((long) remainingTime);
//				
//				nextDrawTime += drawInterval;
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//	}
	
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0 ;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >=1) {
			update();
			repaint();
			delta--;
			drawCount++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("PFS:" + drawCount);
				drawCount =0;
				timer = 0;
			}
		}
	}
	
	public void update() {
	//tính từ góc màn hình bên trái sẽ là tọa độ X:0 Y:0
	//X tăng nếu di chuyển qua phải
	//Y tăng nếu di chuyển xuống dưới
		if(keyH.upPressed == true)
		{
			//di chuyển lên trên speed(4) pixel
			playerY = playerY- playerSpeed;			
		}
		else if(keyH.downPressed == true)
		{
			playerY = playerY + playerSpeed;			
		}
		else if(keyH.leftPressed == true)
		{
			playerX = playerX - playerSpeed;			
		}
		else if(keyH.rightPressed == true)
		{
			playerX = playerX + playerSpeed;			
		}
		
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		//chuyển đối tượng Graphics g => Graphics2D g2
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.white);
		//vẽ 1 hình chữ nahajt lên màn hình
		g2.fillRect(playerX, playerY,tileSize,tileSize);
		//loại bỏ đối tượng g2 sau khi vẽ
		g2.dispose();
	}
}
