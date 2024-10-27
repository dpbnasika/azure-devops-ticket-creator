package com.example.devops.app;

import javax.swing.SwingUtilities;

import com.example.devops.view.DevOpsView;

public class Main {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(DevOpsView::new);
    }
}