package ie.gmit.sw.runner;

import java.awt.event.*;
import javax.swing.*;

import ie.gmit.sw.draw.DisplayGraphics;
import ie.gmit.sw.draw.WordAnalyser;
import ie.gmit.sw.io.DataReader;

public class WindowSystem {
	// cmd
	static DataReader dr;
	static WordAnalyser wa;
	static DisplayGraphics dg;

	// swing
	private JFrame fr;
	private JLabel lblUrl;
	private JTextField tf;
	private JButton btn;
	private int winWidth = 800;
	private int winHeight = 350;

	// data
	private String url;

	public String getUrl() {
		return url;
	}

	public WindowSystem() {
		// cmd
		dr = new DataReader();
		// dg = new DisplayGraphics();

		// swing
		String applicationTitle = "Word Cloud - Ronan Connolly";

		// file chooser/picker
		// String fileName;
		// File fileRef;
		// JFileChooser chooser;

		fr = new JFrame(applicationTitle);

		// url box
		int tfWidth = (int) Math.round(winWidth * .83); // 500;
		int tfHeight = 20;
		;
		int tfX = (int) Math.round((winWidth / 2) - tfWidth / 2);
		int tfY = 50;
		// tf = new JTextField("http://www.");
		tf = new JTextField("http://www.ronanconnolly.ie");

		tf.setBounds(tfX + 50, tfY, tfWidth - 50, tfHeight);

		// url label
		int lblUrlWidth = 50;
		int lblUrlHeight = 20;
		int lblUrlX = tfX;
		int lblUrlY = tfY;
		lblUrl = new JLabel("URL:");
		lblUrl.setBounds(lblUrlX, lblUrlY, lblUrlWidth, lblUrlHeight);

		// create wordcloud button
		int btnWidth = 200;
		int btnHeight = 20;
		int btnX = (int) Math.round((winWidth / 2) - btnWidth / 2);
		int btnY = tfY + tfHeight + 20;
		btn = new JButton("Create Wordcloud");
		btn.setBounds(btnX, btnY, btnWidth, btnHeight);

		// add
		fr.add(lblUrl);
		fr.add(tf);
		fr.add(btn);

		// config
		fr.setSize(winWidth, winHeight);
		fr.setLayout(null);
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setupAL();
	}

	private void setupAL() {
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// reset words and counts
				dr.clearValidWords();

				url = tf.getText();

				try {
					dr.urlReader(url);

					wa = new WordAnalyser(dr.getSortedWords());
					dg = new DisplayGraphics(wa.getWords());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Invalid url: " + url + "\nError: " + e1);
				}
			}
		});
	}

	// public static void main(String[] args) {
	// WindowSystem ws = new WindowSystem();
	//
	// }
}
