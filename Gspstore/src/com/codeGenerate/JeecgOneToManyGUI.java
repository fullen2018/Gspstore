package com.codeGenerate;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;

import com.code.CodeParamEntity;
import com.code.SubTableEntity;
import com.code.generate.CodeGenerateOneToMany;

public class JeecgOneToManyGUI extends JFrame {
	String planets[] = { "uuid","identity","sequence"}; 
	private static final long serialVersionUID = 2420562834623804869L;

	public JeecgOneToManyGUI() {
		JPanel localJPanel = new JPanel();
		setContentPane(localJPanel);
		localJPanel.setLayout(new GridLayout(25, 6));
		JLabel mainLabel = new JLabel("主表参数:");
		Font fnt = new Font("Serief", Font.BOLD, 18);
		mainLabel.setFont(fnt);
		final JLabel msgJLabel = new JLabel("提示:GUI仅满足少于两子表");
		JLabel mainTableNameLabel = new JLabel("主表[表名]:");
		final JTextField mainTableName = new JTextField(20);
		JLabel mainEntityLabel = new JLabel("主表[实体名]:");
		final JTextField mainEntity = new JTextField(20);
		JLabel keyLabel = new JLabel("主表[主键生成策略]:");
		final JComboBox keyStyle = new JComboBox(planets);
		JLabel seqLabel = new JLabel("主表[主键oracle序列号]:");
		final JTextField seq = new JTextField(20);
		JLabel mainEntityPackageLabel = new JLabel("主表[包名]:");
		final JTextField mainEntityPackage = new JTextField();
		JLabel mainDescriptionLabel = new JLabel("主表[描述]");
		final JTextField mainDescription = new JTextField(20);
		ButtonGroup localButtonGroup = new ButtonGroup();
		JRadioButton mainModeA = new JRadioButton("A:明细单页布局显示");
		mainModeA.setSelected(true);
		JRadioButton mainModeB = new JRadioButton("B:明细采用Tab布局展现");
		localButtonGroup.add(mainModeA);
		localButtonGroup.add(mainModeB);

		JLabel label1 = new JLabel("子表一:");
		label1.setFont(fnt);
		JLabel line1 = new JLabel();
		JLabel tableNameLabel1 = new JLabel("子表[表名]:");
		final JTextField tableName1 = new JTextField();
		JLabel tableEntityNameLabel1 = new JLabel("子表[实体名]:");
		JLabel keyLabel1 = new JLabel("子表[主键生成策略]:");
		JLabel seqLabel1 = new JLabel("主表[主键oracle序列号]:");
		final JTextField seq1 = new JTextField(20);
		final JComboBox keyStyle1 = new JComboBox(planets);
		final JTextField tableEntityName1 = new JTextField(20);
		JLabel entityPackageLabel1 = new JLabel("子表[包]:");
		final JTextField entityPackage1 = new JTextField(20);
		JLabel descriptionLabel1 = new JLabel("子表[描述]:");
		final JTextField description1 = new JTextField(20);
		JLabel foreignKeysLabel1 = new JLabel("子表[外键：多个采用逗号隔开]:");
		final JTextField foreignKeys1 = new JTextField(20);

		JLabel label2 = new JLabel("子表二:");
		label2.setFont(fnt);
		JLabel line2 = new JLabel();
		JLabel tableNameLabel2 = new JLabel("子表[表名]:");
		final JTextField tableName2 = new JTextField();
		JLabel tableEntityNameLabel2 = new JLabel("子表[实体名]:");
		final JTextField tableEntityName2 = new JTextField(20);
		JLabel keyLabel2 = new JLabel("子表[主键生成策略]:");
		final JComboBox keyStyle2 = new JComboBox(planets);
		JLabel seqLabel2 = new JLabel("主表[主键oracle序列号]:");
		final JTextField seq2 = new JTextField(20);
		JLabel entityPackageLabel2 = new JLabel("子表[包]:");
		final JTextField entityPackage2 = new JTextField(20);
		JLabel descriptionLabel2 = new JLabel("子表[描述]:");
		final JTextField description2 = new JTextField(20);
		JLabel foreignKeysLabel2 = new JLabel("子表[外键:与主表关联外键]:");
		final JTextField foreignKeys2 = new JTextField(20);
		
		
		JButton localJButton1 = new JButton("生成");
		JButton localJButton2 = new JButton("退出");

		localJPanel.add(mainLabel);
		localJPanel.add(msgJLabel);
		localJPanel.add(mainTableNameLabel);
		localJPanel.add(mainTableName);
		localJPanel.add(mainEntityLabel);
		localJPanel.add(mainEntity);
		localJPanel.add(keyLabel);
		localJPanel.add(keyStyle);
		localJPanel.add(seqLabel);
		localJPanel.add(seq);
		localJPanel.add(mainEntityPackageLabel);
		localJPanel.add(mainEntityPackage);
		localJPanel.add(mainDescriptionLabel);
		localJPanel.add(mainDescription);
		localJPanel.add(mainModeA);
		localJPanel.add(mainModeB);
		
		localJPanel.add(label1);
		localJPanel.add(line1);
		localJPanel.add(tableNameLabel1);
		localJPanel.add(tableName1);
		localJPanel.add(tableEntityNameLabel1);
		localJPanel.add(tableEntityName1);
		localJPanel.add(keyLabel1);
		localJPanel.add(keyStyle1);
		localJPanel.add(seqLabel1);
		localJPanel.add(seq1);
		localJPanel.add(entityPackageLabel1);
		localJPanel.add(entityPackage1);
		localJPanel.add(descriptionLabel1);
		localJPanel.add(description1);
		localJPanel.add(foreignKeysLabel1);
		localJPanel.add(foreignKeys1);

		localJPanel.add(label2);
		localJPanel.add(line2);
		localJPanel.add(tableNameLabel2);
		localJPanel.add(tableName2);
		localJPanel.add(tableEntityNameLabel2);
		localJPanel.add(tableEntityName2);
		localJPanel.add(keyLabel2);
		localJPanel.add(keyStyle2);
		localJPanel.add(seqLabel2);
		localJPanel.add(seq2);
		localJPanel.add(entityPackageLabel2);
		localJPanel.add(entityPackage2);
		localJPanel.add(descriptionLabel2);
		localJPanel.add(description2);
		localJPanel.add(foreignKeysLabel2);
		localJPanel.add(foreignKeys2);
		
		
		
		localJButton2.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
			    System.exit(0);
			}
			
		});
		localJButton1.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				//-----输入校验--------------------------------------------
				if (StringUtils.isBlank(mainTableName.getText())) {
					msgJLabel.setForeground(Color.red);
					msgJLabel.setText("主表[表名]不能为空！");
					return;
				}
				if (StringUtils.isBlank(mainEntity.getText())) {
					msgJLabel.setForeground(Color.red);
					msgJLabel.setText("主表[实体名]不能为空！");
					return;
				}
				if (StringUtils.isBlank(mainEntityPackage.getText())) {
					msgJLabel.setForeground(Color.red);
					msgJLabel.setText("主表[包]不能为空！");
					return;
				}
				if (StringUtils.isBlank(mainDescription.getText())) {
					msgJLabel.setForeground(Color.red);
					msgJLabel.setText("主表[描述]不能为空！");
					return;
				}
				//-----输入校验--------------------------------------------
				if (StringUtils.isNotBlank(tableName1.getText())) {
					if (StringUtils.isBlank(tableEntityName1.getText())) {
						msgJLabel.setForeground(Color.red);
						msgJLabel.setText("子表1[实体名]不能为空！");
						return;
					}
					if (StringUtils.isBlank(entityPackage1.getText())) {
						msgJLabel.setForeground(Color.red);
						msgJLabel.setText("子表1[包]不能为空！");
						return;
					}
					if (StringUtils.isBlank(description1.getText())) {
						msgJLabel.setForeground(Color.red);
						msgJLabel.setText("子表1[描述]不能为空！");
						return;
					}
					if (StringUtils.isBlank(foreignKeys1.getText())) {
						msgJLabel.setForeground(Color.red);
						msgJLabel.setText("子表1[外键]不能为空！");
						return;
					}
				}
				//-----输入校验--------------------------------------------
				if (StringUtils.isNotBlank(tableName2.getText())) {
					if (StringUtils.isBlank(tableEntityName2.getText())) {
						msgJLabel.setForeground(Color.red);
						msgJLabel.setText("子表2[实体名]不能为空！");
						return;
					}
					if (StringUtils.isBlank(entityPackage2.getText())) {
						msgJLabel.setForeground(Color.red);
						msgJLabel.setText("子表2[包]不能为空！");
						return;
					}
					if (StringUtils.isBlank(description2.getText())) {
						msgJLabel.setForeground(Color.red);
						msgJLabel.setText("子表2[描述]不能为空！");
						return;
					}
					if (StringUtils.isBlank(foreignKeys2.getText())) {
						msgJLabel.setForeground(Color.red);
						msgJLabel.setText("子表2[外键]不能为空！");
						return;
					}
				}
				//-------------------------------------------------------
				if(e.getSource() instanceof JButton){
					CodeParamEntity codeParamEntityIn = new CodeParamEntity();
					codeParamEntityIn.setTableName(mainTableName.getText());//主表[表名]
					codeParamEntityIn.setEntityName(mainEntity.getText());	 //主表[实体名]
					codeParamEntityIn.setEntityPackage(mainEntityPackage.getText());	 //主表[包名]
					codeParamEntityIn.setPrimaryKeyPolicy(keyStyle.getSelectedItem().toString());//主表[主键生成策略]
					codeParamEntityIn.setSequenceCode(seq.getText());	 //主表[序列号]
					codeParamEntityIn.setFtlDescription(mainDescription.getText());	 //主表[描述]
					codeParamEntityIn.setFtl_mode(CodeGenerateOneToMany.FTL_MODE_B);//主表[模板 A:明细单页布局显示 B:明细采用Tab布局展现]
					
					//第二步：设置子表集合
					List<SubTableEntity> subTabParamIn = new ArrayList<SubTableEntity>();
					//[1].子表一
					SubTableEntity po = new SubTableEntity();
					po.setTableName(tableName1.getText());//子表[表名]
					po.setEntityName(tableEntityName1.getText());	 //子表[实体名]
					po.setPrimaryKeyPolicy(keyStyle1.getSelectedItem().toString());//主表[主键生成策略]
					po.setSequenceCode(seq1.getText());	 //主表[序列号]
					po.setEntityPackage(entityPackage1.getText());			 //子表[包]
					po.setFtlDescription(description1.getText());		 //子表[描述]
					po.setForeignKeys(foreignKeys1.getText().split(","));//子表[外键:与主表关联外键]
					subTabParamIn.add(po);
					//[2].子表二
					SubTableEntity po2 = new SubTableEntity();
					po2.setTableName(tableName2.getText());		//子表[表名]
					po2.setPrimaryKeyPolicy(keyStyle2.getSelectedItem().toString());//主表[主键生成策略]
					po2.setSequenceCode(seq2.getText());	 //主表[序列号]
					po2.setEntityName(tableEntityName2.getText());			//子表[实体名]
					po2.setEntityPackage(entityPackage2.getText()); 					//子表[包]
					po2.setFtlDescription(description2.getText());				//子表[描述]
					po2.setForeignKeys(foreignKeys2.getText().split(","));//子表[外键:与主表关联外键]
					if(!StringUtils.isBlank(po2.getTableName())){
						subTabParamIn.add(po2);
					}
					codeParamEntityIn.setSubTabParam(subTabParamIn);
					
					
					if(StringUtils.isBlank(po.getTableName())&&StringUtils.isBlank(po2.getTableName())){
						msgJLabel.setForeground(Color.red);
						msgJLabel.setText("至少要有一个关联子表！");
						return;
					}
					//第三步：一对多(父子表)数据模型,代码生成
					CodeGenerateOneToMany.oneToManyCreate(subTabParamIn, codeParamEntityIn);
					msgJLabel.setForeground(Color.red);
					msgJLabel.setText("一对多数据模型，代码生成成功!");
				}
			}
			
		});
		localJPanel.add(localJButton1);
		localJPanel.add(localJButton2);

		setTitle("代码生成器[父子表数据模型]");
		setVisible(true);
		setDefaultCloseOperation(3);
		setSize(new Dimension(400, 660));
		setResizable(false);
		setLocationRelativeTo(getOwner());
	}

	public static void main(String args[]) {
		new JeecgOneToManyGUI();
	}
};