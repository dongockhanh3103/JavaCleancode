package vn.kms.launch.cleancode.common;

public class ErrorInfo<K,V> {
    K key;
    V value;
    public ErrorInfo(K key,V value){
        this.key=key;
        this.value=value;
    }
    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key= key;
    }

    public V getValue() {
        return value;
    }

    public String toString(){
        return String.format("%s\t%s",this.key,this.value);
    }

    public void setValue(V value) {
        this.value = value;
    }
}
