package gwu.csci.arc.gui;

import gwu.csci.arc.CPU;
import gwu.csci.arc.IndexRegister;
import gwu.csci.arc.isa.LDA;
import gwu.csci.arc.isa.LDR;
import gwu.csci.arc.isa.LDX;
import gwu.csci.arc.isa.STR;
import gwu.csci.arc.isa.STX;
import gwu.csci.arc.test.Initialization;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.Color;

import javax.swing.JSlider;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;

public class UI extends JFrame {
	
	static UI frame;
	
	CPU cpu = CPU.getInstance();
	IndexRegister xr;
	Initialization init = new Initialization();
	static boolean ifInitial = true;
	
	// for instruction opcode & address recognition uses
	char[] Instruction = new char[18];
	char[] Opcode = new char[6];
	char[] address = new char[12];
	char[] address_mem = new char[12];
	boolean flag = false;
	String ins = "";

	private JPanel contentPane;
	private JLabel lblNewLabel_2;
	private JLabel lblR;
	private JTextField SetTxt_R0;
	private JTextField SetTxt_R1;
	private JTextField SetTxt_R2;
	private JTextField SetTxt_R3;
	private JButton SbmBtn_R0;
	private JButton SbmBtn_R1;
	private JButton SbmBtn_R2;
	private JButton SbmBtn_R3;
	private JLabel lblValue;
	private JLabel lblMemoryInput;
	private JLabel lblAddressInput;
	private JTextField SetTxt_Addr;
	private JLabel lblNewLabel_3;
	private JButton SbmBtn_Ins;
	private JLabel lblPc;
	private JTextField SetTxt_PC;
	private JButton SbmBtn_SglStp;
	private JLabel lblR_1;
	private JLabel lblR_2;
	private JLabel lblR_3;
	private JLabel lblR_4;
	private JLabel lblValue_1;
	private JLabel lblPc_1;
	private JLabel lblX;
	private JLabel lblX_1;
	private JLabel lblX_2;
	private JLabel lblMar;
	private JLabel lblMbr;
	private JLabel lblValue_2;
	private JLabel lblOutput;
	private JTextArea DspTxt_Cns;
	private JLabel DspTxt_R0;
	private JLabel DspTxt_R1;
	private JLabel DspTxt_R2;
	private JLabel DspTxt_R3;
	private JLabel DspTxt_PC;
	private JLabel DspTxt_X1;
	private JLabel DspTxt_X2;
	private JLabel DspTxt_X3;
	private JLabel DspTxt_MAR;
	private JLabel DspTxt_MBR;
	private JScrollPane scrollPane;
	private JTextArea SetTxt_Mem;
	private JScrollPane scrollPane_1;
	private JTextArea SetTxt_Ins;
	private JScrollPane scrollPane_2;
	private JButton SbmBtn_PC;
	private JCheckBox chckbxPrepreparation;
	private JTextArea DspTxt_Log;
	private JLabel lblLog;
	private JScrollPane scrollPane_3;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//UI frame = new UI();
					frame = new UI();
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
	public UI() {
		
		setTitle("Simulator");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(15, 120, 1280, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{32, 154, 31, 61, 114, 186, 34, 127, 117, 104, 22, 102, 95, 0};
		gbl_contentPane.rowHeights = new int[]{20, 20, 20, 20, 34, 0, 0, 0, 44, 0, 34, 34, 34, 34, 34, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblValue = new JLabel("Value:");
		GridBagConstraints gbc_lblValue = new GridBagConstraints();
		gbc_lblValue.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblValue.gridx = 1;
		gbc_lblValue.gridy = 0;
		contentPane.add(lblValue, gbc_lblValue);
		
		lblMemoryInput = new JLabel("Memory Input:");
		GridBagConstraints gbc_lblMemoryInput = new GridBagConstraints();
		gbc_lblMemoryInput.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblMemoryInput.insets = new Insets(0, 0, 5, 5);
		gbc_lblMemoryInput.gridx = 4;
		gbc_lblMemoryInput.gridy = 0;
		contentPane.add(lblMemoryInput, gbc_lblMemoryInput);
		
		lblNewLabel_3 = new JLabel("Instruction Input:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 7;
		gbc_lblNewLabel_3.gridy = 0;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		lblLog = new JLabel("Log:");
		GridBagConstraints gbc_lblLog = new GridBagConstraints();
		gbc_lblLog.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblLog.insets = new Insets(0, 0, 5, 5);
		gbc_lblLog.gridx = 11;
		gbc_lblLog.gridy = 0;
		contentPane.add(lblLog, gbc_lblLog);
		
		JLabel lblNewLabel_1 = new JLabel("R0:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
				
		SetTxt_R0 = new JTextField();
		SetTxt_R0.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
		
				String Current = SetTxt_R0.getText();
				
				// restrict input size and format for R0
				if (Current.length() == 18) e.setKeyChar((char) 00);
				if ((e.getKeyChar() != '0') && (e.getKeyChar() != '1')) e.setKeyChar((char) 00);
			}
		});
		
		GridBagConstraints gbc_SetTxt_R0 = new GridBagConstraints();
		gbc_SetTxt_R0.insets = new Insets(0, 0, 5, 5);
		gbc_SetTxt_R0.fill = GridBagConstraints.HORIZONTAL;
		gbc_SetTxt_R0.gridx = 1;
		gbc_SetTxt_R0.gridy = 1;
		contentPane.add(SetTxt_R0, gbc_SetTxt_R0);
		SetTxt_R0.setColumns(5);
		
		
		SbmBtn_R0 = new JButton("Submit");
		SbmBtn_R0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// overwrite R0
				char[] Current = new char[18];
				char[] Current_dsp = new char[18];
				Current = SetTxt_R0.getText().toCharArray();
				char[] id = {'0', '0'};
				
				cpu.writeGPR(Current, id, Current.length);
				
				cpu.readGPR(Current_dsp, id, Current_dsp.length);
				DspTxt_R0.setText(new String(Current_dsp));
			}
		});
		GridBagConstraints gbc_SbmBtn_R0 = new GridBagConstraints();
		gbc_SbmBtn_R0.anchor = GridBagConstraints.WEST;
		gbc_SbmBtn_R0.insets = new Insets(0, 0, 5, 5);
		gbc_SbmBtn_R0.gridx = 2;
		gbc_SbmBtn_R0.gridy = 1;
		contentPane.add(SbmBtn_R0, gbc_SbmBtn_R0);
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridheight = 3;
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.gridx = 4;
		gbc_scrollPane_1.gridy = 1;
		contentPane.add(scrollPane_1, gbc_scrollPane_1);
		
		SetTxt_Mem = new JTextArea();
		SetTxt_Mem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String Current = SetTxt_Mem.getText();
				
				// restrict input size and format for memory input
				if (Current.length() == 18) e.setKeyChar((char) 00);
				if ((e.getKeyChar() != '0') && (e.getKeyChar() != '1')) e.setKeyChar((char) 00);
			}
		});
		SetTxt_Mem.setWrapStyleWord(true);
		SetTxt_Mem.setLineWrap(true);
		scrollPane_1.setViewportView(SetTxt_Mem);
		
		scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridheight = 4;
		gbc_scrollPane_2.gridwidth = 3;
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_2.gridx = 7;
		gbc_scrollPane_2.gridy = 1;
		contentPane.add(scrollPane_2, gbc_scrollPane_2);
		
		SetTxt_Ins = new JTextArea();
		scrollPane_2.setViewportView(SetTxt_Ins);
		SetTxt_Ins.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String Current = SetTxt_Ins.getText();
				if (Current.length() == 18) e.setKeyChar((char) 00);
				if ((e.getKeyChar() != '0') && (e.getKeyChar() != '1')) e.setKeyChar((char) 00);
			}
		});
		SetTxt_Ins.setText("000000000000000000");
		SetTxt_Ins.setLineWrap(true);
		
		scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.gridwidth = 2;
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridheight = 14;
		gbc_scrollPane_3.gridx = 11;
		gbc_scrollPane_3.gridy = 1;
		contentPane.add(scrollPane_3, gbc_scrollPane_3);
		
		DspTxt_Log = new JTextArea();
		DspTxt_Log.setEditable(false);
		scrollPane_3.setViewportView(DspTxt_Log);
		DspTxt_Log.setWrapStyleWord(true);
		DspTxt_Log.setLineWrap(true);
		
		lblNewLabel_2 = new JLabel("R1:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		SetTxt_R1 = new JTextField();
		SetTxt_R1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String Current = SetTxt_R1.getText();
				
				// restrict input size and format for R1
				if (Current.length() == 18) e.setKeyChar((char) 00);
				if ((e.getKeyChar() != '0') && (e.getKeyChar() != '1')) e.setKeyChar((char) 00);
			}
		});
		GridBagConstraints gbc_SetTxt_R1 = new GridBagConstraints();
		gbc_SetTxt_R1.insets = new Insets(0, 0, 5, 5);
		gbc_SetTxt_R1.fill = GridBagConstraints.HORIZONTAL;
		gbc_SetTxt_R1.gridx = 1;
		gbc_SetTxt_R1.gridy = 2;
		contentPane.add(SetTxt_R1, gbc_SetTxt_R1);
		SetTxt_R1.setColumns(5);
		
		SbmBtn_R1 = new JButton("Submit");
		SbmBtn_R1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// overwrite R1
				char[] Current = new char[18];
				char[] Current_dsp = new char[18];
				Current = SetTxt_R1.getText().toCharArray();
				char[] id = {'0', '1'};
				
				cpu.writeGPR(Current, id, Current.length);
				
				cpu.readGPR(Current_dsp, id, Current_dsp.length);
				DspTxt_R1.setText(new String(Current_dsp));
			}
		});
		GridBagConstraints gbc_SbmBtn_R1 = new GridBagConstraints();
		gbc_SbmBtn_R1.anchor = GridBagConstraints.WEST;
		gbc_SbmBtn_R1.insets = new Insets(0, 0, 5, 5);
		gbc_SbmBtn_R1.gridx = 2;
		gbc_SbmBtn_R1.gridy = 2;
		contentPane.add(SbmBtn_R1, gbc_SbmBtn_R1);
		
		JLabel lblNewLabel = new JLabel("R2:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 3;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		SetTxt_R2 = new JTextField();
		SetTxt_R2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String Current = SetTxt_R2.getText();
				
				// restrict input size and format for R2
				if (Current.length() == 18) e.setKeyChar((char) 00);
				if ((e.getKeyChar() != '0') && (e.getKeyChar() != '1')) e.setKeyChar((char) 00);
			}
		});
		GridBagConstraints gbc_SetTxt_R2 = new GridBagConstraints();
		gbc_SetTxt_R2.insets = new Insets(0, 0, 5, 5);
		gbc_SetTxt_R2.fill = GridBagConstraints.HORIZONTAL;
		gbc_SetTxt_R2.gridx = 1;
		gbc_SetTxt_R2.gridy = 3;
		contentPane.add(SetTxt_R2, gbc_SetTxt_R2);
		SetTxt_R2.setColumns(5);
		
		SbmBtn_R2 = new JButton("Submit");
		SbmBtn_R2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// overwrite R2
				char[] Current = new char[18];
				char[] Current_dsp = new char[18];
				Current = SetTxt_R2.getText().toCharArray();
				char[] id = {'1', '0'};
				
				cpu.writeGPR(Current, id, Current.length);
				
				cpu.readGPR(Current_dsp, id, Current_dsp.length);
				DspTxt_R2.setText(new String(Current_dsp));
			}
		});
		GridBagConstraints gbc_SbmBtn_R2 = new GridBagConstraints();
		gbc_SbmBtn_R2.anchor = GridBagConstraints.WEST;
		gbc_SbmBtn_R2.insets = new Insets(0, 0, 5, 5);
		gbc_SbmBtn_R2.gridx = 2;
		gbc_SbmBtn_R2.gridy = 3;
		contentPane.add(SbmBtn_R2, gbc_SbmBtn_R2);
		
		lblR = new JLabel("R3:");
		GridBagConstraints gbc_lblR = new GridBagConstraints();
		gbc_lblR.anchor = GridBagConstraints.EAST;
		gbc_lblR.insets = new Insets(0, 0, 5, 5);
		gbc_lblR.gridx = 0;
		gbc_lblR.gridy = 4;
		contentPane.add(lblR, gbc_lblR);
		
		SetTxt_R3 = new JTextField();
		SetTxt_R3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String Current = SetTxt_R3.getText();
				
				// restrict input size and format for R3
				if (Current.length() == 18) e.setKeyChar((char) 00);
				if ((e.getKeyChar() != '0') && (e.getKeyChar() != '1')) e.setKeyChar((char) 00);
			}
		});
		GridBagConstraints gbc_SetTxt_R3 = new GridBagConstraints();
		gbc_SetTxt_R3.insets = new Insets(0, 0, 5, 5);
		gbc_SetTxt_R3.fill = GridBagConstraints.HORIZONTAL;
		gbc_SetTxt_R3.gridx = 1;
		gbc_SetTxt_R3.gridy = 4;
		contentPane.add(SetTxt_R3, gbc_SetTxt_R3);
		SetTxt_R3.setColumns(5);
		
		SbmBtn_R3 = new JButton("Submit");
		SbmBtn_R3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// overwrite R3
				char[] Current = new char[18];
				char[] Current_dsp = new char[18];
				Current = SetTxt_R3.getText().toCharArray();
				char[] id = {'1', '1'};
				
				cpu.writeGPR(Current, id, Current.length);
				
				cpu.readGPR(Current_dsp, id, Current_dsp.length);
				DspTxt_R3.setText(new String(Current_dsp));
			}
		});
		GridBagConstraints gbc_SbmBtn_R3 = new GridBagConstraints();
		gbc_SbmBtn_R3.anchor = GridBagConstraints.WEST;
		gbc_SbmBtn_R3.insets = new Insets(0, 0, 5, 5);
		gbc_SbmBtn_R3.gridx = 2;
		gbc_SbmBtn_R3.gridy = 4;
		contentPane.add(SbmBtn_R3, gbc_SbmBtn_R3);
		
		lblAddressInput = new JLabel("Address Input:");
		GridBagConstraints gbc_lblAddressInput = new GridBagConstraints();
		gbc_lblAddressInput.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblAddressInput.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddressInput.gridx = 4;
		gbc_lblAddressInput.gridy = 4;
		contentPane.add(lblAddressInput, gbc_lblAddressInput);
		
		SetTxt_Addr = new JTextField();
		SetTxt_Addr.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String Current = SetTxt_Addr.getText();
				
				// restrict input size and format for address 
				if (Current.length() == 12) e.setKeyChar((char) 00);
				if ((e.getKeyChar() != '0') && (e.getKeyChar() != '1')) e.setKeyChar((char) 00);
			}
		});
		GridBagConstraints gbc_SetTxt_Addr = new GridBagConstraints();
		gbc_SetTxt_Addr.gridwidth = 2;
		gbc_SetTxt_Addr.insets = new Insets(0, 0, 5, 5);
		gbc_SetTxt_Addr.fill = GridBagConstraints.HORIZONTAL;
		gbc_SetTxt_Addr.gridx = 4;
		gbc_SetTxt_Addr.gridy = 5;
		contentPane.add(SetTxt_Addr, gbc_SetTxt_Addr);
		SetTxt_Addr.setColumns(10);
		
		SbmBtn_Ins = new JButton("Submit");
		SbmBtn_Ins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i < 18; i++) Instruction[i] = '0';
				Instruction = SetTxt_Ins.getText().toCharArray();
				
				opcode_check();
				
				// get address if no instruction error
				if (flag == true)
					for (int i = 6; i < 18; i++) address[i-6] = Instruction[i];
				
				
				if (flag == false) DspTxt_Cns.setText(DspTxt_Cns.getText() + "Fail: Instruction Error!\n\n");
				else
				{ 
					cpu.writeIns(Instruction, Instruction.length, address_mem);
					//instruction_run();
					DspTxt_Cns.setText(DspTxt_Cns.getText() + "Instruction Submitted.\n"+"The Instruction ("+ ins +" "+ new String(address)+ ") is written into Memory at: " + new String(address_mem) + ".\n");
				}
			}
		});
		GridBagConstraints gbc_SbmBtn_Ins = new GridBagConstraints();
		gbc_SbmBtn_Ins.anchor = GridBagConstraints.NORTHEAST;
		gbc_SbmBtn_Ins.insets = new Insets(0, 0, 5, 5);
		gbc_SbmBtn_Ins.gridx = 9;
		gbc_SbmBtn_Ins.gridy = 5;
		contentPane.add(SbmBtn_Ins, gbc_SbmBtn_Ins);
		
		chckbxPrepreparation = new JCheckBox("Prepreparation");
		chckbxPrepreparation.setSelected(true);
		chckbxPrepreparation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ifInitial == false) {
					ifInitial = true;
				}
				else {
					ifInitial = false;
				}
			}
		});
		
		SetTxt_PC = new JTextField();
		SetTxt_PC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String Current = SetTxt_PC.getText();
				
				// restrict input size and format for PC
				if (Current.length() == 12) e.setKeyChar((char) 00);
				if ((e.getKeyChar() != '0') && (e.getKeyChar() != '1')) e.setKeyChar((char) 00);
			}
		});
		
		lblPc = new JLabel("PC:");
		GridBagConstraints gbc_lblPc = new GridBagConstraints();
		gbc_lblPc.anchor = GridBagConstraints.EAST;
		gbc_lblPc.insets = new Insets(0, 0, 5, 5);
		gbc_lblPc.gridx = 7;
		gbc_lblPc.gridy = 6;
		contentPane.add(lblPc, gbc_lblPc);
		GridBagConstraints gbc_SetTxt_PC = new GridBagConstraints();
		gbc_SetTxt_PC.insets = new Insets(0, 0, 5, 5);
		gbc_SetTxt_PC.fill = GridBagConstraints.HORIZONTAL;
		gbc_SetTxt_PC.gridx = 8;
		gbc_SetTxt_PC.gridy = 6;
		contentPane.add(SetTxt_PC, gbc_SetTxt_PC);
		SetTxt_PC.setColumns(10);
		GridBagConstraints gbc_chckbxPrepreparation = new GridBagConstraints();
		gbc_chckbxPrepreparation.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxPrepreparation.gridx = 9;
		gbc_chckbxPrepreparation.gridy = 6;
		contentPane.add(chckbxPrepreparation, gbc_chckbxPrepreparation);
		
		SbmBtn_SglStp = new JButton("Single Step");
		SbmBtn_SglStp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				char[] Current = new char[18];
				char[] pc = new char[12];
				char[] X1 = new char[12], X2 = new char[12], X3 = new char[12], MAR = new char[12], MBR = new char[18];
				char[] id;
				
				//LDX ldx = new LDX(cpu);
				//ldx.start();
				//init.toRun();

				
				
				cpu.readPC(pc, pc.length);
				cpu.readMem(Instruction, Instruction.length, pc);
				opcode_check();
					
				if (flag == false) { 
					DspTxt_Cns.setText(DspTxt_Cns.getText() + "Fail: Unrecognized Instuction!\n");
				}
				else { 
					instruction_run();
				}
				
				
				//status update
				
				// R0 operation
				id = new char[] {'0', '0'};
				
				cpu.readGPR(Current, id, Current.length);
				DspTxt_R0.setText(new String(Current));
				
				// R1 operation
				id = new char[] {'0', '1'};
				
				cpu.readGPR(Current, id, Current.length);
				DspTxt_R1.setText(new String(Current));
				
				// R2 operation
				id = new char[] {'1', '0'};
				
				cpu.readGPR(Current, id, Current.length);
				DspTxt_R2.setText(new String(Current));
				
				// R3 operation
				id = new char[] {'1', '1'};
				
				cpu.readGPR(Current, id, Current.length);
				DspTxt_R3.setText(new String(Current));
				
				// PC operation
				cpu.readPC(pc, pc.length);
				DspTxt_PC.setText(new String(pc));
				
				// X1 operation
				id = new char[] {'0', '1'};
				
				cpu.readXR(X1, id, xr.getLength());
				DspTxt_X1.setText(new String(X1));
				
				// X2 operation
				id = new char[] {'1', '0'};
				
				cpu.readXR(X2, id, xr.getLength());
				DspTxt_X2.setText(new String(X2));
				
				// X3 operation
				id = new char[] {'1', '1'};
				
				cpu.readXR(X3, id, xr.getLength());
				DspTxt_X3.setText(new String(X3));
				
				// MAR operation
				MAR = cpu.getMAR();
				DspTxt_MAR.setText(new String(MAR));
				
				// MBR operation
				MBR = cpu.getMBR();
				DspTxt_MBR.setText(new String(MBR));
			}
		});
		
		SbmBtn_PC = new JButton("Submit");
		SbmBtn_PC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// overwrite PC
				char[] Current = new char[12];
				char[] Current_dsp = new char[12];
				Current = SetTxt_PC.getText().toCharArray();
				
				cpu.writePC(Current, Current.length);
				
				cpu.readPC(Current_dsp, Current_dsp.length);
				DspTxt_PC.setText(new String(Current_dsp));
			}
		});
		
		GridBagConstraints gbc_SbmBtn_PC = new GridBagConstraints();
		gbc_SbmBtn_PC.anchor = GridBagConstraints.EAST;
		gbc_SbmBtn_PC.insets = new Insets(0, 0, 5, 5);
		gbc_SbmBtn_PC.gridx = 8;
		gbc_SbmBtn_PC.gridy = 7;
		contentPane.add(SbmBtn_PC, gbc_SbmBtn_PC);
		GridBagConstraints gbc_SbmBtn_SglStp = new GridBagConstraints();
		gbc_SbmBtn_SglStp.fill = GridBagConstraints.HORIZONTAL;
		gbc_SbmBtn_SglStp.insets = new Insets(0, 0, 5, 5);
		gbc_SbmBtn_SglStp.gridx = 9;
		gbc_SbmBtn_SglStp.gridy = 8;
		contentPane.add(SbmBtn_SglStp, gbc_SbmBtn_SglStp);
		
		lblValue_1 = new JLabel("Value:");
		GridBagConstraints gbc_lblValue_1 = new GridBagConstraints();
		gbc_lblValue_1.anchor = GridBagConstraints.SOUTH;
		gbc_lblValue_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblValue_1.gridx = 1;
		gbc_lblValue_1.gridy = 9;
		contentPane.add(lblValue_1, gbc_lblValue_1);
		
		lblValue_2 = new JLabel("Value:");
		GridBagConstraints gbc_lblValue_2 = new GridBagConstraints();
		gbc_lblValue_2.anchor = GridBagConstraints.SOUTH;
		gbc_lblValue_2.gridwidth = 2;
		gbc_lblValue_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblValue_2.gridx = 3;
		gbc_lblValue_2.gridy = 9;
		contentPane.add(lblValue_2, gbc_lblValue_2);
		
		lblOutput = new JLabel("Output:");
		GridBagConstraints gbc_lblOutput = new GridBagConstraints();
		gbc_lblOutput.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblOutput.insets = new Insets(0, 0, 5, 5);
		gbc_lblOutput.gridx = 5;
		gbc_lblOutput.gridy = 9;
		contentPane.add(lblOutput, gbc_lblOutput);
		
		lblR_1 = new JLabel("R0:");
		GridBagConstraints gbc_lblR_1 = new GridBagConstraints();
		gbc_lblR_1.anchor = GridBagConstraints.EAST;
		gbc_lblR_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblR_1.gridx = 0;
		gbc_lblR_1.gridy = 10;
		contentPane.add(lblR_1, gbc_lblR_1);
		
		DspTxt_R0 = new JLabel("0");
		GridBagConstraints gbc_DspTxt_R0 = new GridBagConstraints();
		gbc_DspTxt_R0.insets = new Insets(0, 0, 5, 5);
		gbc_DspTxt_R0.gridx = 1;
		gbc_DspTxt_R0.gridy = 10;
		contentPane.add(DspTxt_R0, gbc_DspTxt_R0);
		
		lblX = new JLabel("X1:");
		GridBagConstraints gbc_lblX = new GridBagConstraints();
		gbc_lblX.anchor = GridBagConstraints.EAST;
		gbc_lblX.insets = new Insets(0, 0, 5, 5);
		gbc_lblX.gridx = 2;
		gbc_lblX.gridy = 10;
		contentPane.add(lblX, gbc_lblX);
		
		DspTxt_X1 = new JLabel("0");
		GridBagConstraints gbc_DspTxt_X1 = new GridBagConstraints();
		gbc_DspTxt_X1.gridwidth = 2;
		gbc_DspTxt_X1.insets = new Insets(0, 0, 5, 5);
		gbc_DspTxt_X1.gridx = 3;
		gbc_DspTxt_X1.gridy = 10;
		contentPane.add(DspTxt_X1, gbc_DspTxt_X1);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.gridx = 5;
		gbc_scrollPane.gridy = 10;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		DspTxt_Cns = new JTextArea();
		scrollPane.setViewportView(DspTxt_Cns);
		DspTxt_Cns.setEditable(false);
		DspTxt_Cns.setWrapStyleWord(true);
		DspTxt_Cns.setLineWrap(true);
		
		lblR_2 = new JLabel("R1:");
		GridBagConstraints gbc_lblR_2 = new GridBagConstraints();
		gbc_lblR_2.anchor = GridBagConstraints.EAST;
		gbc_lblR_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblR_2.gridx = 0;
		gbc_lblR_2.gridy = 11;
		contentPane.add(lblR_2, gbc_lblR_2);
		
		DspTxt_R1 = new JLabel("0");
		GridBagConstraints gbc_DspTxt_R1 = new GridBagConstraints();
		gbc_DspTxt_R1.insets = new Insets(0, 0, 5, 5);
		gbc_DspTxt_R1.gridx = 1;
		gbc_DspTxt_R1.gridy = 11;
		contentPane.add(DspTxt_R1, gbc_DspTxt_R1);
		
		lblX_1 = new JLabel("X2:");
		GridBagConstraints gbc_lblX_1 = new GridBagConstraints();
		gbc_lblX_1.anchor = GridBagConstraints.EAST;
		gbc_lblX_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblX_1.gridx = 2;
		gbc_lblX_1.gridy = 11;
		contentPane.add(lblX_1, gbc_lblX_1);
		
		DspTxt_X2 = new JLabel("0");
		GridBagConstraints gbc_DspTxt_X2 = new GridBagConstraints();
		gbc_DspTxt_X2.gridwidth = 2;
		gbc_DspTxt_X2.insets = new Insets(0, 0, 5, 5);
		gbc_DspTxt_X2.gridx = 3;
		gbc_DspTxt_X2.gridy = 11;
		contentPane.add(DspTxt_X2, gbc_DspTxt_X2);
		
		lblR_3 = new JLabel("R2:");
		GridBagConstraints gbc_lblR_3 = new GridBagConstraints();
		gbc_lblR_3.anchor = GridBagConstraints.EAST;
		gbc_lblR_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblR_3.gridx = 0;
		gbc_lblR_3.gridy = 12;
		contentPane.add(lblR_3, gbc_lblR_3);
		
		DspTxt_R2 = new JLabel("0");
		GridBagConstraints gbc_DspTxt_R2 = new GridBagConstraints();
		gbc_DspTxt_R2.insets = new Insets(0, 0, 5, 5);
		gbc_DspTxt_R2.gridx = 1;
		gbc_DspTxt_R2.gridy = 12;
		contentPane.add(DspTxt_R2, gbc_DspTxt_R2);
		
		lblX_2 = new JLabel("X3:");
		GridBagConstraints gbc_lblX_2 = new GridBagConstraints();
		gbc_lblX_2.anchor = GridBagConstraints.EAST;
		gbc_lblX_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblX_2.gridx = 2;
		gbc_lblX_2.gridy = 12;
		contentPane.add(lblX_2, gbc_lblX_2);
		
		DspTxt_X3 = new JLabel("0");
		GridBagConstraints gbc_DspTxt_X3 = new GridBagConstraints();
		gbc_DspTxt_X3.gridwidth = 2;
		gbc_DspTxt_X3.insets = new Insets(0, 0, 5, 5);
		gbc_DspTxt_X3.gridx = 3;
		gbc_DspTxt_X3.gridy = 12;
		contentPane.add(DspTxt_X3, gbc_DspTxt_X3);
		
		lblR_4 = new JLabel("R3:");
		GridBagConstraints gbc_lblR_4 = new GridBagConstraints();
		gbc_lblR_4.anchor = GridBagConstraints.EAST;
		gbc_lblR_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblR_4.gridx = 0;
		gbc_lblR_4.gridy = 13;
		contentPane.add(lblR_4, gbc_lblR_4);
		
		DspTxt_R3 = new JLabel("0");
		GridBagConstraints gbc_DspTxt_R3 = new GridBagConstraints();
		gbc_DspTxt_R3.insets = new Insets(0, 0, 5, 5);
		gbc_DspTxt_R3.gridx = 1;
		gbc_DspTxt_R3.gridy = 13;
		contentPane.add(DspTxt_R3, gbc_DspTxt_R3);
		
		lblMar = new JLabel("MAR:");
		GridBagConstraints gbc_lblMar = new GridBagConstraints();
		gbc_lblMar.anchor = GridBagConstraints.EAST;
		gbc_lblMar.insets = new Insets(0, 0, 5, 5);
		gbc_lblMar.gridx = 2;
		gbc_lblMar.gridy = 13;
		contentPane.add(lblMar, gbc_lblMar);
		
		DspTxt_MAR = new JLabel("0");
		GridBagConstraints gbc_DspTxt_MAR = new GridBagConstraints();
		gbc_DspTxt_MAR.gridwidth = 2;
		gbc_DspTxt_MAR.insets = new Insets(0, 0, 5, 5);
		gbc_DspTxt_MAR.gridx = 3;
		gbc_DspTxt_MAR.gridy = 13;
		contentPane.add(DspTxt_MAR, gbc_DspTxt_MAR);
		
		lblPc_1 = new JLabel("PC:");
		GridBagConstraints gbc_lblPc_1 = new GridBagConstraints();
		gbc_lblPc_1.anchor = GridBagConstraints.EAST;
		gbc_lblPc_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblPc_1.gridx = 0;
		gbc_lblPc_1.gridy = 14;
		contentPane.add(lblPc_1, gbc_lblPc_1);
		
		DspTxt_PC = new JLabel("0");
		GridBagConstraints gbc_DspTxt_PC = new GridBagConstraints();
		gbc_DspTxt_PC.insets = new Insets(0, 0, 0, 5);
		gbc_DspTxt_PC.gridx = 1;
		gbc_DspTxt_PC.gridy = 14;
		contentPane.add(DspTxt_PC, gbc_DspTxt_PC);
		
		lblMbr = new JLabel("MBR:");
		GridBagConstraints gbc_lblMbr = new GridBagConstraints();
		gbc_lblMbr.anchor = GridBagConstraints.EAST;
		gbc_lblMbr.insets = new Insets(0, 0, 0, 5);
		gbc_lblMbr.gridx = 2;
		gbc_lblMbr.gridy = 14;
		contentPane.add(lblMbr, gbc_lblMbr);
		
		DspTxt_MBR = new JLabel("0");
		GridBagConstraints gbc_DspTxt_MBR = new GridBagConstraints();
		gbc_DspTxt_MBR.gridwidth = 2;
		gbc_DspTxt_MBR.insets = new Insets(0, 0, 0, 5);
		gbc_DspTxt_MBR.gridx = 3;
		gbc_DspTxt_MBR.gridy = 14;
		contentPane.add(DspTxt_MBR, gbc_DspTxt_MBR);
	}
	
	// get the first six digits of an instruction
	// and check if it is an opcode
	private void opcode_check()
	{
		// get opcode from instruction
		for (int i = 0; i < 6; i++) Opcode[i] = Instruction[i];
		
		// if opcode starts w/ 1
		if (Opcode[0] == '1')
		{
			// and ends w/ 1
			if (Opcode[5] == '1')
			{
				// and in-betweens are 0
				for (int i = 1; i < 5; i++)
				{
					if (Opcode[i] == '0') flag = true;
					else
					{
						flag = false;
						break;
					}
				}
				
				if (flag == true) ins = "LDX";
			}
			
			// and ends w/ 10
			if ((Opcode[4] == '1') && (Opcode[5] == '0'))
			{
				for (int i = 1; i < 4; i++)
				{
					// and in-betweens are 0
					if (Opcode[i] == '0') flag = true;
					else
					{
						flag = false;
						break;
					}
				}
				
				if (flag == true) ins = "STX";
			}
		}
		
		// if opcode starts w/ four 0
		boolean mark = true;
		
		for (int i = 0; i < 4; i++)
		{
			if (Opcode[i] != '0')
			{
				mark = false;
				break;
			}
		}
		
		if (mark == true)
		{
			// and ends w/ 01, 10, 11
			if ((Opcode[4] == '0') && (Opcode[5] == '1'))
			{
				flag = true;
				ins = "LDR";
			}
			
			if ((Opcode[4] == '1') && (Opcode[5] == '0'))
			{
				flag = true;
				ins = "STR";
			}
			
			if ((Opcode[4] == '1') && (Opcode[5] == '1'))
			{
				flag = true;
				ins = "LDA";
			}
		}
	}
	
	// run instruction, write in memory, and display result
	private void instruction_run()
	{
		if (ins == "LDR")
		{
			if(ifInitial) {
				init.preRun_LDR();
			}
			LDR ldr = new LDR(cpu);
			DspTxt_Cns.setText(DspTxt_Cns.getText() + "Begin to run " + ins + ".\n");
			ldr.start();
		}
		else if (ins == "STR")
		{
			if(ifInitial) {
				init.preRun_STR();
			}
			STR str = new STR(cpu);
			DspTxt_Cns.setText(DspTxt_Cns.getText() + "Begin to run " + ins + ".\n");
			str.start();
		}
		else if (ins == "LDA")
		{
			if(ifInitial) {
				init.preRun_LDA();
			}
			LDA lda = new LDA(cpu);
			DspTxt_Cns.setText(DspTxt_Cns.getText() + "Begin to run " + ins + ".\n");
			lda.start();
		}
		else if (ins == "LDX")
		{
			if(ifInitial) {
				init.preRun_LDX();
			}
			LDX ldx = new LDX(cpu);
			DspTxt_Cns.setText(DspTxt_Cns.getText() + "Begin to run " + ins + ".\n");
			ldx.start();
		}
		
		if (ins == "STX")
		{
			if(ifInitial) {
				init.preRun_STX();
			}
			STX stx = new STX(cpu);
			DspTxt_Cns.setText(DspTxt_Cns.getText() + "Begin to run " + ins + ".\n");
			stx.start();
		}
		
		//cpu.writeIns(Opcode, Opcode.length, address_mem);
		DspTxt_Cns.setText(DspTxt_Cns.getText() + "Success: " + ins + " " + new String(address) + ".\n\n");
	}
	
	// return UI instance in order to have print interfaces
	public static UI getUIinstance()
	{
		if (frame != null)
			return frame;
		
		return new UI();
	}

	// print interfaces
	public void print2console(String content)
	{
		String Current = DspTxt_Cns.getText();
		DspTxt_Cns.setText(Current + content + "\n");
	}
	
	public void print2console(int content)
	{
		String Current = DspTxt_Cns.getText();
		DspTxt_Cns.setText(Current + content + "\n");
	}
	
	public void print2log(String content)
	{
		String Current = DspTxt_Log.getText();
		DspTxt_Log.setText(Current + content + "\n");
	}
	
	public void print2log(int content)
	{
		String Current = DspTxt_Log.getText();
		DspTxt_Log.setText(Current + content + "\n");
	}
}
