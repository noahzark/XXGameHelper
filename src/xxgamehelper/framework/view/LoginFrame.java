package xxgamehelper.framework.view;
import info.clearthought.layout.TableLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import javax.swing.WindowConstants;
import xxgamehelper.framework.control.listener.LoginFrameListener;

/***
 * A login frame template contains two input box and a hint label.
 * @author LongFangzhou
 * @version 1.0
 */
public class LoginFrame extends javax.swing.JFrame {;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8545418791754043434L;
	private JLabel labelUsername;
	private JTextField jTextFieldA;
	private JLabel labelPassword;
	private JLabel labelHint;
	private JButton buttonCancel;
	private JButton buttonConfirm;
	private JTextField jTextFieldB;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				LoginFrame inst = new LoginFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public LoginFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			TableLayout thisLayout = new TableLayout(new double[][] {{TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL}, {30.0, 20.0, 30.0, 20.0, 30.0, 20.0}});
			thisLayout.setHGap(5);
			thisLayout.setVGap(5);
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			this.setTitle("Login Frame");
			{
				labelUsername = new JLabel();
				getContentPane().add(labelUsername, "1, 0");
				labelUsername.setText("Username:");
			}
			{
				jTextFieldA = new JTextField(null, "", 0);
				getContentPane().add(getJTextFieldA(), "1, 1, 2, 1");
			}
			{
				labelPassword = new JLabel();
				getContentPane().add(labelPassword, "1, 2");
				labelPassword.setText("Password:");
			}
			{
				jTextFieldB = new JTextField();
				getContentPane().add(getJTextFieldB(), "1, 3, 2, 3");
			}
			{
				buttonConfirm = new JButton();
				getContentPane().add(buttonConfirm, "1, 4");
				buttonConfirm.setText("Confirm");
			}
			{
				buttonCancel = new JButton();
				getContentPane().add(buttonCancel, "2, 4");
				buttonCancel.setText("Cancel");
			}
			{
				labelHint = new JLabel();
				getContentPane().add(labelHint, "0, 5, 3, 5, c, f");
				labelHint.setText("Copyright Long Fangzhou. 2014");
			}
			pack();
			this.setSize(400, 220);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	public JLabel getLabelUsername() {
		return labelUsername;
	}
	
	/**
	 * @return the labelPassword
	 */
	public JLabel getLabelPassword() {
		return labelPassword;
	}

	/**
	 * @return the labelHint
	 */
	public JLabel getLabelHint() {
		return labelHint;
	}

	public JTextField getJTextFieldA() {
		return jTextFieldA;
	}
	
	public JTextField getJTextFieldB() {
		return jTextFieldB;
	}
	
	public void addActionListener(LoginFrameListener listener) {
		this.buttonConfirm.addActionListener(listener);
		this.buttonCancel.addActionListener(listener);
	}

	/**
	 * @return the buttonCancel
	 */
	public JButton getButtonCancel() {
		return buttonCancel;
	}

	/**
	 * @return the buttonConfirm
	 */
	public JButton getButtonConfirm() {
		return buttonConfirm;
	}

}
