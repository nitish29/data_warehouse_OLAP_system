package com.ub.cse601.ui;

import java.awt.Cursor;
import java.awt.EventQueue;
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

	public OLAPUi() {
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CentralBiostarWindow window = new CentralBiostarWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}