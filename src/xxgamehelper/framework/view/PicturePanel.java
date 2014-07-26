package xxgamehelper.framework.view;

import java.awt.Graphics;
import java.awt.Image;

/***
 * A panel to show an image.
 * @author LongFangzhou
 */
public class PicturePanel extends javax.swing.JPanel {

	private static final long serialVersionUID = -6163168963073616763L;
	private Image image;
	
	PicturePanel(Image image) {
		this.image = image;
	}
	
	@Override  
    public void paintComponent(Graphics g) {
		g.drawImage(image,
				image.getWidth(this), image.getHeight(this),
				this);
	}

}
