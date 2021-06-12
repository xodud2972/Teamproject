package bike;

import main.AppMain;
import main.Page;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Bike extends Page {
    JPanel p_west;
    JPanel p_east;
    JPanel p_south;

    JPanel p_center;
    JPanel p_search;
    Choice ch_category;
    JTextField t_keyword;
    JButton bt_search;

    JTable table;
    JScrollPane scroll_table;

    Thread thread;

    String[] columns = {"no", "company", "model", "Year", "Category", "Power_HP", "Bore_mm", "Stroke_mm", "Torque_Nm", "Engine_type", "Fuel_system", "Cooling_system", "Gearbox", "Front_suspension", "Rear_suspension", "Front_tyre", "Rear_tyre", "Front_brakes", "Rear_brakes", "Compression_Ratio", "Light", "Displacement_ccm", "Wheelbase_mm", "Fuel_capacity_liters", "Dry_weight_kg", "Weight_incl._oil_gas_etc_kg", "Overall_height_mm", "Overall_length_mm", "Overall_width_mm", "Oil_capacity_liters", "Seat_height_mm", "Compression_Enumerator", "Fuel_consumption_miles/gallon", "Automatic_gearbox", "Torque_Benchmark_RPM", "Power_Benchmark_RPM"};
    String[][] records = {};

    public Bike(AppMain appMain) {
        super(appMain);

        p_south=new JPanel();
        p_west = new JPanel();
        p_east = new JPanel();
        p_center = new JPanel();
        p_search = new JPanel();

        ch_category = new Choice();
        for(String string_category : columns){
            ch_category.add(string_category);
        }


        t_keyword = new JTextField();
        bt_search = new JButton("검색");

        table = new JTable(new AbstractTableModel() {
            public int getRowCount() {
                return records.length;
            }

            public int getColumnCount() {
                return columns.length;
            }

            public String getColumnName(int col) {
                return columns[col];
            }

            public Object getValueAt(int row, int col) {
                return records[row][col];
            }

            public void setValueAt(Object val, int row, int col) {
                records[row][col] = (String) val;
                updateTable();
            }

            public boolean isCellEditable(int row, int col) {
                if (col == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        });

        scroll_table = new JScrollPane(table);

        Dimension d = new Dimension(180, 30);
        setLayout(new BorderLayout());

        p_center.setLayout(new BorderLayout());
        ch_category.setPreferredSize(d);
        t_keyword.setPreferredSize(new Dimension(450, 30));

        //모양 잡아주기
        p_west.setPreferredSize(new Dimension(7,800));
        add(p_west, BorderLayout.WEST);
        p_east.setPreferredSize(new Dimension(7,800));
        add(p_east, BorderLayout.EAST);
        p_south.setPreferredSize(new Dimension(1160,42));
        add(p_south, BorderLayout.SOUTH);

        // 검색영역
        p_search.add(ch_category);
        p_search.add(t_keyword);
        p_search.add(bt_search);
        p_center.add(p_search, BorderLayout.NORTH);
        p_center.add(scroll_table);
        add(p_center);

        thread = new Thread() {
            @Override
            public void run() {
                getList();
            }
        };
        thread.start();


        //이벤트리스너
        bt_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(ch_category.getSelectedItem());
                search();
            }
        });
    }

    private void search() {
        BikeDao conn=new BikeDao();

        BikeDto searchDto=new BikeDto();
        searchDto.setSearchcategory(ch_category.getSelectedItem());
        searchDto.setKeyword(t_keyword.getText());
        try {
            List<BikeDto> list = conn.search(searchDto);
            refresh(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTable(){
        BikeDao conn = new BikeDao();
        BikeDto dto = new BikeDto();

        dto.setNo((String) table.getValueAt(table.getSelectedRow(), 0));
        dto.setCompany((String) table.getValueAt(table.getSelectedRow(), 1));
        dto.setModel((String) table.getValueAt(table.getSelectedRow(), 2));
        dto.setYear((String) table.getValueAt(table.getSelectedRow(), 3));
        dto.setCategory((String) table.getValueAt(table.getSelectedRow(), 4));
        dto.setPower_HP((String) table.getValueAt(table.getSelectedRow(), 5));
        dto.setBore_mm((String) table.getValueAt(table.getSelectedRow(), 6));
        dto.setStroke_mm((String) table.getValueAt(table.getSelectedRow(), 7));
        dto.setTorque_Nm((String) table.getValueAt(table.getSelectedRow(), 8));
        dto.setEngine_type((String) table.getValueAt(table.getSelectedRow(), 9));
        dto.setFuel_system((String) table.getValueAt(table.getSelectedRow(), 10));
        dto.setCooling_system((String) table.getValueAt(table.getSelectedRow(), 11));
        dto.setGearbox((String) table.getValueAt(table.getSelectedRow(), 12));
        dto.setFront_suspension((String) table.getValueAt(table.getSelectedRow(), 13));
        dto.setRear_suspension((String) table.getValueAt(table.getSelectedRow(), 14));
        dto.setFront_tyre((String) table.getValueAt(table.getSelectedRow(), 15));
        dto.setRear_tyre((String) table.getValueAt(table.getSelectedRow(), 16));
        dto.setFront_brakes((String) table.getValueAt(table.getSelectedRow(), 17));
        dto.setRear_brakes((String) table.getValueAt(table.getSelectedRow(), 18));
        dto.setCompression_Ratio((String) table.getValueAt(table.getSelectedRow(), 19));
        dto.setLight((String) table.getValueAt(table.getSelectedRow(), 20));
        dto.setDisplacement_ccm((String) table.getValueAt(table.getSelectedRow(), 21));
        dto.setWheelbase_mm((String) table.getValueAt(table.getSelectedRow(), 22));
        dto.setFuel_capacity_liters((String) table.getValueAt(table.getSelectedRow(), 23));
        dto.setDry_weight_kg((String) table.getValueAt(table.getSelectedRow(), 24));
        dto.setWeight_incl_oil_gas_etc_kg((String) table.getValueAt(table.getSelectedRow(), 25));
        dto.setOverall_height_mm((String) table.getValueAt(table.getSelectedRow(), 26));
        dto.setOverall_length_mm((String) table.getValueAt(table.getSelectedRow(), 27));
        dto.setOverall_width_mm((String) table.getValueAt(table.getSelectedRow(), 28));
        dto.setOil_capacity_liters((String) table.getValueAt(table.getSelectedRow(), 29));
        dto.setSeat_height_mm((String) table.getValueAt(table.getSelectedRow(), 30));
        dto.setCompression_Enumerator((String) table.getValueAt(table.getSelectedRow(), 31));
        dto.setFuel_consumption_miles_gallon((String) table.getValueAt(table.getSelectedRow(), 32));
        dto.setAutomatic_gearbox((String) table.getValueAt(table.getSelectedRow(), 33));
        dto.setTorque_Benchmark_RPM((String) table.getValueAt(table.getSelectedRow(), 34));
        dto.setPower_Benchmark_RPM((String) table.getValueAt(table.getSelectedRow(), 35));


        int result = -1;
        try {
            result = conn.update(dto);
            if (result > 0) {
                JOptionPane.showMessageDialog(this.getAppMain(), "업데이트 완료");
            } else {
                JOptionPane.showMessageDialog(this.getAppMain(), "업데이트 실패");
            }
        } catch (NullPointerException e) {
            result = -2;
        } catch (Exception e) {
            e.printStackTrace();
            result = -1;
        }
    }

    public void getList() {
        BikeDao conn = new BikeDao();

        try {
            List<BikeDto> list = conn.selectAll();
            refresh(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void refresh(List<BikeDto> list){

        String[][] data = new String[list.size()][columns.length];

        int index = 0;
        while (index < list.size()) {
            data[index][0] = list.get(index).getNo();
            data[index][1] = list.get(index).getCompany();
            data[index][2] = list.get(index).getModel();
            data[index][3] = list.get(index).getYear();
            data[index][4] = list.get(index).getCategory();
            data[index][5] = list.get(index).getPower_HP();
            data[index][6] = list.get(index).getBore_mm();
            data[index][7] = list.get(index).getStroke_mm();
            data[index][8] = list.get(index).getTorque_Nm();
            data[index][9] = list.get(index).getEngine_type();
            data[index][10] = list.get(index).getFuel_system();
            data[index][11] = list.get(index).getCooling_system();
            data[index][12] = list.get(index).getGearbox();
            data[index][13] = list.get(index).getFront_suspension();
            data[index][14] = list.get(index).getRear_suspension();
            data[index][15] = list.get(index).getFront_tyre();
            data[index][16] = list.get(index).getRear_tyre();
            data[index][17] = list.get(index).getFront_brakes();
            data[index][18] = list.get(index).getRear_brakes();
            data[index][19] = list.get(index).getCompression_Ratio();
            data[index][20] = list.get(index).getLight();
            data[index][21] = list.get(index).getDisplacement_ccm();
            data[index][22] = list.get(index).getWheelbase_mm();
            data[index][23] = list.get(index).getFuel_capacity_liters();
            data[index][24] = list.get(index).getDry_weight_kg();
            data[index][25] = list.get(index).getWeight_incl_oil_gas_etc_kg();
            data[index][26] = list.get(index).getOverall_height_mm();
            data[index][27] = list.get(index).getOverall_length_mm();
            data[index][28] = list.get(index).getOverall_width_mm();
            data[index][29] = list.get(index).getOil_capacity_liters();
            data[index][30] = list.get(index).getSeat_height_mm();
            data[index][31] = list.get(index).getCompression_Enumerator();
            data[index][32] = list.get(index).getFuel_consumption_miles_gallon();
            data[index][33] = list.get(index).getAutomatic_gearbox();
            data[index][34] = list.get(index).getTorque_Benchmark_RPM();
            data[index][35] = list.get(index).getPower_Benchmark_RPM();
            index++;
        }

        records = data;

        table.updateUI();
    }
}
