package com.fouriApps.view.backing;

import javax.faces.event.ActionEvent;
import com.fouriApps.view.utils.ADFUtils;
import com.fouriApps.view.utils.JSFUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.ViewObject;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class subcontractReportBb {
    private RichInputDate date1;
    private RichSelectOneChoice reportFormat;

    public subcontractReportBb() {
    }

    public String onClickDownload() {
        // Add event code here...
        return null;
    }

    public void onClickDownload(ActionEvent actionEvent) throws ParseException {
       System.err.println("Enter into the download method");
        String url = null;
        ViewObject bu    = ADFUtils.findIterator("xxstg_business_units1_rovoIterator").getViewObject();
        String bu_id=bu.getCurrentRow().getAttribute("BuId")==null?"0":bu.getCurrentRow().getAttribute("BuId").toString();
        System.err.println("BU_ID--->"+bu_id);
        String date=date1.getValue()==null?"0":date1.getValue().toString();
        String selectedDate=getFormattedDate(date);
        String format=reportFormat.getValue()==null?"0":reportFormat.getValue().toString();
    //prod//
//    url= "http://omnijcsprod01.omniyat.com/SubcontractReport/webresources/approved/PCreport?P_BU_ID="+bu_id+"&P_DATE="+selectedDate+"&P_FILE_FORMAT="+format;     
    url= "https://jcs.omniyat.com/SubcontractReport/webresources/approved/PCreport?P_BU_ID="+bu_id+"&P_DATE="+selectedDate+"&P_FILE_FORMAT="+format; 
    System.err.println(url);
        FacesContext fctx = FacesContext.getCurrentInstance(); 
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        StringBuilder script = new StringBuilder();
        script.append("window.open(\"" + url + "\");");
        erks.addScript(fctx, script.toString());
    }

    public void setDate1(RichInputDate date1) {
        this.date1 = date1;
    }

    public RichInputDate getDate1() {
        return date1;
    }
    public String getFormattedDate(String repDate) throws ParseException { 
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
        java.util.Date date = formatter.parse(repDate);  
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
        return ft.format(date);
    }
    public void setReportFormat(RichSelectOneChoice reportFormat) {
        this.reportFormat = reportFormat;
    }

    public RichSelectOneChoice getReportFormat() {
        return reportFormat;
    }

    public void onClickProCostingDownload(ActionEvent actionEvent) {
         String url = null;
         ViewObject bu    = ADFUtils.findIterator("xxstg_business_units1_rovoIterator").getViewObject();
         String bu_id=bu.getCurrentRow().getAttribute("BuId")==null?"0":bu.getCurrentRow().getAttribute("BuId").toString();
         String format=reportFormat.getValue()==null?"0":reportFormat.getValue().toString();
        //prod//
        //    url= "http://omnijcsprod01.omniyat.com/SubcontractReport/webresources/approved/PCreport?P_BU_ID="+bu_id+"&P_DATE="+selectedDate+"&P_FILE_FORMAT="+format;
        url= "https://jcs.omniyat.com/SubcontractReport/webresources/project/costingreport?P_BU_ID="+bu_id+"&P_FILE_FORMAT="+format;
        System.err.println(url);
         FacesContext fctx = FacesContext.getCurrentInstance(); 
         ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
         StringBuilder script = new StringBuilder();
         script.append("window.open(\"" + url + "\");");
         erks.addScript(fctx, script.toString());
    }
}
