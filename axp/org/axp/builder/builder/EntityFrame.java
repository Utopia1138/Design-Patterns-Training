package org.axp.builder.builder;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.function.Supplier;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EntityFrame<E> extends JFrame {
	private static final long serialVersionUID = 6934127159559802729L;
	
	private EntityPanel<E> panel;
	private ArrayList<E> entities = new ArrayList<>();
	private JButton print;
	private JButton addEmp;

	public EntityFrame( EntityPanel<E> panel, Supplier<E> generator ) {
		super( "Employees" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		JPanel root = new JPanel();
		root.setBorder( new EmptyBorder( 5, 8, 8, 8 ) );
		root.setLayout( new BorderLayout( 5, 5 ) );
		
		this.panel = panel;
		
		print = new JButton( "Print" );
		print.addActionListener( l -> {
			for ( E entity : entities ) {
				System.out.println( entity );
			}
			
			System.out.println();
		});

		addEmp = new JButton( "Add" );
		addEmp.addActionListener( l -> { 
			addEntity( generator.get() );
		});
		
		root.add( panel, BorderLayout.NORTH );
		root.add( print, BorderLayout.WEST );
		root.add( addEmp, BorderLayout.EAST );
		
		add( root );
	}
	
	public void addEntity( E entity ) {
		entities.add( entity );
		panel.addRow( entity );
		pack();
	}
}
