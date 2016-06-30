
	import java.awt.Graphics;
	import java.awt.Image;
	import javax.swing.ImageIcon;
	import javax.swing.JPanel;

	@SuppressWarnings("serial")
	public class Menu extends JPanel {
		private String url = "d:/naoooka/java/projekty/Makao/obrazki/";

		public Menu() {
		}

		public void paintComponent(Graphics g) {
			ImageIcon im = new ImageIcon(url + "tlo_dol.jpg");
			Image img = im.getImage();
			g.drawImage(img, 0, 0, null, null);
			setVisible(true);
		}

		public String toString() {
			return "menu do gry w Makao";
		}
	}