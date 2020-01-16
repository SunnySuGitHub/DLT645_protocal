package Entity;

import Utils.CheckUtil;

import java.util.Arrays;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/15 15:55
 * DLY645 1997版数据实体
 */
public class ReadData1997 {

    private String packageType;
    private Integer dataLength;
    private byte[] effectiveData;      //有效数据
    private byte controlCode;          //控制码
    private String centerAddress;      //表地址

    public ReadData1997(byte[] bytes, int dataLength) {
        /**
         * 起始符  地址域  起始符  控制码  数据域长度              数据域                校验码   结束符
         *  0       1~6      7      8         9                   9+n
         * 1字节   6字节   1字节   1字节     1字节         n字节(2字节数据标识+数据)      1字节   1字节
         */
        boolean check = CheckUtil.checkData(bytes);
        if (!check) {
            System.out.println("check error");
            System.out.print("error msg: ");
            for (byte b : bytes) {
                System.out.print(b + " ");
            }
            return;
        }

        if (bytes.length == 9) { // 固定长度帧格式
            this.dataLength = dataLength;
            this.centerAddress = CheckUtil.GetCenterAddr(bytes, packageType);
            this.controlCode = bytes[1];
            String pkgType = DataIdentify1997.getControlcode_Name().get(this.controlCode);
            this.packageType = pkgType;
        } else {
            this.centerAddress = CheckUtil.GetCenterAddr(bytes, packageType);
            this.dataLength = dataLength;
            this.controlCode = bytes[4];  //控制码
            String pkgType = DataIdentify1997.getControlcode_Name().get(this.controlCode);
            this.packageType = pkgType;
            byte[] datas = new byte[dataLength-5];
            System.arraycopy(bytes, 9, datas, 0, dataLength-5);
            this.effectiveData = datas;
        }
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    public byte[] getEffectiveData() {
        return effectiveData;
    }

    public void setEffectiveData(byte[] effectiveData) {
        this.effectiveData = effectiveData;
    }

    public byte getControlCode() {
        return controlCode;
    }

    public void setControlCode(byte controlCode) {
        this.controlCode = controlCode;
    }

    public String getCenterAddress() {
        return centerAddress;
    }

    public void setCenterAddress(String centerAddress) {
        this.centerAddress = centerAddress;
    }

    @Override
    public String toString() {
        return "ReadData1997{" +
                "packageType='" + packageType + '\'' +
                ", dataLength=" + dataLength +
                ", effectiveData=" + Arrays.toString(effectiveData) +
                ", controlCode=" + controlCode +
                ", centerAddress='" + centerAddress + '\'' +
                '}';
    }
}
