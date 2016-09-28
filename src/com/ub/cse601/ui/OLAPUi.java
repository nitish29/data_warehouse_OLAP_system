package com.ub.cse601.ui;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.ub.cse601.sql.DBClient;

public class OLAPUi {
	private static final long serialVersionUID = 1L;

	private JComboBox<String> patientCombo = new JComboBox<String>();
	private JComboBox<String> diseaseCombo = new JComboBox<String>();
	private JPanel mainPanel = new JPanel();

	public OLAPUi() {

		JFrame frame = new JFrame("CSE_601_Project1");
		frame.setSize(1000, 800);
		diseaseCombo.addItem("ALL");
		diseaseCombo.addItem("Tumor");
		GridLayout gridLayout = new GridLayout(5, 2);
		mainPanel.setLayout(gridLayout);
		mainPanel.add(diseaseCombo);
		frame.getContentPane().add(mainPanel);
		frame.setVisible(true);
		// frame.pack();
	}

	public static void main(String[] args) throws Exception {

		DBClient client = new DBClient();
		ResultSet rs = client.getConn().createStatement().executeQuery("Select * from DISEASE");
		System.out.println("DS_ID"+ "\t"+"DISEASE_NAME");
		while(rs.next()){
			System.out.println(rs.getString("DS_ID") +"\t"+rs.getString("NAME"));
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				OLAPUi olapUi = new OLAPUi();
			}
		});

	}
}