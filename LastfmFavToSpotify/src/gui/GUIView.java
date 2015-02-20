// THE VIEW
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUIView extends JFrame {

	private static final long serialVersionUID = 1L;

	private final String WINDOW_TITLE = "LastFM favourites list to Spotify playlist generator";
	private final String INSTRUCTIONS = "How to use it?\n\n"
			+ "1. Type in your LastFM username and click \"Generate playlist\".\n\n"
			+ "2. Wait (may take up to a few minutes).\n\n"
			+ "3. Click \"Copy to clipboard\".\n\n"
			+ "4. In Spotify application create a new playlist.\n\n"
			+ "5. Open the playlist.\n\n"
			+ "6. Use keyboard shortcut Ctrl+V to paste the copied playlist.\n\n"
			+ "7. Done!";
	
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
		usernameTextField.setMaximumSize(new Dimension(100, 25));
		usernameLabel = new JLabel("Your username", SwingConstants.CENTER);
		
		generateButton = new JButton("Generate playlist");
		generateButton.setPreferredSize(new Dimension(100, 25));
		
		copyToClipboard = new JButton("Copy to clipboard");
		copyToClipboard.setPreferredSize(new Dimension(100, 25));
		
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

}
