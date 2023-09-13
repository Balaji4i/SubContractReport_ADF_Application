package com.fouriApps.view.backing;

import com.fouriApps.view.utils.ADFUtils;

import com.fouriApps.view.utils.JSFUtils;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import okhttp3.Request;

import okhttp3.Response;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputDate;

import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.nav.RichGoMenuItem;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class PrismReportBb {
    private RichInputDate stmtDate;
    private RichInputComboboxListOfValues custId;
    private RichInputComboboxListOfValues customerLov;
    private RichSelectOneChoice reportFormat;
    private RichInputDate fromDate;
    private RichInputDate toDate;
    private RichSelectOneChoice reportType;
    private RichSelectOneChoice propertyId;
    private RichInputDate fromRDate;
    private RichInputDate toRDate;
    private RichInputDate soaDate;
    private RichInputComboboxListOfValues soaCustName;
    private RichInputText custNumber;
    private RichSelectOneChoice cmpropertyId;
    private RichCommandMenuItem cmi1;
    private RichGoMenuItem gmi2;

    public PrismReportBb() {
        super();
    }

    public void okForStmtAccount(ActionEvent actionEvent) throws ParseException {
        String url = null;
        String selectedDate = null;
        String repDate = null;
        String customerName = null;
        String customerId = null;
        String path = null;
        String fileType = null;
        String _reportType=null;
        Object formatType = ADFUtils.evaluateEL("#{bindings.ReportFormat.attributeValue}");
        Object reportTypes = ADFUtils.evaluateEL("#{bindings.ReportType.attributeValue}");
        ViewObject funVo = ADFUtils.getApplicationModuleForDataControl("PrismReport_AMDataControl").findViewObject("Functions_VO");
        ViewCriteria vc = funVo.createViewCriteria();
        ViewCriteriaRow vcRow = vc.createViewCriteriaRow();
        vcRow.setAttribute("FuncShortCode", "CSOA");
        vc.addRow(vcRow);
        funVo.applyViewCriteria(vc);
        funVo.executeQuery();
        if (funVo.first() != null) {
            Row r = funVo.first();
            path = r.getAttribute("PagePath") == null ? "" : r.getAttribute("PagePath").toString();
        }
        funVo.applyViewCriteria(null);

        if (stmtDate.getValue() != null) {
            repDate = stmtDate.getValue().toString();
            selectedDate = getFormattedDate(repDate);
        }
        if (custId.getValue() != null) {
            customerName = custId.getValue().toString();
            customerId = getCustomerId(customerName);
        }
        if (formatType != null) {
            fileType = formatType.toString();
        }
        
        if(reportTypes!=null){
            _reportType=reportTypes.toString();
        }
        
//        System.err.println(fileType+"-----"+customerId+"-------"+selectedDate);
        url = path + "?P_File_Type=" + fileType + "&P_CUST_ID=" + customerId + "&P_DATE=" + selectedDate + "&P_REPORT_TYPE=" + _reportType;
//        System.err.println("URL--> "+url);
        
        FacesContext fctx = FacesContext.getCurrentInstance();
        String taskflowURL = url;
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        StringBuilder script = new StringBuilder();
        script.append("window.open(\"" + taskflowURL + "\");");
        erks.addScript(fctx, script.toString());
    }

    public void setStmtDate(RichInputDate stmtDate) {
        this.stmtDate = stmtDate;
    }

    public RichInputDate getStmtDate() {
        return stmtDate;
    }

    public void setCustId(RichInputComboboxListOfValues custId) {
        this.custId = custId;
    }

    public RichInputComboboxListOfValues getCustId() {
        return custId;
    }

    public void setReportFormat(RichSelectOneChoice reportFormat) {
        this.reportFormat = reportFormat;
    }

    public RichSelectOneChoice getReportFormat() {
        return reportFormat;
    }

    private String getCustomerId(String customerName) {
        String cusId = null;
        ViewObject funVo = ADFUtils.getApplicationModuleForDataControl("PrismReport_AMDataControl").findViewObject("XXSTG_CUSTOMER_ROVO");
        ViewCriteria vc = funVo.createViewCriteria();
        ViewCriteriaRow vcRow = vc.createViewCriteriaRow();
        vcRow.setAttribute("CustomerName", customerName);
        vc.addRow(vcRow);
        funVo.applyViewCriteria(vc);
        funVo.executeQuery();
        if (funVo.first() != null) {
            Row r = funVo.first();
            cusId = r.getAttribute("CustId") == null ? "0" : r.getAttribute("CustId").toString();
        }
        funVo.applyViewCriteria(null);
        return cusId;
    }

    private String getFormattedDate(String repDate) throws ParseException { 
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
        java.util.Date date = formatter.parse(repDate);  
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
        return ft.format(date);
    }

    public void setFromDate(RichInputDate fromDate) {
        this.fromDate = fromDate;
    }

    public RichInputDate getFromDate() {
        return fromDate;
    }

    public void setToDate(RichInputDate toDate) {
        this.toDate = toDate;
    }

    public RichInputDate getToDate() {
        return toDate;
    }
    public void downloadSaleReport(ActionEvent actionEvent) throws ParseException {
        String fromDateS = null;
        String toDateS = null;
        String url = null;
        ViewObject vo = ADFUtils.findIterator("XXSTG_ORGANIZATIONS_ROVOIterator").getViewObject(); 
        Row r = vo.getCurrentRow();
        String orgId = r.getAttribute("DummyBu") == null ? null : r.getAttribute("DummyBu").toString();
        String unitType = r.getAttribute("UnitType") == null ? null : r.getAttribute("UnitType").toString();
        
        if (fromDate.getValue() != null) {
            String d = fromDate.getValue().toString();
            fromDateS = getFormattedDate(d);
        }
        if (toDate.getValue() != null) {
            String d = toDate.getValue().toString();
            toDateS = getFormattedDate(d);
        }
        
//        System.err.println(orgId + "-----" + unitType + "-----" + fromDateS + "-----" + toDateS);
        
        String path = null; 
        ViewObject funcVo = ADFUtils.getApplicationModuleForDataControl("PrismReport_AMDataControl").findViewObject("Functions_VO");
        ViewCriteria vc = funcVo.createViewCriteria();
        ViewCriteriaRow vcRow = vc.createViewCriteriaRow();
        vcRow.setAttribute("FuncShortCode", "SR");
        vc.addRow(vcRow);
        funcVo.applyViewCriteria(vc);
        funcVo.executeQuery();
        if (funcVo.getEstimatedRowCount()!=0) {
            Row funRow = funcVo.first();
            path = funRow.getAttribute("PagePath") == null ? "" : funRow.getAttribute("PagePath").toString();
        }      
        
        url = path + "?P_ORG=" + orgId + "&P_UNIT_TYPE=" + unitType + "&P_FDATE=" + fromDateS + "&P_TDATE=" + toDateS;
        
//        System.err.println(url);
        
        FacesContext fctx = FacesContext.getCurrentInstance(); 
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        StringBuilder script = new StringBuilder();
        script.append("window.open(\"" + url + "\");");
        erks.addScript(fctx, script.toString());
    }


    public void setReportType(RichSelectOneChoice reportType) {
        this.reportType = reportType;
    }

    public RichSelectOneChoice getReportType() {
        return reportType;
    }
    
    public String getReportPathFromDB(String shortCode){
        String path = null;
        ViewObject funcVo = ADFUtils.getApplicationModuleForDataControl("PrismReport_AMDataControl").findViewObject("Functions_VO");
        ViewCriteria vc = funcVo.createViewCriteria();
        ViewCriteriaRow vcRow = vc.createViewCriteriaRow();
        vcRow.setAttribute("FuncShortCode", shortCode);
        vc.addRow(vcRow);
        funcVo.applyViewCriteria(vc);
        funcVo.executeQuery();
        if (funcVo.getEstimatedRowCount()!=0) {
            Row funRow = funcVo.first();
            path = funRow.getAttribute("PagePath") == null ? "" : funRow.getAttribute("PagePath").toString();
        }    
        return path;
    }
    
    public void openPage(String url){
        FacesContext fctx = FacesContext.getCurrentInstance(); 
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        StringBuilder script = new StringBuilder();
        script.append("window.open(\"" + url + "\");");
        erks.addScript(fctx, script.toString());
    }

    public void receiptDownload(ActionEvent actionEvent) {
        ViewObject vo = ADFUtils.findIterator("SeperateReport_ROVOIterator").getViewObject(); 
        Row r = vo.getCurrentRow();
        String receiptNo = r.getAttribute("ReceiptNo") == null ? "0" : r.getAttribute("ReceiptNo").toString();
        String format = r.getAttribute("Format") == null ? "EXCEL" : r.getAttribute("Format").toString();
        String path = this.getReportPathFromDB("BR");
        String url = path + "?P_RECP_NUM=" + receiptNo + "&P_File_Type=" + format;
        System.err.println("URL-"+url);
        this.openPage(url);
    }

    public void downloadInventory(ActionEvent actionEvent) {
        ViewObject vo = ADFUtils.findIterator("SeperateReport_ROVOIterator").getViewObject(); 
        Row r = vo.getCurrentRow();
        String property = r.getAttribute("Property2") == null ? "0" : r.getAttribute("Property2").toString();
        String building = r.getAttribute("Building") == null ? "0" : r.getAttribute("Building").toString();
        String format = r.getAttribute("Format") == null ? "EXCEL" : r.getAttribute("Format").toString();
        String path = this.getReportPathFromDB("AI");
        String url = path + "?P_PROP_NAME=" + property + "&P_BUILD_NAME=" + building + "&P_File_Type=" + format;
        System.err.println("URL-"+url);
        this.openPage(url);
    }

    public void unitStatusDownload(ActionEvent actionEvent) {
        ViewObject vo = ADFUtils.findIterator("SeperateReport_ROVOIterator").getViewObject(); 
        Row r = vo.getCurrentRow();
        String project = r.getAttribute("Project") == null ? "0" : r.getAttribute("Project").toString();
        String property = r.getAttribute("Property") == null ? "0" : r.getAttribute("Property").toString();
        String format = r.getAttribute("Format") == null ? "EXCEL" : r.getAttribute("Format").toString();
        String path = this.getReportPathFromDB("US");
        String url = path + "?P_PROJ_NAME=" + project + "&P_PROP_NAME=" + property + "&P_File_Type=" + format;
        System.err.println("URL-"+url);
        this.openPage(url);
    }
    
    public void unitStatusNewDownload(ActionEvent actionEvent) {
        ViewObject vo = ADFUtils.findIterator("SeperateReport_ROVOIterator").getViewObject(); 
        Row r = vo.getCurrentRow();
        String project = r.getAttribute("Project") == null ? "0" : r.getAttribute("Project").toString();
        String property = r.getAttribute("Property") == null ? "0" : r.getAttribute("Property").toString();
        String format = r.getAttribute("Format") == null ? "EXCEL" : r.getAttribute("Format").toString();
        String path = this.getReportPathFromDB("USN");
        String url = path + "?P_PROJ_NAME=" + project + "&P_PROP_NAME=" + property + "&P_File_Type=" + format;
        System.err.println("URL-"+url);
        this.openPage(url);
    }

    public void customerMasterDownload(ActionEvent actionEvent) {
        String url = null;
        String customerName = "0";
        String path = null;
        String customerId = "0";
       //String customerLlov="0";
//        String l_propertyId="59";
        ViewObject funVo = ADFUtils.getApplicationModuleForDataControl("PrismReport_AMDataControl").findViewObject("Functions_VO");
        ViewCriteria vc = funVo.createViewCriteria();
        ViewCriteriaRow vcRow = vc.createViewCriteriaRow();
        vcRow.setAttribute("FuncShortCode", "CUST_M");
        vc.addRow(vcRow);
        funVo.applyViewCriteria(vc);
        funVo.executeQuery();
        if (funVo.first() != null) {
            Row r = funVo.first();
            path = r.getAttribute("PagePath") == null ? "" : r.getAttribute("PagePath").toString();
        }
        funVo.applyViewCriteria(null);
        if (customerLov.getValue()!= null) {
        System.err.println("cust ==>"+customerLov.getValue().toString());
        customerName = customerLov.getValue().toString();
        customerId = getCustomerId(customerName);
        } else {
                System.err.println("cust ==>"+customerLov.getValue());
            System.err.println("no value");
          customerId="0";
            }
        System.err.println("cust ==>"+customerId);       
        ViewObject vo = ADFUtils.findIterator("SeperateReport_ROVOIterator").getViewObject(); 
        Row r = vo.getCurrentRow();
        String project = r.getAttribute("Project") == null ? "0" : r.getAttribute("Project").toString();
//        String property = r.getAttribute("Property") == null ? "0" : r.getAttribute("Property").toString();
         System.err.println("project ==>"+project);

        ViewObject proVO=ADFUtils.findIterator("PropertyMasterRO1Iterator").getViewObject();
       // String l_propertyId=proVO.getCurrentRow().getAttribute("Id")==null?"":proVO.getCurrentRow().getAttribute("Id").toString();
//        System.err.println("l_propertyId=>"+l_propertyId);
//        System.err.println("l_propertyId 2=>"+cmpropertyId.getValue());
//        System.err.println("property 3=>"+project);
//        System.err.println("property 4=>"+property);


        
//        if(propertyId.getValue()!=null){
//            l_propertyId=propertyId.getValue().toString();    
//        }
//        System.err.println("pro id==>"+propertyId.getValue());
       
        System.err.println(path+"-----"+customerName);
        url = path + "?P_CUST=" + customerId+"&P_PRO_ID=" + project;
        System.err.println("URL--> "+url);
        
        FacesContext fctx = FacesContext.getCurrentInstance();
        String taskflowURL = url;
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        StringBuilder script = new StringBuilder();
        script.append("window.open(\"" + taskflowURL + "\");");
        erks.addScript(fctx, script.toString());
    }

    public void setCustomerLov(RichInputComboboxListOfValues customerLov) {
        this.customerLov = customerLov;
    }

    public RichInputComboboxListOfValues getCustomerLov() {
        return customerLov;
    }

    public void setPropertyId(RichSelectOneChoice propertyId) {
        this.propertyId = propertyId;
    }

    public RichSelectOneChoice getPropertyId() {
        return propertyId;
    }

    public void onClickReceiptDetailXl(ActionEvent actionEvent) throws ParseException {
        String url = null;
        String path = null;
        String fromDate = null;
        String toDate = null;
        String selectedFromDate = null;
        String selectedToDate = null;
        ViewObject proVO=ADFUtils.findIterator("PropertyMasterRO1Iterator").getViewObject();
        String l_propertyId=proVO.getCurrentRow().getAttribute("Id")==null?"59":proVO.getCurrentRow().getAttribute("Id").toString();
        System.err.println("l_propertyId=>"+l_propertyId);
        ViewObject funVo = ADFUtils.getApplicationModuleForDataControl("PrismReport_AMDataControl").findViewObject("Functions_VO");
        ViewCriteria vc = funVo.createViewCriteria();
        ViewCriteriaRow vcRow = vc.createViewCriteriaRow();
        vcRow.setAttribute("FuncShortCode", "RECDEC");
        vc.addRow(vcRow);
        funVo.applyViewCriteria(vc);
        funVo.executeQuery();
        if (funVo.first() != null) {
            Row r = funVo.first();
            path = r.getAttribute("PagePath") == null ? "" : r.getAttribute("PagePath").toString();
        }
        funVo.applyViewCriteria(null);
        
        
        if (fromRDate.getValue() != null) {
            fromDate = fromRDate.getValue().toString();
            selectedFromDate = getFormattedDate(fromDate);
        }
        
        if (toRDate.getValue() != null) {
            toDate = toRDate.getValue().toString();
            selectedToDate = getFormattedDate(toDate);
        }
        
        url = path + "?P_File_Type=xlsx"+"&P_PROP_NAME=" + l_propertyId+"&P_FROM_DATE="+selectedFromDate+"&P_TO_DATE="+selectedToDate;
        System.err.println("URL--> "+url);        
        
        FacesContext fctx = FacesContext.getCurrentInstance();
        String taskflowURL = url;
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        StringBuilder script = new StringBuilder();
        script.append("window.open(\"" + taskflowURL + "\");");
        erks.addScript(fctx, script.toString());
                
    }

    public void setFromRDate(RichInputDate fromRDate) {
        this.fromRDate = fromRDate;
    }

    public RichInputDate getFromRDate() {
        return fromRDate;
    }

    public void setToRDate(RichInputDate toRDate) {
        this.toRDate = toRDate;
    }

    public RichInputDate getToRDate() {
        return toRDate;
    }

    public void onClickReceiptDetailPdf(ActionEvent actionEvent) throws ParseException {
        String url = null;
        String path = null;
        String fromDate = null;
        String toDate = null;
        String selectedFromDate = null;
        String selectedToDate = null;
        ViewObject proVO=ADFUtils.findIterator("PropertyMasterRO1Iterator").getViewObject();
        String l_propertyId=proVO.getCurrentRow().getAttribute("Id")==null?"59":proVO.getCurrentRow().getAttribute("Id").toString();
        System.err.println("l_propertyId=>"+l_propertyId);
        ViewObject funVo = ADFUtils.getApplicationModuleForDataControl("PrismReport_AMDataControl").findViewObject("Functions_VO");
        ViewCriteria vc = funVo.createViewCriteria();
        ViewCriteriaRow vcRow = vc.createViewCriteriaRow();
        vcRow.setAttribute("FuncShortCode", "RECDEC");
        vc.addRow(vcRow);
        funVo.applyViewCriteria(vc);
        funVo.executeQuery();
        if (funVo.first() != null) {
            Row r = funVo.first();
            path = r.getAttribute("PagePath") == null ? "" : r.getAttribute("PagePath").toString();
        }
        funVo.applyViewCriteria(null);
        
        
        if (fromRDate.getValue() != null) {
            fromDate = fromRDate.getValue().toString();
            selectedFromDate = getFormattedDate(fromDate);
        }
        
        if (toRDate.getValue() != null) {
            toDate = toRDate.getValue().toString();
            selectedToDate = getFormattedDate(toDate);
        }
        
        url = path + "?P_File_Type=pdf"+"&P_PROP_NAME=" + l_propertyId+"&P_FROM_DATE="+selectedFromDate+"&P_TO_DATE="+selectedToDate;
        System.err.println("URL--> "+url);        
        
        FacesContext fctx = FacesContext.getCurrentInstance();
        String taskflowURL = url;
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        StringBuilder script = new StringBuilder();
        script.append("window.open(\"" + taskflowURL + "\");");
        erks.addScript(fctx, script.toString());
    }

    public void setSoaDate(RichInputDate soaDate) {
        this.soaDate = soaDate;
    }

    public RichInputDate getSoaDate() {
        return soaDate;
    }

    public void soaExcel(ActionEvent actionEvent) throws ParseException {
        String url = null;
        String path = null;
        String fromDate = null;
        String toDate = null;
        String selectedFromDate = null;
        String selectedToDate = null;

        String customerName = "0";
        String l_custId = "0";
        String custNum="0";
    

        ViewObject custVO=ADFUtils.findIterator("XXSTG_CUSTOMER_ROVO1Iterator").getViewObject();

        //        String l_custId=custVO.getCurrentRow().getAttribute("CustId")==null?"59":custVO.getCurrentRow().getAttribute("CustId").toString();
        //        System.err.println("l_custId=>"+l_custId);

        if (soaCustName.getValue() != null && custNumber.getValue()!=null) {
            customerName = soaCustName.getValue().toString();
            custNum=custNumber.getValue().toString();
            l_custId = getSOACustomerId(customerName, custNum);
        }
        else {
            l_custId="59";
        }
//        ViewObject orgVo = ADFUtils.findIterator("XXSTG_ORGANIZATIONS_ROVOIterator").getViewObject(); 
//        Row or = orgVo.getCurrentRow();
//        String orgId = or.getAttribute("DummyBu") == null ? "59" : or.getAttribute("DummyBu").toString();

        //
        ViewObject funVo = ADFUtils.getApplicationModuleForDataControl("PrismReport_AMDataControl").findViewObject("Functions_VO");
        ViewCriteria vc = funVo.createViewCriteria();
        ViewCriteriaRow vcRow = vc.createViewCriteriaRow();
        vcRow.setAttribute("FuncShortCode", "CSOA");
        vc.addRow(vcRow);
        funVo.applyViewCriteria(vc);
        funVo.executeQuery();
        if (funVo.first() != null) {
            Row r = funVo.first();
            path = r.getAttribute("PagePath") == null ? "" : r.getAttribute("PagePath").toString();
        }
        funVo.applyViewCriteria(null);
        
        if (soaDate.getValue() != null) {
            fromDate = soaDate.getValue().toString();
            selectedFromDate = getFormattedDate(fromDate);
        }
        
        
        
        
        url = path + "?P_File_Type=xlsx"+"&P_CUST_ID=" + l_custId+"&P_DATE="+selectedFromDate+"&P_REPORT_TYPE=CUST_STATEMENT_ACCOUNT";
       // System.err.println("URL--> "+url);        
        
        FacesContext fctx = FacesContext.getCurrentInstance();
        String taskflowURL = url;
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        StringBuilder script = new StringBuilder();
        script.append("window.open(\"" + taskflowURL + "\");");
        erks.addScript(fctx, script.toString());
    }
    
    
    private String getSOACustomerId(String customerName, String customerNum) {
        String cusId = null;
        ViewObject funVo = ADFUtils.getApplicationModuleForDataControl("PrismReport_AMDataControl").findViewObject("XXSTG_CUSTOMER_ROVO1");
        ViewCriteria vc = funVo.createViewCriteria();
        ViewCriteriaRow vcRow = vc.createViewCriteriaRow();
        vcRow.setAttribute("CustomerName", customerName);
        vcRow.setAttribute("CustomerNumber", customerNum);
        vc.addRow(vcRow);
        funVo.applyViewCriteria(vc);
        funVo.executeQuery();
        if (funVo.first() != null) {
            Row r = funVo.first();
            cusId = r.getAttribute("CustId") == null ? "0" : r.getAttribute("CustId").toString();
        }
        
        funVo.applyViewCriteria(null);
        return cusId;
    }

    public void soaExcelSummary(ActionEvent actionEvent) throws ParseException {
        String url = null;
        String path = null;
        String fromDate = null;
        String toDate = null;
        String selectedFromDate = null;
        String selectedToDate = null;

        String customerName = "0";
        String l_custId = "0";
        String custNum="0";

        ViewObject custVO=ADFUtils.findIterator("XXSTG_CUSTOMER_ROVO1Iterator").getViewObject();

        //        String l_custId=custVO.getCurrentRow().getAttribute("CustId")==null?"59":custVO.getCurrentRow().getAttribute("CustId").toString();
        //        System.err.println("l_custId=>"+l_custId);

        if (soaCustName.getValue() != null && custNumber.getValue()!=null) {
            customerName = soaCustName.getValue().toString();
            custNum=custNumber.getValue().toString();
            l_custId = getSOACustomerId(customerName, custNum);
        }
        else {
            l_custId="59";
        }
        
//        ViewObject orgVo = ADFUtils.findIterator("XXSTG_ORGANIZATIONS_ROVOIterator").getViewObject(); 
//        Row or = orgVo.getCurrentRow();
//        String orgId = or.getAttribute("DummyBu") == null ? "59" : or.getAttribute("DummyBu").toString();
        //
        ViewObject funVo = ADFUtils.getApplicationModuleForDataControl("PrismReport_AMDataControl").findViewObject("Functions_VO");
        ViewCriteria vc = funVo.createViewCriteria();
        ViewCriteriaRow vcRow = vc.createViewCriteriaRow();
        vcRow.setAttribute("FuncShortCode", "CSOA");
        vc.addRow(vcRow);
        funVo.applyViewCriteria(vc);
        funVo.executeQuery();
        if (funVo.first() != null) {
            Row r = funVo.first();
            path = r.getAttribute("PagePath") == null ? "" : r.getAttribute("PagePath").toString();
        }
        funVo.applyViewCriteria(null);
        
        if (soaDate.getValue() != null) {
            fromDate = soaDate.getValue().toString();
            selectedFromDate = getFormattedDate(fromDate);
        }
        
            url = path + "?P_File_Type=xlsx"+"&P_CUST_ID=" + l_custId+"&P_DATE="+selectedFromDate+"&P_REPORT_TYPE=AR_SUMMARY";
 //System.err.println("URL--> "+url);        
        
        FacesContext fctx = FacesContext.getCurrentInstance();
        String taskflowURL = url;
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        StringBuilder script = new StringBuilder();
        script.append("window.open(\"" + taskflowURL + "\");");
        erks.addScript(fctx, script.toString());
    }

    public void setSoaCustName(RichInputComboboxListOfValues soaCustName) {
        this.soaCustName = soaCustName;
    }

    public RichInputComboboxListOfValues getSoaCustName() {
        return soaCustName;
    }

    public void setCustNumber(RichInputText custNumber) {
        this.custNumber = custNumber;
    }

    public RichInputText getCustNumber() {
        return custNumber;
    }


    public void setCmpropertyId(RichSelectOneChoice cmpropertyId) {
        this.cmpropertyId = cmpropertyId;
    }

    public RichSelectOneChoice getCmpropertyId() {
        return cmpropertyId;
    }

    public void otherChargesDownload(ActionEvent actionEvent) {
              String unitId="0";;
                ViewObject vo = ADFUtils.findIterator("SeperateReport_ROVOIterator").getViewObject(); 
                Row r = vo.getCurrentRow();
                String propertyId = r.getAttribute("Property2") == null ? "59" : r.getAttribute("Property2").toString();
              // System.err.println("PROP-"+propertyId);
               String unitNo = r.getAttribute("UnitNumber") == null ? "0" : r.getAttribute("UnitNumber").toString();
               //System.err.println("UNITNUMBER-"+unitNo);
               if(unitNo!="0")
               {
                   unitId=getUnitId(propertyId,unitNo);
               }
               else
               {
                   unitId="59";
               }
               //System.err.println("UNITID-"+unitId);
                String path = this.getReportPathFromDB("OC");
                String url = path + "?P_File_Type=xlsx"+"&P_PROP_ID=" + propertyId + "&P_UNIT_ID=" + unitId;
                //System.err.println("URL-"+url);
                this.openPage(url);
    }
    private String getUnitId(String propId, String unitNumber) {
        String unitId = null;
        ViewObject funVo = ADFUtils.getApplicationModuleForDataControl("PrismReport_AMDataControl").findViewObject("PropertyUnitsROVO1");
        ViewCriteria vc = funVo.createViewCriteria();
        ViewCriteriaRow vcRow = vc.createViewCriteriaRow();
        vcRow.setAttribute("PropertyId", propId);
        vcRow.setAttribute("UnitNumber", unitNumber);
        vc.addRow(vcRow);
        funVo.applyViewCriteria(vc);
        funVo.executeQuery();
        if (funVo.first() != null) {
            Row r = funVo.first();
            unitId = r.getAttribute("UnitId") == null ? "0" : r.getAttribute("UnitId").toString();
        }
        
        funVo.applyViewCriteria(null);
        return unitId;
    }

    public void doExecuteFcrReport(ActionEvent actionEvent) {
        // Add event code here...
        Object projectid=JSFUtils.resolveExpression("#{bindings.ProjectId_Trans.inputValue}");
        Object date=JSFUtils.resolveExpression("#{bindings.FcrDate.inputValue}");            
        ADFContext.getCurrent().getSessionScope().put("pid", projectid);
        ADFContext.getCurrent().getSessionScope().put("dates", date);
                try {
                OkHttpClient client = new OkHttpClient();
                MediaType mediaType = MediaType.parse("text/xml");
                Request request = new Request.Builder().url("https://omnijcsprod01.omniyat.com/OmniyatFcrReportCall/webresources/fcrservice/fcrReport?p_project_id="+projectid+"&p_date="+date+"").build();
                Response response = client.newCall(request).execute();
                JSFUtils.addFacesInformationMessage("Report 1st part called !!!"+response);
                }catch (Exception e) {
                    JSFUtils.addFacesInformationMessage("Under catch" + e);
                    e.printStackTrace();
                }
        ADFContext.getCurrent().getPageFlowScope().put("exeFcr", "");
        AdfFacesContext.getCurrentInstance().addPartialTarget(cmi1);
        AdfFacesContext.getCurrentInstance().addPartialTarget(gmi2);
    }

    public void doSlctProjName(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue()!=null){
        Object projectid=JSFUtils.resolveExpression("#{bindings.ProjectId_Trans.inputValue}");
//        Object date=JSFUtils.resolveExpression("#{bindings.FcrDate.inputValue}");            
        ADFContext.getCurrent().getSessionScope().put("pid", projectid);
//        ADFContext.getCurrent().getSessionScope().put("dates", date);
        ADFContext.getCurrent().getPageFlowScope().put("exeFcr", "exeFcr");
        AdfFacesContext.getCurrentInstance().addPartialTarget(cmi1);
        AdfFacesContext.getCurrentInstance().addPartialTarget(gmi2);
        }
    }

    public void doEntrFcrDate(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue()!=null){
//        Object projectid=JSFUtils.resolveExpression("#{bindings.ProjectId_Trans.inputValue}");
        Object date=JSFUtils.resolveExpression("#{bindings.FcrDate.inputValue}");
//        ADFContext.getCurrent().getSessionScope().put("pid", projectid);
        ADFContext.getCurrent().getSessionScope().put("dates", date);
        ADFContext.getCurrent().getPageFlowScope().put("exeFcr", "exeFcr");
        AdfFacesContext.getCurrentInstance().addPartialTarget(cmi1);
        AdfFacesContext.getCurrentInstance().addPartialTarget(gmi2);
        }
    }

    public void setCmi1(RichCommandMenuItem cmi1) {
        this.cmi1 = cmi1;
    }

    public RichCommandMenuItem getCmi1() {
        return cmi1;
    }

    public void setGmi2(RichGoMenuItem gmi2) {
        this.gmi2 = gmi2;
    }

    public RichGoMenuItem getGmi2() {
        return gmi2;
    }
}
