package student.view.displays;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.ArrayList;
import java.util.Collection;
// import java.util.List;

import javax.swing.JDialog;
import javax.swing.JRadioButton;

import student.model.IAnimalModel.AnimalRecord;

public final class DisplayUtils {
    
    private DisplayUtils() {
        // private constructor
    }

    public static void styleRadioButton(JRadioButton button){
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBorder(null);
    }

    
    // public static DisplayUtils.CSVResult ParseCSV() {

    //     String fileName = "C:\\Users\\mrash\\OneDrive\\Align\\cs5004\\FinalProject\\final-project-group-3\\data\\sample.csv"; 
    //     List<String[]> data = new ArrayList<>();
    //     String[] headings = null;

    //     try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
    //         String line;

    //         while ((line = br.readLine()) != null) {
    //             if (line.startsWith("name")) {
    //                 headings = line.split(",", -1);
    //                 continue;
    //             }
    //             String[] fields = line.split(",", -1); // -1 preserves empty fields
    //             data.add(fields);
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }

    //     // Convert to 2D array if needed
    //     String[][] dataArray = new String[data.size()][];
    //     dataArray = data.toArray(dataArray);

    //     return new CSVResult(dataArray, headings);
    // }
    // public static class CSVResult {
    //     public String[][] data;
    //     public String[] headings;
    
    //     public CSVResult(String[][] data, String[] headings) {
    //         this.data = data;
    //         this.headings = headings;
    //     }
    // }

    public static String[][] recordsToTableData(Collection<AnimalRecord> records){
        String[][] data = new String[records.size()][6];

        int i = 0;
        for(AnimalRecord record : records){
            data[i][0] = record.name();
            data[i][1] = String.valueOf(record.population());
            data[i][2] = String.valueOf(record.speed());
            data[i][3] = String.valueOf(record.averageWeight());
            data[i][4] = record.diet();
            data[i][5] = record.location();
            i++;
        }

        return data;
    }

    public static ActionListener cancelButtonListener(JDialog dialog){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("Canceling Operation");
                dialog.dispose();
            }
        };
    }

    // public static void main(String[] args) {
    //     DisplayUtils.ParseCSV();
    // }
}

