package pers.mihao.careerism.java.base.java_generic;

public class BaseDO {
    protected Integer uuid;

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Object getT(){
        return new BaseDO();
    }
}
