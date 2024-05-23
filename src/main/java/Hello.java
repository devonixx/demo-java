package main.java;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Hello extends HttpServlet {

   public void init() throws ServletException {
      // Do required initialization if needed
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

      // Set response content type
      response.setContentType("image/png");

      // Generate the Fibonacci spiral
      int width = 800;
      int height = 800;
      BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2d = bufferedImage.createGraphics();

      // Set background color
      g2d.setColor(Color.WHITE);
      g2d.fillRect(0, 0, width, height);

      // Set spiral color
      g2d.setColor(Color.BLACK);

      // Draw Fibonacci spiral
      int x = width / 2;
      int y = height / 2;
      int a = 0;
      int b = 1;
      int temp;

      for (int i = 0; i < 10; i++) {
         temp = a;
         a = b;
         b = temp + b;

         drawArc(g2d, x, y, a, i % 4);

         switch (i % 4) {
            case 0:
               x += a;
               y -= b;
               break;
            case 1:
               x += b;
               y += a;
               break;
            case 2:
               x -= a;
               y += b;
               break;
            case 3:
               x -= b;
               y -= a;
               break;
         }
      }

      // Cleanup
      g2d.dispose();

      // Write image as response
      ServletOutputStream out = response.getOutputStream();
      ImageIO.write(bufferedImage, "png", out);
      out.close();
   }

   private void drawArc(Graphics2D g2d, int x, int y, int radius, int quadrant) {
      int arcStartAngle;
      switch (quadrant) {
         case 0:
            arcStartAngle = 0;
            break;
         case 1:
            arcStartAngle = 90;
            break;
         case 2:
            arcStartAngle = 180;
            break;
         case 3:
            arcStartAngle = 270;
            break;
         default:
            arcStartAngle = 0;
            break;
      }
      g2d.drawArc(x - radius, y - radius, 2 * radius, 2 * radius, arcStartAngle, 90);
   }
}
