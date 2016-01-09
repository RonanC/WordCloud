package ie.gmit.sw.runner;

import java.awt.event.*;
import java.io.File;
import javax.swing.*;

import ie.gmit.sw.draw.DisplayGraphics;
import ie.gmit.sw.draw.WordAnalyser;
import ie.gmit.sw.io.DataReader;

public class WindowSystem {
	// core
	private DataReader dr;
	private WordAnalyser wa;
	private DisplayGraphics dg;

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
	private JLabel lblMaxIterations;
	private JTextField tfMaxIterations;

	// Radio
	private JRadioButton rbtnChoice;
	private JRadioButton rbtnChoice2;
	private ButtonGroup grpChoice;

	// file chooser
	private JButton btnFc;
	private JLabel lblFc;
	private File selectedFile;

	// data
	private int maxWords;
	private int maxIterations;

	// data
	private String url;
	int choice; // 0 or 1, url or data

	public String getUrl() {
		return url;
	}

	public WindowSystem() {
		// cmd
		dr = new DataReader();

		// swing
		String applicationTitle = "Word Cloud - Ronan Connolly - 2016 ©";

		// config
		choice = 0;

		fr = new JFrame(applicationTitle);

		createComps();
		addComps();
		config();
		setupAL();
	}

	private void config() {
		fr.setSize(winWidth, winHeight);
		fr.setLayout(null);
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setResizable(false);

		// opens program centre of screen
		fr.setLocationRelativeTo(null);

	}

	private void createComps() {
		// MAX NUM
		// maxNum tf
		int tfMwWidth = (int) Math.round(winWidth * .83); // 500;
		int tfMwHeight = 20;
		int tfMwX = (int) Math.round((winWidth / 2) - tfMwWidth / 2);
		int tfMwY = 50;
		tfMaxWords = new JTextField("50");
		tfMaxWords.setBounds(tfMwX + 75, tfMwY, tfMwWidth - (tfMwWidth *= .90), tfMwHeight);

		// maxNum label
		int lblMwWidth = 100;
		int lblMwHeight = 20;
		int lblMwX = tfMwX;
		int lblMwY = tfMwY;
		lblMaxWords = new JLabel("Max Words:");
		lblMaxWords.setBounds(lblMwX, lblMwY, lblMwWidth, lblMwHeight);

		// MAX ITERATIONS
		// maxNum tf
		int tfMiWidth = (int) Math.round(winWidth * .83); // 500;
		int tfMiHeight = 20;
		int tfMiX = ((int) Math.round((winWidth / 2) - tfMiWidth / 2)) + ((tfMwWidth - (tfMwWidth *= .90)) + lblMwX)
				+ 50;
		int tfMiY = 50;
		tfMaxIterations = new JTextField("2500000");
		tfMaxIterations.setBounds(tfMiX + 100, tfMiY, tfMiWidth - (tfMiWidth *= .85), tfMiHeight);

		// maxNum label
		int lblMiWidth = 100;
		int lblMiHeight = 20;
		int lblMiX = tfMiX;
		int lblMiY = tfMiY;
		lblMaxIterations = new JLabel("Max Iterations:");
		lblMaxIterations.setBounds(lblMiX, lblMiY, lblMiWidth, lblMiHeight);

		// RADIO
		// url
		int rbtnWidth = 75;
		int rbtnHeight = 20;
		int rbtnX = tfMiX + lblMiWidth + 200;
		int rbtnY = tfMiY;
		rbtnChoice = new JRadioButton("Url", true);
		rbtnChoice.setBounds(rbtnX, rbtnY, rbtnWidth, rbtnHeight);
		fr.add(rbtnChoice);

		// data
		int rbtnX2 = rbtnX + 75;
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
		tfUrl = new JTextField("http://www.gutenberg.org/files/1661/1661-h/1661-h.htm");
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

	private void addComps() {
		// add
		fr.add(tfMaxWords);
		fr.add(lblMaxWords);

		fr.add(tfMaxIterations);
		fr.add(lblMaxIterations);

		fr.add(lblUrl);
		fr.add(tfUrl);

		fr.add(btnFc);
		fr.add(lblFc);

		fr.add(btnCreate);

	}

	private void setupAL() {
		// create word cloud button
		btnCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					maxWords = Integer.parseInt(tfMaxWords.getText());
				} catch (NumberFormatException nfe) {
					tfMaxWords.setText("50");
					JOptionPane.showMessageDialog(null, "Invalid Max Words, default set to 50 \nError: " + nfe);
					maxWords = 50;
				}

				try {
					maxIterations = Integer.parseInt(tfMaxIterations.getText());
				} catch (NumberFormatException nfe) {
					tfMaxIterations.setText("2500000");
					JOptionPane.showMessageDialog(null,
							"Invalid Max Iteration, default set to 2500000 \nError: " + nfe);
					maxWords = 2500000;
				}

				System.out.println("Chosen:\t\tmaxWords: " + maxWords + "\t\tmaxIterations: " + maxIterations);
				// reset words and counts
				dr.clearValidWords();

				url = tfUrl.getText();

				if (choice == 0) {
					// url
					try {
						dr.urlReader(url);

						wa = new WordAnalyser(dr.getSortedWords(), maxWords);
						setDg(new DisplayGraphics(wa.getWords(), maxWords, maxIterations));
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Invalid url: " + url + "\nError: " + e1);
					}
				} else {
					// file
					try {
						dr.fileReader(selectedFile.getAbsolutePath());

						wa = new WordAnalyser(dr.getSortedWords(), maxWords);
						setDg(new DisplayGraphics(wa.getWords(), maxWords, maxIterations));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Invalid data file. " + "\nError: " + e2);
					}

				}
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

	public DisplayGraphics getDg() {
		return dg;
	}

	public void setDg(DisplayGraphics dg) {
		this.dg = dg;
	}
}
