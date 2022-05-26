package view.tm;

public class CustomerTM {
    private String CusID;
    private String CusTitle;
    private String CusName;
    private String CusAddress;
    private String City;
    private String Province;
    private String PostCode;

    public CustomerTM() {
    }

    public CustomerTM(String cusID, String cusTitle, String cusName, String cusAddress, String city, String province, String postCode) {
        CusID = cusID;
        CusTitle = cusTitle;
        CusName = cusName;
        CusAddress = cusAddress;
        City = city;
        Province = province;
        PostCode = postCode;
    }

    public String getCusID() {
        return CusID;
    }

    public void setCusID(String cusID) {
        CusID = cusID;
    }

    public String getCusTitle() {
        return CusTitle;
    }

    public void setCusTitle(String cusTitle) {
        CusTitle = cusTitle;
    }

    public String getCusName() {
        return CusName;
    }

    public void setCusName(String cusName) {
        CusName = cusName;
    }

    public String getCusAddress() {
        return CusAddress;
    }

    public void setCusAddress(String cusAddress) {
        CusAddress = cusAddress;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String postCode) {
        PostCode = postCode;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "CusID='" + CusID + '\'' +
                ", CusTitle='" + CusTitle + '\'' +
                ", CusName='" + CusName + '\'' +
                ", CusAddress='" + CusAddress + '\'' +
                ", City='" + City + '\'' +
                ", Province='" + Province + '\'' +
                ", PostCode='" + PostCode + '\'' +
                '}';
    }
}
