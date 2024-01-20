package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class GradedItem {

    private SimpleStringProperty gradedItemId;
    private SimpleStringProperty sectionId;
    private SimpleStringProperty gradedItemTitle;
    private SimpleStringProperty gradedItemDescription;
    private SimpleStringProperty gradedItemDate;
    private SimpleStringProperty gradedItemAverage;
    private SimpleStringProperty gradedItemString;

    public GradedItem() {
        this.gradedItemId = new SimpleStringProperty(this, "gradedItemId", "n/a");
        this.sectionId = new SimpleStringProperty(this, "sectionId", "n/a");
        this.gradedItemTitle = new SimpleStringProperty(this, "gradedItemTitle", "n/a");
        this.gradedItemDescription = new SimpleStringProperty(this, "gradedItemDescription", "n/a");
        this.gradedItemDate = new SimpleStringProperty(this, "gradedItemDate", "n/a");
        this.gradedItemAverage = new SimpleStringProperty(this, "gradedItemAverage", "n/a");
        this.gradedItemString = new SimpleStringProperty(this, "gradedItemString", "n/a");
    }

    public static void processGradedItemTable(ResultSet rs, ObservableList<GradedItem> gradedItemList) {
        GradedItem testGradedItem = null;

        gradedItemList.clear();

        try {
            while (rs.next()) {
                String gradedItemId = rs.getString("Graded_Item_Id");
                String sectionId = rs.getString("Section_Id");
                String gradedItemTitle = rs.getString("Graded_Item_Title");
                String gradedItemDescription = rs.getString("Graded_Item_Description");
                String gradedItemDate = rs.getString("Graded_Item_Date");

                testGradedItem = new GradedItem();
                testGradedItem.setGradedItemId(gradedItemId);
                testGradedItem.setSectionId(sectionId);
                testGradedItem.setGradedItemTitle(gradedItemTitle);
                testGradedItem.setGradedItemDescription(gradedItemDescription);
                testGradedItem.setGradedItemDate(gradedItemDate);
                testGradedItem.setGradedItemString("Id: " + testGradedItem.getGradedItemId() +
                        ", " + testGradedItem.getGradedItemTitle() +
                        ", " + testGradedItem.getGradedItemDate());

                if (!gradedItemList.contains(testGradedItem)) {
                    gradedItemList.add(testGradedItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void processGradedItemColumns(ResultSet rs, ObservableList<GradedItem> gradedItemList, SchoolController schoolController){
        try {
            //Clear the grade book table
            schoolController.getGradeBookTableView().getItems().clear();
            schoolController.getGradeBookTableView().getColumns().clear();

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                schoolController.getGradeBookTableView().getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            /**
             * ******************************
             * Data added to ObservableList *
             *******************************
             */
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                schoolController.gradeBookData.add(row);
            }

            //FINALLY ADDED TO TableView
            schoolController.getGradeBookTableView().setItems(schoolController.gradeBookData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GradedItem gradedItem)) return false;

        return Objects.equals(gradedItemId, gradedItem.gradedItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gradedItemId);
    }

    public String getGradedItemId() {
        return gradedItemId.get();
    }

    public StringProperty gradedItemIdProperty() {
        return gradedItemId;
    }

    public void setGradedItemId(String gradedItemId) {
        this.gradedItemId.set(gradedItemId);
    }

    public String getSectionId() {
        return sectionId.get();
    }

    public StringProperty sectionIdProperty() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId.set(sectionId);
    }

    public String getGradedItemTitle() {
        return gradedItemTitle.get();
    }

    public StringProperty gradedItemTitleProperty() {
        return gradedItemTitle;
    }

    public void setGradedItemTitle(String gradedItemTitle) {
        this.gradedItemTitle.set(gradedItemTitle);
    }

    public String getGradedItemDescription() {
        return gradedItemDescription.get();
    }

    public StringProperty gradedItemDescriptionProperty() {
        return gradedItemDescription;
    }

    public void setGradedItemDescription(String gradedItemDescription) {
        this.gradedItemDescription.set(gradedItemDescription);
    }

    public String getGradedItemDate() {
        return gradedItemDate.get();
    }

    public StringProperty gradedItemDateProperty() {
        return gradedItemDate;
    }

    public void setGradedItemDate(String gradedItemDate) {
        this.gradedItemDate.set(gradedItemDate);
    }

    public String getGradedItemString() {
        return gradedItemString.get();
    }

    public StringProperty gradedItemStringProperty() {
        return gradedItemString;
    }

    public void setGradedItemString(String gradedItemString) {
        this.gradedItemString.set(gradedItemString);
    }

    public String getGradedItemAverage() {
        return gradedItemAverage.get();
    }

    public StringProperty gradedItemAverageProperty() {
        return gradedItemAverage;
    }

    public void setGradedItemAverage(String gradedItemAverage) {
        this.gradedItemAverage.set(gradedItemAverage);
    }
}

