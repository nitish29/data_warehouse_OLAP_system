package com.ub.cse601.ui;

import java.awt.EventQueue;

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