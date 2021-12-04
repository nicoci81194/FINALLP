package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import datos.Conexion;
import datos.dVenta;
import entidad.eVenta;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.UIManager;
import java.awt.Color;

public class cCliente extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtIdC;
	private JLabel lblRegistros;
	private JButton btnSalir;
	private JButton btnReporte;
	private JButton btnBuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cCliente frame = new cCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	//ACA NO OLVIDAR PARA LLAMAR AL REPORTE
	private Connection connection = new Conexion().conectar();
	dVenta dv= new dVenta();
	int totalregistros1 = 0;
	
	//DATOS PARA LA CARGA DEL JTABLE
	DefaultTableModel tabla;
	String Columnas[] = {"IdVenta","IdCliente","IdTrabajador","NumeroVenta","FechaVenta","Monto","Estado"};
	Object Filas[][];
	ArrayList<eVenta> miLista;	
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTable table;
	private JScrollPane scrollPane;
	
	//METODO PARA CARGAR EL CONTROL JTABLE
	private void CargarTabla() {
		
		tabla = new DefaultTableModel();
		miLista = new ArrayList<eVenta>();
		miLista = dv.Listar();
		Filas = new Object[miLista.size()][7];
		for(int i = 0; i < Columnas.length; i++)tabla.addColumn(Columnas[i]);
		for(int i = 0; i < miLista.size(); i++) {
			Filas[i][0] = miLista.get(i).getIdVenta();
			Filas[i][1] = miLista.get(i).getIdCliente();
			Filas[i][2] = miLista.get(i).getIdTrabajador();
			Filas[i][3] = miLista.get(i).getNumeroVenta();
			Filas[i][4] = miLista.get(i).getFechaVenta();
			Filas[i][5] = miLista.get(i).getMonto();
			Filas[i][6] = miLista.get(i).getEstado();
			
			tabla.addRow(Filas[i]);
		}
		table.setModel(tabla);		
	}	
	
	public void contar() {
		totalregistros1= tabla.getRowCount();
		lblRegistros.setText("Registros "+Integer.toString(totalregistros1));
	}
	
	public cCliente() {
		setTitle("Consulta");
		getContentPane().setBackground(Color.WHITE);
		setBackground(UIManager.getColor("Button.highlight"));
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 628, 383);
		getContentPane().setLayout(null);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonBuscar(e);
			}
		});
		btnBuscar.setIcon(new ImageIcon(cCliente.class.getResource("/img/buscarCliente.png")));
		btnBuscar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnBuscar.setBackground(SystemColor.menu);
		btnBuscar.setBounds(166, 85, 130, 29);
		getContentPane().add(btnBuscar);
		
		btnReporte = new JButton("Reporte");
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonReporte(e);
			}
		});
		btnReporte.setIcon(new ImageIcon(cCliente.class.getResource("/img/reporteBT.png")));
		btnReporte.setFont(new Font("Verdana", Font.BOLD, 12));
		btnReporte.setBackground(SystemColor.menu);
		btnReporte.setBounds(332, 85, 130, 29);
		getContentPane().add(btnReporte);
		
		lblRegistros = new JLabel("Registros 0");
		lblRegistros.setFont(new Font("Verdana", Font.BOLD, 12));
		lblRegistros.setBounds(10, 315, 156, 14);
		getContentPane().add(lblRegistros);
		
		JLabel lblConsultaPorDni = new JLabel("CONSULTA DE VENTAS POR CLIENTE");
		lblConsultaPorDni.setFont(new Font("Verdana", Font.BOLD, 14));
		lblConsultaPorDni.setBounds(166, 11, 310, 20);
		getContentPane().add(lblConsultaPorDni);
		
		JLabel lblNewLabel_1 = new JLabel("Id Cliente");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 57, 141, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtIdC = new JTextField();
		txtIdC.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtIdC.setColumns(10);
		txtIdC.setBounds(102, 54, 174, 20);
		getContentPane().add(txtIdC);
		
		btnSalir = new JButton("Cerrar");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonCerrar(e);
			}
		});
		btnSalir.setIcon(new ImageIcon(cCliente.class.getResource("/img/cerrarN.png")));
		btnSalir.setFont(new Font("Verdana", Font.BOLD, 12));
		btnSalir.setBackground(SystemColor.menu);
		btnSalir.setBounds(472, 308, 130, 29);
		getContentPane().add(btnSalir);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 132, 592, 167);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		CargarTabla();
		contar();

	}
	
	protected void BotonBuscar(ActionEvent e) {
		limpiarTabla();
		BuscarId(txtIdC.getText());
		
		//CONTAR REGISTROS
		dv.totalregistros = table.getRowCount();	
		lblRegistros.setText("Registros "+Integer.toString(dv.totalregistros));
	}
	
	void  BuscarId(String string){
		dVenta ds = new dVenta();		
		DefaultTableModel modelo = ds.BuscarId(string);
		table.setModel(modelo);		
	}
	
	//LIMPIAR TABLA
	void limpiarTabla(){
		for(int i=0; i<tabla.getRowCount(); i++){
			tabla.removeRow(i);
			i=i-1;
		}
	}
	
	protected void BotonReporte(ActionEvent e) {
		Map p = new HashMap();
		p.put("IdCli", txtIdC.getText());

		JasperReport report;
		JasperPrint print;
		
		try {
			//ruta proyecto
			report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+
					"/src/reporte/rCliente.jrxml");
			print=JasperFillManager.fillReport(report, p, connection);
			//visualizar
			JasperViewer view = new JasperViewer(print,false);
			
			view.setTitle("Reporte de Ventas por Cliente");
			view.setVisible(true);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	protected void BotonCerrar(ActionEvent e) {
		dispose();
	}
}
