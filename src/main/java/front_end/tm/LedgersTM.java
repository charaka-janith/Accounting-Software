package front_end.tm;

import com.jfoenix.controls.JFXButton;

public class LedgersTM {
    private String acct;
    private JFXButton goTo;

    public LedgersTM() {
    }

    public LedgersTM(String acct, JFXButton goTo) {
        this.acct = acct;
        this.goTo = goTo;
    }

    public String getAcct() {
        return acct;
    }

    public void setAcct(String acct) {
        this.acct = acct;
    }

    public JFXButton getGoTo() {
        return goTo;
    }

    public void setGoTo(JFXButton goTo) {
        this.goTo = goTo;
    }
}
