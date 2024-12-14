//package com.Quande.servlet;
//
//import javax.imageio.ImageIO;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.util.Random;
//
//@WebServlet(name = "ImgServlet")
//public class ImgServlet extends HttpServlet {
//    /**
//     * 处理POST请求，生成并返回一个包含简单数学验证题的图片
//     * 该方法首先创建一个缓冲图像，并在图像上绘制一个验证题，包括两个随机数和它们的和
//     * 验证题的样式（如背景色、文字颜色）是随机生成的，以增加验证题的多样性
//     * 最后，将验证题的图片写入响应流，同时将验证题的答案保存在session中，用于后续的验证
//     *
//     * @param request  包含请求信息的HttpServletRequest对象
//     * @param response 用于发送响应的HttpServletResponse对象
//     * @throws ServletException 如果Servlet遇到异常
//     * @throws IOException 如果发生输入或输出异常
//     */
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // 创建一个68x25像素的RGB图像缓冲区
//        BufferedImage bfi = new BufferedImage(68, 25, BufferedImage.TYPE_INT_RGB);
//
//        // 获取图像的Graphics对象，用于绘制图形和文字
//        Graphics g = bfi.getGraphics();
//
//        // 创建一个StringBuffer对象，用于存储验证题的字符串表示
//        StringBuffer sb = new StringBuffer();
//
//        // 画背景框
//        Color color = new Color(200,215,250);
//        g.setColor(color);
//        g.fillRect(0, 0, 68,30);
//
//        // 第一个数字
//        Random r = new Random();
//        int tmp1 = r.nextInt(20);
//        g.setColor(new Color(r.nextInt(100),r.nextInt(100),r.nextInt(100))); // 设置随机颜色
//        g.drawString(tmp1+"",3, 18);
//
//        // 加号
//        g.setColor(new Color(r.nextInt(100),r.nextInt(100),r.nextInt(100))); // 设置随机颜色
//        g.drawString("+",18, 18);
//
//        // 第二个数字
//        int tmp2 = r.nextInt(20);
//        g.setColor(new Color(r.nextInt(100),r.nextInt(100),r.nextInt(100))); // 设置随机颜色
//        g.drawString(tmp2+"",33, 18);
//
//        // 等号
//        g.setColor(new Color(r.nextInt(100),r.nextInt(100),r.nextInt(100))); // 设置随机颜色
//        g.drawString("=",48, 18);
//
//        // 问号
//        g.setColor(new Color(r.nextInt(100),r.nextInt(100),r.nextInt(100))); // 设置随机颜色
//        g.drawString("?",57, 18);
//
//        // 保存到session
//        int result = tmp1+tmp2;
//        request.getSession().setAttribute("VerifyCode", result+"");
//
//        // 写入response输出流
//        ImageIO.write(bfi, "JPG", response.getOutputStream());
//    }
//}
//
//
//    /**
//     * 处理GET请求的方法
//     * 当服务器接收到GET请求时，会调用此方法执行相应的操作
//     *
//     * @param request  包含请求数据的HttpServletRequest对象，用于获取请求信息
//     * @param response 用于向客户端发送响应数据的HttpServletResponse对象
//     * @throws ServletException 如果Servlet遇到异常
//     * @throws IOException 如果发生输入或输出异常
//     *
//     * 注意：此方法通过调用doPost方法来处理GET请求，实现了代码的复用
//     */
//    public void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        // 调用doPost方法处理GET请求，统一请求处理逻辑
//        doPost(request,response);
//    }
//}



package com.Quande.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "ImgServlet")
public class ImgServlet extends HttpServlet {
    /**
     * 处理POST请求，生成并返回一个包含简单数学验证题的图片
     * 该方法首先创建一个缓冲图像，并在图像上绘制一个验证题，包括两个随机数和它们的和
     * 验证题的样式（如背景色、文字颜色）是随机生成的，以增加验证题的多样性
     * 最后，将验证题的图片写入响应流，同时将验证题的答案保存在session中，用于后续的验证
     *
     * @param request  包含请求信息的HttpServletRequest对象
     * @param response 用于发送响应的HttpServletResponse对象
     * @throws ServletException 如果Servlet遇到异常
     * @throws IOException 如果发生输入或输出异常
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建一个68x25像素的RGB图像缓冲区
        BufferedImage bfi = new BufferedImage(68, 25, BufferedImage.TYPE_INT_RGB);

        // 获取图像的Graphics2D对象，用于绘制图形和文字
        Graphics2D g = bfi.createGraphics();

        // 设置抗锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 画背景框
        Color color = new Color(200, 215, 250);
        g.setColor(color);
        g.fillRect(0, 0, 68, 30);

        // 添加噪声
        addNoise(g, 68, 25);

        // 添加扭曲
        addDistortion(g, 68, 25);

        // 第一个数字
        Random r = new Random();
        int tmp1 = r.nextInt(20);
        g.setColor(new Color(r.nextInt(100), r.nextInt(100), r.nextInt(100))); // 设置随机颜色
        g.drawString(tmp1 + "", 3, 18);

        // 加号
        g.setColor(new Color(r.nextInt(100), r.nextInt(100), r.nextInt(100))); // 设置随机颜色
        g.drawString("+", 18, 18);

        // 第二个数字
        int tmp2 = r.nextInt(20);
        g.setColor(new Color(r.nextInt(100), r.nextInt(100), r.nextInt(100))); // 设置随机颜色
        g.drawString(tmp2 + "", 33, 18);

        // 等号
        g.setColor(new Color(r.nextInt(100), r.nextInt(100), r.nextInt(100))); // 设置随机颜色
        g.drawString("=", 48, 18);

        // 问号
        g.setColor(new Color(r.nextInt(100), r.nextInt(100), r.nextInt(100))); // 设置随机颜色
        g.drawString("?", 57, 18);

        // 保存到session
        int result = tmp1 + tmp2;
        request.getSession().setAttribute("VerifyCode", result + "");

        // 写入response输出流
        ImageIO.write(bfi, "JPG", response.getOutputStream());

        // 释放资源
        g.dispose();
    }

    private void addNoise(Graphics2D g, int width, int height) {
        Random r = new Random();
        for (int i = 0; i < 50; i++) { // 添加50个噪声点
            int x = r.nextInt(width);
            int y = r.nextInt(height);
            g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            g.fillRect(x, y, 1, 1);
        }
    }

    private void addDistortion(Graphics2D g, int width, int height) {
        Random r = new Random();
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < 3; i++) { // 添加3条扭曲线
            int x1 = r.nextInt(width);
            int y1 = r.nextInt(height);
            int x2 = r.nextInt(width);
            int y2 = r.nextInt(height);
            int cx1 = r.nextInt(width);
            int cy1 = r.nextInt(height);
            int cx2 = r.nextInt(width);
            int cy2 = r.nextInt(height);
            CubicCurve2D curve = new CubicCurve2D.Float(x1, y1, cx1, cy1, cx2, cy2, x2, y2);
            g.draw(curve);
        }
    }

    /**
     * 处理GET请求的方法
     * 当服务器接收到GET请求时，会调用此方法执行相应的操作
     *
     * @param request  包含请求数据的HttpServletRequest对象，用于获取请求信息
     * @param response 用于向客户端发送响应数据的HttpServletResponse对象
     * @throws ServletException 如果Servlet遇到异常
     * @throws IOException 如果发生输入或输出异常
     *
     * 注意：此方法通过调用doPost方法来处理GET请求，实现了代码的复用
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 调用doPost方法处理GET请求，统一请求处理逻辑
        doPost(request, response);
    }
}
