package org.rmj.sales.agentfx.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.rmj.appdriver.agentfx.ShowMessageFX;

public class CancelledInvoiceController implements Initializable {

    @FXML
    private AnchorPane dataPane;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtReturnNo;

    @FXML
    private TextArea txtRemarks;

    private boolean bCancelled = false;
    private String psReturnSlipNo = "";
    private String psRemarks = "";

    public String getReturnSlipNo() {
        return psReturnSlipNo;
    }
    
    public String getRemarks() {
        return psRemarks;
    }

    public boolean isCancelled() {
        return bCancelled;
    }
    private final String pxeModuleName = CancelledInvoiceController.class.getName();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnOk.setOnAction(this::cmdButton_Click);
        btnCancel.setOnAction(this::cmdButton_Click);
    }

    public void cmdButton_Click(ActionEvent event) {
        String lsButton = ((Button) event.getSource()).getId();
        switch (lsButton) {
            case "btnOk":
                if (isEntryOk() == false) {
                    ShowMessageFX.Warning("Remarks is required for cancellation.", pxeModuleName, "Please inform MIS/SEG");
                } else {
                    bCancelled = false;
                    unloadScene(event);
                }
                break;
            case "btnCancel":
                bCancelled = true;
                unloadScene(event);
        }
    }

    private boolean isEntryOk() {
        psReturnSlipNo = txtReturnNo.getText().toLowerCase();
        psRemarks = txtRemarks.getText().toLowerCase();
        try {
            
            return txtRemarks.getText().isEmpty();
        } catch (NumberFormatException ex) {
            System.err.println(ex);
        }
        return false;
    }

    private void unloadScene(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
