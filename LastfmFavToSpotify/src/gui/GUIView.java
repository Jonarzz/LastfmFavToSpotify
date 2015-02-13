// THE VIEW
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUIView extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final String WINDOW_TITLE = "LastFM favourites list to Spotify playlist generator";
	private static final String INSTRUCTIONS = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec nec finibus nisi, vitae pulvinar neque. Aliquam ullamcorper odio in libero malesuada, sed egestas sapien pretium. Nunc suscipit a mi ut fermentum. Praesent et rutrum urna. Aliquam at mollis nulla. Suspendisse quam nisi, fermentum tempus lorem eu, euismod ultrices nunc. In ultricies, mauris ac porta auctor, purus nisl dapibus leo, vel faucibus augue augue a nunc.";
	
	private JTextField usernameTextField;
	private JLabel usernameLabel;
	
	private JButton generateButton;
	private JButton copyToClipboard;
	
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
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		
		usernameTextField = new JTextField(10);
		usernameTextField.setMaximumSize(new Dimension(100, 23));
		usernameLabel = new JLabel("Your username", SwingConstants.CENTER);
		
		generateButton = new JButton("Generate playlist");
		generateButton.setPreferredSize(new Dimension(10, 25));
		
		copyToClipboard = new JButton("Copy to clipboard");
		copyToClipboard.setPreferredSize(new Dimension(10, 25));
		
		listedSongsArea = new JTextArea(10, 25);
		listedSongsArea.setEditable(false);
		listedSongsArea.setFont(UIManager.getDefaults().getFont("TabbedPane.font"));
		
		scrollPane = new JScrollPane(listedSongsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		instructionsArea = new JTextArea(INSTRUCTIONS, 5, 20);
		setInstructionsArea();
		
		placeElementsOnGUI();
		
		this.setTitle(WINDOW_TITLE);
		this.setResizable(false);
	}
	
	private void setInstructionsArea() {
		instructionsArea.setOpaque(false);
		instructionsArea.setEditable(false);
		instructionsArea.setLineWrap(true);
		instructionsArea.setHighlighter(null);
		instructionsArea.setFont(UIManager.getDefaults().getFont("TabbedPane.font"));
	}
	
	private void placeElementsOnGUI() {
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(10, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		
		JPanel topPanel = new JPanel();
		
		GroupLayout topLayout = new GroupLayout(topPanel);
		topLayout.setAutoCreateGaps(true);
		topLayout.setAutoCreateContainerGaps(true);
		
		topLayout.setVerticalGroup(topLayout.createSequentialGroup()
				.addComponent(usernameLabel)
				.addGroup(topLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(usernameTextField)
					.addComponent(generateButton)
					.addComponent(copyToClipboard)
				)
			);
		
		topLayout.setHorizontalGroup(topLayout.createSequentialGroup()
				.addGroup(topLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(usernameLabel)
					.addComponent(usernameTextField)
				)
				.addComponent(generateButton)
				.addComponent(copyToClipboard)
			);
		

		topPanel.setLayout(topLayout);
		
		mainPanel.add(topPanel, BorderLayout.NORTH);
		
		mainPanel.add(scrollPane, BorderLayout.EAST);
		mainPanel.add(instructionsArea, BorderLayout.CENTER);
		
		this.add(mainPanel);
		
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
	
	public void addCopyToClipboardListener(ActionListener listenForCopyToClipboard) {
		copyToClipboard.addActionListener(listenForCopyToClipboard);
	}
	
	public void setErrorMessage(String errorMessage) {
		listedSongsArea.setText(errorMessage);
	}


}
