// THE VIEW
package gui;

import java.awt.event.ActionListener;

import javax.swing.*;

public class GUIView extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final String WINDOW_TITLE = "LastFM favourites list to Spotify playlist generator";
	private static final String INSTRUCTIONS = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec nec finibus nisi, vitae pulvinar neque. Aliquam ullamcorper odio in libero malesuada, sed egestas sapien pretium. Nunc suscipit a mi ut fermentum. Praesent et rutrum urna. Aliquam at mollis nulla. Suspendisse quam nisi, fermentum tempus lorem eu, euismod ultrices nunc. In ultricies, mauris ac porta auctor, purus nisl dapibus leo, vel faucibus augue augue a nunc.";
	
	private JTextField usernameTextField;
	private JLabel usernameLabel;
	
	private JButton generateButton;
	
	private JTextArea listedSongsArea;
	private JTextArea instructionsArea;
	
	private JScrollPane scrollPane;
	
	public GUIView() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		JPanel mainPanel = new JPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 400);
		this.setLocationRelativeTo(null);
		
		usernameTextField = new JTextField(10);
		usernameLabel = new JLabel("Your username");
		generateButton = new JButton("Generate playlist");
		
		listedSongsArea = new JTextArea(20, 50); // TODO copy to clipbard
		listedSongsArea.setEditable(false);
		listedSongsArea.setFont(UIManager.getDefaults().getFont("TabbedPane.font"));
		
		scrollPane = new JScrollPane(listedSongsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		instructionsArea = new JTextArea(INSTRUCTIONS, 5, 20);
		instructionsArea.setOpaque(false);
		instructionsArea.setEditable(false);
		instructionsArea.setLineWrap(true);
		instructionsArea.setHighlighter(null);
		instructionsArea.setFont(UIManager.getDefaults().getFont("TabbedPane.font"));
		
		mainPanel.add(usernameTextField);
		mainPanel.add(usernameLabel);
		mainPanel.add(generateButton);
		mainPanel.add(scrollPane);
		mainPanel.add(instructionsArea);
		
		this.add(mainPanel);
		this.setTitle(WINDOW_TITLE);
	}
	
	public String getUsername() {
		return usernameTextField.getText();
	}
	
	public void setListedSongsArea(String text) {
		listedSongsArea.setText(text);
	}
	
	public void addGenerateListener(ActionListener listenForGenerateButton) {
		generateButton.addActionListener(listenForGenerateButton);
	}
	
	public void setErrorMessage(String errorMessage) {
		listedSongsArea.setText(errorMessage);
	}


}
