package xxgamehelper.framework.view;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import xxgamehelper.framework.control.messenger.Messenger;

/***
 * A dialog to show validation code image
 * and recieve user input with a action listener.
 * @author LongFangzhou
 */
public class ValidationCodeDialog extends javax.swing.JDialog {

	private static final long serialVersionUID = -7788463834654580009L;
	private JButton btnConfirm;
	private JLabel lblText;
	private JPanel picturePanel;
	private JTextField txtValidationCode;
	
	private ActionListener codeListener;
	private Image image;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * Show the validation code dialog
	 * and receive code text message with a action listener.
	 * @param messenger The helper messenger
	 * @param image The code image
	 * @param codeListener The action listener to receive codes
	 */
	public static void show(
			Messenger messenger,
			String fileName,
			final ActionListener codeListener) {
		try {
			final Image image = ImageIO.read(new File(
					messenger.getWorkPath()+fileName));
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					JFrame frame = new JFrame();
					ValidationCodeDialog inst = new ValidationCodeDialog(frame, image, codeListener);
					inst.setVisible(true);
				}
			});
		} catch (IOException e) {
			messenger.showError(e);
			codeListener.actionPerformed(new ActionEvent(
					null,
					ActionEvent.ACTION_PERFORMED,
					""));
			return;
		}
		
	}
	
	public ValidationCodeDialog(JFrame frame, Image image,
			ActionListener codeListener) {
		super(frame);
		this.image = image;
		this.codeListener = codeListener;
		initGUI();
	}
	
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			getContentPane().setLayout(thisLayout);
			{
				lblText = new JLabel();
				getContentPane().add(lblText, BorderLayout.WEST);
				lblText.setText("Validation Code:");
			}
			{
				picturePanel = new PicturePanel(image);
				getContentPane().add(picturePanel, BorderLayout.NORTH);
				picturePanel.setPreferredSize(new java.awt.Dimension(384, 220));
			}
			{
				txtValidationCode = new JTextField();
				getContentPane().add(txtValidationCode, BorderLayout.CENTER);
			}
			{
				btnConfirm = new JButton();
				getContentPane().add(btnConfirm, BorderLayout.SOUTH);
				btnConfirm.setText("Confirm");
				btnConfirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						codeListener.actionPerformed(new ActionEvent(
								evt,
								evt.getID(),
								txtValidationCode.getText()));
						dispose();
					}
				});
			}
			this.setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
