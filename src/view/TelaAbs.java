package view;

import javax.swing.JFrame;

public class TelaAbs extends JFrame {
	
	private int WIDTH, HEIGHT;
	private String TITLE;
	
	public TelaAbs(String TITLE, int WIDTH, int HEIGHT) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.TITLE = TITLE;
		
		setSize(WIDTH, HEIGHT);
		setTitle(TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		
		setVisible(false);
	}
	
}
