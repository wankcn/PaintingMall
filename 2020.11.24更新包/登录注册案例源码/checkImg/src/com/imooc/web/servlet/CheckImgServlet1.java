package com.imooc.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CheckImgServlet1")
public class CheckImgServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int width = 120;
		int height = 30;
		// 步骤一:在内存中生成一张图片
		BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		// 步骤二:操作该图片,设置背景色及绘制边框
		Graphics graphics = bufferedImage.getGraphics();
		// 设置背景色
		graphics.setColor(Color.YELLOW);
		graphics.fillRect(0, 0, width, height);
		// 绘制边框
		graphics.setColor(Color.BLUE);
		graphics.drawRect(0, 0, width-1, height-1);
		
		// 步骤三:生成随机的四个字母或数字,写入到图片中
		Graphics2D g2d = (Graphics2D)graphics;
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("宋体",Font.BOLD,18));//a 4 3 r
		
		String words = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		int x = 10;
		for(int i=0;i<4;i++){
			int idx = random.nextInt(words.length());
			// 获得指定位置的字符
			char ch = words.charAt(idx);
			// 旋转的角度  -30 到 30 之间
			int jiaodu = random.nextInt(60) - 30;
			// 将角度转化成弧度:
			double theta = jiaodu * Math.PI / 180;
			g2d.rotate(theta, x, 20);
			g2d.drawString(String.valueOf(ch), x, 20);
			g2d.rotate(-theta, x, 20);
			
			x+=30;
		}
		// 步骤四:将内存中的图片,进行输出
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
