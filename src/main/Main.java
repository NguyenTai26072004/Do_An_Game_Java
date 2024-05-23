package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame window =new JFrame();
		//Câu lệnh này để đóng cửa sổ game đúng cách khi ta ấn dấu x
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Không thể thay đổi kích thước cửa sổ
		window.setResizable(false);
		//Đặt tiêu đề cho game
		window.setTitle("Indomitable Warrior ");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		//Bởi vì cửa sổ cần Chỉnh kích thước để phù hợp 
		//với kích thước ưa thích và cách bố trí của các thành phần con
		window.pack();
		
		//Cửa sổ sẽ được xuất hiện ở giữa màn hình
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();
	}

}
