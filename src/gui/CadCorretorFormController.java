package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.entities.AtuationZone;
import model.entities.Corretor;
import model.exceptions.ValidationException;
import model.services.CorretorService;

public class CadCorretorFormController implements Initializable {

	private Corretor entity;
	private CorretorService service;
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtFolga;
	@FXML
	private ComboBox<AtuationZone> comboBoxAtuationZone;
	@FXML
	private Button btEnviar;
	
	private ObservableList<AtuationZone> obsList;
	
	
	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			notifyDataChangeListeners();
			Utils.currentStage(event).close();
		}
		catch(ValidationException e) {
			e.getMessage();
		}
		catch (DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), Alert.AlertType.ERROR);
		}
	}
	
	
	public void setCorretorService(CorretorService service) {
		this.service = service;
	}
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}	
	
	private Corretor getFormData() {
		Corretor obj = new Corretor();

		ValidationException exception = new ValidationException("Validation Error");

		if (txtName.getText() == null || txtName.getText().trim().equals("")) {
			exception.addError("name", "Field can't be empty");
		}
		obj.setName(txtName.getText());

		if (exception.getErrors().size() > 0) {
			throw exception;
		}

		return obj;
	}
	
	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {		
	}
	
	public void initializeNodes() {
		Constraints.setTextFieldMaxLength(txtName, 30);
	}
	
	public void loadAssociatedObjects() {
		
	}
	
	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtName.setText(entity.getName());
	}
	
	
	
}
