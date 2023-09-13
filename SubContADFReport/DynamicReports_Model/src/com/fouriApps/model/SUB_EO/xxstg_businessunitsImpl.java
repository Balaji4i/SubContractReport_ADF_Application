package com.fouriApps.model.SUB_EO;

import java.math.BigDecimal;

import java.sql.Date;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.RowInconsistentException;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.TransactionEvent;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Nov 17 09:06:44 IST 2022
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class xxstg_businessunitsImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        BuId {
            public Object get(xxstg_businessunitsImpl obj) {
                return obj.getBuId();
            }

            public void put(xxstg_businessunitsImpl obj, Object value) {
                obj.setBuId((BigDecimal)value);
            }
        }
        ,
        BuName {
            public Object get(xxstg_businessunitsImpl obj) {
                return obj.getBuName();
            }

            public void put(xxstg_businessunitsImpl obj, Object value) {
                obj.setBuName((String)value);
            }
        }
        ,
        LocationId {
            public Object get(xxstg_businessunitsImpl obj) {
                return obj.getLocationId();
            }

            public void put(xxstg_businessunitsImpl obj, Object value) {
                obj.setLocationId((BigDecimal)value);
            }
        }
        ,
        BusinessGroupId {
            public Object get(xxstg_businessunitsImpl obj) {
                return obj.getBusinessGroupId();
            }

            public void put(xxstg_businessunitsImpl obj, Object value) {
                obj.setBusinessGroupId((BigDecimal)value);
            }
        }
        ,
        DateFrom {
            public Object get(xxstg_businessunitsImpl obj) {
                return obj.getDateFrom();
            }

            public void put(xxstg_businessunitsImpl obj, Object value) {
                obj.setDateFrom((Date)value);
            }
        }
        ,
        DateTo {
            public Object get(xxstg_businessunitsImpl obj) {
                return obj.getDateTo();
            }

            public void put(xxstg_businessunitsImpl obj, Object value) {
                obj.setDateTo((Date)value);
            }
        }
        ,
        ManagerId {
            public Object get(xxstg_businessunitsImpl obj) {
                return obj.getManagerId();
            }

            public void put(xxstg_businessunitsImpl obj, Object value) {
                obj.setManagerId((String)value);
            }
        }
        ,
        LegalEntityId {
            public Object get(xxstg_businessunitsImpl obj) {
                return obj.getLegalEntityId();
            }

            public void put(xxstg_businessunitsImpl obj, Object value) {
                obj.setLegalEntityId((String)value);
            }
        }
        ,
        PrimaryLedgerId {
            public Object get(xxstg_businessunitsImpl obj) {
                return obj.getPrimaryLedgerId();
            }

            public void put(xxstg_businessunitsImpl obj, Object value) {
                obj.setPrimaryLedgerId((String)value);
            }
        }
        ,
        DefaultSetId {
            public Object get(xxstg_businessunitsImpl obj) {
                return obj.getDefaultSetId();
            }

            public void put(xxstg_businessunitsImpl obj, Object value) {
                obj.setDefaultSetId((String)value);
            }
        }
        ,
        ShortCode {
            public Object get(xxstg_businessunitsImpl obj) {
                return obj.getShortCode();
            }

            public void put(xxstg_businessunitsImpl obj, Object value) {
                obj.setShortCode((String)value);
            }
        }
        ,
        EnabledForHrFlag {
            public Object get(xxstg_businessunitsImpl obj) {
                return obj.getEnabledForHrFlag();
            }

            public void put(xxstg_businessunitsImpl obj, Object value) {
                obj.setEnabledForHrFlag((String)value);
            }
        }
        ,
        FinBusinessUnitId {
            public Object get(xxstg_businessunitsImpl obj) {
                return obj.getFinBusinessUnitId();
            }

            public void put(xxstg_businessunitsImpl obj, Object value) {
                obj.setFinBusinessUnitId((String)value);
            }
        }
        ,
        DefaultCurrencyCode {
            public Object get(xxstg_businessunitsImpl obj) {
                return obj.getDefaultCurrencyCode();
            }

            public void put(xxstg_businessunitsImpl obj, Object value) {
                obj.setDefaultCurrencyCode((String)value);
            }
        }
        ,
        ProfitCenterFlag {
            public Object get(xxstg_businessunitsImpl obj) {
                return obj.getProfitCenterFlag();
            }

            public void put(xxstg_businessunitsImpl obj, Object value) {
                obj.setProfitCenterFlag((String)value);
            }
        }
        ,
        Status {
            public Object get(xxstg_businessunitsImpl obj) {
                return obj.getStatus();
            }

            public void put(xxstg_businessunitsImpl obj, Object value) {
                obj.setStatus((String)value);
            }
        }
        ,
        LeType {
            public Object get(xxstg_businessunitsImpl obj) {
                return obj.getLeType();
            }

            public void put(xxstg_businessunitsImpl obj, Object value) {
                obj.setLeType((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(xxstg_businessunitsImpl object);

        public abstract void put(xxstg_businessunitsImpl object, Object value);

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static final int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }
    public static final int BUID = AttributesEnum.BuId.index();
    public static final int BUNAME = AttributesEnum.BuName.index();
    public static final int LOCATIONID = AttributesEnum.LocationId.index();
    public static final int BUSINESSGROUPID = AttributesEnum.BusinessGroupId.index();
    public static final int DATEFROM = AttributesEnum.DateFrom.index();
    public static final int DATETO = AttributesEnum.DateTo.index();
    public static final int MANAGERID = AttributesEnum.ManagerId.index();
    public static final int LEGALENTITYID = AttributesEnum.LegalEntityId.index();
    public static final int PRIMARYLEDGERID = AttributesEnum.PrimaryLedgerId.index();
    public static final int DEFAULTSETID = AttributesEnum.DefaultSetId.index();
    public static final int SHORTCODE = AttributesEnum.ShortCode.index();
    public static final int ENABLEDFORHRFLAG = AttributesEnum.EnabledForHrFlag.index();
    public static final int FINBUSINESSUNITID = AttributesEnum.FinBusinessUnitId.index();
    public static final int DEFAULTCURRENCYCODE = AttributesEnum.DefaultCurrencyCode.index();
    public static final int PROFITCENTERFLAG = AttributesEnum.ProfitCenterFlag.index();
    public static final int STATUS = AttributesEnum.Status.index();
    public static final int LETYPE = AttributesEnum.LeType.index();

    /**
     * This is the default constructor (do not remove).
     */
    public xxstg_businessunitsImpl() {
    }

    /**
     * Gets the attribute value for BuId, using the alias name BuId.
     * @return the BuId
     */
    public BigDecimal getBuId() {
        return (BigDecimal)getAttributeInternal(BUID);
    }

    /**
     * Sets <code>value</code> as the attribute value for BuId.
     * @param value value to set the BuId
     */
    public void setBuId(BigDecimal value) {
        setAttributeInternal(BUID, value);
    }

    /**
     * Gets the attribute value for BuName, using the alias name BuName.
     * @return the BuName
     */
    public String getBuName() {
        return (String)getAttributeInternal(BUNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for BuName.
     * @param value value to set the BuName
     */
    public void setBuName(String value) {
        setAttributeInternal(BUNAME, value);
    }

    /**
     * Gets the attribute value for LocationId, using the alias name LocationId.
     * @return the LocationId
     */
    public BigDecimal getLocationId() {
        return (BigDecimal)getAttributeInternal(LOCATIONID);
    }

    /**
     * Sets <code>value</code> as the attribute value for LocationId.
     * @param value value to set the LocationId
     */
    public void setLocationId(BigDecimal value) {
        setAttributeInternal(LOCATIONID, value);
    }

    /**
     * Gets the attribute value for BusinessGroupId, using the alias name BusinessGroupId.
     * @return the BusinessGroupId
     */
    public BigDecimal getBusinessGroupId() {
        return (BigDecimal)getAttributeInternal(BUSINESSGROUPID);
    }

    /**
     * Sets <code>value</code> as the attribute value for BusinessGroupId.
     * @param value value to set the BusinessGroupId
     */
    public void setBusinessGroupId(BigDecimal value) {
        setAttributeInternal(BUSINESSGROUPID, value);
    }

    /**
     * Gets the attribute value for DateFrom, using the alias name DateFrom.
     * @return the DateFrom
     */
    public Date getDateFrom() {
        return (Date)getAttributeInternal(DATEFROM);
    }

    /**
     * Sets <code>value</code> as the attribute value for DateFrom.
     * @param value value to set the DateFrom
     */
    public void setDateFrom(Date value) {
        setAttributeInternal(DATEFROM, value);
    }

    /**
     * Gets the attribute value for DateTo, using the alias name DateTo.
     * @return the DateTo
     */
    public Date getDateTo() {
        return (Date)getAttributeInternal(DATETO);
    }

    /**
     * Sets <code>value</code> as the attribute value for DateTo.
     * @param value value to set the DateTo
     */
    public void setDateTo(Date value) {
        setAttributeInternal(DATETO, value);
    }

    /**
     * Gets the attribute value for ManagerId, using the alias name ManagerId.
     * @return the ManagerId
     */
    public String getManagerId() {
        return (String)getAttributeInternal(MANAGERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for ManagerId.
     * @param value value to set the ManagerId
     */
    public void setManagerId(String value) {
        setAttributeInternal(MANAGERID, value);
    }

    /**
     * Gets the attribute value for LegalEntityId, using the alias name LegalEntityId.
     * @return the LegalEntityId
     */
    public String getLegalEntityId() {
        return (String)getAttributeInternal(LEGALENTITYID);
    }

    /**
     * Sets <code>value</code> as the attribute value for LegalEntityId.
     * @param value value to set the LegalEntityId
     */
    public void setLegalEntityId(String value) {
        setAttributeInternal(LEGALENTITYID, value);
    }

    /**
     * Gets the attribute value for PrimaryLedgerId, using the alias name PrimaryLedgerId.
     * @return the PrimaryLedgerId
     */
    public String getPrimaryLedgerId() {
        return (String)getAttributeInternal(PRIMARYLEDGERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for PrimaryLedgerId.
     * @param value value to set the PrimaryLedgerId
     */
    public void setPrimaryLedgerId(String value) {
        setAttributeInternal(PRIMARYLEDGERID, value);
    }

    /**
     * Gets the attribute value for DefaultSetId, using the alias name DefaultSetId.
     * @return the DefaultSetId
     */
    public String getDefaultSetId() {
        return (String)getAttributeInternal(DEFAULTSETID);
    }

    /**
     * Sets <code>value</code> as the attribute value for DefaultSetId.
     * @param value value to set the DefaultSetId
     */
    public void setDefaultSetId(String value) {
        setAttributeInternal(DEFAULTSETID, value);
    }

    /**
     * Gets the attribute value for ShortCode, using the alias name ShortCode.
     * @return the ShortCode
     */
    public String getShortCode() {
        return (String)getAttributeInternal(SHORTCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for ShortCode.
     * @param value value to set the ShortCode
     */
    public void setShortCode(String value) {
        setAttributeInternal(SHORTCODE, value);
    }

    /**
     * Gets the attribute value for EnabledForHrFlag, using the alias name EnabledForHrFlag.
     * @return the EnabledForHrFlag
     */
    public String getEnabledForHrFlag() {
        return (String)getAttributeInternal(ENABLEDFORHRFLAG);
    }

    /**
     * Sets <code>value</code> as the attribute value for EnabledForHrFlag.
     * @param value value to set the EnabledForHrFlag
     */
    public void setEnabledForHrFlag(String value) {
        setAttributeInternal(ENABLEDFORHRFLAG, value);
    }

    /**
     * Gets the attribute value for FinBusinessUnitId, using the alias name FinBusinessUnitId.
     * @return the FinBusinessUnitId
     */
    public String getFinBusinessUnitId() {
        return (String)getAttributeInternal(FINBUSINESSUNITID);
    }

    /**
     * Sets <code>value</code> as the attribute value for FinBusinessUnitId.
     * @param value value to set the FinBusinessUnitId
     */
    public void setFinBusinessUnitId(String value) {
        setAttributeInternal(FINBUSINESSUNITID, value);
    }

    /**
     * Gets the attribute value for DefaultCurrencyCode, using the alias name DefaultCurrencyCode.
     * @return the DefaultCurrencyCode
     */
    public String getDefaultCurrencyCode() {
        return (String)getAttributeInternal(DEFAULTCURRENCYCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for DefaultCurrencyCode.
     * @param value value to set the DefaultCurrencyCode
     */
    public void setDefaultCurrencyCode(String value) {
        setAttributeInternal(DEFAULTCURRENCYCODE, value);
    }

    /**
     * Gets the attribute value for ProfitCenterFlag, using the alias name ProfitCenterFlag.
     * @return the ProfitCenterFlag
     */
    public String getProfitCenterFlag() {
        return (String)getAttributeInternal(PROFITCENTERFLAG);
    }

    /**
     * Sets <code>value</code> as the attribute value for ProfitCenterFlag.
     * @param value value to set the ProfitCenterFlag
     */
    public void setProfitCenterFlag(String value) {
        setAttributeInternal(PROFITCENTERFLAG, value);
    }

    /**
     * Gets the attribute value for Status, using the alias name Status.
     * @return the Status
     */
    public String getStatus() {
        return (String)getAttributeInternal(STATUS);
    }

    /**
     * Sets <code>value</code> as the attribute value for Status.
     * @param value value to set the Status
     */
    public void setStatus(String value) {
        setAttributeInternal(STATUS, value);
    }

    /**
     * Gets the attribute value for LeType, using the alias name LeType.
     * @return the LeType
     */
    public String getLeType() {
        return (String)getAttributeInternal(LETYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for LeType.
     * @param value value to set the LeType
     */
    public void setLeType(String value) {
        setAttributeInternal(LETYPE, value);
    }

    /**
     * getAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param attrDef the attribute

     * @return the attribute value
     * @throws Exception
     */
    protected Object getAttrInvokeAccessor(int index,
                                           AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            return AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].get(this);
        }
        return super.getAttrInvokeAccessor(index, attrDef);
    }

    /**
     * setAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param value the value to assign to the attribute
     * @param attrDef the attribute

     * @throws Exception
     */
    protected void setAttrInvokeAccessor(int index, Object value,
                                         AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].put(this, value);
            return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
    }

    /**
     * @param buId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(BigDecimal buId) {
        return new Key(new Object[]{buId});
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.fouriApps.model.SUB_EO.xxstg_businessunits");
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
    }

    /**
     * Add locking logic here.
     */
     @Override
    public void lock() {
        try {
                super.lock();
        }
        catch (RowInconsistentException e) {
            refresh(REFRESH_WITH_DB_ONLY_IF_UNCHANGED| REFRESH_CONTAINEES);
            super.lock();
        }
    }

    /**
     * Custom DML update/insert/delete logic here.
     * @param operation the operation type
     * @param e the transaction event
     */
    protected void doDML(int operation, TransactionEvent e) {
        super.doDML(operation, e);
    }
}
