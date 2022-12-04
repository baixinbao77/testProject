package jxd.bxb.test.Connect.Conn.type;

import jxd.bxb.test.Connect.annotation.TableName;

/**
 * @ClassName DbObject
 * @Description TODO
 * @Author 白新报
 * @Date 2022/10/28 21:35
 * @Version 1.0
 **/
public class DbObject {
    private String name;
    private Integer id;
    private String xtype;
    private Integer uid;
    private Integer info;
    private Integer status;
    private Integer baseSchemaVer;
    private Integer replinfo;
    private Integer parentObj;
    private String crdate;
    private Integer ftcatid;
    private Integer schemaVer;
    private Integer statsSchemaVer;
    private String type;
    private Integer userstat;
    private Integer sysstat;
    private Integer indexdel;
    private String refdate;
    private Integer version;
    private Integer deltrig;
    private Integer instrig;
    private Integer updtrig;
    private Integer seltrig;
    private Integer category;
    private Integer cache;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getXtype() {
        return xtype;
    }

    public void setXtype(String xtype) {
        this.xtype = xtype;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getInfo() {
        return info;
    }

    public void setInfo(Integer info) {
        this.info = info;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBaseSchemaVer() {
        return baseSchemaVer;
    }

    public void setBaseSchemaVer(Integer baseSchemaVer) {
        this.baseSchemaVer = baseSchemaVer;
    }

    public Integer getReplinfo() {
        return replinfo;
    }

    public void setReplinfo(Integer replinfo) {
        this.replinfo = replinfo;
    }

    public Integer getParentObj() {
        return parentObj;
    }

    public void setParentObj(Integer parentObj) {
        this.parentObj = parentObj;
    }

    public String getCrdate() {
        return crdate;
    }

    public void setCrdate(String crdate) {
        this.crdate = crdate;
    }

    public Integer getFtcatid() {
        return ftcatid;
    }

    public void setFtcatid(Integer ftcatid) {
        this.ftcatid = ftcatid;
    }

    public Integer getSchemaVer() {
        return schemaVer;
    }

    public void setSchemaVer(Integer schemaVer) {
        this.schemaVer = schemaVer;
    }

    public Integer getStatsSchemaVer() {
        return statsSchemaVer;
    }

    public void setStatsSchemaVer(Integer statsSchemaVer) {
        this.statsSchemaVer = statsSchemaVer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUserstat() {
        return userstat;
    }

    public void setUserstat(Integer userstat) {
        this.userstat = userstat;
    }

    public Integer getSysstat() {
        return sysstat;
    }

    public void setSysstat(Integer sysstat) {
        this.sysstat = sysstat;
    }

    public Integer getIndexdel() {
        return indexdel;
    }

    public void setIndexdel(Integer indexdel) {
        this.indexdel = indexdel;
    }

    public String getRefdate() {
        return refdate;
    }

    public void setRefdate(String refdate) {
        this.refdate = refdate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDeltrig() {
        return deltrig;
    }

    public void setDeltrig(Integer deltrig) {
        this.deltrig = deltrig;
    }

    public Integer getInstrig() {
        return instrig;
    }

    public void setInstrig(Integer instrig) {
        this.instrig = instrig;
    }

    public Integer getUpdtrig() {
        return updtrig;
    }

    public void setUpdtrig(Integer updtrig) {
        this.updtrig = updtrig;
    }

    public Integer getSeltrig() {
        return seltrig;
    }

    public void setSeltrig(Integer seltrig) {
        this.seltrig = seltrig;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getCache() {
        return cache;
    }

    public void setCache(Integer cache) {
        this.cache = cache;
    }

    @Override
    public String toString() {
        return "DbObject{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", xtype='" + xtype + '\'' +
                ", uid=" + uid +
                ", info=" + info +
                ", status=" + status +
                ", baseSchemaVer=" + baseSchemaVer +
                ", replinfo=" + replinfo +
                ", parentObj=" + parentObj +
                ", crdate='" + crdate + '\'' +
                ", ftcatid=" + ftcatid +
                ", schemaVer=" + schemaVer +
                ", statsSchemaVer=" + statsSchemaVer +
                ", type='" + type + '\'' +
                ", userstat=" + userstat +
                ", sysstat=" + sysstat +
                ", indexdel=" + indexdel +
                ", refdate='" + refdate + '\'' +
                ", version=" + version +
                ", deltrig=" + deltrig +
                ", instrig=" + instrig +
                ", updtrig=" + updtrig +
                ", seltrig=" + seltrig +
                ", category=" + category +
                ", cache=" + cache +
                '}';
    }
}
