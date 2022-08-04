package pers.darren.springboot.mybatis;

public class EnDeCrypt {

    private String value;

    public EnDeCrypt() {
    }

    public EnDeCrypt(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
