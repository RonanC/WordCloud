package ie.gmit.sw;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import ie.gmit.sw.graphics.GraphicsProcessor;
import ie.gmit.sw.io.DataProcesser;
import ie.gmit.sw.io.DataProcess;

/**
 * 
 * A Swing windows system GUI that presents the user with various options. Such
 * as the max number of words to be processed, the max number of iterations the
 * intersection checker should process per word and whether to choose a URL or
 * data as input.
 * @author Ronan
 */
public class WindowSystem {
	// core
	private DataProcesser dataProcessor;
	private GraphicsProcessor graphicsProcessor;

	// basic
	private JFrame fr;
	private JLabel lblUrl;
	private JTextField tfUrl;
	private JButton btnCreate;
	private int winWidth = 800;
	private int winHeight = 225;

	// more
	private JLabel lblMaxWords;
	private JTextField tfMaxWords;
	private JLabel lblFontSize;
	private JTextField tfMaxFontSize;

	// Radio
	private JRadioButton rbtnChoice;
	private JRadioButton rbtnChoice2;
	private ButtonGroup grpChoice;

	// file chooser
	private JButton btnFc;
	private JLabel lblFc;
	private File selectedFile;

	// data
	private int maxWords = 80;
	private int maxFont = 170;
	private int maxWordsDef = 80;
	private int maxFontDef = 170;
	private String maxWordsStr = "80";
	private String maxFontStr = "170";
	private String url;
	int choice; // 0 or 1, url or data

	/**
	 * Starts the program running
	 */
	public WindowSystem() {
		// processors
		dataProcessor = new DataProcess();
		graphicsProcessor = new GraphicsProcessor();

		// swing title
		String applicationTitle = "Word Cloud - Ronan Connolly - 2016 ©";

		// config
		choice = 0;

		fr = new JFrame(applicationTitle);

		createComps();
		addComps();
		config();
		setupAL();
	}

	/**
	 * Configuration settings for the frame.
	 */
	private void config() {
		// set up icon
		// Image image = new ImageIcon("cloud.png").getImage();
		// fr.setIconImage(image);

		fr.setSize(winWidth, winHeight);
		fr.setLayout(null);
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setResizable(false);

		// opens program center of screen
		fr.setLocationRelativeTo(null);

	}

	/**
	 * Creates the various GUI components needed.
	 */
	private void createComps() {
		// MAX WORDS
		// maxNum tf
		int tfMwWidth = (int) Math.round(winWidth * .83); // 500;
		int tfMwHeight = 20;
		int tfMwX = (int) Math.round((winWidth / 2) - tfMwWidth / 2);
		int tfMwY = 50;
		tfMaxWords = new JTextField(maxWordsStr);
		tfMaxWords.setBounds(tfMwX + 75, tfMwY, tfMwWidth - (tfMwWidth *= .90), tfMwHeight);

		// maxNum label
		int lblMwWidth = 100;
		int lblMwHeight = 20;
		int lblMwX = tfMwX;
		int lblMwY = tfMwY;
		lblMaxWords = new JLabel("Max Words:");
		lblMaxWords.setBounds(lblMwX, lblMwY, lblMwWidth, lblMwHeight);

		// MAX ITERATIONS
		// maxNum label
		int lblMiWidth = 140;
		int lblMiHeight = 20;
		int lblMiX = tfMwX + (tfMwWidth - (tfMwWidth *= .90)) + 100;
		int lblMiY = 50;
		lblFontSize = new JLabel("Max Font:");
		lblFontSize.setBounds(lblMiX, lblMiY, lblMiWidth, lblMiHeight);

		// maxNum tf
		int tfMiWidth = 50; // 500;
		int tfMiHeight = 20;
		int tfMiX = lblMiX + lblMiWidth - 75;
		int tfMiY = 50;
		tfMaxFontSize = new JTextField(maxFontStr);
		tfMaxFontSize.setBounds(tfMiX, tfMiY, tfMiWidth, tfMiHeight);

		// RADIO
		// url
		int rbtnWidth = 75;
		int rbtnHeight = 20;
		int rbtnX = tfMiX + tfMiWidth + 50;
		int rbtnY = tfMiY;
		rbtnChoice = new JRadioButton("Url", true);
		rbtnChoice.setBounds(rbtnX, rbtnY, rbtnWidth, rbtnHeight);
		fr.add(rbtnChoice);

		// data
		int rbtnX2 = rbtnX + 60;
		int rbtnY2 = tfMiY;
		rbtnChoice2 = new JRadioButton("Data");
		rbtnChoice2.setBounds(rbtnX2, rbtnY2, rbtnWidth, rbtnHeight);
		fr.add(rbtnChoice2);

		// group
		grpChoice = new ButtonGroup();
		grpChoice.add(rbtnChoice);
		grpChoice.add(rbtnChoice2);

		// URL
		// url box
		int tfWidth = (int) Math.round(winWidth * .83); // 500;
		int tfHeight = 20;
		int tfX = (int) Math.round((winWidth / 2) - tfWidth / 2);
		int tfY = tfMwY + 35;
		tfUrl = new JTextField("https://en.wikipedia.org/wiki/War_and_Peace");
		tfUrl.setBounds(tfX + 50, tfY, tfWidth - 50, tfHeight);

		// url label
		int lblUrlWidth = 50;
		int lblUrlHeight = 20;
		int lblUrlX = tfX;
		int lblUrlY = tfY;
		lblUrl = new JLabel("URL:");
		lblUrl.setBounds(lblUrlX, lblUrlY, lblUrlWidth, lblUrlHeight);

		// FILE CHOOSER
		// btn
		int btnFcWidth = tfWidth / 4;
		int btnFcHeight = tfHeight;
		int btnFcX = tfX * 2;
		int btnFcY = tfY;
		btnFc = new JButton("Select File");
		btnFc.setBounds(btnFcX, btnFcY, btnFcWidth, btnFcHeight);
		btnFc.setVisible(false);

		// lbl
		int lblFcWidth = btnFcWidth;
		int lblFcHeight = btnFcHeight;
		int lblX = btnFcX + 200;
		int lblY = btnFcY;
		lblFc = new JLabel("No File Selected");
		lblFc.setBounds(lblX, lblY, lblFcWidth, lblFcHeight);
		lblFc.setVisible(false);

		// create wordcloud button
		int btnWidth = 200;
		int btnHeight = 20;
		int btnX = (int) Math.round((winWidth / 2) - btnWidth / 2);
		int btnY = tfY + tfHeight + 20;
		btnCreate = new JButton("Create Wordcloud");
		btnCreate.setBounds(btnX, btnY, btnWidth, btnHeight);

	}

	/**
	 * Adds the various components to the frame.
	 */
	private void addComps() {
		// add
		fr.add(tfMaxWords);
		fr.add(lblMaxWords);

		fr.add(tfMaxFontSize);
		fr.add(lblFontSize);

		fr.add(lblUrl);
		fr.add(tfUrl);

		fr.add(btnFc);
		fr.add(lblFc);

		fr.add(btnCreate);

	}

	/**
	 * Sets up the Action Listeners.
	 */
	private void setupAL() {
		// create word cloud button
		btnCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					maxWords = Integer.parseInt(tfMaxWords.getText());
				} catch (NumberFormatException nfe) {
					tfMaxWords.setText(maxWordsStr);
					JOptionPane.showMessageDialog(null, "Invalid Max Words, default set to "+ maxWordsDef +" \nError: " + nfe);
					maxWords = maxWordsDef;
				}
				
//				maxWordsBounds();

				try {
					maxFont = Integer.parseInt(tfMaxFontSize.getText());
				} catch (NumberFormatException nfe) {
					tfMaxFontSize.setText(maxFontStr);
					JOptionPane.showMessageDialog(null,
							"Invalid Max Font Size, default set to "+maxFontDef+" \nError: " + nfe);
					maxFont = maxFontDef;
				}

				// log
				System.out.println("Chosen:\t\tmaxWords: " + maxWords + "\t\tmaxFont: " + maxFont);

				// reset words and counts
				dataProcessor.clearValidWords();

				// get url
				url = tfUrl.getText();

				if (choice == 0) {
					// url
					try {
						dataProcessor.urlReader(url);
						processGraphics();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Invalid url: " + url + "\nError: " + e1);
					}
				} else {
					// file
					try {
						dataProcessor.fileReader(selectedFile.getAbsolutePath());
						processGraphics();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Invalid data file. " + "\nError: " + e2);
					}

				}
			}

//			private void maxWordsBounds() {
//				// we are keeping max words within the bounds of 3 to 1000
//				if(maxWords < 3){
//					maxWords = 3;
//				}
//				else if (maxWords > 1000){
//					maxWords = 1000;
//				}
//			}

			private void processGraphics() {
				graphicsProcessor.process(dataProcessor.getSortedWords(), maxWords, maxFont);
				graphicsProcessor.displayGraphics();
			}

		});

		// Radio Buttons
		rbtnChoice.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				choice = 0;
				lblUrl.setText("URL:");
				tfUrl.setVisible(true);

				btnFc.setVisible(false);
				lblFc.setVisible(false);

				btnCreate.setEnabled(true);
			}
		});

		rbtnChoice2.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				choice = 1;
				lblUrl.setText("File:");
				tfUrl.setVisible(false);

				btnFc.setVisible(true);
				lblFc.setVisible(true);

				if (selectedFile == null) {
					btnCreate.setEnabled(false);
				} else {
					btnCreate.setEnabled(true);
				}
			}
		});

		// file chooser button
		btnFc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
					lblFc.setText(selectedFile.getName());
					btnCreate.setEnabled(true);
				}

			}
		});
	}
}
