package com.uade.seminario.tpo.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import com.uade.seminario.tpo.controller.SistemadeReparaciones;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class AltaEquipoView extends javax.swing.JFrame {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel4;
	private JButton altagarantia;
	private JTextField nrogarantia;
	private JLabel jLabel9;
	private JLabel jLabel8;
	private JButton ingresar;
	private JButton imprimir;
	private JCheckBox repararTodos;
	private JCheckBox garantia;
	private JTextField fecha;
	private JLabel jLabel7;
	private JButton cliente;
	private JTextField nroDoc;
	private JComboBox jComboBox1;
	private JTextField accesorios;
	private JTextField numeroModelo;
	private JTextField numeroEquipo;
	private JLabel jLabel6;
	private JLabel jLabel5;
	private JLabel jLabel3;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AltaEquipoView inst = new AltaEquipoView();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public AltaEquipoView() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Datos Equipo : ");
				jLabel1.setBounds(12, 12, 96, 16);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Numero Equipo : ");
				jLabel2.setBounds(12, 40, 122, 16);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("Numero Modelo : ");
				jLabel3.setBounds(12, 68, 134, 16);
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("Accesorios : ");
				jLabel4.setBounds(12, 96, 122, 16);
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setText("Tipo Documento : ");
				jLabel5.setBounds(12, 160, 134, 16);
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6);
				jLabel6.setText("Numero Documento : ");
				jLabel6.setBounds(12, 189, 140, 16);
			}
			{
				numeroEquipo = new JTextField();
				getContentPane().add(numeroEquipo);
				numeroEquipo.setBounds(158, 37, 214, 23);
			}
			{
				numeroModelo = new JTextField();
				getContentPane().add(numeroModelo);
				numeroModelo.setBounds(158, 65, 214, 23);
			}
			{
				accesorios = new JTextField();
				getContentPane().add(accesorios);
				accesorios.setBounds(158, 93, 214, 23);
			}
			{
				ComboBoxModel jComboBox1Model = 
						new DefaultComboBoxModel(
								new String[] { "DNI", "CI","LC" });
				jComboBox1 = new JComboBox();
				getContentPane().add(jComboBox1);
				jComboBox1.setModel(jComboBox1Model);
				jComboBox1.setBounds(158, 157, 98, 23);
			}
			{
				nroDoc = new JTextField();
				getContentPane().add(nroDoc);
				nroDoc.setBounds(158, 186, 158, 23);
			}
			{
				cliente = new JButton();
				getContentPane().add(cliente);
				cliente.setText("Alta Cliente");
				cliente.setBounds(158, 129, 100, 23);
				cliente.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						AltaClienteView view = new AltaClienteView();
						view.setVisible(true);
						
					}
				});
			}
			{
				jLabel7 = new JLabel();
				getContentPane().add(jLabel7);
				jLabel7.setText("Datos Cliente : ");
				jLabel7.setBounds(12, 132, 109, 16);
			}
			{
				jLabel8 = new JLabel();
				getContentPane().add(jLabel8);
				jLabel8.setText("Fecha : ");
				jLabel8.setBounds(190, 12, 58, 16);
			}
			{
				fecha = new JTextField();
				getContentPane().add(fecha);
				fecha.setBounds(248, 9, 124, 23);
			}
			{
				garantia = new JCheckBox();
				getContentPane().add(garantia);
				garantia.setText("En Garantia");
				garantia.setBounds(12, 243, 109, 20);
				garantia.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						if(garantia.isSelected()){
							altagarantia.setVisible(true);
							jLabel9.setVisible(true);
							nrogarantia.setVisible(true);
						}
						if(!garantia.isSelected()){
							altagarantia.setVisible(false);
							jLabel9.setVisible(false);
							nrogarantia.setVisible(false);
							nrogarantia.setText("");
						}
							
						
					}
				});
			}
			{
				repararTodos = new JCheckBox();
				getContentPane().add(repararTodos);
				repararTodos.setText("Reparar de todos modos");
				repararTodos.setBounds(12, 313, 178, 20);
			}
			{
				imprimir = new JButton();
				getContentPane().add(imprimir);
				imprimir.setText("Imprimir Remito");
				imprimir.setBounds(41, 360, 140, 23);
			}
			{
				ingresar = new JButton();
				getContentPane().add(ingresar);
				ingresar.setText("Ingresar Equipo");
				ingresar.setBounds(210, 360, 138, 23);
				ingresar.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						boolean repararTodosModos1=false;
						if(garantia.isSelected()){
							
						}
							
						if(repararTodos.isSelected())
							repararTodosModos1=true;
						Date fecha1=new Date(Date.parse(fecha.getText()));
						if(!numeroEquipo.getText().equals("")&& !numeroModelo.getText().equals("") && !nroDoc.getText().equals("")){
							SistemadeReparaciones.getInstancia().altaEquipo(Integer.parseInt(numeroEquipo.getText()),Integer.parseInt(numeroModelo.getText()), jComboBox1.getSelectedItem().toString(), nroDoc.getText(), fecha1, nrogarantia.getText(), repararTodosModos1);
						}
						
					}
				});
			}
			{
				jLabel9 = new JLabel();
				getContentPane().add(jLabel9);
				jLabel9.setText("Numero Garantia: ");
				jLabel9.setBounds(13, 280, 116, 16);
				jLabel9.setVisible(false);
			}
			{
				nrogarantia = new JTextField();
				getContentPane().add(nrogarantia);
				nrogarantia.setBounds(120, 277, 142, 23);
				nrogarantia.setVisible(false);
			}
			{
				altagarantia = new JButton();
				getContentPane().add(altagarantia);
				altagarantia.setText("Alta Garantia");
				altagarantia.setBounds(229, 242, 143, 23);
				altagarantia.setVisible(false);
				altagarantia.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						AltaGarantiaView view = new AltaGarantiaView();
						view.setVisible(true);
						
					}
				});
			}
			pack();
			this.setSize(400, 433);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
